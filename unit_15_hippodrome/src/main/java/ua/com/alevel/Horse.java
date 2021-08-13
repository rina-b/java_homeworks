package ua.com.alevel;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

final class Horse implements Runnable {

    private static final int MIN_MOVE = 100;
    private static final int MAX_MOVE = 200;

    private static final int MIN_SLEEP = 400;
    private static final int MAX_SLEEP = 500;

    private static final Queue<String> finishQ = new ArrayBlockingQueue<>(10);

    private final String name;

    private Hippodrome hippodrome;

    private int position;

    Horse(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    public Queue<String> getFinishQ(){
        return finishQ;
    }

    @Override
    public void run() {
        position = 0;

        int distance = hippodrome.distance;
        do {
            move();
            delay();
        } while (position < distance);

        finishQ.add(Thread.currentThread().getName());

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
                hippodrome.distance - position);
        position += move;
        System.out.printf("%s ran %d meters, total of %d%n", name, move, position);
    }

    private int randomNum(int max, int min) {
        return hippodrome.random.nextInt(max - min + 1) + min;
    }

}
