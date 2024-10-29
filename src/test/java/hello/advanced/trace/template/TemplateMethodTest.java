package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
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

        // Execute business logic
        log.info("비즈니스 로직 1 실행");

        // Stop business logic
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();

        // Execute business logic
        log.info("비즈니스 로직 2 실행");

        // Stop business logic
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    @DisplayName("Apply template method pattern")
    @Test
    void templateMethodV1() {
        // given
        AbstractTemplate template1 = new SubClassLogic1();
        AbstractTemplate template2 = new SubClassLogic2();

        // when
        template1.execute();
        template2.execute();

        // then
    }
    
    @DisplayName("Apply template method pattern to use Anonymous Class")
    @Test
    void templateMethodV2() {
        // given
        AbstractTemplate template1 = new AbstractTemplate() {

            @Override
            protected void call() {
                log.info("Execute business logic 1");
            }

        };

        AbstractTemplate template2 = new AbstractTemplate() {

            @Override
            protected void call() {
                log.info("Execute business logic 2");
            }

        };

        // when
        log.info("클래스 이름 1 = {}", template1.getClass());
        template1.execute();

        log.info("클래스 이름 2 = {}", template2.getClass());
        template2.execute();

        // then
    }

}
