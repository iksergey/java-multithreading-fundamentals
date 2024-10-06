public class Ex02 {
    public static void main(String[] args) {
        // Создаем экземпляр NewThread
        NewThread newThread = new NewThread();

        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Главный поток: " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Главный поток прерван.");
        } finally {
            System.out.println("Главный поток завершен.");
        }

        // // Ждем завершения дочернего потока
        // try {
        // newThread.t.join();
        // } catch (InterruptedException e) {
        // System.out.println("Ожидание дочернего потока прервано.");
        // }
    }
}

class NewThread implements Runnable {
    Thread t;

    NewThread() {
        t = new Thread(this, "Демонстрационный поток");
        System.out.println("Дочерний поток создан: " + t);
        t.start();
    }

    // Точка входа во второй поток
    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Дочерний поток: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Дочерний поток прерван.");
        } finally {
            System.out.println("Дочерний поток завершен.");
        }
    }
}
