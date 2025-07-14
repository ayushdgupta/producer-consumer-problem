public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        SharedResource resource = new SharedResource();

//        for single time calling
//        Thread producer = new Thread(resource::produce);
//        Thread consumer = new Thread(resource::consumer);
//
//        producer.start();
//        consumer.start();

        Thread producer = new Thread(() -> {
            int i = 0;
            while (i < 10){
                try {
                    resource.produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                i++;
            }
        });

        Thread consumer = new Thread(() -> {
            int i = 0;
            while (i < 10){
                try {
                    resource.consumer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                i++;
            }
        });

        producer.start();
        consumer.start();

    }
}