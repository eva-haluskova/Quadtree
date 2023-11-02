package MainLogic;

import Data.CadastralObject;
import Data.LandParcel;
import QuadTree.QuadTree;


public class Cadaster {

    private QuadTree<CadastralObject> quadTree;

    public Cadaster() {
        this.quadTree = new QuadTree<CadastralObject>(0, 10000, 0, 1000, 0);
    }

    public void addRealEstate() {

    }



}
