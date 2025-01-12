package juc.Main;


import java.util.concurrent.atomic.AtomicInteger;

public class JMMReorderingExample {
    private static boolean flag = false;
    private static int value = 0;

    public static void main(String[] args) throws InterruptedException {
//        sun.misc.Unsafe
        AtomicInteger integer;
        for (int i = 0; i < 1000; i++) {
           new Thread(() ->{

               Thread t1 = new Thread(() -> {
                   value = 42;        // 1
                   flag = true;       // 2
               });

               Thread t2 = new Thread(() -> {
                   if (flag) {        // 3
                       System.out.println(value);  // 4
                   }
               });

               t1.start();
               t2.start();
               try {
                   t1.join();
                   t2.join();
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }

           }).start();
        }

    }
}
