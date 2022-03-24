package uk.co.lalev.multithreadingdemo;

import android.telecom.Call;
import android.util.Log;

import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class WordGenerator implements Runnable {
    private Queue<String> words;
    private int wordLength;
    private int numWords;
    private int numberInRow;
    private BiConsumer<Integer, Integer> progressNotifier;
    private Random rnd;
    private int step;

    public WordGenerator(Queue<String> words, int numWords, int wordLength, Integer numberInRow, BiConsumer<Integer, Integer> progressNotifier) {
        this.words = words;
        this.numWords = numWords;
        this.wordLength = wordLength;
        this.numberInRow = numberInRow;
        step = numWords / 100;
        this.progressNotifier = progressNotifier;
        this.rnd = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        for (int j = 0; j < numWords; j++) {
            StringBuilder word = new StringBuilder();
            for (int i = 0; i < wordLength; i++) {
                char nextChar = (char) (65 + rnd.nextInt(26));
                word.append(nextChar);
            }
            words.add(word.toString());

            if ((j % step) == 0) {
                progressNotifier.accept(numberInRow, j / step);
                Log.d("WordGenerator", "Step" + (j / step));
            }
        }
    }
}
