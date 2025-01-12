package juc.Main;

public class Main {

    // 使用volatile关键字声明flag变量，确保其在多线程环境下的可见性
    private static boolean flag = true;

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (flag) {
                // 循环体
                System.out.println("线程正在运行..." + i++);
                try {
                    Thread.sleep(1000); // 模拟耗时操作
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("线程停止运行。");
        });

        thread.start();

        try {
            Thread.sleep(5000); // 让线程运行5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 修改flag的值，这个修改对其他线程是立即可见的
        flag = false;
    }
}
