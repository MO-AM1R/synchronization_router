package Classes;

public class Device extends Thread{
    String type ;
    Router router ;
    int connectionId ;

    public Device(String nameOfDevice, String typeOfDevice, Router router) {
        super(nameOfDevice) ;
        type = typeOfDevice ;
        this.router = router ;
        connectionId = 1 ;
    }

    private void login(){
        Network.logToFile("Connection " + connectionId + ": " + getName() + " login");
        System.out.println("Connection " + connectionId + ": " + getName() + " login");
    }

    private void preformOnlineActivity(){
        Network.logToFile("Connection " + connectionId + ": " + getName() + " perform online activity ");
        System.out.println("Connection " + connectionId + ": " + getName() + " perform online activity ");
    }

    private void logout(){
        Network.logToFile("Connection " + connectionId + ": " + getName() + " Logged out");
        System.out.println("Connection " + connectionId + ": " + getName() + " Logged out");
    }

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

    public String getType() {
        return type;
    }

    public int getConnectionId() {
        return connectionId ;
    }

    public void setConnection(int connection) {
        this.connectionId = connection;
    }
}
