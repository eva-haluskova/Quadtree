package Data;

import QuadTree.Coordinates;

import java.util.ArrayList;


public class RealEstate extends CadastralObject {

    private int serialNumber;
    private ArrayList<LandParcel> belongingLandParcels;

    public RealEstate(String parDescription,GPS[] parCoordinates, int parSerialNumber) {
        super(parDescription, parCoordinates);
        this.serialNumber = parSerialNumber;
    }

    @Override
    public TypeOfCadastralObject isInstanceOf() {
        return TypeOfCadastralObject.REAL_ESTATE;
    }

    @Override
    public int compareTo(CadastralObject o) {
        return 0;
    }
}
