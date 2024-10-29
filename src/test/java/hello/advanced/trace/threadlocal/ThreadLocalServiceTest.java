package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
class ThreadLocalServiceTest {

    private ThreadLocalService service = new ThreadLocalService();

    @DisplayName("field test")
    @Test
    void field() {
        // given
        log.info("main start");

        Runnable userA = () -> {
            service.logic("userA");
        };

        Runnable userB = () -> {
            service.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");

        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        // when
        threadA.start();
        sleep(100); // 동시성 문제 발생 O
//        sleep(2000); // 동시성 문제 발생 X
        threadB.start();

        sleep(3000);
        log.info("main exit");

        // then
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
