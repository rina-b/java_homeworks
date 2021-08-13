package ua.com.alevel;

import ua.com.alevel.hellofromthread.HelloFromThread;
import ua.com.alevel.multithreadprimenumbers.CountPrimeNumbers;

import java.util.List;

public class MultithreadingMain {

    public static void main(String[] arg) {
        System.out.println("--------------------------HELLO FROM THREAD-------------------------------");
        helloFromThread();
        System.out.println("--------------------------COUNT PRIME NUMBERS-------------------------------");
        countPrimeNumbers();
    }

    public static void helloFromThread(){
        HelloFromThread t = new HelloFromThread(0);
        t.start();
    }

    public static void countPrimeNumbers(){
        int count = 0;
        List<Integer> nums = List.of(1, 2, 5, 4, 7, 6, 7, 8, 5, 10, 8);

        CountPrimeNumbers countPrimeNumbers1 = new CountPrimeNumbers(nums.subList(0, nums.size()/2));
        CountPrimeNumbers countPrimeNumbers2 = new CountPrimeNumbers(nums.subList(nums.size()/2, nums.size()));

        countPrimeNumbers1.start();
        countPrimeNumbers2.start();
        try {
            countPrimeNumbers1.join();
            countPrimeNumbers2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getCause());
        }
        count = countPrimeNumbers1.getCount() + countPrimeNumbers2.getCount();
        System.out.println("Count of prime numbers = " + count);
    }
}
