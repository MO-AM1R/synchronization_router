package Classes;

public class Device extends Thread{
    String type ;
    Router router ;
    int connection ;
    public Device(String nameOfDevice, String typeOfDevice, Router router) {
        super(nameOfDevice) ;
        type = typeOfDevice ;
        this.router = router ;
        connection = 1 ;
    }

    @Override
    synchronized public void run(){
        router.getSemaphore().decrement(this);
        try {
            connection = router.occupyConnection(this);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        Network.logToFile("Connection " + connection + ": " + getName() + " login");
        System.out.println("Connection " + connection + ": " + getName() + " login");

        try {
            sleep((long)(Math.random() * 1000));
            Network.logToFile("Connection " + connection + ": " + getName() + " perform online activity ");
            System.out.println("Connection " + connection + ": " + getName() + " perform online activity ");
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            sleep((long)(Math.random() * 1000));
            router.releaseConnection(this);
            router.getSemaphore().increment();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getType() {
        return type;
    }

    public int getConnection() {
        return connection ;
    }

    public void setConnection(int connection) {
        this.connection = connection;
    }
}
