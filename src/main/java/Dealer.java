public class Dealer {

    private static final int PRODUCTION_TIME = 3000;
    private static final int CARS = 10;
    private static final int WANT_TO_BUY = 5;
    private static int ProducedCars = 0;

    public synchronized void buyCar() {
        for (int i = 0; i < WANT_TO_BUY; i++) {
            if (ProducedCars < 1) {
                System.out.println("Машин нет");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                ProducedCars--;
                System.out.printf("%s уехал на новеньком авто\n", Thread.currentThread().getName());
                Thread.currentThread().stop();
            }
        }
    }

    public void produceCar() {
        for (int i = CARS; i > 0 ; i--) {
            try {
                Thread.sleep(PRODUCTION_TIME);
            } catch (InterruptedException e) {
                return;
            }
            System.out.println("Производитель Toyota выпустил 1 авто");
            ProducedCars++;
            synchronized (this) {
                notify();
            }
        }
    }

}
