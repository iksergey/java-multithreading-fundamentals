Пример взаимной блокировки (deadlock) на Java:

```java
public class DeadlockExample {
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
```

В этом примере:

1. Создаются два статических объекта `RESOURCE_1` и `RESOURCE_2`, которые будут использоваться для синхронизации.

2. Запускаются два потока:
   - Первый поток сначала захватывает `RESOURCE_1`, затем пытается захватить `RESOURCE_2`.
   - Второй поток сначала захватывает `RESOURCE_2`, затем пытается захватить `RESOURCE_1`.

3. Между захватом ресурсов добавлена небольшая задержка (100 мс), чтобы увеличить вероятность возникновения взаимной блокировки.

4. Когда программа запускается, оба потока захватывают свои первые ресурсы, а затем пытаются захватить вторые ресурсы, которые уже заняты другим потоком. В результате возникает ситуация взаимной блокировки.

При запуске этой программы вы увидите, что потоки захватывают свои первые ресурсы, но затем программа "зависает", так как оба потока ожидают освобождения ресурсов, которые удерживаются другим потоком.
