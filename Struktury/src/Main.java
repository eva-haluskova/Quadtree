import Data.CadastralObjectGenerator;
import Data.GPS;
import Data.MapCoordinates;
import GUI.Cadaster;
import QuadTree.Coordinates;
import QuadTree.QuadTree;
import QuadTree.Place;
import QuadTree.ReadWriterOfTree;
import QuadTree.Tester.TesterForPlace;

import java.util.Random;


public class Main {

    public static void main(String[] args) {

        int maxDepth = 10;
        double sizeOfObjets = 100;
        int numberOfObjects = 100000;
        int width = 1000;
        int height = 1000 ;
        Random random = new Random();
        Coordinates coorsForSearch = new Coordinates(0,width,0,height);
        QuadTree<Place> testTree = new QuadTree<Place>(coorsForSearch,maxDepth);

        TesterForPlace tester = new TesterForPlace(testTree);
        tester.generateInsert(numberOfObjects, sizeOfObjets, random, coorsForSearch);

        tester.generateFind(coorsForSearch);

        testTree.tryToOptimalize();

        tester.generateFind(coorsForSearch);

        tester.generateDelete();

        System.out.println("Zatial to funguje ^-^");
    }

}

