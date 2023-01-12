package _00;

import cn.hutool.core.date.DateUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    private volatile int count = 0;
    public  void reentrantLockTest() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            count++;
            if (count <= 8) {
                System.out.println("=======");
            }
        } finally {
            lock.unlock();
        }
    }

    public void executor(int taskCount, Runnable runnable) {
        ExecutorService executorService = Executors.newFixedThreadPool(taskCount);
        for (int i = 0; i < taskCount; i++) {
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }
    public static void main(String[] args) {
        LockTest lockTest = new LockTest();
        for (int i = 0; i < 10; i++) {
            lockTest.executor(10, () -> lockTest.reentrantLockTest());
        }

    }
}
