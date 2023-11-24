package Classes;

public class Semaphore {
    private int value ;

    public Semaphore(int value){
        this.value = value ;
    }

    /**
     *<pre>
     *This method {@code occupy} it add device
     *and check if value become less than 0
     *then put device in waiting
     *</pre>
     */
    synchronized void decrement(Device device){
        value--;
        if (value < 0) {
            try {
                Thread.sleep((long)(Math.random() * 1000));
                Network.logToFile("(" + device.getName() + ") " + "(" + device.getType() + ") " + "arrived and waiting");
                System.out.println("(" + device.getName() + ") " + "(" + device.getType() + ") " + "arrived and waiting");
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            Network.logToFile("(" + device.getName() + ") " + "(" + device.getType() + ") " + "arrived");
            System.out.println("(" + device.getName() + ") " + "(" + device.getType() + ") " + "arrived");
            try {
                Thread.sleep((long)(Math.random() * 1000));
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     *<pre>
     *This method {@code release} it release one device
     *and check if the value become 0 or less
     *so it notify that
     *</pre>
     */
    synchronized void increment(){
        ++value ;
        if (value <= 0) {
            notify();
        }
    }
}