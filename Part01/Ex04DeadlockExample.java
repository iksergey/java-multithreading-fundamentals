public class Ex04DeadlockExample {
    private static final Object RESOURCE_1 = new Object();
    private static final Object RESOURCE_2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (RESOURCE_1) {
                System.out.println("Поток 1: Захватил ресурс 1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Поток 1: Ожидает ресурс 2");
                synchronized (RESOURCE_2) {
                    System.out.println("Поток 1: Захватил ресурс 2");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (RESOURCE_2) {
                System.out.println("Поток 2: Захватил ресурс 2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Поток 2: Ожидает ресурс 1");
                synchronized (RESOURCE_1) {
                    System.out.println("Поток 2: Захватил ресурс 1");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
