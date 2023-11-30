package Classes;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Network {
    /**
     *<pre>
     *Method {@code setFile} to set the file and create it if not exists
     *if it exists it remove its content
     *</pre>
     */
    private static void setFile(){
        try {
            Path path = Paths.get(System.getProperty("user.dir") + "\\out.txt");
            File file = new File(path.toString());

            if (!file.exists()) {
                if (!file.createNewFile()) {
                    System.out.println("Error in creating the file");
                }
            }
            FileWriter fileWriter = new FileWriter(path.toString()) ;
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter) ;

            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     *<pre>
     *Method {@code logToFile} to add content to the file
     *</pre>
     * @param content
     * <strong style="color:'white'"> represent the content which will add in the file</strong>
     */
    public static void logToFile(String content){
        try{
            Path path = Paths.get(System.getProperty("user.dir") + "\\out.txt") ;
            Files.write(path, (content + '\n').getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     *<pre>
     *The main method {@code main} to run the application
     *</pre>
     */
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

        setFile() ;
        for (int i = 0; i < totalDevices; i++) {
            devices.get(i).start();
        }

    }
}