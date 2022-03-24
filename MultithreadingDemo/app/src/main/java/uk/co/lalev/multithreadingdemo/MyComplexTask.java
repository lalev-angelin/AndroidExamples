package uk.co.lalev.multithreadingdemo;

public class MyComplexTask extends Thread {
    @Override
    public void run() {
        super.run();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
