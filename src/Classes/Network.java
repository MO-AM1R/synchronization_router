package Classes;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Network {
    public static void logToFile(String content){
        try{
            Path path = Paths.get(System.getProperty("user.dir") + "\\out.txt") ;
            File file = new File(path.toString()) ;

            if (!file.exists()){
                if (!file.createNewFile()){
                    System.out.println("Error in creating the file");
                }
            }
            Files.write(path, (content + '\n').getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

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

        logToFile("\n---------------------------------------------------\n");
        for (int i = 0; i < totalDevices; i++) {
            devices.get(i).start();
        }

    }
}