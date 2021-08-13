package ua.com.alevel.hellofromthread;

public class HelloFromThread extends Thread {
    int counter;

    public HelloFromThread(int counter) {
        this.counter = counter;
    }

    public void run() {
        if (counter < 49) {
            HelloFromThread t = new HelloFromThread(counter + 1);
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException("Can`t create a thread");
            }
        }
        System.out.println("Hello from thread " + counter);
    }
}

