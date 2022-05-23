package main.files;

import main.model.Train;

import java.io.*;

public class BinaryFile {
    public void writeBinaryToFile(Train[] arr, String filename)  {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(arr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Train[] readBinaryFromFile( String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            return (Train[]) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new Train[0];
        }
    }
}
