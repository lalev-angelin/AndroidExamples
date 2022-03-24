package uk.co.lalev.multithreadingdemo;

import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class PalindromeFinder implements Runnable {
        private Queue<String> in;
        private Set<String> out;
        private Random rnd;

        public PalindromeFinder(Queue<String> in, Set<String> out) {
            this.in = in;
            this.out = out;
            this.rnd = new Random(System.currentTimeMillis());
        }

        private boolean isPalindrome(String word) {
            char[] letters = word.toCharArray();
            for (int i=0; i<letters.length/2; i++) {
                if (letters[i]!=letters[letters.length-i-1]) return false;
            }
            return true;
        }

        @Override
        public void run() {
            String word = in.poll();
            while (word!=null) {
                if (isPalindrome(word)) out.add(word);
                word = in.poll();

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
}
