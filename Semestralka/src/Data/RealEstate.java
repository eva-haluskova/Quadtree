package Data;

import QuadTree.Coordinates;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class RealEstate extends CadastralObject {

    private int serialNumber;
    private ArrayList<LandParcel> belongingLandParcels;

    public RealEstate(String parDescription,GPS[] parCoordinates, int parSerialNumber) {
        super(parDescription, parCoordinates);
        this.serialNumber = parSerialNumber;
        this.belongingLandParcels = new ArrayList<>();
    }

    @Override
    public TypeOfCadastralObject isInstanceOf() {
        return TypeOfCadastralObject.REAL_ESTATE;
    }

    @Override
    public String toString() {
        return "Real Estate, serial number: " + this.serialNumber + ", " + super.toString();
    }

    public String[] toListOfString() {
        String[] s = {this.isInstanceOf().toString(),Integer.toString(this.serialNumber),this.description};
        return s;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void addBelongingLandParcel(LandParcel parLandParcle) {
        this.belongingLandParcels.add(parLandParcle);
    }

    public ArrayList<LandParcel> getBelongingLandParcels(){
        return this.belongingLandParcels;
    }
}
