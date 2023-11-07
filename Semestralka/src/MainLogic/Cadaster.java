package MainLogic;

import Data.CadastralObject;
import Data.LandParcel;
import Data.RealEstate;
import QuadTree.QuadTree;
import QuadTree.Coordinates;
import QuadTree.Data;

import java.util.ArrayList;
import java.util.UUID;
import java.util.WeakHashMap;

public class Cadaster {

    private QuadTree<CadastralObject> quadTree;
    private int uniqueID;

    public Cadaster() {
        this.quadTree = new QuadTree<CadastralObject>(0, 10000, 0, 1000, 0);
        this.uniqueID = 0;
    }

    public void createRealEstate(int parSerialNumber, Coordinates parCoordinates, String parDescription) {
        RealEstate newRealEstate = new RealEstate(parDescription,parSerialNumber);
        Data<CadastralObject> dataToInsert = new Data(newRealEstate,parCoordinates,this.uniqueID);
        this.addToTree(dataToInsert, this.quadTree);
        this.uniqueID ++;
    }

    public void createLandParcel(int parParcelNumber, Coordinates parCoordinates, String parDescription) {
        // TODO
    }

    public void addToTree(Data<CadastralObject> parNewRealEstate, QuadTree<CadastralObject> parTree) {
        this.quadTree.insert(parNewRealEstate, this.quadTree.getRoot());
    }

    public ArrayList<Data<CadastralObject>> findAccordingCoordinates(Coordinates parCoordinates) {
        return this.quadTree.find(parCoordinates);
    }

    public ArrayList<Data<CadastralObject>> findInArea(Coordinates parCoordinates) {
        return this.quadTree.findInArea(parCoordinates);
    }

    public QuadTree<CadastralObject> totoMusimZmenit() {
        return this.quadTree;
    }






}
