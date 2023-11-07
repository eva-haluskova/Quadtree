import Data.CadastralObject;
import Data.RealEstate;
import Data.MapCoordinates;
import Data.GPS;
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

//        int velkost = 100;
//        Random random = new Random();
//        random.setSeed(30);
//        QuadTree<Place> testTree = new QuadTree<Place>(0, velkost, 0, velkost,6);
//
//        Coordinates coorsOfSearch = new Coordinates(30,60,30,80);
//        TesterForPlace placeTester = new TesterForPlace(testTree);
//        //placeTester.testOfSeed(4,100000,coorsOfSearch);
//        //placeTester.testForSeed(2,20,coorsOfSearch);
//        //placeTester.generateInsert(50000,random,coorsOfSearch);
//
//        System.out.println("-------- SKUSKA INSER DELETE -------------");
//        placeTester.testOfChangeOfDepth(3,10000, coorsOfSearch);
//
//        System.out.println("--------- SKUSKA FIND ----------");
//
//        QuadTree<CadastralObject> cadastralTest = new QuadTree<CadastralObject>(0, velkost, 0, velkost,6);
//        RealEstateTester estateTester = new RealEstateTester(cadastralTest);
//        estateTester.generateInsert(100000,random,coorsOfSearch);
//        ArrayList<Data<RealEstate>> zoznam =  estateTester.generateFind(coorsOfSearch);
//        for (int i = 0; i < zoznam.size(); i++) {
//            System.out.println(zoznam.get(i).getData());
//        }
//
//        estateTester.generateDelete();


        GPS gpsOne = new GPS(GPS.Latitude.NORTH, 30,GPS.Longitude.WEST,40);
        GPS gpsTwo = new GPS(GPS.Latitude.SOUTH, 20,GPS.Longitude.EAST,10);
        GPS[] listOfRootGPS = {gpsTwo,gpsOne};
        MapCoordinates testMap = new MapCoordinates(listOfRootGPS);

        Coordinates coors = testMap.getCoordinatesOfRoot();
        System.out.println("  x1 = " + coors.getLowerX() +
                " ,x2 = " + coors.getUpperX() +
                " ,y1 = " + coors.getLowerY() +
                " ,y2 = " + coors.getUpperY());

        GPS gpsTwo1 = new GPS(GPS.Latitude.NORTH, 30,GPS.Longitude.EAST,5);
        GPS gpsOne1 = new GPS(GPS.Latitude.NORTH, 20,GPS.Longitude.EAST,10);
        GPS[] o = {gpsOne1,gpsTwo1};

        Coordinates coors1 = testMap.getCoordinatesValue(gpsOne1,gpsTwo1);
        System.out.println("  x1 = " + coors1.getLowerX() +
                " ,y1 = " + coors1.getLowerY() +
                " ,x2 = " + coors1.getUpperX() +
                " ,y2 = " + coors1.getUpperY());





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



// TODO vdase pozjednodusovat vyrazy list.get(0). a zaroevn list.remove(0)....da sa to, vis?
// TODO over si ci to doublevanie ma zmysel pri < >
// TODO over si ci niekde neporusujes zapuzdrenie, getovanie zoznamov cez kopiu!!!
// TODO with index access method pay attention if index exists?
// TODO zaujimavy poznaok o zmene velkosti ako ked som zvacsila velkost sa mi pekne krasne zvysila vyska stromu pri vlastne 10satine dat...teda dat na desasitine velkosti
// TODO MUST!!! change your arrays at least to linked list or sometnig more efficient!!!!
// TODO pri mazanai aj znizovanie stupna stromu?
// TODO think about if is needed in method findAppropriateNode that one if in case current isnt leaf.!
// TODO pripadne si refactorni insert na krajsi
// TODO returvnovanie tych onych. Porusenie zapuzdrenia? (DUfam ze nie)(salala)RESULT
// TODO dories ten generator poondiaty
// TODO refactorni si cooridnates a s tym vsetky suvisiace metody ktore mas vsade po triedach...to tam nema co robit, ked to porovnana iba dooridnates!!!
// TODO refactorni cely node, isto vela sa da pomazat a dat do coorinates a tak
// TODO ZAPUZDRENIE!!!
// TODO sorry jako, ale v delete nemas osetrenu zmenu hlbky stromu..teda akoze iba aktualizovanie ciselok, vis, ked tak potom oprav aj ten test na to
// TODO tu zmenu vysky zvas si to ukladanie levelu nodu!! ulahcilo by ti to zivot, dobra rada nad zlato to je!!!
