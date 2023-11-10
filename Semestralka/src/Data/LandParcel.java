package Data;

import QuadTree.Coordinates;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LandParcel extends CadastralObject {

    private int parcelNumber;
    private ArrayList<RealEstate> belongingRealEstates;

    public LandParcel(String parDescription,GPS[] parCoordinates, int parParcelNumber) {
        super(parDescription, parCoordinates);
        this.parcelNumber = parParcelNumber;
        this.belongingRealEstates = new ArrayList<>();
    }

    @Override
    public TypeOfCadastralObject isInstanceOf() {
        return TypeOfCadastralObject.LAND_PARCEL;
    }

    @Override
    public String toString() {
        return "Type of object: Land Parcel, parcel number: " + this.parcelNumber + ", desc: " + this.getDescription() +
                    "    lat: " + this.GPSCoordinates[0].getLatitude() +
                        ", pos: " + this.GPSCoordinates[0].getLatitudePosition() +
                        ", long: " + this.GPSCoordinates[0].getLongitude() +
                        ", pos: " + this.GPSCoordinates[0].getLongitudePosition() +
        "    lat: " + this.GPSCoordinates[1].getLatitude() +
                ", pos: " + this.GPSCoordinates[1].getLatitudePosition() +
                ", long: " + this.GPSCoordinates[1].getLongitude() +
                ", pos: " + this.GPSCoordinates[1].getLongitudePosition() +
                this.returnRealEstatesInString();
    }

    public String[] toListOfString() {
        String[] s = {this.isInstanceOf().toString(),Integer.toString(this.parcelNumber),this.description};
        return s;
    }

    public int getParcelNumber() {
        return parcelNumber;
    }

    public void setParcelNumber(int parcelNumber) {
        this.parcelNumber = parcelNumber;
    }

    public void addBelongingRealEstate(RealEstate parRealEstate) {
        this.belongingRealEstates.add(parRealEstate);
    }

    public ArrayList<RealEstate> getBelongingRealEstates() {
        return this.belongingRealEstates;
    }

    public String returnRealEstatesInString() {
        String ret = "";
        for (int i = 0; i < this.belongingRealEstates.size(); i++) {
            ret = ret + this.belongingRealEstates.get(i).toString() + "\n";
        }
        return ret;
    }
}
