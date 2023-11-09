package QuadTree;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.List;

public class ReadWriterOfTree<T> {

    private QuadTree<T> tree;

    public ReadWriterOfTree(QuadTree<T> parTree) {
        this.tree = parTree;
    }

    public void readData(String parCsvFilePath) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(parCsvFilePath))) {
            // Define the data you want to write
            List<String[]> data = List.of(
                    new String[] {"John", "Doe", "john@example.com"},
                    new String[] {"Alice", "Smith", "alice@example.com"}
            );

            // Write data to the CSV file
            for (String[] row : data) {
                for (int i = 0; i < row.length; i++) {
                    writer.write(row[i]);
                    if (i < row.length - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
            }

           // System.out.println("Data has been written to " + parCsvFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
