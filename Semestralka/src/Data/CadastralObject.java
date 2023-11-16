package Data;

import QuadTree.Coordinates;

import java.text.DecimalFormat;

/**
 * Represent some cadastral object - in this case it could be Land Parcel or Real Estate.
 * This abstract class contains common data for all inherit classes, possible methods and abstract methods
 * which is needed to override in child.
 */
public abstract class CadastralObject {

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

    public String toString() {

        DecimalFormat df = new DecimalFormat("#.###");
        return  "desc: " + this.description +
                ", 1. coors: lat: " + this.GPSCoordinates[0].stringMapLatitudeName() +
                ", pos: " + df.format(this.GPSCoordinates[0].getLatitudePosition()) +
                ", long: " + this.GPSCoordinates[0].stringMapLongitudeName() +
                ", pos: " + df.format(this.GPSCoordinates[0].getLongitudePosition()) +
                ", 2. coors: lat: " + this.GPSCoordinates[1].stringMapLatitudeName() +
                ", pos: " + df.format(this.GPSCoordinates[1].getLatitudePosition()) +
                ", long: " + this.GPSCoordinates[1].stringMapLongitudeName() +
                ", pos: " + df.format(this.GPSCoordinates[1].getLongitudePosition());
    }

    public abstract String[] toListOfString();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GPS[] getGPSCoordinates() {
        return GPSCoordinates;
    }

    public void setGPSCoordinates(GPS[] GPSCoordinates) {
        this.GPSCoordinates = GPSCoordinates;
    }
}
