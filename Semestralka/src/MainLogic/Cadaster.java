package MainLogic;

import Data.CadastralObject;
import Data.LandParcel;
import Data.RealEstate;
import QuadTree.QuadTree;
import QuadTree.Coordinates;
import QuadTree.Data;
import Data.GPS;
import Data.MapCoordinates;

import java.util.ArrayList;
import java.util.UUID;
import java.util.WeakHashMap;

public class Cadaster {

    private MapCoordinates mapEstateTree;
    private MapCoordinates mapParcelTree;

    private CadastralObjectGenerator generator;

    private QuadTree<LandParcel> landParcelQuadTree;
    private QuadTree<RealEstate> realEstateQuadTree;

    public Cadaster() {
    }

    public void createLandParcelTree(GPS[] parCoordinates, int parMaxDepth) {
        this.mapParcelTree = new MapCoordinates(parCoordinates);
        this.landParcelQuadTree = new QuadTree<LandParcel>(this.mapParcelTree.getCoordinatesValue(parCoordinates),parMaxDepth);
        System.out.println("Land Parcel tree is created!");
    }

    public void createRealEstateTree(GPS[] parCoordinates, int parMaxDepth) {
        this.mapEstateTree = new MapCoordinates(parCoordinates);
        this.realEstateQuadTree = new QuadTree<RealEstate>(this.mapEstateTree.getCoordinatesValue(parCoordinates),parMaxDepth);
        System.out.println("Real Estate tree is created!");
    }

    public void insertLandParcel(int parParcelNumber, GPS[] parCoordinates, String parDescription) {
        LandParcel newLandParcel = new LandParcel(parDescription,parCoordinates, parParcelNumber);
        Data<LandParcel> dataToInsert = new Data(newLandParcel,this.mapParcelTree.getCoordinatesValue(parCoordinates),UUID.randomUUID());
        this.landParcelQuadTree.insert(dataToInsert,this.landParcelQuadTree.getRoot());
        System.out.println("Data land parcel: " + newLandParcel.toString() + " is created!");
    }

    public void insertRealEstate(int parSerialNumber, GPS[] parCoordinates, String parDescription) {
        RealEstate newRealEstate = new RealEstate(parDescription,parCoordinates,parSerialNumber);
        Data<RealEstate> dataToInsert = new Data(newRealEstate,this.mapEstateTree.getCoordinatesValue(parCoordinates),UUID.randomUUID());
        this.realEstateQuadTree.insert(dataToInsert,this.realEstateQuadTree.getRoot());
        System.out.println("Data real estate: " + newRealEstate.toString() + " is created!");
    }


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

    public void deleteLandParcel(Data<LandParcel> dataToDelete) {
        this.landParcelQuadTree.delete(dataToDelete);
        System.out.println("deleted land parcel");
    }

    public void deleteRealEstate(Data<RealEstate> dataToDelete) {
        this.realEstateQuadTree.delete(dataToDelete);
        System.out.println("delete real estate");
    }


    public ArrayList<Data<? extends CadastralObject>> findAccordingCoordinates(GPS[] parCoordinates) {
        Coordinates coorsForSearchEstates = this.mapEstateTree.getCoordinatesValue(parCoordinates);
        Coordinates coorsForSearchParcels = this.mapParcelTree.getCoordinatesValue(parCoordinates);
        ArrayList<Data<? extends CadastralObject>> dataToReturn = new ArrayList<>();
        dataToReturn.addAll(this.landParcelQuadTree.find(coorsForSearchParcels));
        dataToReturn.addAll(this.realEstateQuadTree.find(coorsForSearchEstates));
        return dataToReturn;
    }

    public ArrayList<Data<? extends CadastralObject>> findInArea(GPS[] parCoordinates) {
        Coordinates coorsForSearchEstates = this.mapEstateTree.getCoordinatesValue(parCoordinates);
        Coordinates coorsForSearchParcels = this.mapParcelTree.getCoordinatesValue(parCoordinates);
        ArrayList<Data<? extends CadastralObject>> dataToReturn = new ArrayList<>();
        ArrayList<Data<RealEstate>> zozone = this.realEstateQuadTree.find(coorsForSearchEstates);
        dataToReturn.addAll(zozone);
        dataToReturn.addAll(this.landParcelQuadTree.find(coorsForSearchParcels));

        return dataToReturn;
    }

    public ArrayList<Data<? extends CadastralObject>> generateObjects(CadastralObjectGenerator.GenerateOption parType,int parCount, int parSize, GPS[] parRange) {
        ArrayList<CadastralObject> list = this.generator.generateObjects(parType,parCount,parSize,parRange);
        MapCoordinates mp = new MapCoordinates(parRange);
        ArrayList<Data<? extends CadastralObject>> dataToReturn = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            dataToReturn.add(new Data(list.get(i),mp.getCoordinatesValue(list.get(i).getGPSCoordinates())));
        }
        return dataToReturn;
    }

    public ArrayList<Data<? extends CadastralObject>> returnAllData() {
        ArrayList<Data<? extends CadastralObject>> dataToReturn = new ArrayList<>();
        dataToReturn.addAll(this.landParcelQuadTree.find(this.landParcelQuadTree.getRangeOfTree()));
        dataToReturn.addAll(this.realEstateQuadTree.find(this.realEstateQuadTree.getRangeOfTree()));
        return dataToReturn;
    }

    public QuadTree<RealEstate> getRealEstateQuadTree() {
        return this.realEstateQuadTree;
    }

    public QuadTree<LandParcel> getLandParcelQuadTree() {
        return this.landParcelQuadTree;
    }

}
