import Data.CadastralObject;
import Data.GPS;
import Data.RealEstate;
import Data.LandParcel;

import MainLogic.CadastralObjectGenerator;
import MainLogic.CadastralObjectGenerator.*;
import QuadTree.Data;
import QuadTree.Tester.TesterForPlace;
import QuadTree.Place;
import QuadTree.Tester.RealEstateTester;
import QuadTree.Tester.LandParcelTester;
import QuadTree.QuadTree;
import QuadTree.Coordinates;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {




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
 * - AUTOMATICKE NAPLNENIE PARCIEL A TAK...
 * potom, bud:
 * - nasadis na to gui
 * alebo:
 * - SPRAVIS OPTIMALIZACIU
 */


/**
 * dneska este:
 * dorobit guja:
 *      - vytvorenie stromu
 *      - editacia
 *      - vytvorenie
 *      - find
 *      - delete
 * opravit delete
 * opravit insert
 * urobit find
 * model aj s premenov coordinates, mysli na to
 * spatna funkcia pre curadnice
 * automaticke smerniky na parceli
 *
 * ak nahodou:
 * - generator instanci
 * - porozmyslat nad ukladanim
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
// TODO gui delete all
// TODO finvoavnie nemas dobre...teda spon to co potrebujes, ze bodov to naide vsetko cez to sa to kryje
// TODO upratat pak pracu s coordniates v quadTree a quadNode







//int velkost = 100;
//    Random random = new Random();
//        random.setSeed(30);
//                QuadTree<Place> testTree = new QuadTree<Place>(0, velkost, 0, velkost,6);
//
//        Coordinates coorsOfSearch = new Coordinates(30,60,30,80);
//        TesterForPlace placeTester = new TesterForPlace(testTree);
//        //placeTester.testOfSeed(4,100000,coorsOfSearch);
//        //placeTester.testForSeed(2,20,coorsOfSearch);
//        //placeTester.generateInsert(50000,random,coorsOfSearch);
//
//        System.out.println("-------- SKUSKA INSER DELETE CHANGE OF DEPTH-------------");
//
//        placeTester.testOfChangeOfDepth(3,10000, coorsOfSearch);
//
//        System.out.println("--------- SKUSKA FIND ----------");
//
//
//        QuadTree<LandParcel> parcelTest = new QuadTree<LandParcel>(0, velkost, 0, velkost,6);
//
//        LandParcelTester parcelTester = new LandParcelTester(parcelTest);
//        parcelTester.generateInsert(100,random,coorsOfSearch);
//        ArrayList<Data<LandParcel>> zoznam2 =  parcelTester.generateFind(coorsOfSearch);
//        for (int i = 0; i < zoznam2.size(); i++) {
//        System.out.println(zoznam2.get(i).getData());
//        }
//        parcelTester.generateDelete();







//
//
//        QuadTree<CadastralObject> cadastralTest = new QuadTree<CadastralObject>(0, velkost, 0, velkost,6);
//
//        CadastralObjectTester cadastralTester = new CadastralObjectTester(parcelTest);
//        cadastralTester.generateInsert(100,random,coorsOfSearch);
//        ArrayList<Data<CadastralObject>> zoznam3 = cadastralTester.generateFind(coorsOfSearch);
//
//        System.out.println("--- list of land parcels ---");
//        for (int i = 0; i < zoznam3.size(); i++) {
//            if (zoznam3.get(i).getData().isInstanceOf() == TypeOfCadastralObject.LAND_PARCEL) {
//                System.out.println(zoznam3.get(i).getData());
//            }
//        }
//
//        System.out.println("--- list of real estates ---");
//        for (int i = 0; i < zoznam3.size(); i++) {
//            if (zoznam3.get(i).getData().isInstanceOf() == TypeOfCadastralObject.REAL_ESTATE) {
//                System.out.println(zoznam3.get(i).getData());
//            }
//        }
//
//        cadastralTester.generateDelete();
//
//
//        double x1 = random.nextDouble() * (velkost - velkost - 1);
//        double x2 = x1 + velkost;
//        double y1 = random.nextDouble() * (velkost - velkost - 1);
//        double y2 = y1 + velkost;
//
//        Coordinates coors = new Coordinates(x1, x2, y1, y2);
//        GPS one = new GPS(GPS.Latitude.NORTH,x1,GPS.Longitude.EAST,40);
//        GPS two = new GPS(GPS.Latitude.NORTH,x1,GPS.Longitude.WEST,30);
//        GPS[] par = {one, two};
//
//        RealEstate realEstate = new RealEstate("ss",par,2);
//        LandParcel newLan = new LandParcel("SS",par,3);
//        Data<LandParcel> p = new Data(newLan,coors, UUID.randomUUID());
//        Data<RealEstate> e = new Data(realEstate,coors,UUID.randomUUID());
//
//
//        ArrayList<Data<CadastralObject>> zoz = new ArrayList<>();
//       // zoz.add(newLan);
//
//        Data<CadastralObject> cad = new Data(p,coors,UUID.randomUUID());
//
//        ArrayList<Data<CadastralObject>> zoz2 = new ArrayList<>();
//        zoz2.add(cad);
//
//        ArrayList<Data<? extends CadastralObject>> z = new ArrayList<>();
//        z.add(p);
//        z.add(e);


/*
        GPS gpsOne = new GPS(GPS.Latitude.NORTH, 30,GPS.Longitude.WEST,40);
        GPS gpsTwo = new GPS(GPS.Latitude.SOUTH, 20,GPS.Longitude.WEST,10);
        GPS[] listOfRootGPS = {gpsTwo,gpsOne};
        MapCoordinates testMap = new MapCoordinates(listOfRootGPS);

        Coordinates coors = testMap.getCoordinatesOfRoot();
        System.out.println("  x1 = " + coors.getLowerX() +
                " ,x2 = " + coors.getUpperX() +
                " ,y1 = " + coors.getLowerY() +
                " ,y2 = " + coors.getUpperY());

        GPS gpsOne1 = new GPS(GPS.Latitude.NORTH, 20,GPS.Longitude.WEST,10);
        GPS gpsTwo1 = new GPS(GPS.Latitude.SOUTH, 5,GPS.Longitude.EAST,5);
        GPS[] o = {gpsOne1,gpsTwo1};

        Coordinates coors1 = testMap.getCoordinatesValue(o);
        System.out.println("  x1 = " + coors1.getLowerX() +
                " ,y1 = " + coors1.getLowerY() +
                " ,x2 = " + coors1.getUpperX() +
                " ,y2 = " + coors1.getUpperY());

        GPS[] gpss = testMap.getGPSValue(coors1);
        System.out.println("lat: " + gpss[0].getLatitude() +
                            ", pos: " + gpss[0].getLatitudePosition() +
                            ", long: " + gpss[0].getLongitude() +
                            ", pos: " + gpss[0].getLongitudePosition());
        System.out.println("lat: " + gpss[1].getLatitude() +
                ", pos: " + gpss[1].getLatitudePosition() +
                ", long: " + gpss[1].getLongitude() +
                ", pos: " + gpss[1].getLongitudePosition());

*/

//        GPS rlto = new GPS(GPS.Latitude.NORTH,50, GPS.Longitude.WEST,60);
//        GPS rltt = new GPS(GPS.Latitude.SOUTH,60,GPS.Longitude.EAST,70);
//        GPS[] rls = {rlto,rltt};
//
//        GPS lpto = new GPS(GPS.Latitude.NORTH,90, GPS.Longitude.WEST,60);
//        GPS lptt = new GPS(GPS.Latitude.SOUTH,60,GPS.Longitude.EAST,80);
//        GPS[] lps = {lpto,lptt};
//
//        GPS oneone = new GPS(GPS.Latitude.NORTH,20, GPS.Longitude.WEST,30);
//        GPS onetwo = new GPS(GPS.Latitude.SOUTH,40,GPS.Longitude.EAST,10);
//        GPS[] one = {oneone,onetwo};
//
//        GPS twoone = new GPS(GPS.Latitude.NORTH,30, GPS.Longitude.WEST,10);
//        GPS towtwo = new GPS(GPS.Latitude.SOUTH,10,GPS.Longitude.EAST,10);
//        GPS[] two = {twoone,towtwo};
//
//        GPS threeone = new GPS(GPS.Latitude.NORTH,40, GPS.Longitude.WEST,10);
//        GPS threetwo = new GPS(GPS.Latitude.SOUTH,30,GPS.Longitude.EAST,20);
//        GPS[] three = {threeone,threetwo};
//
//        GPS fourone = new GPS(GPS.Latitude.NORTH,20, GPS.Longitude.WEST,10);
//        GPS fourtwo = new GPS(GPS.Latitude.SOUTH,50,GPS.Longitude.EAST,5);
//        GPS[] four = {fourone,fourtwo};
//
//        Cadaster cadaster = new Cadaster();
//        cadaster.createRealEstateTree(rls,3);
//        cadaster.createLandParcelTree(lps,4);
//
//        cadaster.insertRealEstate(2,rls,"coze");
//        cadaster.insertLandParcel(3,two,"haa");
//        cadaster.insertLandParcel(4,three,"eeee");
//        ArrayList<Data<? extends CadastralObject>>  zoz = cadaster.findInArea(rls);
//        for (int i = 0; i < zoz.size(); i++) {
//            System.out.println(zoz.get(i));
//        }
/*
        GPS oneone = new GPS(GPS.Latitude.NORTH,20, GPS.Longitude.WEST,30);
        GPS onetwo = new GPS(GPS.Latitude.SOUTH,40,GPS.Longitude.EAST,10);
        GPS[] one = {oneone,onetwo};

        GPS twoone = new GPS(GPS.Latitude.NORTH,30, GPS.Longitude.WEST,10);
        GPS towtwo = new GPS(GPS.Latitude.SOUTH,10,GPS.Longitude.EAST,10);
        GPS[] two = {twoone,towtwo};

        GPS threeone = new GPS(GPS.Latitude.NORTH,40, GPS.Longitude.WEST,10);
        GPS threetwo = new GPS(GPS.Latitude.SOUTH,30,GPS.Longitude.EAST,20);
        GPS[] three = {threeone,threetwo};

        CadastralObjectGenerator cg = new CadastralObjectGenerator();

        ArrayList<CadastralObject> lista = cg.generateObjects(GenerateOption.LAND_PARCEL,30,10,one);
        for (int i = 0; i < lista.size(); i++) {
        System.out.println(lista.get(i).toString());
        GPS[] gpss = lista.get(i).getGPSCoordinates();
        System.out.println("    lat: " + gpss[0].getLatitude() +
        ", pos: " + gpss[0].getLatitudePosition() +
        ", long: " + gpss[0].getLongitude() +
        ", pos: " + gpss[0].getLongitudePosition());
        System.out.println("    lat: " + gpss[1].getLatitude() +
        ", pos: " + gpss[1].getLatitudePosition() +
        ", long: " + gpss[1].getLongitude() +
        ", pos: " + gpss[1].getLongitudePosition());
        }

        ArrayList<CadastralObject> listb = cg.generateObjects(CadastralObjectGenerator.GenerateOption.REAL_ESTATE,30,4,two);
        for (int i = 0; i < listb.size(); i++) {
        System.out.println(listb.get(i).toString());
        GPS[] gpss = listb.get(i).getGPSCoordinates();
        System.out.println("    lat: " + gpss[0].getLatitude() +
        ", pos: " + gpss[0].getLatitudePosition() +
        ", long: " + gpss[0].getLongitude() +
        ", pos: " + gpss[0].getLongitudePosition());
        System.out.println("    lat: " + gpss[1].getLatitude() +
        ", pos: " + gpss[1].getLatitudePosition() +
        ", long: " + gpss[1].getLongitude() +
        ", pos: " + gpss[1].getLongitudePosition());
        }

        ArrayList<CadastralObject> listc =cg.generateObjects(CadastralObjectGenerator.GenerateOption.BOTH,30,8,three);
        for (int i = 0; i < listc.size(); i++) {
        System.out.println(listc.get(i).toString());
        GPS[] gpss = listc.get(i).getGPSCoordinates();
        System.out.println("    lat: " + gpss[0].getLatitude() +
        ", pos: " + gpss[0].getLatitudePosition() +
        ", long: " + gpss[0].getLongitude() +
        ", pos: " + gpss[0].getLongitudePosition());
        System.out.println("    lat: " + gpss[1].getLatitude() +
        ", pos: " + gpss[1].getLatitudePosition() +
        ", long: " + gpss[1].getLongitude() +
        ", pos: " + gpss[1].getLongitudePosition());
        }
*/
//        int rozsahx = 30;
//        int rozsahy = 20;
//        double velkostObjektu = 3;

//        Random random = new Random();
//
//        double x1 = random.nextDouble() * (rozsahx - velkostObjektu);
//        double x2 = x1 + velkostObjektu;
//        double y1 = random.nextDouble() * (rozsahy - velkostObjektu);
//        double y2 = y1 + velkostObjektu;
//
//        System.out.println(x1 + " " + x2 + " " + y1 + " " + y2);
//
//
//        System.out.println(random.nextDouble());
//        System.out.println(random.nextDouble());
//        System.out.println(random.nextDouble());

//        for (int i = 0; i < lista.size(); i++) {
//            System.out.println(lista.get(i).toString());
//            GPS[] gpss = lista.get(i).getGPSCoordinates();
//            System.out.println("    lat: " + gpss[0].getLatitude() +
//                    ", pos: " + gpss[0].getLatitudePosition() +
//                    ", long: " + gpss[0].getLongitude() +
//                    ", pos: " + gpss[0].getLongitudePosition());
//            System.out.println("    lat: " + gpss[1].getLatitude() +
//                    ", pos: " + gpss[1].getLatitudePosition() +
//                    ", long: " + gpss[1].getLongitude() +
//                    ", pos: " + gpss[1].getLongitudePosition());
//        }
