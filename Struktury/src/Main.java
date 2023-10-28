import QuadTree.QuadTree;
import QuadTree.Place;
import QuadTree.Coordinates;
import QuadTree.Data;
import QuadTree.GPS;
import QuadTree.GPS.*;
import QuadTree.QuadTreeTester;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int velkost = 100;

        Random random = new Random();
        //random.setSeed(15);
        QuadTree<Place> testTree = new QuadTree<Place>(0, velkost, 0, velkost,20);
        int roundNumber = 100;

        Coordinates coors0 = new Coordinates(35,15);
        Place place0 = new Place("Location0");
        Data<Place> data0 = new Data(place0, coors0);
        testTree.insertSimpleTwo(data0);
        Coordinates coors1 = new Coordinates(90,6);
        Place place1 = new Place("Location1");
        Data<Place> data1 = new Data(place1, coors1);
        testTree.insertSimpleTwo(data1);
        Coordinates coors2 = new Coordinates(26,37);
        Place place2 = new Place("Location2");
        Data<Place> data2 = new Data(place2, coors2);
        testTree.insertSimpleTwo(data2);
        Coordinates coors3 = new Coordinates(98,11);
        Place place3 = new Place("Location3");
        Data<Place> data3 = new Data(place3, coors3);
        testTree.insertSimpleTwo(data3);
        Coordinates coors4 = new Coordinates(70,75);
        Place place4 = new Place("Location4");
        Data<Place> data4 = new Data(place4, coors4);
        testTree.insertSimpleTwo(data4);
        Coordinates coors5 = new Coordinates(80,40);
        Place place5 = new Place("Location5");
        Data<Place> data5 = new Data(place5, coors5);
        testTree.insertSimpleTwo(data5);
        Coordinates coors6 = new Coordinates(63,30);
        Place place6 = new Place("Location6");
        Data<Place> data6 = new Data(place6, coors6);
        testTree.insertSimpleTwo(data6);
        Coordinates coors7 = new Coordinates(42,70);
        Place place7 = new Place("Location7");
        Data<Place> data7 = new Data(place7, coors7);
        testTree.insertSimpleTwo(data7);
        Coordinates coors8 = new Coordinates(4,88);
        Place place8 = new Place("Location8");
        Data<Place> data8 = new Data(place8, coors8);
        testTree.insertSimpleTwo(data8);
        Coordinates coors9 = new Coordinates(63,42);
        Place place9 = new Place("Location9");
        Data<Place> data9 = new Data(place9, coors9);
        testTree.insertSimpleTwo(data9);
        Coordinates coors10 = new Coordinates(27,56);
        Place place10 = new Place("Location10");
        Data<Place> data10 = new Data(place10, coors10);
        testTree.insertSimpleTwo(data10);
        Coordinates coors11 = new Coordinates(26,37);
        Place place11 = new Place("Location11");
        Data<Place> data11 = new Data(place11, coors11);
        testTree.insertSimpleTwo(data11);
        Coordinates coors12 = new Coordinates(80,40);
        Place place12 = new Place("Location12");
        Data<Place> data12 = new Data(place12, coors12);
        testTree.insertSimpleTwo(data12);
        Coordinates coors13 = new Coordinates(63,30);
        Place place13 = new Place("Location13");
        Data<Place> data13 = new Data(place13, coors13);
        testTree.insertSimpleTwo(data13);
        Coordinates coors14 = new Coordinates(70,75);
        Place place14 = new Place("Location14");
        Data<Place> data14 = new Data(place14, coors14);
        testTree.insertSimpleTwo(data14);
        //Coordinates coors15 = new Coordinates(80,62);
        Coordinates coors15 = new Coordinates(90,6);

        Place place15 = new Place("Location15");
        Data<Place> data15 = new Data(place15, coors15);
        testTree.insertSimpleTwo(data15);
        Coordinates coors16 = new Coordinates(75,54);
        Place place16 = new Place("Location16");
        Data<Place> data16 = new Data(place16, coors16);
        testTree.insertSimpleTwo(data16);
        Coordinates coors17 = new Coordinates(98,11);
        Place place17 = new Place("Location17");
        Data<Place> data17 = new Data(place17, coors17);
        testTree.insertSimpleTwo(data17);
        Coordinates coors18 = new Coordinates(93.75,6.25);
        Place place18 = new Place("Location18");
        Data<Place> data18 = new Data(place18, coors18);
        testTree.insertSimpleTwo(data18);
        Coordinates coors19 = new Coordinates(89,8);
        Place place19 = new Place("Location19");
        Data<Place> data19 = new Data(place19, coors19);
        testTree.insertSimpleTwo(data19);
        Coordinates coors20 = new Coordinates(93.75,9);
        Place place20 = new Place("Location20");
        Data<Place> data20 = new Data(place20, coors20);
        testTree.insertSimpleTwo(data20);
        Coordinates coors21 = new Coordinates(90.625,2);
        Place place21 = new Place("Location21");
        Data<Place> data21 = new Data(place21, coors21);
        testTree.insertSimpleTwo(data21);
        Coordinates coors22 = new Coordinates(90,6);
        Place place22 = new Place("Location22");
        Data<Place> data22 = new Data(place22, coors22);
        testTree.insertSimpleTwo(data22);
        Coordinates coors23 = new Coordinates(91,5);
        Place place23 = new Place("Location23");
        Data<Place> data23 = new Data(place23, coors23);
        testTree.insertSimpleTwo(data23);
        Coordinates coors24 = new Coordinates(75,13);
        Place place24 = new Place("Location2");
        Data<Place> data24 = new Data(place24, coors24);
        testTree.insertSimpleTwo(data24);

        System.out.println(testTree.getDepth());
        System.out.println(testTree.getMaxDepth());

        /**
         * paskvilske robenie doublv
         */
        /*
        int widthOfPolygon = 20;
        int max = 100;
        int min = 0;
        for (int i = 1; i <= 1000000; i++) {
            double x1 = (double)(((random.nextInt(max - min) + 1) + min) / roundNumber);
            double x2 = x1 + widthOfPolygon;
            double y1 = (double)(((random.nextInt(max - min) + 1) + min) / roundNumber);
            double y2 = y1 + widthOfPolygon;
            //System.out.println(x1);
            //System.out.println(y1);

            Coordinates coors = new Coordinates(x1, x2, y1, y2);
            //Coordinates coors = new Coordinates(x1, y1);
            Place place = new Place("Location" + i);
            Data<Place> data = new Data(place, coors);

            testTree.insert(data);
            //System.out.println(testTree.insert(data) ? "Inserted: " + place.getName() : "Failed to insert: " + place.getName());
        }

        System.out.println(testTree.getDepth());
        System.out.println(testTree.getMaxDepth());
*/

        /**
         * normlane generovanie doubl0v
         */
/*
        int widthOfPolygon = 10;
        for (int i = 1; i <= 30; i++) {
            double x1 = random.nextDouble() * (velkost - widthOfPolygon - 1);
            double x2 = x1 + widthOfPolygon;
            double y1 = random.nextDouble() * (velkost - widthOfPolygon - 1);
            double y2 = y1 + widthOfPolygon;

            //x1 = (double)Math.round(x1 * roundNumber) / roundNumber;
            //y1 = (double)Math.round(y1 * roundNumber) / roundNumber;
            //System.out.println(x1);
            //System.out.println(y1);
            Coordinates coors = new Coordinates(x1, x2, y1, y2);
            //Coordinates coors = new Coordinates(x1, y1);
            Place place = new Place("Location" + i);
            Data<Place> data = new Data(place, coors);

             testTree.insertSimpleTwo(data);
            //System.out.println(testTree.insertSimpleTwo(data) ? "Inserted: " + place.getName() : "Failed to insert: " + place.getName());
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
        QuadTreeTester tester = new QuadTreeTester(testTree);
        //tester.levelOrderTraversal();
        tester.levelOrderTraversalWithNodeNumbers();

        Coordinates co = new Coordinates(90,6);

        ArrayList<Data<Place>> ar = testTree.find(co);
        for (Data<Place> pl:
             ar) {
            System.out.println(pl.getData().getName());
        }



        System.out.println("Zatial to funguje ^-^");
    }
}

// TODO vdase pozjednodusovat vyrazy list.get(0). a zaroevn list.remove(0)....da sa to, vis?
// TODO over si ci to doublevanie ma zmysel pri < >
// TODO over si ci niekde neporusujes zapuzdrenie, getovanie zoznamov cez kopiu!!!
// TODO with index access method pay attention if index exists?
// TODO zaujimavy poznaok o zmene velkosti ako ked som zvacsila velkost sa mi pekne krasne zvysila vyska stromu pri vlastne 10satine dat...teda dat na desasitine velkosti