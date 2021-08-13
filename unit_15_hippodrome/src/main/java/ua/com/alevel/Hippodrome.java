package ua.com.alevel;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

final class Hippodrome {
    final int distance;

    final Random random;

    Hippodrome(int distance) {
        this.distance = distance;
        random = new Random();
    }

    int getDistance() {
        return distance;
    }

    public Queue<String> getFinishedHorses(){
        ExecutorService executorService  = Executors.newFixedThreadPool(10);
        Horse horse = new Horse(Thread.currentThread().getName());

        for (int i = 0; i < 10; i++) {
            executorService.submit(horse);
        }

        executorService.shutdown();

        return horse.getFinishQ();
    }

}
