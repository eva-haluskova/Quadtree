package QuadTree;

/**
 * Class represents coordinates of some object we want to insert into quad tree.
 * It holds latitude and longitude of coordinates, with centre on equator.
 * It holds if latitude is north or south and its position on it and the same for longitute -
 * if it's east or west and it's position. If we imagine, quad tree has X and Y coordinates,
 * it has size of width - x, and height - y. So it has four coordinates - xmin, xmax, ymin, ymax.
 * Let say:
 *  - equator has coordinates [(xmin + xmax)/2,(ymin + ymax)/2]
 *  - northernmost edge has coordinates [xmin,ymin] - [xmax,ymin]
 *  - southernmost edge has coordinates [xmin,ymax] - [xmax,ymax]
 *  - westernmost edge has coordinates [xmin,ymin] - [xmin,ymax]
 *  - easternmost edge has coordinates [xmax,ymin] - [xmax,ymax]
 */
public class GPS {

    public enum Latitude {
        NORTH,
        SOUTH
    }

    public enum Longitude {
        EAST,
        WEST
    }

    private double latitudePosition;
    private double longitudePosition;
    private Latitude latitude;
    private Longitude longitude;

    public GPS(
            double parLatitudePosition,
            double parLongitudePosition,
            Latitude parLatitude,
            Longitude parLongitude
    ) {

        this.latitude = parLatitude;
        this.longitude = parLongitude;
        this.latitudePosition = parLatitudePosition;
        this.longitudePosition = parLongitudePosition;
    }

    public double getLatitudePosition() {
        return latitudePosition;
    }

    public void setLatitudePosition(double latitudePosition) {
        this.latitudePosition = latitudePosition;
    }

    public double getLongitudePosition() {
        return longitudePosition;
    }

    public void setLongitudePosition(double longitudePosition) {
        this.longitudePosition = longitudePosition;
    }

    public Latitude getLatitude() {
        return latitude;
    }

    public void setLatitude(Latitude latitude) {
        this.latitude = latitude;
    }

    public Longitude getLongitude() {
        return longitude;
    }

    public void setLongitude(Longitude longitude) {
        this.longitude = longitude;
    }


}
