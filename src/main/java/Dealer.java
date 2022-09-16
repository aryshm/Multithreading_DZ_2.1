public class Dealer {

    private static final int PRODUCTION_TIME = 3000;
    private static final int CARS = 10;
    private static int producedCars = 0;
    private static boolean flag = true;

public synchronized void buyCar() {
   while (flag){
        if (producedCars < 1) {
            System.out.println("Машин нет");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            producedCars--;
            System.out.printf("%s уехал на новеньком авто\n", Thread.currentThread().getName());
            flag = false;
        }
    }
   flag = true;
}

    public void produceCar() {
        for (int i = CARS; i > 0 ; i--) {
            try {
                Thread.sleep(PRODUCTION_TIME);
            } catch (InterruptedException e) {
                return;
            }
            System.out.println("Производитель Toyota выпустил 1 авто");
            producedCars++;
            synchronized (this) {
                notify();
            }
        }
    }

}
