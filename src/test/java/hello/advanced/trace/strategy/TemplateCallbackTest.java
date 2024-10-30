package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.template.Callback;
import hello.advanced.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {
    
    @DisplayName("template callback pattern - Annonymous inner class")
    @Test
    void callbackV1() {
        // given
        TimeLogTemplate template = new TimeLogTemplate();

        // when
        // then
        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("Execute business logic 1");
            }
        });
        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("Execute business logic 2");
            }
        });
    }

    @DisplayName("template callback pattern - lambda")
    @Test
    void callbackV2() {
        // given
        TimeLogTemplate template = new TimeLogTemplate();

        // when
        // then
        template.execute(() -> log.info("Execute business logic 1"));
        template.execute(() -> log.info("Execute business logic 2"));
    }
    
}
