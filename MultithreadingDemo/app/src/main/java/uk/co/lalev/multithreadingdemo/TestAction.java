package uk.co.lalev.multithreadingdemo;

import java.util.Random;
import java.util.concurrent.Callable;

public class TestAction implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            //
        }

        Random r = new Random(System.currentTimeMillis());
        return r.nextInt();
    }
}
