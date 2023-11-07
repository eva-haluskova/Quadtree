package Data;

import QuadTree.Coordinates;

public abstract class CadastralObject implements Comparable<CadastralObject> {

    public enum TypeOfCadastralObject {
        LAND_PARCEL,
        REAL_ESTATE
    }

    private static final int COORDINATES_COUNT = 2;

    protected String description;
    protected GPS[] GPSCoordinates;

    public CadastralObject(String parDescription,GPS[] parCoordinates) {
        this.description = parDescription;
        this.GPSCoordinates = new GPS[COORDINATES_COUNT];
        this.GPSCoordinates[0] = parCoordinates[0];
        this.GPSCoordinates[1] = parCoordinates[1];
    }

    public abstract TypeOfCadastralObject isInstanceOf();
}
