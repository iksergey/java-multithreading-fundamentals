public class MainSync {
    public static void main(String[] args) {
        SharedBuffer sharedBuffer = new SharedBuffer();
        Thread producerThread = new Thread(new Producer(sharedBuffer));
        Thread consumerThread = new Thread(new Consumer(sharedBuffer));

        producerThread.start();
        consumerThread.start();
    }
}

/**
 * Класс, который будет использоваться для хранения и обмена
 * данными между потоками
 */
class SharedBuffer {
    private int contents;
    private boolean available = false;

    public synchronized void put(int value) {
        while (available) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        contents = value;
        available = true;
        System.out.println("Производитель положил: " + contents);
        notify();
    }

    public synchronized int get() {
        while (!available) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        available = false;
        System.out.println("Потребитель получил: " + contents);
        notify();
        return contents;
    }
}

class Producer implements Runnable {
    private SharedBuffer sharedBuffer;

    public Producer(SharedBuffer sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            sharedBuffer.put(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
}

class Consumer implements Runnable {
    private SharedBuffer sharedBuffer;

    public Consumer(SharedBuffer sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            sharedBuffer.get();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
}
