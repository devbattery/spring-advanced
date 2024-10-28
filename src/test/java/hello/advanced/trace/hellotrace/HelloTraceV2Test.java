package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {

    @DisplayName("begin end test")
    @Test
    void begin_end() {
        // given
        HelloTraceV2 trace = new HelloTraceV2();

        // when
        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");

        // then
        trace.end(status2);
        trace.end(status1);
    }

    @DisplayName("begin exception test")
    @Test
    void begin_exception() {
        // given
        HelloTraceV2 trace = new HelloTraceV2();

        // when
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");

        // then
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }

}
