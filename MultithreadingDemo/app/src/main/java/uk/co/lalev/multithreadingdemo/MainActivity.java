package uk.co.lalev.multithreadingdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Application;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainActivity extends AppCompatActivity {

    Thread thread1;
    ProgressBar progressBar1;
    ProgressBar progressBar2;
    ProgressBar progressBar3;
    Button button3;

    Thread thread4;
    TextView timeView;
    AtomicReference<Double> testProgress = new AtomicReference<>(0.0);

    Queue<String> words = new ConcurrentLinkedDeque<>();
    Set<String> palindromes = new ConcurrentSkipListSet<>();

    int[] progress = new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar1 = findViewById(R.id.progressBar1);
        progressBar2 = findViewById(R.id.progressBar2);
        progressBar3 = findViewById(R.id.progressBar3);

        button3 = findViewById(R.id.task3StartBtn);

        timeView = findViewById(R.id.timeView);
    }

    public void onBtn1StarClick(View v) {
//       new Thread(new Runnable() {
//           @Override
//           public void run() {
//
//           }
//       });


        thread1 = new Thread(()->{

            int start = 2;
            int stop = 10_000_000;

            for (int i=start; i<=stop; i++) {

                if (Thread.interrupted()) {
                    return;
                }

                if (i%100==0) {
                    int progress = (int)(((double)i / (stop - start)) * 100);
                    runOnUiThread(() -> {
                        progressBar1.setIndeterminate(false);
                        progressBar1.setProgress(progress);
                    });
                }

                for (int j=2; j<start/2; j++) {

                    if ((i % j)==0) {
                        // ne e prosto
                        continue;
                    } else {
                        // e prosto
                    }
                }
            }
        });
        thread1.start();
    }

    public void onBtn1StopClick(View v) {
        thread1.interrupt();
    }

     public void onBtn2StartClick(View v) throws ExecutionException, InterruptedException {

        ExecutorService es = Executors.newFixedThreadPool(4);
        Future<?> f1 = es.submit(()->{
           for (int i=0; i<50_000_000; i++) {
               Double d = testProgress.getAndUpdate((a)->a+1);
           }
        });
        Future<?> f2 = es.submit(()->{
            for (int i=0; i<50_000_000; i++) {
                Double d = testProgress.getAndAccumulate(1.0, (p,q)->p+q);
            }
        });

        f1.get();
        f2.get();

        new AlertDialog.Builder(this)
                .setTitle("Test")
                .setMessage(String.valueOf(testProgress.get()))
                .show();


    }


    public void onBtn2StopClick(View v) {

    }

    public void updateProgressIndicator3(int number, int val) {
        progress[number] = val;

        int prog = 0;
        for (int p : progress) {
            prog+=p;
        }

        final int totalProgress = prog/4;

        runOnUiThread(()->progressBar3.setProgress(totalProgress));
    }

    /**
     * Генерира списък с думи и търси палиндроми измежду тях, като използва потоци за
     * ускоряване на работата.
     * @param v
     */
    public void onBtn3StartClick(View v)  {

        // Видове конкурентни колекции в Java
        // ConcurrentHashMap === HashMap --> NonBlocking, Not sorted
        // ConcurrentSkipListMap ~~~  TreeMap --> NonBlocking, Sorted
        // ConcurrentSkipListSet === Set     ---> NonBlocking, Sorted
        // ConcurrentLinkedDeque === Deque--> LinkedList  --> NonBlocking, Not sorted, Ordered
        // LinkedBlockingQueue === Queue ---> LinkedList  --> Blocking, Not sorted, Ordered
        // CopyOnWriteArrayList === ArrayList
        // CopyOnWriteArraySet === Set

        //Collections.synchronizedCollection(Collection c);
        //Collections.synchronizedList(List l);
        //Collections.synchronizedMap(Map m);
        //Collections.synchronizedNavigableMap(NavigableMap n);
        //Collections.synchronizedNavigableSet(Set s);
        //Collections.synchronizedSet(Set s);
        //Collections.synchronizedSortedMap(SortedMap s);
        //Collections.synchronizedSortedSet(SortedSet ss);

        progressBar3.setProgress(0);
        button3.setEnabled(false);

        Toast.makeText(this, "Starting generation", Toast.LENGTH_SHORT).show();
        ExecutorService es = Executors.newFixedThreadPool(4);

        es.submit(new WordGenerator(words, 10_000, 7, 0, this::updateProgressIndicator3));
        es.submit(new WordGenerator(words, 10_000, 7, 1, this::updateProgressIndicator3));
        es.submit(new WordGenerator(words, 10_000, 7, 2, this::updateProgressIndicator3));
        es.submit(new WordGenerator(words, 10_000, 7, 3, this::updateProgressIndicator3));
        es.shutdown();

        new Thread(()-> {
            try {
                if (!es.awaitTermination(100, TimeUnit.SECONDS)) {
                    runOnUiThread(() -> Toast.makeText(this, "The generation process is taking too long (100 seconds)", Toast.LENGTH_SHORT).show());
                    // Exit app here ?
                }
            } catch (InterruptedException e) {
                runOnUiThread(() -> Toast.makeText(this, "The generation process is taking too long (100 seconds)", Toast.LENGTH_SHORT).show());
                // Exit app here ?
            }

            searchPalindromes();
        }).start();

    }

    public void searchPalindromes() {
        runOnUiThread(()->{
            Toast.makeText(this, "Finished generation " + words.size() + " elements", Toast.LENGTH_SHORT).show();
        });

        final int totalSize = words.size();


        new Thread(()->{

            int progress =0;
            do {
                progress = ((totalSize - words.size())*100)/totalSize;

                Log.d("searchPalindromes", "Progress "+progress);

                int finalProgress = progress;
                runOnUiThread(()->{
                    progressBar3.setProgress(finalProgress);
                });

                if (progress<100) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } while (progress<100);
        }).start();

        ExecutorService es = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 8; i++) {
            es.submit(new PalindromeFinder(words, palindromes));
        }
        es.shutdown();

        new Thread(() -> {
            try {
                if (!es.awaitTermination(100, TimeUnit.SECONDS)) {
                    runOnUiThread(()->{
                        Toast.makeText(this, "The search process is taking too long (100 seconds)", Toast.LENGTH_SHORT).show();
                    });
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            displayResults();
        }).start();
    }

    public void displayResults() {
        runOnUiThread(()-> {
            Toast.makeText(this, "Finished the search", Toast.LENGTH_SHORT).show();

            new AlertDialog.Builder(this)
                    .setTitle("Results")
                    .setMessage(palindromes.size() + " " + Arrays.toString(palindromes.stream().limit(5).toArray()))
                    .show();

            button3.setEnabled(true);
        });
    }

    public void onBtn3StopClick(View v) {

    }

    public void onBtn4StartClick(View v) {
        thread4 = new Thread(()->{
            int seconds = 0;

            while(!Thread.interrupted()) {
                try {
                    Thread.sleep(1000);
                    seconds++;
                    int tmpSeconds = seconds;
                    runOnUiThread(()->timeView.setText(String.valueOf(tmpSeconds)));
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        thread4.start();
    }

    public void onBtn4StopClick(View v) {
        thread4.interrupt();
    }

}