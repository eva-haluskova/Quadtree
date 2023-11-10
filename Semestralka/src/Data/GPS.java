package Data;

public class GPS {

    public enum Latitude {
        NORTH,
        SOUTH
    }

    public enum Longitude {
        EAST,
        WEST
    }

    private static final double EPSILON = 0.00000001;
    private static final int FIRST = 0;
    private static final int SECOND = 1;

    private Latitude latitude;
    private Longitude longitude;
    private double latitudePosition;
    private double longitudePosition;

    public GPS (
            Latitude parLatitude,
            double parLatitudePosition,
            Longitude parLongitude,
            double parLongitudePosition
    ) {
        this.latitude = parLatitude;
        this.longitude = parLongitude;
        this.latitudePosition = parLatitudePosition;
        this.longitudePosition = parLongitudePosition;
    }

    /**
     * Getters and setters
     */
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

    //  -1 - param gps is smaller than this , 1  - param gps is bigger than this
    public int isBiggerThan(GPS parGps) {
//        if (this.getLatitude() == parGps.getLatitude() &&
//                this.getLatitude() == Latitude.NORTH &&
//                this.getLatitudePosition() > parGps.getLatitudePosition()
//            &&
//                this.getLongitude() == parGps.getLongitude() &&
//                        this.getLongitude() == Longitude.WEST &&
//                        this.getLongitudePosition() > parGps.getLongitudePosition()) {
//            return -1;
//        } else if (this.getLatitude() == parGps.getLatitude() &&
//                this.getLatitude() == Latitude.NORTH &&
//                this.getLatitudePosition() > parGps.getLatitudePosition()
//                &&
//                this.getLongitude() == Longitude.WEST &&
//                parGps.getLongitude() == Longitude.EAST) {
//            return -1;
//        } else if (this.getLatitude() == Latitude.NORTH &&
//                  parGps.getLatitude() == Latitude.SOUTH
//                &&
//                this.getLongitude() == parGps.getLongitude() &&
//                        this.getLongitude() == Longitude.WEST &&
//                        this.getLongitudePosition() > parGps.getLongitudePosition()) {
//            return -1;
//        } else if (this.getLatitude() == Latitude.NORTH &&
//                parGps.getLatitude() == Latitude.SOUTH &&
//                this.getLongitude() == Longitude.WEST &&
//                parGps.getLongitude() == Longitude.EAST) {
//            return -1;
//        }
        
        if (this.getLatitude() == parGps.getLatitude() &&
                this.getLatitude() == Latitude.NORTH &&
                this.getLatitudePosition() > parGps.getLatitudePosition()
            &&
                this.getLongitude() == parGps.getLongitude() &&
                        this.getLongitude() == Longitude.WEST &&
                        this.getLongitudePosition() > parGps.getLongitudePosition()) {
            return -1;
        // 3.1
        } else if (this.getLatitude() == parGps.getLatitude() &&
                this.getLatitude() == Latitude.SOUTH &&
                this.getLatitudePosition() < parGps.getLatitudePosition()
            &&
                this.getLongitude() == parGps.getLongitude() &&
                        this.getLongitude() == Longitude.WEST &&
                        this.getLongitudePosition() > parGps.getLongitudePosition()) {
            return -1;
        // 4.1
        } else if (this.getLatitude() == parGps.getLatitude() &&
                this.getLatitude() == Latitude.SOUTH &&
                this.getLatitudePosition() < parGps.getLatitudePosition()
                &&
                this.getLongitude() == parGps.getLongitude() &&
                this.getLongitude() == Longitude.EAST &&
                this.getLongitudePosition() < parGps.getLongitudePosition()) {
            return -1;
        // 1.3
        } else if (this.getLatitude() == Latitude.NORTH &&
                  parGps.getLatitude() == Latitude.SOUTH
                &&
                this.getLongitude() == parGps.getLongitude() &&
                        this.getLongitude() == Longitude.WEST &&
                        this.getLongitudePosition() > parGps.getLongitudePosition()) {
            return -1;
        // 2.2
        } else if (this.getLatitude() == Latitude.NORTH &&
                parGps.getLatitude() == Latitude.SOUTH
                &&
                this.getLongitude() == parGps.getLongitude() &&
                this.getLongitude() == Longitude.EAST &&
                this.getLongitudePosition() < parGps.getLongitudePosition()) {
            return -1;
        // 2.1
        } else if (this.getLatitude() == parGps.getLatitude() &&
                this.getLatitude() == Latitude.NORTH &&
                this.getLatitudePosition() > parGps.getLatitudePosition()
                &&
                this.getLongitude() == parGps.getLongitude() &&
                this.getLongitude() == Longitude.EAST &&
                this.getLongitudePosition() < parGps.getLongitudePosition()) {
            return -1;
        // 3.2
        } else if (this.getLatitude() == parGps.getLatitude() &&
                this.getLatitude() == Latitude.SOUTH &&
                this.getLatitudePosition() < parGps.getLatitudePosition()
                &&
                this.getLongitude() == Longitude.WEST &&
                parGps.getLongitude() == Longitude.EAST ){
            return -1;
        // 1.2
        } else if (this.getLatitude() == parGps.getLatitude() &&
                this.getLatitude() == Latitude.NORTH &&
                this.getLatitudePosition() > parGps.getLatitudePosition()
                &&
                this.getLongitude() == Longitude.WEST &&
                parGps.getLongitude() == Longitude.EAST) {
            return -1;
        // 1.4
        } else if (this.getLatitude() == Latitude.NORTH &&
                parGps.getLatitude() == Latitude.SOUTH &&
                this.getLongitude() == Longitude.WEST &&
                parGps.getLongitude() == Longitude.EAST
        ) {
            return -1;
        }

        return 1;
    }

    /**
     * Return true when one coordinates are the same
     * with another given coordinates, false otherwise
     */
    public static boolean equals(GPS parCoordinatesOne, GPS parCoordinatesTwo) {
        return Math.abs(parCoordinatesOne.getLatitudePosition() - parCoordinatesTwo.getLatitudePosition()) < EPSILON &&
                Math.abs(parCoordinatesOne.getLongitudePosition() - parCoordinatesTwo.getLongitudePosition()) < EPSILON &&
                parCoordinatesOne.getLatitude() == parCoordinatesTwo.getLatitude() &&
                parCoordinatesOne.getLongitude() == parCoordinatesTwo.getLongitude();
    }

    /**
     * Return true when one array of coordinates are the same
     * with another given array of coordinates, false otherwise
     */
    public static boolean equals(GPS[] parCoordinatesOne, GPS[] parCoordinatesTwo) {
        boolean areSame = false;
        for (int i = 0; i < parCoordinatesOne.length; i++) {
            if (Math.abs(parCoordinatesOne[i].getLatitudePosition() - parCoordinatesTwo[i].getLatitudePosition()) < EPSILON &&
                    Math.abs(parCoordinatesOne[i].getLongitudePosition() - parCoordinatesTwo[i].getLongitudePosition()) < EPSILON &&
                    parCoordinatesOne[i].getLatitude() == parCoordinatesTwo[i].getLatitude() &&
                    parCoordinatesOne[i].getLongitude() == parCoordinatesTwo[i].getLongitude()) {
                areSame = true;
            } else {
                areSame = false;
            }
        }
        System.out.println("bol som tu");
        return areSame;
    }


    public String[] returnAsStringArray() {
        // latitude, longitude, latipos, longpos
        String[] s = {this.latitude.toString(),Double.toString(this.latitudePosition), this.longitude.toString()
                , Double.toString(this.longitudePosition)};
        return s;
    }


}