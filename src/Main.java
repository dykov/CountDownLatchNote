import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {

        var latch = new CountDownLatch(3);
        Runnable runner = () -> {
            /* some work */
            System.out.println("count down at " + System.nanoTime());
            latch.countDown();
            /* some work */
        };

        var executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorService.submit(runner);
        }
        executorService.shutdown();

        latch.await(); //the main thread is waiting while counter is greater than 0

        System.out.println("done");
        System.out.println("latch.getCount() = " + latch.getCount());

    }
}