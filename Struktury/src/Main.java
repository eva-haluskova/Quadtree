import Data.CadastralObject;
import Data.RealEstate;
import QuadTree.QuadTree;
import QuadTree.Place;
import QuadTree.Coordinates;
import QuadTree.Tester.RealEstateTester;
import QuadTree.Tester.TesterForPlace;
import QuadTree.Data;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int velkost = 100;
        Random random = new Random();
        random.setSeed(30);
        QuadTree<Place> testTree = new QuadTree<Place>(0, velkost, 0, velkost,6);

        Coordinates coorsOfSearch = new Coordinates(30,60,30,80);
        TesterForPlace placeTester = new TesterForPlace(testTree);
        //placeTester.testOfSeed(4,100000,coorsOfSearch);
        //placeTester.testForSeed(2,20,coorsOfSearch);
        //placeTester.generateInsert(50000,random,coorsOfSearch);

        System.out.println("-------- SKUSKA INSER DELETE -------------");
        placeTester.testOfChangeOfDepth(3,10000, coorsOfSearch);

        System.out.println("--------- SKUSKA FIND ----------");

        QuadTree<CadastralObject> cadastralTest = new QuadTree<CadastralObject>(0, velkost, 0, velkost,6);
        RealEstateTester estateTester = new RealEstateTester(cadastralTest);
        estateTester.generateInsert(100000,random,coorsOfSearch);
        ArrayList<Data<RealEstate>> zoznam =  estateTester.generateFind(coorsOfSearch);
        for (int i = 0; i < zoznam.size(); i++) {
            System.out.println(zoznam.get(i).getData());
        }

        estateTester.generateDelete();

        System.out.println("Zatial to funguje ^-^");
    }
}

// TODO porovnavanie cez equals
/**
 * skoncila si asi tak, ze okna I,F,E,D mas zhruba hotove, potrbujes dorobit prevolavanie sprvanych metodiek.
 * Ale na to si predtym dorob:
 * utorok:
 * - urob si model:
 * - vytvorenie troch stromov
 * - vsetky metodky zo stromu
 * - praca so surdnicami!!
 * - edit
 * - oprav si PK, pracu s generikami :)
 * potom, bud:
 * - nasadis na to gui
 * alebo:
 * - SPRAVIS OPTIMALIZACIU
 */

/**
 * ESTE TREBA
 * - ukladanie a nacitavanie do suborov
 * - spravit vhodny generator
 * - poriesit suradnice
 * - poriesti generika
 * - edit
 * - urobit optimalizaciu
 * - porfilerom pokukat
 * - dorobit gui
 * - zdravie stromu
 * - spravit cadaster
 * - isto mnoho dalsieho
 */