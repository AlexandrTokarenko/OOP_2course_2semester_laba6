package main.files;

import main.model.Train;
import main.convertors.TrainConvertor;

import java.io.*;

public class TextFile {
    public void writeToFile(Train[] arr, String filename) {
        try (PrintWriter out = new PrintWriter(filename)) {
            out.println(arr.length);
            for (Train train : arr) {
                out.println(TrainConvertor.toText(train));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Train[] readFromFile(String filename ) {
        Train[] arr;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            arr = new Train[Integer.parseInt(reader.readLine())];
            String s;
            int count = 0;
            while ((s = reader.readLine()) != null) {
                Train t = TrainConvertor.fromText(s);
                arr[count++] = t;
            }
            return arr;
        } catch (IOException e) {
            e.printStackTrace();
            return new Train[0];
        }
    }
}
