package Classes;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Network {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is the number of WI-FI Connections?");
        int numberOfConnections = scanner.nextInt();

        System.out.println("What is the number of devices Clients want to connect?");
        int totalDevices = scanner.nextInt();

        List<Device> devices = new Vector<>() ;
        Router router = new Router(numberOfConnections);
        scanner.nextLine();

        for (int i = 0; i < totalDevices; i++) {
            String[] inputs = (scanner.nextLine()).split(" ");
            devices.add(new Device(inputs[0], inputs[1], router));

        }

        for (int i = 0; i < totalDevices; i++) {
            devices.get(i).start();
        }
    }
}