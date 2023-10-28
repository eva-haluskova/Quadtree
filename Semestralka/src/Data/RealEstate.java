package Data;

import QuadTree.Coordinates;
import QuadTree.GPS;

import java.util.ArrayList;


public class RealEstate {

    private static final int GPS_COUNT = 2;


    private int serialNumber;
    private String description;
    private ArrayList<LandParcel> belongingLandParcels; // referencie
    private GPS[] GPSCoordinates;

    public RealEstate(
            int parSerialNumber,
            String parDescription,
            GPS parCoordinatesOne,
            GPS parCoordinatesTwo
    ) {

        this.serialNumber = parSerialNumber;
        this.description = parDescription;
        this.GPSCoordinates = new GPS[GPS_COUNT];
        this.GPSCoordinates[0] = parCoordinatesOne;
        this.GPSCoordinates[1] = parCoordinatesTwo;
    }
}
