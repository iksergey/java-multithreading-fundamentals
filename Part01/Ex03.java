public class Ex03 {
    public static void main(String[] args) {
        // Создаем и запускаем первый поток
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Поток 1: " + i);
                try {
                    Thread.sleep(1000); // Задержка 1 секунда
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Создаем и запускаем второй поток
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Поток 2: " + i);
                try {
                    Thread.sleep(1000); // Задержка 1 секунда
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Запускаем потоки
        thread1.start();
        thread2.start();
        // // Ждем завершения обоих потоков
        // try {
        // thread1.join();
        // thread2.join();
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        // System.out.println("Оба потока завершили работу");
        System.out.println("Оба потока работают");
    }
}
