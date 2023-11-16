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

        int maxDepth = 6;
        double sizeOfObjets = 10;
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

// TODO zmena vysky stromu
// TODO insert aby neinsertol existujuce
// TODO overenie find
// TODO delete znizovanie hlbky stromu
// TODO SPRAVIS OPTIMALIZACIU
// TODO mapovanie vyrvaranie dat mas zle
// TODO zaujimavy poznaok o zmene velkosti ako ked som zvacsila velkost sa mi pekne krasne zvysila vyska stromu pri vlastne 10satine dat...teda dat na desasitine velkosti
// TODO gujo dorobit funckionality
// TODO preco ti to pada :)
