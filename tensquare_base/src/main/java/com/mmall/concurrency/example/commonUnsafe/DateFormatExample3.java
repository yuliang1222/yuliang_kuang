package com.mmall.concurrency.example.commonUnsafe;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

@Slf4j
@ThreadSafe
public class DateFormatExample3 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws Exception {
//        ExecutorService executorService = Executors.newCachedThreadPool();
	    ThreadFactory nameThreadFactory = new ThreadFactoryBuilder().setNameFormat("yuliang-pool-%d").build();
	    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 200, 5L, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(1024), nameThreadFactory,new ThreadPoolExecutor.CallerRunsPolicy());
	    final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
	        threadPoolExecutor.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
	    threadPoolExecutor.shutdown();
    }

    private static void update(int i) {
        log.info("{}, {}", i,  LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
