package hello.advanced.trace.hellotrace;

import static org.assertj.core.api.Assertions.*;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HelloTraceV1Test {
    
    @DisplayName("begin end test")
    @Test
    void begin_end() {
        // given
        HelloTraceV1 trace = new HelloTraceV1();

        // when
        TraceStatus status = trace.begin("hello");

        // then
        trace.end(status);
    }

    @DisplayName("begin exception test")
    @Test
    void begin_exception() {
        // given
        HelloTraceV1 trace = new HelloTraceV1();

        // when
        TraceStatus status = trace.begin("hello");

        // then
        trace.exception(status, new IllegalStateException());
    }

}
