package Classes;
import static java.lang.Thread.sleep;

public class Router {
    private final Semaphore devices ;
    private int numberOfConnections;
    private final boolean[] isConnected ;

    public Router(int numberOfConnections){
        devices = new Semaphore(numberOfConnections) ;
        isConnected = new boolean[numberOfConnections] ;
    }

    public synchronized int occupyConnection(Device device) throws InterruptedException {
        for (int i = 0; i < isConnected.length; i++) {
            if (!isConnected[i]){
                ++numberOfConnections ;
                isConnected[i] = true ;
                device.setConnection(i + 1) ;
                Network.logToFile("Connection " + device.getConnection() + ": " + device.getName() + " Occupied");
                System.out.println("Connection " + device.getConnection() + ": " + device.getName() + " Occupied");

                sleep((long)(Math.random() * 1000));
                break;
            }
        }
        return device.getConnection();
    }

    synchronized public void releaseConnection(Device device){
        --numberOfConnections ;
        isConnected[device.getConnection() - 1] = false ;
        notify();
        Network.logToFile("Connection " + device.getConnection() + ": " + device.getName() + " Logged out");
        System.out.println("Connection " + device.getConnection() + ": " + device.getName() + " Logged out");
    }

    public Semaphore getSemaphore() {
        return devices;
    }
}
