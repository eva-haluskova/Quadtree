package Data;

import QuadTree.Coordinates;
import QuadTree.GPS;

public class LandParcel {

    private static final int GPS_COUNT = 2;

    private int parcelNumber;
    private String description;
    private RealEstate belongingRealEstates; // referencie
    private GPS[] GPSCoordinates;

    public LandParcel(
            int parParcelNumber,
            String parDescription,
            GPS parCoordinatesOne,
            GPS parCoordinatesTwo
    ) {
        this.parcelNumber = parParcelNumber;
        this.description = parDescription;
        this.GPSCoordinates = new GPS[GPS_COUNT];
        this.GPSCoordinates[0] = parCoordinatesOne;
        this.GPSCoordinates[1] = parCoordinatesTwo;

    }

    public String getDescription() {
        return description;
    }

    public RealEstate getBelongingRealEstates() {
        return belongingRealEstates;
    }

    // pay attention, this is probably bad
    public GPS[] getGPSCoordinates() {
        return GPSCoordinates;
    }

    public GPS getGPSCoordinatesOne() {
        return this.GPSCoordinates[0];
    }

    public GPS getGPSCoordinatesTwo() {
        return this.GPSCoordinates[1];
    }
}
