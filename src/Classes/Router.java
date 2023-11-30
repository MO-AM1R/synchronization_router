package Classes;
import java.util.Arrays;
import java.util.List;

public class Router {
    private final Semaphore devices ;
    private final List<Boolean> connections;

    public Router(int numberOfConnections){
        devices = new Semaphore(numberOfConnections) ;

        Boolean[] tempArray = new Boolean[numberOfConnections] ;
        Arrays.fill(tempArray, false);
        connections = Arrays.asList(tempArray);
    }

    public void occupyConnection(Device device) {
        devices.decrement(device);
        int index = connections.indexOf(false) ;
        connections.set(index, true) ;
        device.setConnection(index + 1);
        Network.logToFile("Connection " + device.getConnectionId() + ": " + device.getName() + " Occupied");
        System.out.println("Connection " + device.getConnectionId() + ": " + device.getName() + " Occupied");
    }

    public void releaseConnection(Device device){
        connections.set(device.getConnectionId() - 1, false);
        devices.increment();
    }
}
