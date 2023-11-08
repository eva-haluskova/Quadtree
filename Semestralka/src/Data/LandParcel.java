package Data;

import QuadTree.Coordinates;

public class LandParcel extends CadastralObject {

    private int parcelNumber;
    private RealEstate belongingRealEstates;

    public LandParcel(String parDescription,GPS[] parCoordinates, int parParcelNumber) {
        super(parDescription, parCoordinates);
        this.parcelNumber = parParcelNumber;
    }

    @Override
    public TypeOfCadastralObject isInstanceOf() {
        return TypeOfCadastralObject.LAND_PARCEL;
    }

    @Override
    public String toString() {
        return "Type of object: Land Parcel, parcel number: " + this.parcelNumber + ", desc: " + this.getDescription();
    }

    public int getParcelNumber() {
        return parcelNumber;
    }

    public void setParcelNumber(int parcelNumber) {
        this.parcelNumber = parcelNumber;
    }

}
