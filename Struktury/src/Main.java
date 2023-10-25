import QuadTree.QuadTree;
import QuadTree.Place;
import QuadTree.Coordinates;
import QuadTree.Data;
import QuadTree.QuadTreeTester;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();
        random.setSeed(15);
        QuadTree<Place> testTree = new QuadTree<Place>(0, 100000, 0, 100000,20);
        int roundNumber = 100;

        /**
         * paskvilske robenie doublv
         */

        int max = 100000;
        int min = 0;
        for (int i = 1; i <= 1000000; i++) {
            double x1 = (double)(((random.nextInt(max - min) + 1) + min) / roundNumber);
            double x2 = x1 + (double)(((random.nextInt(max - min) + 1) + min) / roundNumber);
            double y1 = (double)(((random.nextInt(max - min) + 1) + min) / roundNumber);
            double y2 = y1 + (double)(((random.nextInt(max - min) + 1) + min) / roundNumber);
            //System.out.println(x1);
            //System.out.println(y1);

            Coordinates coors = new Coordinates(x1, x2, y1, y2);
            //Coordinates coors = new Coordinates(x1, y1);
            Place place = new Place("Location" + i);
            Data<Place> data = new Data(place, coors);

            testTree.insert(data);
            //System.out.println(testTree.insertD(data) ? "Inserted: " + place.getName() : "Failed to insert: " + place.getName());
        }

        System.out.println(testTree.getDepth());
        System.out.println(testTree.getMaxDepth());


        /**
         * normlane generovanie doubl0v
         */
/*
        for (int i = 1; i <= 10000000; i++) {
            double x1 = random.nextDouble() * 100;
            double x2 = x1 + random.nextDouble() * (100 - x1);
            double y1 = random.nextDouble() * 100;
            double y2 = y1 + random.nextDouble() * (100 - y1);

            x1 = (double)Math.round(x1 * roundNumber) / roundNumber;
            y1 = (double)Math.round(y1 * roundNumber) / roundNumber;
            //System.out.println(x1);
            //System.out.println(y1);
            Coordinates coors = new Coordinates(x1, x2, y1, y2);
            //Coordinates coors = new Coordinates(x1, y1);
            Place place = new Place("Location" + i);
            Data<Place> data = new Data(place, coors);

            testTree.insert(data);
            //System.out.println(testTree.insertD(data) ? "Inserted: " + place.getName() : "Failed to insert: " + place.getName());
        }

        System.out.println(testTree.getDepth());
        System.out.println(testTree.getMaxDepth());
*/

        /**
         * testovanie vkladanie rovnakych dat
         */
        /*
        Coordinates coors1 = new Coordinates(98.38, 98.38, 34.23, 34.23);
        Place place1 = new Place("Location");
        Data<Place> data1 = new Data(place1, coors1);
        testTree.insert(data1);
        Coordinates coors = new Coordinates(98.38, 98.38, 34.23,34.23);
        Place place = new Place("Location");
        Data<Place> data = new Data(place, coors);
        testTree.insert(data);
        Coordinates coors2 = new Coordinates(98.38, 98.38, 34.23,34.23);
        Place place2 = new Place("Location");
        Data<Place> data2 = new Data(place2, coors2);
        testTree.insert(data2);
        */

        /**
         * testovac seedov a kedy mi to spadne
         */
        /*
        for (int j = 0; j < 200; j++) {
            random.setSeed(j);
            System.out.println("AKTUALNY SEED JE " + j);
            int max = 10000;
            int min = 0;
            for (int i = 1; i <= 5; i++) {
                double x1 = (double)(((random.nextInt(max - min) + 1) + min) / roundNumber);
                double x2 = x1 + random.nextDouble() * (100 - x1);
                double y1 = (double)(((random.nextInt(max - min) + 1) + min) / roundNumber);
                double y2 = y1 + random.nextDouble() * (100 - y1);
                System.out.println(x1);
                System.out.println(y1);

                //Coordinates coors = new Coordinates(x1, x2, y1, y2);
                Coordinates coors = new Coordinates(x1, y1);
                Place place = new Place("Location" + i);
                Data<Place> data = new Data(place, coors);

                testTree.insertD(data);
                //System.out.println(testTree.insertD(data) ? "Inserted: " + place.getName() : "Failed to insert: " + place.getName());
            }

        }
        */

        /**
         * vypis nodov a dat v nich
         */
        //QuadTreeTester tester = new QuadTreeTester(testTree);
        //tester.levelOrderTraversal();
        //tester.levelOrderTraversalWithNodeNumbers();
        System.out.println("Zatial to funguje ^-^");
    }
}

// TODO vdase pozjednodusovat vyrazy list.get(0). a zaroevn list.remove(0)....da sa to, vis?
// TODO over si ci to doublevanie ma zmysel pri < >
// TODO over si ci niekde neporusujes zapuzdrenie

