import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriterToFile {
    public void write(List<String> list) throws IOException {
        File file = new File("C:\\Users\\romak\\Desktop\\LabJava15Writer\\Result.txt");
        boolean check = true;
        try {
            if (!file.exists()) {
                check = file.createNewFile();
                System.out.println("File created!");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (check) {
            FileWriter writer = new FileWriter(file);

            for (int i = 0; i < list.size(); i++) {
                writer.write(list.get(i) + "\n");
            }

            System.out.println("Data written to file " + file.getName());
            writer.close();
        }
    }

    public String parse(String string) {
        string = string.substring(1, string.length() - 1);
        return string;
    }
}
