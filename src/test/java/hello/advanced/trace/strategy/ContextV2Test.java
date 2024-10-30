package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV2;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    @DisplayName("strategy pattern test")
    @Test
    void strategyV1() {
        // given
        ContextV2 context = new ContextV2();

        // when
        // then
        context.execute(new StrategyLogic2());
        context.execute(new StrategyLogic1());
    }

    @DisplayName("strategy pattern test to use anonymous inner class")
    @Test
    void strategyV2() {
        // given
        ContextV2 context = new ContextV2();

        // when
        // then
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("Execute business logic 1");
            }
        });
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("Execute business logic 2");
            }
        });
    }

    @DisplayName("strategy pattern test to use lambda")
    @Test
    void strategyV3() {
        // given
        ContextV2 context = new ContextV2();

        // when
        // then
        context.execute(() -> log.info("Execute business logic 1"));
        context.execute(() -> log.info("Execute business logic 2"));
    }

}
