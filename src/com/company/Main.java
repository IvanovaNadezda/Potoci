package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Процесс (Программа)
        // Поток
        // Синхронизация потоков (один работает другой не работает)
        // new MyThread("+").start(); // 1 поток
        //new MyThread("-").start(); // 2 поток
        MyThread thread1 = new MyThread("+");
        thread1.start();
        MyThread thread2 = new MyThread("-");
        thread2.start();
        Thread.sleep(3000);
        thread1.flag = false;
        thread1.join(); // ждёт завершения потока
        test("1Stopped!");
        // [+][-][+][-][+][-]
    }
    public static Object key = new Object();
    public static void test(String message) {
       // synchronized (key){ // блок
            try{
                System.out.print("[");
                Thread.sleep(1000);
                System.out.print(message);
                Thread.sleep(1000);
                System.out.print("]");
          //      key.notify(); // возобнавляем поток, находящийся в режиме ожидания
          //      key.wait(); // выставляем потоку режим ожидания
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
      //  }
    }
}
class MyThread extends Thread{
    private String mess;
    public boolean flag = true;
    MyThread(String mess){
        this.mess=mess;
    }
    @Override
    public void run() {
        while(flag){
            Main.test(this.mess);
        }
    }
}
class RRR implements Runnable{

    @Override
    public void run() {

    }
}