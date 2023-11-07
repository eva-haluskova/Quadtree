package Data;

import QuadTree.Coordinates;

public class LandParcel extends CadastralObject {

    private int parcelNumber;
    private RealEstate belongingRealEstates;

    public LandParcel(String parDescription, int parParcelNumber) {
        super(parDescription);
        this.parcelNumber = parParcelNumber;
    }

    @Override
    public TypeOfCadastralObject isInstanceOf() {
        return TypeOfCadastralObject.LAND_PARCEL;
    }

    @Override
    public int compareTo(CadastralObject o) {
        return 0;
    }
}
