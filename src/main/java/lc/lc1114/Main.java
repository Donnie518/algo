package lc.lc1114;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.LockSupport;

public class Main {
    AbstractQueuedSynchronizer synchronizer;
    public static void main(String[] args) {
        boolean f = false;
        // 创建 Foo 的实例
        for (int i = 0; i < 600000; i++) {
            FooA foo = new FooA();
                Thread thread1 = new Thread(() -> {
                    foo.first(() -> {});
                });
                Thread thread2 = new Thread(() -> {
                    foo.first(() -> {});
                });
               Thread thread3 = new Thread(() -> {
                    foo.first(() -> {});
                });
                // 启动线程
                thread1.start();
                thread2.start();
                thread3.start();

                // 等待线程完成执行
                try {
                    thread1.join();
                    thread2.join();
                    thread3.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                if(foo.flag.get() != 3){
                    System.out.println("ohNo! flag = " + foo.flag.get());
                    f = true;
                }
        }
        System.out.println("是否发生了意外情况 = " + f);
    }
}


class FooV {
    public volatile int flag = 0;

    // 获取 Unsafe 实例
    private static final Unsafe UNSAFE;
    private static final long FLAG_OFFSET;

    static {
        try {
            // 获取 Unsafe 实例
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);

            // 获取 flag 字段的偏移量
            FLAG_OFFSET = UNSAFE.objectFieldOffset(FooV.class.getDeclaredField("flag"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    // 使用 CAS 方法实现 flag++
    public void first(Runnable printFirst) {
        printFirst.run();

        // CAS 操作
        int oldFlag;
        do {
            oldFlag = flag;
        } while (!UNSAFE.compareAndSwapInt(this, FLAG_OFFSET, oldFlag, oldFlag + 1));
    }


    public void second(Runnable printSecond)  {
        while(flag != 1) {}
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        flag++;
    }

    public void third(Runnable printThird)  {
        while (flag != 2) {}
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
class FooA {

    public final AtomicInteger flag = new AtomicInteger(0);

    public void first(Runnable printFirst) {
        printFirst.run();
        flag.addAndGet(1);
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while(flag.get() != 1) {
            // printSecond.run() outputs "second". Do not change or remove this line.
        }
        printSecond.run();
        flag.addAndGet(1);
    }

    public void third(Runnable printThird) throws InterruptedException {

        while (flag.get() != 2) {

        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
class Foo {
    private final Object lock = new Object();

    private boolean firstFinished = false;
    private boolean secondFinished = false;


    public Foo() {

    }


    public void first(Runnable printFirst)  {
//        synchronized (lock) {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            firstFinished = true;
            // 这里为什么必须是 notifyAll 呢？
            // 线程顺序问题：
            //
            //比如 first() 执行后，通过 notify() 唤醒一个线程，假设唤醒的是 third()，而 second() 仍然在等待 firstFinished == true。这种情况下，third() 线程会被唤醒，但它会立刻重新进入 wait()，直到条件满足，这就浪费了 notify() 的唤醒机会，导致其他线程的唤醒时机可能不符合预期。
            //死锁风险：
            //
            //如果线程被唤醒时，它发现自己的条件没有满足（例如 firstFinished == false 或 secondFinished == false），它会再次进入等待。这样就可能发生一个线程在错误的顺序上被唤醒并进入阻塞状态，导致其他线程无法继续执行。
//            lock.notifyAll();
//        }
    }

    public void second(Runnable printSecond)  {
//        synchronized (lock) {
            while (!firstFinished){
//                lock.wait();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            secondFinished = true;
            // 这里为什么必须是 notifyAll 呢？
//            lock.notifyAll();
//        }
    }

    public void third(Runnable printThird)  {
//        synchronized (lock) {
            while (!secondFinished){
//                lock.wait();
            }

            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
//        }
    }

}
class FooS {
    private final Object lock = new Object();
    private volatile int flag = 0;

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock) {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            flag++;
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock) {
            while (flag != 1) {
                lock.wait();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            flag++;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock) {
            while (flag != 2) {
                lock.wait();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }
}

class FooAQS {
    CountDownLatch countDownLatch;
    private static class Sync extends AbstractQueuedSynchronizer {
        // 使得线程按顺序执行的标志位
        public boolean tryAcquire(int acquires) {
            return getState() == acquires - 1;
        }

        public boolean tryRelease(int releases) {
            setState(getState() + 1);
            return true;
        }
        // 自定义方法
        public void setZeroState(){
            this.setState(0);
        }
    }

    private final Sync sync = new Sync();

    public FooAQS() {
        sync.setZeroState();  // 初始状态是0，意味着 first() 线程能先执行
    }

    public void first(Runnable printFirst) {
        sync.tryAcquire(1);  // 线程尝试获取许可，状态从 0 到 1
        printFirst.run();  // 执行第一个任务
        sync.release(0);  // 释放许可，状态变为 1
    }

    public void second(Runnable printSecond) {
        sync.acquire(2);  // 线程尝试获取许可，状态从 1 到 2
        printSecond.run();  // 执行第二个任务
        sync.release(0);  // 释放许可，状态变为 2
    }

    public void third(Runnable printThird) {
        sync.acquire(3);  // 线程尝试获取许可，状态从 2 到 3
        printThird.run();  // 执行第三个任务
    }
}

class FooAQS2 {
    private static class Sync extends AbstractQueuedSynchronizer {
        // 使得线程按顺序执行的标志位
        public boolean tryAcquire(int acquires) {
            return getState() == acquires - 1;
        }

        public boolean tryRelease(int releases) {
            setState(getState() + 1);
            return true;
        }
        // 自定义方法
        public void setZeroState(){
            this.setState(0);
        }
    }

    private final Sync sync = new Sync();

    public FooAQS2() {
        sync.setZeroState();  // 初始状态是0，意味着 first() 线程能先执行
    }

    public void first(Runnable printFirst) {
        sync.acquire(1);  // 线程尝试获取许可，状态从 0 到 1
        printFirst.run();  // 执行第一个任务
        sync.release(0);  // 释放许可，状态变为 1
    }

    public void second(Runnable printSecond) {
        sync.acquire(2);  // 线程尝试获取许可，状态从 1 到 2
        printSecond.run();  // 执行第二个任务
        sync.release(0);  // 释放许可，状态变为 2
    }

    public void third(Runnable printThird) {
        sync.acquire(3);  // 线程尝试获取许可，状态从 2 到 3
        printThird.run();  // 执行第三个任务
    }
}
class FooAQS3 {

    LockSupport lockSupport;
    public FooAQS3() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
