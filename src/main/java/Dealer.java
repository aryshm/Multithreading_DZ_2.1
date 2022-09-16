import java.util.LinkedList;
import java.util.List;

public class Dealer {

    List<Car> list = new LinkedList<>();
    private static final int PRODUCTION_TIME = 3000;
    private static final int CARS_TO_PRODUCE = 10;
    private static final int FIRST_CAR_TO_TAKE = 0;
    private static boolean flag = true;

    public synchronized void buyCar() {
        while (flag) {
            if (list.isEmpty()) {
                System.out.println("Машин нет");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                list.remove(FIRST_CAR_TO_TAKE);
                System.out.printf("%s уехал на новеньком авто\n", Thread.currentThread().getName());
                flag = false;
            }
        }
        flag = true;
    }

    public void produceCar() {
        for (int i = CARS_TO_PRODUCE; i > 0; i--) {
            try {
                Thread.sleep(PRODUCTION_TIME);
            } catch (InterruptedException e) {
                return;
            }
            System.out.println("Производитель Toyota выпустил 1 авто");
            list.add(new Car());
            synchronized (this) {
                notify();
            }
        }
    }

}
