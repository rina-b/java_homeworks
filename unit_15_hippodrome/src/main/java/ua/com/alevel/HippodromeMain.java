package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class HippodromeMain {
    private static final int RACE_LENGTH = 1000;
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        Hippodrome race = new Hippodrome(RACE_LENGTH);

        System.out.println("Welcome to HappyHippodrome! \n" +
                "Chose your lucky horse number from 1 to 10.");
        int choice;

        try {
            choice = Integer.parseInt(reader.readLine());
            if (choice<0 || choice> 10)
                throw new RuntimeException("There is no such horse: "+ choice);
        } catch (IOException e) {
            throw new RuntimeException(e.getCause());
        }


        Queue<String> horses = race.getFinishedHorses();
        List<Integer> allHorses  = new ArrayList<>();

        for (String name : horses){
            allHorses.add(Integer.parseInt(name.replaceAll("\\D+","")));
        }

        for (int i = 0; i < allHorses.size(); i++) {
            if (allHorses.get(i) == choice) {
                System.out.println("Your horse came " + (i+1));
            }
        }

    }
}
