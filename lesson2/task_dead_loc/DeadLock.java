import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {

    static class Friend {
        private final String name;

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void bow(Friend bower) {
            /*
            * Дедлок возникает из-за того,что
            * 1) при вызове alphonse.bow(gaston) блокируется alphonse
            * 2) при вызове gaston.bow(alphonse) блокируется gaston
            * 3) bower.bowBack(this), где bower = gaston не может выполнится т.к.
            * gaston заблокирован
            * 4) bower.bowBack(this), где bower = alphonse не может выполнится т.к.
             * alphonse заблокирован
             * Поэтому возникает дедлок
             *
             *
             * Для разрешения этой проблеммы мы можем сделать синхранизацию блока кода,
             * а не всего метода, чтобы при вызове bower.bowBack(this) ресурсы освобождались*/
            synchronized(this) {
                System.out.format("%s: %s подстрелил меня!\n", this.name, bower.getName());
                System.out.format("%s: стреляю в ответ!\n", this.name);
            }
                bower.bowBack(this);
        }

        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s подстрелил меня!\n", this.name, bower.getName());
        }
    }

    /**
     * Точка входа в программу
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        Friend alphonse = new Friend("Alphonse");
        Friend gaston = new Friend("Gaston");

        new Thread(() -> alphonse.bow(gaston)).start();
        new Thread(() -> gaston.bow(alphonse)).start();
    }
}