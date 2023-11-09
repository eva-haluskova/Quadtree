package Data;

import QuadTree.Coordinates;

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
        return "Type of object: Real Estate, serial number: " + this.serialNumber + ", desc: " + this.getDescription() +
        "    lat: " + this.GPSCoordinates[0].getLatitude() +
                ", pos: " + this.GPSCoordinates[0].getLatitudePosition() +
                ", long: " + this.GPSCoordinates[0].getLongitude() +
                ", pos: " + this.GPSCoordinates[0].getLongitudePosition() +
                "    lat: " + this.GPSCoordinates[1].getLatitude() +
                ", pos: " + this.GPSCoordinates[1].getLatitudePosition() +
                ", long: " + this.GPSCoordinates[1].getLongitude() +
                ", pos: " + this.GPSCoordinates[1].getLongitudePosition();
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
