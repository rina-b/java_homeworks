package ua.com.alevel.multithreadprimenumbers;

import java.util.List;

public class CountPrimeNumbers extends Thread {

    int counter = 0;
    List<Integer> nums;

    public CountPrimeNumbers(List<Integer> nums) {
        this.nums = nums;
    }

    public void run() {
        for (Integer prime : nums) {
            for (int i = 2; i <= prime / 2; i++) {
                int temp = prime % i;
                if (temp == 0){
                    break;
                }
                counter+=1;
            }
        }
        System.out.println("Prime numbers count from thread " + Thread.currentThread().getName() + " = " + counter);
    }


    public int getCount() {
        return counter;
    }
}


