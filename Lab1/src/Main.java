import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {

    public static final String DIR_PATH = "C:\\Users\\romak\\Desktop\\LabJava15";

    public static void main(String[] args) throws InterruptedException, IOException, ExecutionException {

        WriterToFile writer = new WriterToFile();
        FileNamesGetter fileNamesGetter = new FileNamesGetter();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the source directory:");
        String sourceDirectory = scanner.nextLine();

        while (!new File(sourceDirectory).exists()) {
            System.out.println("Enter the existing source directory");
            sourceDirectory = scanner.nextLine();
        }

        List<String> list = fileNamesGetter.listFilesForFolder(new File(sourceDirectory));

        FutureTask[] futureTasks = new FutureTask[list.size()];


        for (int i = 0; i < list.size(); i++) {
            Callable callable = new FileReaderCustom(new File(list.get(i)));
            futureTasks[i] = new FutureTask(callable);

            Thread t = new Thread(futureTasks[i]);
            t.start();
        }

        List<String> listToWrite = new ArrayList<>();

        for (FutureTask f : futureTasks) {
            System.out.println("\n");
            String string = writer.parse(f.get().toString());
            listToWrite.add(string);
        }

        writer.write(listToWrite);

        System.out.println(listToWrite);
    }
}
