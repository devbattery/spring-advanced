package hello.advanced.trace.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {
    
    @DisplayName("template method test v0")
    @Test
    void templateMethodV0() {
        // given
        logic1();
        logic2();

        // when
        
        // then
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();

        // Play business logic
        log.info("비즈니스 로직 1 실행");

        // Stop business logic
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();

        // Play business logic
        log.info("비즈니스 로직 2 실행");

        // Stop business logic
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

}
