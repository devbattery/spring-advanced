package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldLogTrace implements LogTrace {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    private TraceId traceIdHolder; // traceId 동기화, 동시성 이슈 발생

    @Override
    public TraceStatus begin(String message) {
        syncTraceId();

        TraceId traceId = traceIdHolder;
        long startTimeMs = System.currentTimeMillis();

        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);

        return new TraceStatus(traceId, startTimeMs, message);
    }

    private void syncTraceId() {
        if (traceIdHolder == null) {
            traceIdHolder = new TraceId();
            return;
        }

        traceIdHolder = traceIdHolder.createNextId();
    }

    @Override
    public void end(TraceStatus status) {
        complete(status, null);
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();

        if (e == null) {
            log.info("[{}] {}{} time={}ms",
                    traceId.getId(),
                    addSpace(COMPLETE_PREFIX, traceId.getLevel()),
                    status.getMessage(),
                    resultTimeMs);
            return;
        }

        log.info("[{}] {}{} time={}ms ex={}",
                traceId.getId(),
                addSpace(EX_PREFIX, traceId.getLevel()),
                status.getMessage(),
                resultTimeMs,
                e.toString());

        releaseTraceId();
    }

    private void releaseTraceId() {
        if (traceIdHolder.isFirstLevel()) {
            traceIdHolder = null; // destroy
            return;
        }

        traceIdHolder = traceIdHolder.createPreviousId();
    }

    /**
     * level = 0
     * level = 1 |-->
     * level = 2 |   |-->
     * -------------------------
     * level = 2 ex |   |<X-
     * level = 1 ex |<X-
     */
    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < level; i++) {
            if (i == level - 1) {
                sb.append("|").append(prefix);
                continue;
            }

            sb.append("|   ");
        }

        return sb.toString();
    }

}
