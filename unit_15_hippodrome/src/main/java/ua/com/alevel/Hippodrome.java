package ua.com.alevel;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

final class Hippodrome {

    public Queue<String> getFinishedHorses(){
        ExecutorService executorService  = Executors.newFixedThreadPool(10);
        Horse horse = new Horse();
        for (int i = 0; i < 10; i++) {
            executorService.submit(horse);
        }

        executorService.shutdown();
        return horse.getFinishQ();
    }

}
