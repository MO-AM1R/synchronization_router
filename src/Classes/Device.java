package Classes;

public class Device extends Thread{
    final String type ;
    final Router router ;
    int connectionId ;

    /**
     *<pre>
     *Constructor to set some attributes
     *</pre>
     * @param nameOfDevice
     * <strong style="color:'white'"> represent the name of the device</strong>
     * @param typeOfDevice
     * <strong style="color:'white'"> represent the type of the device</strong>
     * @param router
     * <strong style="color:'white'"> represent the router which the device will connect on</strong>
     */
    public Device(String nameOfDevice, String typeOfDevice, Router router) {
        super(nameOfDevice) ;
        type = typeOfDevice ;
        this.router = router ;
    }

    /**
     *<pre>
     *Method {@code login} to login the device
     *</pre>
     */
    private void login(){
        Network.logToFile("Connection " + connectionId + ": " + getName() + " login");
        System.out.println("Connection " + connectionId + ": " + getName() + " login");
    }

    /**
     *<pre>
     *Method {@code preformOnlineActivity} to allow the device preforming online activity
     *</pre>
     */
    private void preformOnlineActivity(){
        Network.logToFile("Connection " + connectionId + ": " + getName() + " perform online activity ");
        System.out.println("Connection " + connectionId + ": " + getName() + " perform online activity ");
    }

    /**
     *<pre>
     *Method {@code logout} to logout the device
     *</pre>
     */
    private void logout(){
        Network.logToFile("Connection " + connectionId + ": " + getName() + " Logged out");
        System.out.println("Connection " + connectionId + ": " + getName() + " Logged out");
    }

    /**
     *<pre>
     *Method {@code run} this method it run the sequence of methods
     *first occupy if can, login, perform online activity and logout
     *it Override from {@code Thread} class
     *</pre>
     */
    @Override
    synchronized public void run(){
        try {
            router.occupyConnection(this);
            sleep((long)(Math.random() * 1000));

            login();

            sleep((long)(Math.random() * 1000));
            preformOnlineActivity();

            sleep((long)(Math.random() * 1000));

            logout();
            router.releaseConnection(this);
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *<pre>
     *Getter method {@code getType} to get the type of device
     *</pre>
     */
    public String getType() {
        return type;
    }

    /**
     *<pre>
     *Getter method {@code getConnectionId} to get the connection id of device
     *</pre>
     */
    public int getConnectionId() {
        return connectionId ;
    }

    /**
     *<pre>
     *Setter method {@code setConnection} to set the connection id of device
     *</pre>
     */
    public void setConnection(int connection) {
        this.connectionId = connection;
    }
}
