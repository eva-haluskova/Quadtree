import Data.GPS;
import GUI.Cadaster;
import QuadTree.Coordinates;

import java.util.Random;


public class Main {

    public static void main(String[] args) {
        int maxDepth = 5;
        double sizeOfObjets = 10;
        int numberOfObjects = 100;
        int width = 100;
        int height = 100;
        Random random = new Random();

        Coordinates coorsForSearch = new Coordinates(20,40,30,50);

        GPS rlto = new GPS(GPS.Latitude.NORTH,100, GPS.Longitude.WEST,100);
        GPS rltt = new GPS(GPS.Latitude.SOUTH,100,GPS.Longitude.EAST,100);
        GPS[] tree = {rlto,rltt};

        GPS lpto = new GPS(GPS.Latitude.NORTH,90, GPS.Longitude.WEST,60);
        GPS lptt = new GPS(GPS.Latitude.NORTH,60,GPS.Longitude.WEST,40);
        GPS[] zero = {lpto,lptt};

        GPS oneone = new GPS(GPS.Latitude.NORTH,80, GPS.Longitude.WEST,55);
        GPS onetwo = new GPS(GPS.Latitude.NORTH,50,GPS.Longitude.EAST,40);
        GPS[] one = {oneone,onetwo};

        GPS twoone = new GPS(GPS.Latitude.NORTH,95, GPS.Longitude.WEST,70);
        GPS towtwo = new GPS(GPS.Latitude.NORTH,40,GPS.Longitude.WEST,35);
        GPS[] two = {twoone,towtwo};

        GPS threeone = new GPS(GPS.Latitude.NORTH,85, GPS.Longitude.WEST,55);
        GPS threetwo = new GPS(GPS.Latitude.NORTH,65,GPS.Longitude.WEST,45);
        GPS[] three = {threeone,threetwo};

        GPS fourone = new GPS(GPS.Latitude.NORTH,70, GPS.Longitude.WEST,55);
        GPS fourtwo = new GPS(GPS.Latitude.NORTH,65,GPS.Longitude.WEST,55);
        GPS[] four = {fourone,fourtwo};

        GPS threeone8 = new GPS(GPS.Latitude.NORTH,20, GPS.Longitude.WEST,20);
        GPS threetwo8 = new GPS(GPS.Latitude.NORTH,10,GPS.Longitude.WEST,10);
        GPS[] five = {threeone8,threetwo8};

        GPS fourone9 = new GPS(GPS.Latitude.SOUTH,70, GPS.Longitude.EAST,55);
        GPS fourtwo9 = new GPS(GPS.Latitude.SOUTH,65,GPS.Longitude.EAST,55);
        GPS[] six = {fourone9,fourtwo9};

        Cadaster cadaster = new Cadaster();
        cadaster.createLandParcelTree(tree,maxDepth);
        cadaster.createRealEstateTree(tree,maxDepth);
        cadaster.insertRealEstate(56,one,"raz");
        cadaster.insertRealEstate(54,two,"dva");
        cadaster.insertRealEstate(57,three,"tri");
        cadaster.insertRealEstate(51,four,"styri");

        cadaster.insertRealEstate(69,five,"edem");
        cadaster.insertRealEstate(78,six,"reaz");
        cadaster.insertLandParcel(52,zero,"xc");


//        cadaster.generateObjects(GenerateOption.LAND_PARCEL,numberOfObjects, sizeOfObjets,tree);
//        cadaster.generateObjects(GenerateOption.REAL_ESTATE,numberOfObjects,sizeOfObjets,tree);
//        cadaster.generateObjects(GenerateOption.BOTH,numberOfObjects,sizeOfObjets-1,tree);

//        int maxDepth = 5;
//        double sizeOfObjets = 10;
//        int numberOfObjects = 100;
//        int width = 100;
//        int height = 100;
//        Random random = new Random();
//        random.setSeed(30);
//        QuadTree<Place> testTree = new QuadTree<Place>(0, width, 0, height,maxDepth);
//
//        Coordinates coorsOfSearch = new Coordinates(30,60,30,80);
//        TesterForPlace placeTester = new TesterForPlace(testTree);
//        //placeTester.testOfSeed(4,100000,coorsOfSearch);
//        //placeTester.testForSeed(2,20,coorsOfSearch);
//        placeTester.generateInsert(20000,sizeOfObjets,random,coorsOfSearch);
//
//        placeTester.generateFind(coorsOfSearch);
//
//        System.out.println(testTree.getNumberOfItems());
//        testTree.tryToOptimalizeTwoObjects();
//
//        System.out.println("Optimalized");
//        placeTester.generateFind(coorsOfSearch);
//        System.out.println(testTree.getNumberOfItems());
//        placeTester.generateDelete();

        System.out.println("Zatial to funguje ^-^");
    }

}






/*
upravit strom - zmenit vysku - zmenit rozsah
automaticky naplnit
optimalizacia aj po upraveni
takze aj hrana je includnuta v inom objekte
osetrit aby ked zmenim surqadnice na mimo stromun tak sa mi nezmenina
 */

// TODO tie suradnice pooondate pories
// TODO suradnice dorob aby dobre bolo
// TODO spatna funkcia pre curadnice
// TODO praca so surdnicami!!
// TODO over si ci to doublevanie ma zmysel pri < >
// todo cooridnates equals bigger tnat least poresit!!!
// todo mapovanie jednej suradnice do coordinates
// todo porovnavanie doublov

// TODO oprav finde
// TODO insert aby neinsertol existujuce
// TODO overenie jednotlivych operacii
// TODO overenie find
// TODO finvoavnie nemas dobre...teda spon to co potrebujes, ze bodov to naide vsetko cez to sa to kryje

// TODO pomenit arraylisty
// TODO z arrayov na inde polia inteligentnejise

// TODO AUTOMATICKE NAPLNENIE PARCIEL A TAK...

// TODO SPRAVIS OPTIMALIZACIU

// TODO zaujimavy poznaok o zmene velkosti ako ked som zvacsila velkost sa mi pekne krasne zvysila vyska stromu pri vlastne 10satine dat...teda dat na desasitine velkosti
// TODO MUST!!! change your arrays at least to linked list or sometnig more efficient!!!!
// TODO pri mazanai aj znizovanie stupna stromu?
// TODO think about if is needed in method findAppropriateNode that one if in case current isnt leaf.!
// TODO pripadne si refactorni insert na krajsi
// TODO musis pozriest tie vyhladavanie - jednotlivo land parceli a jednotlvo estati. to iste pri finde, delete, edite. find area vsetko, find bodovo jednotlivo

// TODO sorry jako, ale v delete nemas osetrenu zmenu hlbky stromu..teda akoze iba aktualizovanie ciselok, vis, ked tak potom oprav aj ten test na to
// TODO tu zmenu vysky zvas si to ukladanie levelu nodu!! ulahcilo by ti to zivot, dobra rada nad zlato to je!!!

// TODO to gujo mas zle deletovanie a tak podobne vsey objekty...musis si vybzerat iba konkretne
// TODO gujo dorobit funckionality

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

//        int maxDepth = 5;
//        double sizeOfObjets = 10;
//        int numberOfObjects = 100;
//        int width = 100;
//        int height = 100;
//        Random random = new Random();
//        Coordinates coorsOfTree = new Coordinates(0,20,0,20);
//
//        QuadTree quadTree = new QuadTree(coorsOfTree, maxDepth);
//        TesterForPlace treeTester = new TesterForPlace(quadTree);
//
//
//        Coordinates coorsForAreaSearch = new Coordinates(15,15,18,18);
//        Coordinates coorsForSimpleSearch = new Coordinates(30,40);
//
//        // ---- generate insert ----
//        treeTester.generateInsert(numberOfObjects,sizeOfObjets,random,coorsForAreaSearch);
//        // ---- generate simple find ----
//        treeTester.generateSimpleFind(coorsForAreaSearch);


//
//        GPS gpsOne = new GPS(GPS.Latitude.NORTH, 100,GPS.Longitude.WEST,100);
//        GPS gpsTwo = new GPS(GPS.Latitude.SOUTH, 100,GPS.Longitude.EAST,100);
//        GPS[] listOfRootGPS = {gpsOne,gpsTwo};
//        MapCoordinates testMap = new MapCoordinates(listOfRootGPS);
//        System.out.println("root");
//        System.out.println("lat: " + listOfRootGPS[0].getLatitude() +
//                ", pos: " + listOfRootGPS[0].getLatitudePosition() +
//                ", long: " + listOfRootGPS[0].getLongitude() +
//                ", pos: " + listOfRootGPS[0].getLongitudePosition());
//        System.out.println("lat: " + listOfRootGPS[1].getLatitude() +
//                ", pos: " + listOfRootGPS[1].getLatitudePosition() +
//                ", long: " + listOfRootGPS[1].getLongitude() +
//                ", pos: " + listOfRootGPS[1].getLongitudePosition());
//
//
//
//        Coordinates coors = testMap.getCoordinatesOfRoot();
//        System.out.println("  x1 = " + coors.getLowerX() +
//                " ,x2 = " + coors.getUpperX() +
//                " ,y1 = " + coors.getLowerY() +
//                " ,y2 = " + coors.getUpperY());
//
//        GPS gpsOne1 = new GPS(GPS.Latitude.NORTH, 80,GPS.Longitude.WEST,80);
//        GPS gpsTwo1 = new GPS(GPS.Latitude.SOUTH, 80,GPS.Longitude.EAST,80);
//        GPS[] o = {gpsOne1,gpsTwo1};
//
//        System.out.println("map");
//        Coordinates coors1 = testMap.getCoordinatesValue(o);
//        System.out.println("  x1 = " + coors1.getLowerX() +
//                " ,y1 = " + coors1.getLowerY() +
//                " ,x2 = " + coors1.getUpperX() +
//                " ,y2 = " + coors1.getUpperY());
//
//        GPS[] gpss = testMap.getGPSValue(coors1);
//        System.out.println("lat: " + gpss[0].getLatitude() +
//                ", pos: " + gpss[0].getLatitudePosition() +
//                ", long: " + gpss[0].getLongitude() +
//                ", pos: " + gpss[0].getLongitudePosition());
//        System.out.println("lat: " + gpss[1].getLatitude() +
//                ", pos: " + gpss[1].getLatitudePosition() +
//                ", long: " + gpss[1].getLongitude() +
//                ", pos: " + gpss[1].getLongitudePosition());
//


//        // ---- generate find ----
//        treeTester.generateFind(coorsForAreaSearch);
//        // -- generate delete ----
//        treeTester.generateDelete();


//
//        for (int j = 0; j < 1; j++) {
//            int pocetStromou = 100;
//            double premHlbka = 0;
//            for (int i = 0; i < 1; i++) {
//                QuadTree<Place> strom = new QuadTree(0, width, 0, height, maxDepth);
//                TesterForPlace tester = new TesterForPlace(strom);
//                strom.insert(new Data<Place>(new Place("hah"),new Coordinates(1,1,1,1), UUID.randomUUID()));
//                strom.insert(new Data<Place>(new Place("hah"),new Coordinates(99,99,99,99), UUID.randomUUID()));
//                strom.insert(new Data<Place>(new Place("hah"),new Coordinates(99,99,1,1), UUID.randomUUID()));
//                strom.insert(new Data<Place>(new Place("hah"),new Coordinates(1,1,99,99), UUID.randomUUID()));
//                strom.insert(new Data<Place>(new Place("hah"),new Coordinates(1,1,55,55), UUID.randomUUID()));
//               // System.out.println(strom.health());
//
//
//                //tester.generateInsert(numberOfObjects, sizeOfObjets, random, coorsForAreaSearch);
//                //System.out.println(strom.getDepth());
//                System.out.println("Health of tree is: " + strom.health() + " co je isto spatne :)");
//                premHlbka += strom.getDepth();
//            }
//            System.out.println("pri velkosti objetkov: " + sizeOfObjets + " je priem hlbka " +premHlbka/pocetStromou);
//            sizeOfObjets +=2;
// }

//        ReadWriterOfTree workWitFile = new ReadWriterOfTree(quadTree);
//        workWitFile.readData("hola.txt");




/*

        int maxDepth = 5;
        double sizeOfObjets = 10;
        int numberOfObjects = 100;
        int width = 100;
        int height = 100;
        Random random = new Random();

        Coordinates coorsForSearch = new Coordinates(20,40,30,50);

        GPS rlto = new GPS(GPS.Latitude.NORTH,50, GPS.Longitude.WEST,50);
        GPS rltt = new GPS(GPS.Latitude.SOUTH,50,GPS.Longitude.EAST,50);
        GPS[] tree = {rlto,rltt};

        GPS lpto = new GPS(GPS.Latitude.NORTH,90, GPS.Longitude.WEST,60);
        GPS lptt = new GPS(GPS.Latitude.SOUTH,60,GPS.Longitude.EAST,80);
        GPS[] zero = {lpto,lptt};

        GPS oneone = new GPS(GPS.Latitude.NORTH,20, GPS.Longitude.WEST,30);
        GPS onetwo = new GPS(GPS.Latitude.SOUTH,40,GPS.Longitude.EAST,10);
        GPS[] one = {oneone,onetwo};

        GPS twoone = new GPS(GPS.Latitude.NORTH,30, GPS.Longitude.WEST,10);
        GPS towtwo = new GPS(GPS.Latitude.SOUTH,10,GPS.Longitude.EAST,10);
        GPS[] two = {twoone,towtwo};

        GPS threeone = new GPS(GPS.Latitude.NORTH,40, GPS.Longitude.WEST,10);
        GPS threetwo = new GPS(GPS.Latitude.SOUTH,30,GPS.Longitude.EAST,20);
        GPS[] three = {threeone,threetwo};

        GPS fourone = new GPS(GPS.Latitude.NORTH,20, GPS.Longitude.WEST,10);
        GPS fourtwo = new GPS(GPS.Latitude.SOUTH,50,GPS.Longitude.EAST,5);
        GPS[] four = {fourone,fourtwo};

        Cadaster cadaster = new Cadaster();
        cadaster.createLandParcelTree(tree,maxDepth);
        cadaster.createRealEstateTree(tree,maxDepth);
        cadaster.generateObjects(GenerateOption.LAND_PARCEL,numberOfObjects, sizeOfObjets,tree);
        cadaster.generateObjects(GenerateOption.REAL_ESTATE,numberOfObjects,60,tree);
        cadaster.generateObjects(GenerateOption.BOTH,numberOfObjects,sizeOfObjets-1,tree);


        ReadWriterOfTree fileWork = new ReadWriterOfTree(cadaster.returnRealEstateTree(),cadaster.returnLandParcelTree(),cadaster.getRealEstateTreeGPS(),cadaster.getLandParcelTreeGPS());
        //fileWork.writeData("hola.txt");
        fileWork.readData("hola.txt");

        QuadTree<RealEstate> treeR = fileWork.getTreeRealEstate();
        QuadTree<LandParcel> treeL = fileWork.getTreeLandParcel();

        LandParcelTester tester = new LandParcelTester(treeL);

 */
//        ArrayList<Data<? extends CadastralObject>> findedData = cadaster.findInArea(tree);
//        if (findedData.size() == 3 * numberOfObjects) {
//            System.out.println("findlo to dobre");
//        }
//
//
//
//        ArrayList<Data<? extends CadastralObject>> findedData2 = cadaster.findInArea(four);
//        for (int i = 0; i < findedData2.size(); i++) {
//            System.out.println(findedData2.get(i).getData());
//        }
//        int n = findedData.size();
//        int m = findedData2.size();
//        for (int i = 0; i < findedData2.size(); i++) {
//            if (findedData2.get(i).getData().isInstanceOf().equals(CadastralObject.TypeOfCadastralObject.LAND_PARCEL)) {
//                cadaster.deleteLandParcel((Data<LandParcel>) findedData2.get(i));
//            } else {
//                cadaster.deleteRealEstate((Data<RealEstate>) findedData2.get(i));
//            }
//        }
//
//        ArrayList<Data<? extends CadastralObject>> findedData3 = cadaster.findInArea(tree);
//        if (findedData3.size() == (n - m)) {
//            System.out.println("dobre to deletlo");
//        }
//
//
//        Data<? extends CadastralObject> datoToEdit1 = findedData3.get(4);
//        Data<? extends CadastralObject> datoToEdit2 = findedData3.get(7);
//        System.out.println("Dato na edit jedna: " + datoToEdit1.getData().toString());
//        System.out.println("Dato na edit dva: " + datoToEdit2.getData().toString());
//
//        if (datoToEdit1.getData().isInstanceOf().equals(CadastralObject.TypeOfCadastralObject.LAND_PARCEL)) {
//            cadaster.editLandParcelData((Data<LandParcel>) datoToEdit1,40, two, "novy popisok");
//        } else {
//            cadaster.editRealEstateData((Data<RealEstate>) datoToEdit1,50,two,datoToEdit1.getData().getDescription());
//        }
//
//
//        if (datoToEdit2.getData().isInstanceOf().equals(CadastralObject.TypeOfCadastralObject.LAND_PARCEL)) {
//            cadaster.editLandParcelData((Data<LandParcel>) datoToEdit2,40, datoToEdit2.getData().getGPSCoordinates(), "novy popisok");
//        } else {
//            cadaster.editRealEstateData((Data<RealEstate>) datoToEdit2,80,datoToEdit2.getData().getGPSCoordinates(),"novy salal HUU");
//        }
//
//        System.out.println("upravene dato jedna: " + datoToEdit1.getData().toString());
//        System.out.println("upravene dato dva: " + datoToEdit2.getData().toString());




//    int distinctValues = 6;
//            //public List<Integer> generateList(int distinctValues) {
//                int length = (int) Math.pow(2, distinctValues - 1) + 1;
//                List<Integer> result = new ArrayList<>();
//
//                int value = 1;
//                for (int i = 0; i < length; i++) {
//                    result.add(value);
//                    value++;
//                    if (value > distinctValues) {
//                        value = 1; // Reset to 1 when it exceeds the distinct values
//                    }
//                }
//
//              //  return result;
//            //}
//        int length = (int) Math.pow(2, distinctValues - 1) + 1;
//        List<Integer> result = new ArrayList<>();
//
//        int current = 1;
//        for (int i = 0; i < length; i++) {
//            result.add(current);
//            if (i % 2 == 0) {
//                current += 5;
//            } else {
//                current -= 3;
//            }
//        }
//        int length = (int) Math.pow(2, distinctValues - 1) + 1;
//        List<Integer> result = new ArrayList<>();
//
//        for (int i = 0; i < length; i++) {
//
//                int index = i;
//                int value = 1;
//                while (index > 0) {
//                    value++;
//                    index /= 2;
//                }
//            result.add(value);
//        }



// int distinctValues = 6;
// List<Integer> generatedList = generateList(distinctValues);

// System.out.println("Generated List: " + result);









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
