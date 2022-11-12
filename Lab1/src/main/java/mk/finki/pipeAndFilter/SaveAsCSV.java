package mk.finki.pipeAndFilter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SaveAsCSV {
    public void toCSV(List<String> data) {

        try {
            File csv = new File("csvFile.csv");
            if (csv.createNewFile()) {
                System.out.println("File created: " + csv.getName());

            } else {
                System.out.println("File already exists.");
            }
            try (PrintWriter pw = new PrintWriter(csv)) {
                data.forEach(pw::println);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }
}
