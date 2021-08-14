package ua.com.alevel;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

final class Horse implements Callable {

    private static final int MIN_MOVE = 100;
    private static final int MAX_MOVE = 200;

    private static final int MIN_SLEEP = 400;
    private static final int MAX_SLEEP = 500;

    int distance = 1000;

    final Random random = new Random();

    private static Queue<String> finishQ = new ArrayBlockingQueue<>(10);

    private int position;


    @Override
    public Queue call() {
        position = 0;
        do {
            move();
            delay();
        } while (position < distance);

        finishQ.add(Thread.currentThread().toString());
        return getFinishQ();
    }

    private void delay() {
        try {
            Thread.sleep(randomNum(MAX_SLEEP, MIN_SLEEP));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void move() {
        int move = Math.min(
                randomNum(MAX_MOVE, MIN_MOVE),
                distance - position);
        position += move;
        System.out.printf("%s ran %d meters, total of %d%n", Thread.currentThread().getName(), move, position);
    }

    private int randomNum(int max, int min) {
        return random.nextInt(max - min + 1) + min;
    }

    public Queue<String> getFinishQ() {
        return finishQ;
    }
}
