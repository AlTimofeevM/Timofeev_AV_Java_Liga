import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

public class FourMillions {


    /**
     * Класс-счётчик.
     */
    static class Counter {

        /**
         * Буфер счёта
         */
        /*
         Ошибка возникает из-за того, что пока один поток не закончил работать
         с полем count, другой поток уже начинает с ним работать.

         Атомарные операции можно безопастно выполнять при параллельных вычесениях
         в нескольких потоках.Соответсвенно поменял тип на AtomicLong.
        */
        private AtomicLong count = new AtomicLong(0);

        /**
         * Считаем +1
         */
        /*
         Соответственно поменял инкремент.
         */
        public void increment() {
            count.incrementAndGet();
        }

        /**
         * Получить текущее значение счётчика
         */
        /*
         Соответственно поменял возврат значения.
         */
        public long getCount() {
            return count.get();
        }
    }

    private final static int N_THREADS = 4;

    /**
     * Точка входа в программу
     *
     * @param args арг-ты командной строки
     */
    public static void main(String[] args) {
        Counter counter = new Counter();

        ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);

        // создаём java.Util.Stream для интов щт 0 до 4 (искд.)
        // * не путать Stream и Thread
        CompletableFuture<?>[] futures = IntStream.range(0, N_THREADS)
                // вместо каждой цифры запускаем инкременты счётчика
                .mapToObj(ignored -> runCounting(counter, executorService))
                // собираем CompletableFuture'ы в массив
                .toArray(CompletableFuture[]::new);

        // когда все потоки завершат свою работу
        CompletableFuture.allOf(futures).thenRun(() -> {
            // имеем шанс не получить 4 млн
            System.out.println("Total count: " + counter.getCount());
            executorService.shutdown();
        });
    }

    /**
     * Запускает миллион инкрементов счётчика в отдельном потоке
     *
     * @param counter         счётчик для инкрементов
     * @param executorService пул потоков для работы
     *
     * @return CompletableFuture без результата, разрешаемый после завершения инкрементаций
     */
    private static CompletableFuture<?> runCounting(Counter counter, ExecutorService executorService) {
        return CompletableFuture.runAsync(
                () -> {
                    for (int j = 0; j < 1000000; j++) {
                        counter.increment();
                    }
                },
                executorService
        );
    }
}