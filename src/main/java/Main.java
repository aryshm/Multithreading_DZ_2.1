public class Main {

    public static final int BUYERS = 10;
    private static final int ARRIVE_TIME = 2000;

    public static void main(String[] args) throws InterruptedException {

        final Dealer dealer = new Dealer();
        new Manufacturer(dealer).start();
        for (int i = 1; i <= BUYERS; i++) {
            new Consumer(dealer).start();
            Thread.sleep(ARRIVE_TIME);
        }
    }
}
