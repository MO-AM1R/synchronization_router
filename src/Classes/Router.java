package Classes;
import java.util.Arrays;
import java.util.List;

public class Router {
    private final Semaphore devices ;
    private final List<Boolean> connections;

    /**
     *<pre>
     *Constructor to set some attributes
     *</pre>
     * @param numberOfConnections
     * <strong style="color:'white'"> represent the maximum number of connection at same time</strong>
     */
    public Router(int numberOfConnections){
        devices = new Semaphore(numberOfConnections) ;

        Boolean[] tempArray = new Boolean[numberOfConnections] ;
        Arrays.fill(tempArray, false);
        connections = Arrays.asList(tempArray);
    }

    /**
     *<pre>
     *Method {@code occupyConnection} to add new device
     *if there a place to occupy the device
     *if there not place it will waiting till one logout
     *</pre>
     * @param device
     * <strong style="color:'white'"> represent the device which wants to connect</strong>
     */
    public void occupyConnection(Device device) {
        devices.decrement(device);
        int index = connections.indexOf(false) ;
        connections.set(index, true) ;
        device.setConnection(index + 1);
        Network.logToFile("Connection " + device.getConnectionId() + ": " + device.getName() + " Occupied");
        System.out.println("Connection " + device.getConnectionId() + ": " + device.getName() + " Occupied");
    }

    /**
     *<pre>
     *Method {@code releaseConnection} to remove device
     *after that it call {@code increment} to notify that waiting devices
     *that there is place has released
     *</pre>
     * @param device
     * <strong style="color:'white'"> represent the device which wants to connect</strong>
     */
    public void releaseConnection(Device device){
        connections.set(device.getConnectionId() - 1, false);
        devices.increment();
    }
}
