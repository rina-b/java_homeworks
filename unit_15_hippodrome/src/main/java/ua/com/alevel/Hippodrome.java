package ua.com.alevel;

import java.util.Random;

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


}
