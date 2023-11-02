package Data;

import QuadTree.Coordinates;

abstract class CadastralObject {

    private static final int COORDINATES_COUNT = 2;

    protected String description;
    protected Coordinates[] GPSCoordinates;

    public CadastralObject(String parDescription,Coordinates[] parCoordinates) {
        this.description = parDescription;
        this.GPSCoordinates = new Coordinates[COORDINATES_COUNT];
        this.GPSCoordinates[0] = parCoordinates[0];
        this.GPSCoordinates[1] = parCoordinates[1];
    }

    public abstract TypeOfCadastralObject isInstanceOf();
}
