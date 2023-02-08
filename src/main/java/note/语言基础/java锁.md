# Java 锁

### 常见错误

1. 使用ReentrantLock锁在执行线程内创建锁，无法正确上锁。
```java
public class LockTest {
    private int count = 0;
    public void reentrantLockTest() {
        // bad
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            count++;
            if (count <= 8) {
                System.out.println(Thread.currentThread().getId() + " ======= " + count);
            }
        } finally {
            lock.unlock();
        }
    }
}

public class LockTest {
    private int count = 0;
    // good
    ReentrantLock lock = new ReentrantLock();

    public void reentrantLockTest() {
        lock.lock();
        try {
            count++;
            if (count <= 8) {
                System.out.println(Thread.currentThread().getId() + " ======= " + count);
            }
        } finally {
            lock.unlock();
        }
    }
}
```
