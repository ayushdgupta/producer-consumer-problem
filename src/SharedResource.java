public class SharedResource {

    private int data;
    private boolean hasData;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public boolean isHasData() {
        return hasData;
    }

    public void setHasData(boolean hasData) {
        this.hasData = hasData;
    }

    public synchronized void produce() throws InterruptedException {
        while (hasData){
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Producer already has data so waiting ");
                Thread.currentThread().interrupt();
            }
        }
        data = data + 1;
        System.out.println("produces data "+data);
        hasData = true;
        Thread.sleep(1000);
        notify();
    }

    public synchronized void consumer() throws InterruptedException {
        while (!hasData){
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Consumer don't have data so waiting ");
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Consumed data "+data);
        hasData = false;
        Thread.sleep(1000);
        notify();
    }
}
