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
        return "Land Parcel, parcel number: " + this.parcelNumber + ", "  + super.toString();
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
