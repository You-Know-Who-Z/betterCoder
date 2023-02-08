package _00;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantLock 锁
 */
public class LockTest {
    private int count = 0;
    private ReentrantLock lock = new ReentrantLock();

    /**
     * 线程互斥
     */
    public void normalReentrantLockTest() {
        lock.lock();
        try {
            count++;
            if (count <= 8) {
                print(count);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 线程等待，与唤醒
     */
    public void towTest() {
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();
        new Thread(() -> printA(conditionA, conditionB)).start();
        new Thread(() -> printB(conditionB, conditionC)).start();
        new Thread(() -> printC(conditionC, conditionA)).start();
    }

    private void printA(Condition current, Condition next) {
        while (true) {
            lock.lock();
            try {
                print("A");
                current.await();
                print("A被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                next.signal();
            }
        }

    }

    private void printB(Condition current, Condition next) {
        while (true) {

            lock.lock();
            try {
                print("B");
                current.await();
                print("B被唤醒");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                next.signal();
            }
        }
    }

    private void printC(Condition current, Condition next) {
        while (true) {
            lock.lock();
            try {
                print("C");
                current.await();
                print("C被唤醒");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                next.signal();

            }
        }
    }
    /**
     * 线程中止
     *
     * @param taskCount
     * @param runnable
     */
    /**
     * 执行
     *
     * @param taskCount
     * @param runnable
     */
    public void executor(int taskCount, Runnable runnable) {
        ExecutorService executorService = Executors.newFixedThreadPool(taskCount);
        for (int i = 0; i < taskCount; i++) {
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }

    private void print(Object msg) {
        System.out.println(Thread.currentThread().getId() + " ======= " + msg);

    }

    public static void main(String[] args) {
        LockTest lockTest = new LockTest();
//            lockTest.executor(10, () -> lockTest.normalReentrantLockTest());
        lockTest.executor(1, () -> lockTest.towTest());

    }
}
