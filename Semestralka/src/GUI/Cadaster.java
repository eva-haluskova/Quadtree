package GUI;

import Data.CadastralObject;
import Data.LandParcel;
import Data.RealEstate;
import Data.GPS;
import Data.MapCoordinates;
import Data.CadastralObjectGenerator;

import QuadTree.QuadTree;
import QuadTree.Coordinates;
import QuadTree.Data;
import QuadTree.ReadWriterOfTree;

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

    private GPS[] realEstateTreeGPS;
    private GPS[] landParcelTreeGPS;

    public Cadaster() {
        this.generator = new CadastralObjectGenerator();
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
        this.refillEstateData(dataToInsert,dataToInsert.getCoordinates());
        this.landParcelQuadTree.insert(dataToInsert);
        System.out.println("Data land parcel is created!");
        System.out.println("Tree contains " + this.landParcelQuadTree.getNumberOfItems() + " items.");
    }

    public void insertRealEstate(int parSerialNumber, GPS[] parCoordinates, String parDescription) {
        RealEstate newRealEstate = new RealEstate(parDescription,parCoordinates,parSerialNumber);
        Data<RealEstate> dataToInsert = new Data(newRealEstate,this.mapEstateTree.getCoordinatesValue(parCoordinates),UUID.randomUUID());
        this.fillRealEstateWithBelongingLandParcels(dataToInsert);
        this.refillParcelData(dataToInsert,dataToInsert.getCoordinates());
        this.realEstateQuadTree.insert(dataToInsert);
        System.out.println("Data real estate is created!");
        System.out.println("Tree contains " + this.realEstateQuadTree.getNumberOfItems() + " items.");
    }


    /**
     * methods for editing data
     */
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
        System.out.println("Edited real estate data");
    }

    /**
     * method for deleting data
     */
    public void delete(Data<? extends CadastralObject> dataToDelete) {
        if (dataToDelete.getData().isInstanceOf().equals(CadastralObject.TypeOfCadastralObject.LAND_PARCEL)) {
            this.deleteRealEstatesReferences((Data<LandParcel>) dataToDelete, dataToDelete.getCoordinates());
            this.landParcelQuadTree.delete((Data<LandParcel>) dataToDelete);
            System.out.println("delete land parcel");
            System.out.println("Land parcel tree contains " + this.realEstateQuadTree.getNumberOfItems() + " items.");
        } else {
            this.deleteLandParcelReferences((Data<RealEstate>) dataToDelete, dataToDelete.getCoordinates());
            this.realEstateQuadTree.delete((Data<RealEstate>) dataToDelete);
            System.out.println("delete real estate");
            System.out.println("Real estate tree contains " + this.realEstateQuadTree.getNumberOfItems() + " items.");

        }
    }

    /**
     * methods for finding data
     */
    public ArrayList<Data<? extends CadastralObject>> findAccordingCoordinates(GPS parCoordinates, CadastralObject.TypeOfCadastralObject parType) {
        ArrayList<Data<? extends CadastralObject>> dataToReturn = new ArrayList<>();
        if (parType.equals(CadastralObject.TypeOfCadastralObject.REAL_ESTATE)) {
            Coordinates coorsForSearchEstates = this.mapEstateTree.getCoordinatesValue(parCoordinates);
            dataToReturn.addAll(this.realEstateQuadTree.find(coorsForSearchEstates));
            System.out.println("simple find was successful");
        } else {
            Coordinates coorsForSearchParcels = this.mapParcelTree.getCoordinatesValue(parCoordinates);
            dataToReturn.addAll(this.landParcelQuadTree.find(coorsForSearchParcels));
        }
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
    public ArrayList<Data<? extends CadastralObject>> generateObjects(CadastralObjectGenerator.GenerateOption parType, int parCount, double parSize, GPS[] parRange) {
        ArrayList<CadastralObject> list = this.generator.generateObjects(parType,parCount,parSize,parRange);
        MapCoordinates mp = new MapCoordinates(parRange);
        ArrayList<Data<? extends CadastralObject>> dataToReturn = new ArrayList<>();
        ArrayList<Data<LandParcel>> generatedLandParcel = new ArrayList<>();
        ArrayList<Data<RealEstate>> generatedRealEstates = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Data newData = new Data(list.get(i),mp.getCoordinatesValue(list.get(i).getGPSCoordinates()),UUID.randomUUID());
            dataToReturn.add(newData);
            if (list.get(i).isInstanceOf().equals(CadastralObject.TypeOfCadastralObject.LAND_PARCEL)) {
                generatedLandParcel.add(newData);
            } else {
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
            this.refillEstateData(generatedLandParcel.get(i),generatedLandParcel.get(i).getCoordinates());
        }
        for (int i = 0; i < generatedRealEstates.size(); i++) {
            this.fillRealEstateWithBelongingLandParcels(generatedRealEstates.get(i));
            this.refillParcelData(generatedRealEstates.get(i),generatedRealEstates.get(i).getCoordinates());
        }
        System.out.println(parCount + " object was created!");
        return dataToReturn;
    }

    public ArrayList<LandParcel> returnAllIncludingParcels(Data<RealEstate> parData) {
        ArrayList<LandParcel> o = parData.getData().getBelongingLandParcels();
//        for (int i = 0; i < o.size(); i++) {
//            System.out.println(o.get(i).toString());
//        }
        return o;
    }

    public ArrayList<RealEstate> returnAllIncludingEstates(Data<LandParcel> parData) {

        ArrayList<RealEstate> o = parData.getData().getBelongingRealEstates();
//        for (int i = 0; i < o.size(); i++) {
//            System.out.println(o.get(i).toString());
//        }
        return o;
    }


    /**
     * methods for loading data from file and save data to file
     */
    public void saveTrees(String parPath) {
        if (this.readWriterOfTree == null) {
            this.readWriterOfTree = new ReadWriterOfTree(this.realEstateQuadTree,this.landParcelQuadTree,this.realEstateTreeGPS,this.landParcelTreeGPS);
        }
        this.readWriterOfTree.writeData(parPath);
        System.out.println("Data wa successful saved!");
    }

    public void loadTrees(String parPath) {
        if (this.readWriterOfTree == null) {
            this.readWriterOfTree = new ReadWriterOfTree(this.realEstateQuadTree,this.landParcelQuadTree,this.realEstateTreeGPS,this.landParcelTreeGPS);
        }
        this.readWriterOfTree.readData(parPath);
        this.landParcelQuadTree = this.readWriterOfTree.getTreeLandParcel();
        this.landParcelTreeGPS = this.readWriterOfTree.getParTreeLPGPS();
        this.mapParcelTree = new MapCoordinates(this.landParcelTreeGPS);

        this.realEstateQuadTree = this.readWriterOfTree.getTreeRealEstate();
        this.realEstateTreeGPS = this.readWriterOfTree.getParTreeREGPS();
        this.mapEstateTree = new MapCoordinates(this.realEstateTreeGPS);
        this.fillAll();
        System.out.println("Data was successful loaded!");
    }


    /**
     * methods for automatic filling and refilling parcel with pointers to estates which
     * contains and vice versa, also for removing pointers
     */
    private void fillLandParcelWithBelongingRealEstates(Data<LandParcel> parLandParcel) {
        if (this.realEstateQuadTree != null && !this.realEstateQuadTree.isEmpty()) {
            ArrayList<Data<RealEstate>> data = this.realEstateQuadTree.find(parLandParcel.getCoordinates());
            for (int i = 0; i < data.size(); i++) {
                parLandParcel.getData().addBelongingRealEstate(data.get(i).getData());
            }
        }
    }
    private void fillRealEstateWithBelongingLandParcels(Data<RealEstate> parRealEstate) {
        if (this.landParcelQuadTree != null && !this.landParcelQuadTree.isEmpty()) {
            ArrayList<Data<LandParcel>> data = this.landParcelQuadTree.find(parRealEstate.getCoordinates());
            for (int i = 0; i < data.size(); i++) {
                parRealEstate.getData().addBelongingLandParcel(data.get(i).getData());
            }
        }
    }

    private void refillEstateData(Data<LandParcel> parData, Coordinates parCoordinatesOfNewData) {
        if (this.realEstateQuadTree != null && !this.realEstateQuadTree.isEmpty()) {
            ArrayList<Data<RealEstate>> dataForControl = this.realEstateQuadTree.find(parCoordinatesOfNewData);
            for (int i = 0; i < dataForControl.size(); i++) {
                dataForControl.get(i).getData().addBelongingLandParcel(parData.getData());
            }
        }
    }

    private void refillParcelData(Data<RealEstate> parData,Coordinates parCoordinatesOfNewData) {
        if (this.landParcelQuadTree != null && !this.landParcelQuadTree.isEmpty()) {
            ArrayList<Data<LandParcel>> dataForControl = this.landParcelQuadTree.find(parCoordinatesOfNewData);
            for (int i = 0; i < dataForControl.size(); i++) {
                dataForControl.get(i).getData().addBelongingRealEstate(parData.getData());
            }
        }
    }

    private void deleteLandParcelReferences(Data<RealEstate> parData, Coordinates parCoordinatesOfDeletedData) {
        if (this.landParcelQuadTree != null && !this.landParcelQuadTree.isEmpty()) {
            ArrayList<Data<LandParcel>> dataForControl = this.landParcelQuadTree.find(parCoordinatesOfDeletedData);
            for (int i = 0; i < dataForControl.size(); i++) {
                dataForControl.get(i).getData().deleteBelongingRealEstate(parData.getData());
            }
        }
    }

    private void deleteRealEstatesReferences(Data<LandParcel> parData, Coordinates parCoordinatesOfDeletedData) {
        if (this.realEstateQuadTree != null && !this.realEstateQuadTree.isEmpty()) {
            ArrayList<Data<RealEstate>> dataForControl = this.realEstateQuadTree.find(parCoordinatesOfDeletedData);
            for (int i = 0; i < dataForControl.size(); i++) {
                dataForControl.get(i).getData().deleteBelongingLandParcels(parData.getData());
            }
        }
    }

    private void fillAll() {
        ArrayList<Data<LandParcel>> dataToFill = this.landParcelQuadTree.getAllDataInSubTree(this.landParcelQuadTree.getRoot());
        for (int i = 0; i < dataToFill.size(); i++) {
            this.fillLandParcelWithBelongingRealEstates(dataToFill.get(i));
            this.refillEstateData(dataToFill.get(i),dataToFill.get(i).getCoordinates());
        }
        ArrayList<Data<RealEstate>> dataToFill2 = this.realEstateQuadTree.getAllDataInSubTree(this.realEstateQuadTree.getRoot());
        for (int i = 0; i < dataToFill2.size(); i++) {
            this.fillRealEstateWithBelongingLandParcels(dataToFill2.get(i));
            this.refillParcelData(dataToFill2.get(i),dataToFill2.get(i).getCoordinates());
        }

    }

    public void startOptimalization(boolean parBool, CadastralObject.TypeOfCadastralObject type) {
        if (type.equals(CadastralObject.TypeOfCadastralObject.LAND_PARCEL)) {
            this.landParcelQuadTree.setOptimalizationOn(parBool);
        } else {
            this.realEstateQuadTree.setOptimalizationOn(parBool);
        }
    }

    public void changeDepth(int newDepth, CadastralObject.TypeOfCadastralObject type) {
        if (type.equals(CadastralObject.TypeOfCadastralObject.LAND_PARCEL)) {
            this.landParcelQuadTree.changeDepth(newDepth);
        } else {
            this.realEstateQuadTree.changeDepth(newDepth);
        }
    }

}
