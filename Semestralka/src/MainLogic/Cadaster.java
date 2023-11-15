package MainLogic;

import Data.CadastralObject;
import Data.LandParcel;
import Data.RealEstate;
import Data.GPS;
import Data.MapCoordinates;

import QuadTree.QuadTree;
import QuadTree.Coordinates;
import QuadTree.Data;
import QuadTree.ReadWriterOfTree;

import static MainLogic.CadastralObjectGenerator.GenerateOption.LAND_PARCEL;
import static MainLogic.CadastralObjectGenerator.GenerateOption.REAL_ESTATE;

import java.util.ArrayList;
import java.util.UUID;


/**
 * Class function is to store data and ensure work with them. Its purpose is act like
 * "Model" in MVC model, so instance of this class is created in controller which
 * collaborate with model and call function from this storage according to changes in view.
 */
public class Cadaster {

    private MapCoordinates mapEstateTree;
    private MapCoordinates mapParcelTree;
    private final CadastralObjectGenerator generator;
    private QuadTree<LandParcel> landParcelQuadTree;
    private QuadTree<RealEstate> realEstateQuadTree;
    private ReadWriterOfTree readWriterOfTree;

    // TODO needed?
    private GPS[] realEstateTreeGPS;
    private GPS[] landParcelTreeGPS;

    public Cadaster() {


        GPS gpsOne = new GPS(GPS.Latitude.NORTH, 100,GPS.Longitude.WEST,100);
        GPS gpsTwo = new GPS(GPS.Latitude.SOUTH, 100,GPS.Longitude.EAST,100);
        GPS[] listOfRootGPS = {gpsOne,gpsTwo};

        GPS gpsOne4 = new GPS(GPS.Latitude.NORTH, 100,GPS.Longitude.WEST,100);
        GPS gpsTwo4 = new GPS(GPS.Latitude.SOUTH, 100,GPS.Longitude.EAST,100);
        GPS[] listOfRootGPSe = {gpsOne4,gpsTwo4};

        this.generator = new CadastralObjectGenerator();

        this.landParcelTreeGPS = listOfRootGPS;
        this.mapParcelTree = new MapCoordinates(listOfRootGPS);
        this.landParcelQuadTree = new QuadTree<LandParcel>(this.mapParcelTree.getCoordinatesValue(listOfRootGPS),5);
        System.out.println("Land Parcel tree is created!");

        this.mapEstateTree = new MapCoordinates(listOfRootGPS);
        this.realEstateTreeGPS = listOfRootGPS;
        this.realEstateQuadTree = new QuadTree<RealEstate>(this.mapEstateTree.getCoordinatesValue(listOfRootGPSe),5);
        System.out.println("Real Estate tree is created!");

        this.generateObjects(REAL_ESTATE,500,50,listOfRootGPS);
        this.generateObjects(LAND_PARCEL,500,10,listOfRootGPSe);

    }

    /**
     * methods for creating trees
     */
    public void createLandParcelTree(GPS[] parCoordinates, int parMaxDepth) {
        if (this.landParcelQuadTree == null) {
            this.landParcelTreeGPS = parCoordinates;
            this.mapParcelTree = new MapCoordinates(parCoordinates);
            this.landParcelQuadTree = new QuadTree<LandParcel>(this.mapParcelTree.getCoordinatesValue(parCoordinates), parMaxDepth);
            System.out.println("Land Parcel tree is created!");
        } else {
            System.out.println("Sorry, tree is already created!");
        }
    }

    public void createRealEstateTree(GPS[] parCoordinates, int parMaxDepth) {
        if (this.realEstateQuadTree == null) {
            this.realEstateTreeGPS = parCoordinates;
            this.mapEstateTree = new MapCoordinates(parCoordinates);
            this.realEstateQuadTree = new QuadTree<RealEstate>(this.mapEstateTree.getCoordinatesValue(parCoordinates),parMaxDepth);
            System.out.println("Real Estate tree is created!");
        } else {
            System.out.println("Sorry, tree is already created!");
        }
    }

    /**
     * methods for creating and inserting data
     */
    public void insertLandParcel(int parParcelNumber, GPS[] parCoordinates, String parDescription) {
        LandParcel newLandParcel = new LandParcel(parDescription,parCoordinates, parParcelNumber);
        Data<LandParcel> dataToInsert = new Data(newLandParcel,this.mapParcelTree.getCoordinatesValue(parCoordinates),UUID.randomUUID());
        this.fillLandParcelWithBelongingRealEstates(dataToInsert);
        this.landParcelQuadTree.insert(dataToInsert);
        for (int i = 0; i < dataToInsert.getData().getBelongingRealEstates().size(); i++) {
            System.out.println(dataToInsert.getData().getBelongingRealEstates().get(i).toString());
        }
        //System.out.println("Data land parcel: " + newLandParcel.toString() + " is created!");
    }

    public void insertRealEstate(int parSerialNumber, GPS[] parCoordinates, String parDescription) {
        RealEstate newRealEstate = new RealEstate(parDescription,parCoordinates,parSerialNumber);
        Data<RealEstate> dataToInsert = new Data(newRealEstate,this.mapEstateTree.getCoordinatesValue(parCoordinates),UUID.randomUUID());
        this.fillRealEstateWithBelongingLandParcels(dataToInsert);
        this.realEstateQuadTree.insert(dataToInsert);
        //System.out.println("Data real estate: " + newRealEstate.toString() + " is created!");
    }


    /**
     * methods for editing data
     */
    // TODO you probably know and shoud use there below your birliant cadastralObject to make it less of code :)
    public void editLandParcelData(Data<LandParcel> dataToEdit, int parParcelNumber, GPS[] parCoordinates, String parDescription) {

        if (parParcelNumber != dataToEdit.getData().getParcelNumber()) {
            dataToEdit.getData().setParcelNumber(parParcelNumber);
        }
        if (!parDescription.equals(dataToEdit.getData().getDescription())) {
            dataToEdit.getData().setDescription(parDescription);
        }
        if (!parCoordinates.equals(dataToEdit.getData().getGPSCoordinates())) {
            dataToEdit.getData().setGPSCoordinates(parCoordinates);
            this.landParcelQuadTree.edit(dataToEdit,this.mapParcelTree.getCoordinatesValue(parCoordinates));
        }
        System.out.println("Edited parcel data");
    }

    public void editRealEstateData(Data<RealEstate> dataToEdit, int parSerialNumber, GPS[] parCoordinates, String parDescription) {
        if (parSerialNumber != dataToEdit.getData().getSerialNumber()) {
            dataToEdit.getData().setSerialNumber(parSerialNumber);
        }
        if (!parDescription.equals(dataToEdit.getData().getDescription())) {
            dataToEdit.getData().setDescription(parDescription);
        } if (!parCoordinates.equals(dataToEdit.getData().getGPSCoordinates())) {
            dataToEdit.getData().setGPSCoordinates(parCoordinates);
            this.realEstateQuadTree.edit(dataToEdit,this.mapEstateTree.getCoordinatesValue(parCoordinates));
        }
        System.out.println("edited real estate data");
    }

    /**
     * methods for deleting data
     */
    public void deleteLandParcel(Data<LandParcel> dataToDelete) {
        if (dataToDelete != null) {
            this.landParcelQuadTree.delete(dataToDelete);
            System.out.println("deleted land parcel");
        } else {
            System.out.println("data su VRAJ null");
        }
    }

    public void deleteRealEstate(Data<RealEstate> dataToDelete) {
        if (dataToDelete != null) {
            this.realEstateQuadTree.delete(dataToDelete);
            System.out.println("delete real estate");
        } else {
            System.out.println("Data su VRAJ null");
        }
    }

    /**
     * methods for finding data
     */
    public ArrayList<Data<? extends CadastralObject>> findAccordingCoordinates(GPS parCoordinates) {
        Coordinates coorsForSearchEstates = this.mapEstateTree.getCoordinatesValue(parCoordinates);
        Coordinates coorsForSearchParcels = this.mapParcelTree.getCoordinatesValue(parCoordinates);
        ArrayList<Data<? extends CadastralObject>> dataToReturn = new ArrayList<>();
        dataToReturn.addAll(this.landParcelQuadTree.find(coorsForSearchParcels));
        dataToReturn.addAll(this.realEstateQuadTree.find(coorsForSearchEstates));
        System.out.println("simple find was successful");
        return dataToReturn;
    }

    public ArrayList<Data<? extends CadastralObject>> findInArea(GPS[] parCoordinates) {
        Coordinates coorsForSearchEstates = this.mapEstateTree.getCoordinatesValue(parCoordinates);
        Coordinates coorsForSearchParcels = this.mapParcelTree.getCoordinatesValue(parCoordinates);
        ArrayList<Data<? extends CadastralObject>> dataToReturn = new ArrayList<>();
        dataToReturn.addAll(this.realEstateQuadTree.findInArea(coorsForSearchEstates));
        dataToReturn.addAll(this.landParcelQuadTree.findInArea(coorsForSearchParcels));
        System.out.println("find in area was successful");
        return dataToReturn;
    }

    public ArrayList<Data<? extends CadastralObject>> returnAllData() {
        ArrayList<Data<? extends CadastralObject>> dataToReturn = new ArrayList<>();
        dataToReturn.addAll(this.landParcelQuadTree.find(this.landParcelQuadTree.getRangeOfTree()));
        dataToReturn.addAll(this.realEstateQuadTree.find(this.realEstateQuadTree.getRangeOfTree()));
        return dataToReturn;
    }

    /**
     * method for generating new objects
     */
    public ArrayList<Data<? extends CadastralObject>> generateObjects(CadastralObjectGenerator.GenerateOption parType,int parCount, double parSize, GPS[] parRange) {
        ArrayList<CadastralObject> list = this.generator.generateObjects(parType,parCount,parSize,parRange);
        MapCoordinates mp = new MapCoordinates(parRange);
        ArrayList<Data<? extends CadastralObject>> dataToReturn = new ArrayList<>();
        ArrayList<Data<LandParcel>> generatedLandParcel = new ArrayList<>();
        ArrayList<Data<RealEstate>> generatedRealEstates = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Data newData = new Data(list.get(i),mp.getCoordinatesValue(list.get(i).getGPSCoordinates()),UUID.randomUUID());
            dataToReturn.add(newData);
            if (list.get(i).isInstanceOf().equals(CadastralObject.TypeOfCadastralObject.LAND_PARCEL)) {
                //this.fillLandParcelWithBelongingRealEstates(newData);
                generatedLandParcel.add(newData);
            } else {
                //this.fillRealEstateWithBelongingLandParcels(newData);
                generatedRealEstates.add(newData);
            }
        }
        for (int i = 0; i < generatedLandParcel.size(); i++) {
            landParcelQuadTree.insert(generatedLandParcel.get(i));
        }
        for (int i = 0; i < generatedRealEstates.size(); i++) {
            realEstateQuadTree.insert(generatedRealEstates.get(i));
        }
        for (int i = 0; i < generatedLandParcel.size(); i++) {
            this.fillLandParcelWithBelongingRealEstates(generatedLandParcel.get(i));
        }
        for (int i = 0; i < generatedRealEstates.size(); i++) {
            this.fillRealEstateWithBelongingLandParcels(generatedRealEstates.get(i));
        }

        return dataToReturn;
    }

    public ArrayList<LandParcel> returnAllIncludingParcels(Data<RealEstate> parData) {
        ArrayList<LandParcel> o = parData.getData().getBelongingLandParcels();
        for (int i = 0; i < o.size(); i++) {
            System.out.println(o.get(i).toString());
        }
        return o;
    }

    public ArrayList<RealEstate> returnAllIncludingEstates(Data<LandParcel> parData) {

        ArrayList<RealEstate> o = parData.getData().getBelongingRealEstates();
        for (int i = 0; i < o.size(); i++) {
            System.out.println(o.get(i).toString());
        }
        return o;
    }


    /**
     * methods for loading data from file and save data to file
     */
    public void saveTrees(String parPath) {
        if (this.readWriterOfTree == null) {
            this.readWriterOfTree = new ReadWriterOfTree(this.realEstateQuadTree,this.landParcelQuadTree,this.realEstateTreeGPS,this.getLandParcelTreeGPS());
        }
        this.readWriterOfTree.writeData(parPath);
    }

    public void loadTrees(String parPath) {
        if (this.readWriterOfTree == null) {
            this.readWriterOfTree = new ReadWriterOfTree(this.realEstateQuadTree,this.landParcelQuadTree,this.realEstateTreeGPS,this.getLandParcelTreeGPS());
        }
        this.readWriterOfTree.readData(parPath);
        this.landParcelQuadTree = this.readWriterOfTree.getTreeLandParcel();
        this.realEstateQuadTree = this.readWriterOfTree.getTreeRealEstate();
    }


    // TODO not sure if will used
    public QuadTree<LandParcel> returnLandParcelTree() {
        return this.landParcelQuadTree;
    }

    public QuadTree<RealEstate> returnRealEstateTree() {
        return this.realEstateQuadTree;
    }

    public GPS[] getRealEstateTreeGPS() {
        return this.realEstateTreeGPS;
    }

    public GPS[] getLandParcelTreeGPS() {
        return this.landParcelTreeGPS;
    }


    /**
     * methods for automatic filling parcel with pointers to estates which contains and vice versa
     */
    private void fillLandParcelWithBelongingRealEstates(Data<LandParcel> parLandParcel) {
        if (!this.realEstateQuadTree.isEmpty()) {
            ArrayList<Data<RealEstate>> data = this.realEstateQuadTree.find(parLandParcel.getCoordinates());
            System.out.println(" land parcel data has size:" + data.size());
            for (int i = 0; i < data.size(); i++) {
                parLandParcel.getData().addBelongingRealEstate(data.get(i).getData());
                //System.out.println("vlozilo sa mi dato");
            }
        }
    }
    private void fillRealEstateWithBelongingLandParcels(Data<RealEstate> parRealEstate) {
        if (!this.landParcelQuadTree.isEmpty()) {
            ArrayList<Data<LandParcel>> data = this.landParcelQuadTree.find(parRealEstate.getCoordinates());
            System.out.println("real estate data has size:" + data.size());
            for (int i = 0; i < data.size(); i++) {
                parRealEstate.getData().addBelongingLandParcel(data.get(i).getData());
                //System.out.println("zvlozilo sa mi dato");
            }
        }
    }

    /*

   po vlozeni vsetkych dat. Prejde sa ti kazde jedno dato a povkladaju do neho objekty kore don patria.

     */

    private void refillEstateData(Coordinates parCoordinatesOfNewData) {
        // po kazdnom vlozeni data najskor musim do vlozeneho data povkladat referencie a nasledne aj skontrolovat do akych inych dat zasahuje aby sa mi tam vyvtorila referencia na toto dato.
        // ked vymazem dato, musia sa mi nan vymzataz referencie
        ArrayList<Data<RealEstate>> dataForControl = this.realEstateQuadTree.find(parCoordinatesOfNewData);
//        for (int i = 0; i < dataForControl.size(); i++) {
//            dataForControl.get(i).getData().addBelongingLandParcel();
//        }
    }

    private void refillParcelData(Coordinates parCoordinatesOfNewData) {

    }
    // todo eva vies jednoducho kde je chyba, tak to prosim oprav. ked jednoducho vytvars one musis to vzdy kontrolovat saalalahlalala
}
