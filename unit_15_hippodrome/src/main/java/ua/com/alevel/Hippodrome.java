package ua.com.alevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

final class Hippodrome extends Thread {

    public Queue getFinishedHorses() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletionService<Queue> service = new ExecutorCompletionService<>(executorService);
        List<Callable<Queue>> horseList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Horse horse = new Horse();
            horseList.add(horse);
        }
        List<Callable<Queue>> callables = horseList;
        for (Callable<Queue> callable : callables) {
            service.submit(callable);
        }
        Future<Queue> future = null;
        Queue f = null;
        for (int i = 0; i < 10; i++) {
            try {
                future = service.take();
                f = future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n________________________All threads in finishing order________________________ \n"+f +"\n");

        executorService.shutdown();
        return f;
    }
}

