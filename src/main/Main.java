package main;

import main.files.BinaryFile;
import main.files.TextFile;
import main.menu.Menu;
import main.model.Train;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        new Main().run();
    }

    private void run() {

        Train tr = new Train("Коломия",12,15,15,10,10,10,10);
        BinaryFile binaryFile = new BinaryFile();
        TextFile textFile = new TextFile();
        Train[] trains = create();
        Menu menu = new Menu();
        int item;
        do {
            menu.show();
            item = menu.getSelection();
            switch (item) {
                case 1:
                    trains = textFile.readFromFile("laba6.txt");
                    break;
                case 2:
                    textFile.writeToFile(trains,"laba6.txt");
                    break;
                case 3:
                    trains = binaryFile.readBinaryFromFile("laba6.dat");
                    break;
                case 4:
                    binaryFile.writeBinaryToFile(trains,"laba6.dat");
                    break;
                case 5:
                    trains = addtoTheEnd(trains,tr);
                    break;
                case 6:
                    trains = deleteLastElement(trains);
                    break;
                case 7:
                    sortByTime(trains);
                    break;
                case 8:
                    findSentAfterHour(trains);
                    break;
                case 9:
                    findTrainsHaveSeat(trains);
                    break;
                case 10:
                    findTrainsByPointAndSortSeat(trains);
                    break;
                case 11:
                    printTrain(trains);
                    break;
            }
        } while (item != 0);
    }

    private Train[] addtoTheEnd(Train[] trains, Train tr) {
        Train[] result = new Train[trains.length + 1];
        for (int i = 0; i < trains.length; i++) result[i] = trains[i];
        result[result.length-1] = tr;
        return result;
    }

    private Train[] deleteLastElement(Train[] trains) {

        return Arrays.copyOf(trains,(trains.length-1));
    }

    public void printTrain(Train[] st) {

        for (int i = 0; i < st.length; i++) System.out.println(st[i]);
    }
    public void findTrainsHaveSeat(Train[] arr) {

        System.out.println("Введіть пункт призначення");
        Scanner in = new Scanner(System.in);
        String point = in.next();
        Train[] trains = findTrainsByPoint(arr,point);
        Train[] result = new Train[trains.length];
        int count = 0;
        for(Train train: trains) if (train.getCommon() > 0) result[count++] = train;
        printTrain(Arrays.copyOf(result,count));
    }
    public void findSentAfterHour(Train[] trains) {

        System.out.println("Введіть пункт призначення");
        Scanner in = new Scanner(System.in);
        String point = in.next();
        System.out.println("Введіть годину після якої відправляється потяг");
        int hour = in.nextInt();
        Train[] arr = findTrainsByPoint(trains,point);
        Train[] trains1 = new Train[arr.length];
        int count = 0;
        for (Train train : arr) if (train.getHour() >= hour) trains1[count++] = train;
        printTrain(Arrays.copyOf(trains1,count));
    }
    public Train[] findTrainsByPoint(Train[] trains, String point){

        Train[] result = new Train[trains.length];
        int count = 0;
        for (Train train : trains) {
            if (train.getPoint().equals(point)) {
                result[count++] = train;
            }
        }
        return Arrays.copyOf(result,count);
    }
    public void sortByNumberOfPlaces(Train[] trains) {
        for (int i = trains.length-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                int count1 = trains[j].getCommon() + trains[j].getLuxury() + trains[j].getBerth() + trains[j].getCompartment();
                int count2 = trains[j+1].getCommon() + trains[j+1].getLuxury() + trains[j+1].getBerth() + trains[j+1].getCompartment();
                if (count1 > count2) {
                    Train tmp = trains[j];
                    trains[j] = trains[j+1];
                    trains[j+1] = tmp;
                }
            }
        }

    }
    public void sortByTime(Train[] arr){
        System.out.println("Введіть пункт призначення");
        Scanner in = new Scanner(System.in);
        String point = in.next();
        Train[] trains = findTrainsByPoint(arr,point);
        Arrays.sort(trains);
        printTrain(trains);
    }
    public void findTrainsByPointAndSortSeat(Train[] st) {

        System.out.println("Введіть пункт призначення");
        Scanner in = new Scanner(System.in);
        String point = in.next();
        Train[] trains = findTrainsByPoint(st,point);
        sortByNumberOfPlaces(trains);
        printTrain(trains);
    }

    public static Train[] create() {

        return new Train[100];
    }
}
