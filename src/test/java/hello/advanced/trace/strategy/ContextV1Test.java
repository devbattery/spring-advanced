package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @DisplayName("strategy test v0")
    @Test
    void strategyV0() {
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

    @DisplayName("strategy pattern test")
    @Test
    void strategyV1() {
        // given
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        StrategyLogic2 strategyLogic2 = new StrategyLogic2();

        // when
        ContextV1 context1 = new ContextV1(strategyLogic1);
        ContextV1 context2 = new ContextV1(strategyLogic2);

        // then
        context1.execute();
        context2.execute();
    }
    
    @DisplayName("strategy pattern test to use anonymous class")
    @Test
    void strategyV2() {
        // given
        Strategy strategy1 = new Strategy() {
            @Override
            public void call() {
                log.info("Execute business logic 1");
            }
        };
        Strategy strategy2 = new Strategy() {
            @Override
            public void call() {
                log.info("Execute business logic 2");
            }
        };

        // when
        ContextV1 context1 = new ContextV1(strategy1);
        ContextV1 context2 = new ContextV1(strategy2);

        // then
        context1.execute();
        context2.execute();
    }

    @DisplayName("strategy pattern test to use anonymous class compactly")
    @Test
    void strategyV3() {
        // given
        // when
        ContextV1 context1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("Execute business logic 1");
            }
        });
        ContextV1 context2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("Execute business logic 2");
            }
        });

        // then
        context1.execute();
        context2.execute();
    }

    @DisplayName("strategy pattern test to use lambda compactly")
    @Test
    void strategyV4() {
        // given
        // when
        ContextV1 context1 = new ContextV1(() -> log.info("Execute business logic 1"));
        ContextV1 context2 = new ContextV1(() -> log.info("Execute business logic 2"));

        // then
        context1.execute();
        context2.execute();
    }

}
