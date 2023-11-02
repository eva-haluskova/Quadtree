package Data;

import QuadTree.Coordinates;

public class LandParcel extends CadastralObject {

    private int parcelNumber;
    private RealEstate belongingRealEstates;

    public LandParcel(String parDescription,Coordinates[] parCoordinates, int parParcelNumber) {
        super(parDescription,parCoordinates);
        this.parcelNumber = parParcelNumber;
    }

    @Override
    public TypeOfCadastralObject isInstanceOf() {
        return TypeOfCadastralObject.LAND_PARCEL;
    }

}
