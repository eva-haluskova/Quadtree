package QuadTree;

import java.util.ArrayList;
import java.util.Iterator;

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

public class Coordinates {

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

    public Coordinates(
            Latitude parLatitude,
            Longitude parLongitude,
            double parLatitudePosition,
            double parLongitudePosition
    ) {
        this.latitude = parLatitude;
        this.longitude = parLongitude;
        this.latitudePosition = parLatitudePosition;
        this.longitudePosition = parLongitudePosition;
    }

    /**
     * Copy constructor
     */
    public Coordinates(
            Coordinates otherCoordinates
    ) {
        this.latitude = otherCoordinates.getLatitude();
        this.longitude = otherCoordinates.getLongitude();
        this.latitudePosition = otherCoordinates.getLatitudePosition();
        this.longitudePosition = otherCoordinates.getLongitudePosition();
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

    /**
     * Return true when one coordinates are the same
     * with another given coordinates, false otherwise
     */
    public static boolean equals(Coordinates parCoordinatesOne, Coordinates parCoordinatesTwo) {
        return Math.abs(parCoordinatesOne.getLatitudePosition() - parCoordinatesTwo.getLatitudePosition()) < EPSILON &&
                Math.abs(parCoordinatesOne.getLongitudePosition() - parCoordinatesTwo.getLongitudePosition()) < EPSILON &&
                parCoordinatesOne.getLatitude() == parCoordinatesTwo.getLatitude() &&
                parCoordinatesOne.getLongitude() == parCoordinatesTwo.getLongitude();
    }

    /**
     * Return true when one array of coordinates are the same
     * with another given array of coordinates, false otherwise
     */
    public static boolean equals(Coordinates[] parCoordinatesOne, Coordinates[] parCoordinatesTwo) {
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
        return areSame;
    }

    // predpokladame ze prvy point je nalavo hore a druhy je napravo dole
    public static Coordinates[] coordinatesOfNewNode(Coordinates[] parCoordinates, int parQuadrant) {
        Coordinates firstCoordinates = new Coordinates(Latitude.NORTH,Longitude.EAST,0,0);
        Coordinates secondCoordinates = new Coordinates(Latitude.NORTH,Longitude.EAST,0,0);

        Coordinates centrePoint = new Coordinates(Latitude.NORTH,Longitude.EAST,0,0);
        double helpVarLat = parCoordinates[SECOND].latitudePosition -
                ((parCoordinates[FIRST].getLatitudePosition() + parCoordinates[SECOND].getLatitudePosition()) / 2);
        if (helpVarLat < 0) {
            centrePoint.setLatitudePosition(Math.abs(helpVarLat));
            if (parCoordinates[FIRST].getLatitude() == Latitude.SOUTH) {
                centrePoint.setLatitude(Latitude.NORTH);
            } else {
                centrePoint.setLatitude(Latitude.SOUTH);
            }
        }

        double helpVarLong = parCoordinates[SECOND].getLongitudePosition() -
                ((parCoordinates[FIRST].getLongitudePosition() + parCoordinates[SECOND].getLongitudePosition()) / 2);
        if (helpVarLong < 0) {
            centrePoint.setLongitudePosition(Math.abs(helpVarLong));
            if (parCoordinates[FIRST].getLongitude() == Longitude.EAST) {
                centrePoint.setLongitude(Longitude.WEST);
            } else {
                centrePoint.setLongitude(Longitude.EAST);
            }
        }

        switch (parQuadrant) {
            case 0 -> {
                firstCoordinates.setLatitude(parCoordinates[FIRST].getLatitude());
                firstCoordinates.setLatitudePosition(parCoordinates[FIRST].getLatitudePosition());
                firstCoordinates.setLongitude(parCoordinates[FIRST].getLongitude());
                firstCoordinates.setLongitudePosition(parCoordinates[FIRST].getLongitudePosition());

                secondCoordinates.setLatitude(centrePoint.getLatitude());
                secondCoordinates.setLongitude(centrePoint.getLongitude());
                secondCoordinates.setLatitudePosition(centrePoint.getLatitudePosition());
                secondCoordinates.setLongitudePosition(centrePoint.getLongitudePosition());
            }
            case 1 -> {
                firstCoordinates.setLatitude(parCoordinates[FIRST].getLatitude());
                firstCoordinates.setLatitudePosition(parCoordinates[FIRST].getLatitudePosition());
                firstCoordinates.setLongitude(centrePoint.getLongitude());
                firstCoordinates.setLongitudePosition(centrePoint.getLongitudePosition());

                secondCoordinates.setLatitude(centrePoint.getLatitude());
                secondCoordinates.setLongitudePosition(centrePoint.getLatitudePosition());
                secondCoordinates.setLongitude(parCoordinates[SECOND].getLongitude());
                secondCoordinates.setLongitudePosition(parCoordinates[SECOND].getLongitudePosition());
            }
            case 2 -> {
                firstCoordinates.setLatitude(centrePoint.getLatitude());
                firstCoordinates.setLongitude(centrePoint.getLongitude());
                firstCoordinates.setLatitudePosition(centrePoint.getLatitudePosition());
                firstCoordinates.setLongitudePosition(centrePoint.getLongitudePosition());

                secondCoordinates.setLatitude(parCoordinates[SECOND].getLatitude());
                secondCoordinates.setLatitudePosition(parCoordinates[SECOND].getLatitudePosition());
                secondCoordinates.setLongitude(parCoordinates[SECOND].getLongitude());
                secondCoordinates.setLongitudePosition(parCoordinates[SECOND].getLongitudePosition());
            }
            case 3 -> {
                firstCoordinates.setLatitude(centrePoint.getLatitude());
                firstCoordinates.setLatitudePosition(centrePoint.getLatitudePosition());
                firstCoordinates.setLongitude(parCoordinates[FIRST].getLongitude());
                firstCoordinates.setLongitudePosition(parCoordinates[FIRST].getLongitudePosition());

                secondCoordinates.setLatitude(parCoordinates[SECOND].getLatitude());
                secondCoordinates.setLatitudePosition(parCoordinates[SECOND].getLatitudePosition());
                secondCoordinates.setLongitude(centrePoint.getLongitude());
                secondCoordinates.setLongitudePosition(centrePoint.getLongitudePosition());
            }
        }

        Coordinates[] newCoordinates = new Coordinates[2];
        newCoordinates[FIRST] = firstCoordinates;
        newCoordinates[SECOND] = secondCoordinates;
        return newCoordinates;
    }

    /**
     * In case, we need to split current node into new nodes, this method
     * assign to coordinates of some data, in which quadrant of current
     * node it fits. If it isn't fit to any, so it overlap another quadrant
     * or it lies on edge, it will return -1 what means, data should be inserted
     * into current node.
     */
    public static int isFitsToQuadrant(Coordinates[] parCoordinatesOfNode, Coordinates[] parCoordinatesOfData) {
        Coordinates nodeFirst = parCoordinatesOfNode[FIRST];
        Coordinates nodeSecond = parCoordinatesOfNode[SECOND];
        Coordinates dataFirst = parCoordinatesOfData[FIRST];
        Coordinates dataSecond = parCoordinatesOfData[SECOND];

        Coordinates[] quadrantZero = coordinatesOfNewNode(parCoordinatesOfNode,0);
        Coordinates[] quadrantOne = coordinatesOfNewNode(parCoordinatesOfNode, 1);
        Coordinates[] quadrantTwo = coordinatesOfNewNode(parCoordinatesOfNode, 2);
        Coordinates[] quadrantThree = coordinatesOfNewNode(parCoordinatesOfNode, 3);

        if (areCoordinatesIntoAnother(quadrantZero,parCoordinatesOfData)) {
            return 0;
        } else if (areCoordinatesIntoAnother(quadrantOne,parCoordinatesOfData)) {
            return 1;
        } else if (areCoordinatesIntoAnother(quadrantTwo,parCoordinatesOfData)) {
            return 2;
        } else if (areCoordinatesIntoAnother(quadrantThree,parCoordinatesOfData)) {
            return 3;
        } else if (areCoordinatesIntoAnother(parCoordinatesOfNode,parCoordinatesOfData)) {
            return -1;
        } else {
            return -10;
        }
    }

    /**
     * Return true if one array of coordinates are into another
     * @param parOutherCoordinates we expect that this coorinates are outher - second cooridnates fits into this one
     * @param parInnerCoordinates we expect that this coorinates are inner - second cooridnates are around this one
     * @return
     */
    public static boolean areCoordinatesIntoAnother(Coordinates[] parOutherCoordinates, Coordinates[] parInnerCoordinates) {

        Coordinates[] parCoordinatesOfArea = parOutherCoordinates;
        Coordinates[] parCoordinatesOfQuadrant = parInnerCoordinates;

        double lowerXOfArea;
        if (parCoordinatesOfArea[FIRST].getLongitude() == Longitude.WEST) {
            lowerXOfArea = - parCoordinatesOfArea[FIRST].getLongitudePosition();
        } else {
            lowerXOfArea = parCoordinatesOfArea[FIRST].getLongitudePosition();
        }

        double upperXOfArea;
        if (parCoordinatesOfArea[SECOND].getLongitude() == Longitude.WEST) {
            upperXOfArea = - parCoordinatesOfArea[SECOND].getLongitudePosition();
        } else {
            upperXOfArea = parCoordinatesOfArea[SECOND].getLongitudePosition();
        }

        double lowerYOfArea;
        if (parCoordinatesOfArea[FIRST].getLatitude() == Latitude.NORTH) {
            lowerYOfArea = - parCoordinatesOfArea[FIRST].getLatitudePosition();
        } else {
            lowerYOfArea = parCoordinatesOfArea[FIRST].getLatitudePosition();
        }

        double upperYOfArea;
        if (parCoordinatesOfArea[SECOND].getLatitude() == Latitude.NORTH) {
            upperYOfArea = - parCoordinatesOfArea[SECOND].getLatitudePosition();
        } else {
            upperYOfArea = parCoordinatesOfArea[SECOND].getLatitudePosition();
        }

        double lowerXOfQuadrant;
        if (parCoordinatesOfQuadrant[FIRST].getLongitude() == Longitude.WEST) {
            lowerXOfQuadrant = - parCoordinatesOfQuadrant[FIRST].getLongitudePosition();
        } else {
            lowerXOfQuadrant = parCoordinatesOfQuadrant[FIRST].getLongitudePosition();
        }

        double upperXfQuadrant;
        if (parCoordinatesOfQuadrant[SECOND].getLongitude() == Longitude.WEST) {
            upperXfQuadrant = - parCoordinatesOfQuadrant[SECOND].getLongitudePosition();
        } else {
            upperXfQuadrant = parCoordinatesOfQuadrant[SECOND].getLongitudePosition();
        }

        double lowerYfQuadrant;
        if (parCoordinatesOfQuadrant[FIRST].getLatitude() == Latitude.NORTH) {
            lowerYfQuadrant = - parCoordinatesOfQuadrant[FIRST].getLatitudePosition();
        } else {
            lowerYfQuadrant = parCoordinatesOfQuadrant[FIRST].getLatitudePosition();
        }

        double upperYfQuadrant;
        if (parCoordinatesOfQuadrant[SECOND].getLatitude() == Latitude.NORTH) {
            upperYfQuadrant = - parCoordinatesOfQuadrant[SECOND].getLatitudePosition();
        } else {
            upperYfQuadrant = parCoordinatesOfQuadrant[SECOND].getLatitudePosition();
        }

        return (lowerXOfArea <= lowerXOfQuadrant &&
                upperXOfArea >= upperXfQuadrant &&
                lowerYOfArea <= lowerYfQuadrant &&
                upperYOfArea >= upperYfQuadrant);




//        Coordinates outherFirst = parOutherCoordinates[FIRST];
//        Coordinates outherSecond = parOutherCoordinates[SECOND];
//        Coordinates innerFirst = parInnerCoordinates[FIRST];
//        Coordinates innerSecond = parInnerCoordinates[SECOND];
//        boolean firstGood = false;
//        boolean secondGood = false;
//
//        // osetrenie hornej suradnicky
//        //1.1
//        if (outherFirst.getLatitude() == innerFirst.getLatitude() &&
//                outherFirst.getLatitude() == Latitude.NORTH &&
//                outherFirst.getLatitudePosition() > innerFirst.getLatitudePosition()
//            &&
//                outherFirst.getLongitude() == innerFirst.getLongitude() &&
//                        outherFirst.getLongitude() == Longitude.WEST &&
//                        outherFirst.getLongitudePosition() > innerFirst.getLongitudePosition()) {
//
//            firstGood = true;
//        // 3.1
//        } else if (outherFirst.getLatitude() == innerFirst.getLatitude() &&
//                outherFirst.getLatitude() == Latitude.SOUTH &&
//                outherFirst.getLatitudePosition() < innerFirst.getLatitudePosition()
//            &&
//                outherFirst.getLongitude() == innerFirst.getLongitude() &&
//                        outherFirst.getLongitude() == Longitude.WEST &&
//                        outherFirst.getLongitudePosition() > innerFirst.getLongitudePosition()) {
//            firstGood = true;
//        // 4.1
//        } else if (outherFirst.getLatitude() == innerFirst.getLatitude() &&
//                outherFirst.getLatitude() == Latitude.SOUTH &&
//                outherFirst.getLatitudePosition() < innerFirst.getLatitudePosition()
//                &&
//                outherFirst.getLongitude() == innerFirst.getLongitude() &&
//                outherFirst.getLongitude() == Longitude.EAST &&
//                outherFirst.getLongitudePosition() < innerFirst.getLongitudePosition()) {
//            firstGood = true;
//        // 1.3
//        } else if (outherFirst.getLatitude() == Latitude.NORTH &&
//                  innerFirst.getLatitude() == Latitude.SOUTH
//                &&
//                outherFirst.getLongitude() == innerFirst.getLongitude() &&
//                        outherFirst.getLongitude() == Longitude.WEST &&
//                        outherFirst.getLongitudePosition() > innerFirst.getLongitudePosition()) {
//            firstGood = true;
//        // 2.2
//        } else if (outherFirst.getLatitude() == Latitude.NORTH &&
//                innerFirst.getLatitude() == Latitude.SOUTH
//                &&
//                outherFirst.getLongitude() == innerFirst.getLongitude() &&
//                outherFirst.getLongitude() == Longitude.EAST &&
//                outherFirst.getLongitudePosition() < innerFirst.getLongitudePosition()) {
//            firstGood = true;
//        // 2.1
//        } else if (outherFirst.getLatitude() == innerFirst.getLatitude() &&
//                outherFirst.getLatitude() == Latitude.NORTH &&
//                outherFirst.getLatitudePosition() > innerFirst.getLatitudePosition()
//                &&
//                outherFirst.getLongitude() == innerFirst.getLongitude() &&
//                outherFirst.getLongitude() == Longitude.EAST &&
//                outherFirst.getLongitudePosition() < innerFirst.getLongitudePosition()) {
//            firstGood = true;
//        // 3.2
//        } else if (outherFirst.getLatitude() == innerFirst.getLatitude() &&
//                outherFirst.getLatitude() == Latitude.SOUTH &&
//                outherFirst.getLatitudePosition() < innerFirst.getLatitudePosition()
//                &&
//                outherFirst.getLongitude() == Longitude.WEST &&
//                innerFirst.getLongitude() == Longitude.EAST ){
//            firstGood = true;
//        // 1.2
//        } else if (outherFirst.getLatitude() == innerFirst.getLatitude() &&
//                outherFirst.getLatitude() == Latitude.NORTH &&
//                outherFirst.getLatitudePosition() > innerFirst.getLatitudePosition()
//                &&
//                outherFirst.getLongitude() == Longitude.WEST &&
//                innerFirst.getLongitude() == Longitude.EAST) {
//            firstGood = true;
//        // 1.4
//        } else if (outherFirst.getLatitude() == Latitude.NORTH &&
//                innerFirst.getLatitude() == Latitude.SOUTH &&
//                outherFirst.getLongitude() == Longitude.WEST &&
//                innerFirst.getLongitude() == Longitude.EAST
//        ) {
//            firstGood = true;
//        }
//
//        // osetrenie druhej suradnicky
//        //1.1
//        if (outherSecond.getLatitude() == innerSecond.getLatitude() &&
//                outherSecond.getLatitude() == Latitude.SOUTH &&
//                outherSecond.getLatitudePosition() > innerSecond.getLatitudePosition()
//                &&
//                outherSecond.getLongitude() == innerSecond.getLongitude() &&
//                outherSecond.getLongitude() == Longitude.EAST &&
//                outherSecond.getLongitudePosition() > innerSecond.getLongitudePosition()
//        ) {
//            secondGood = true;
//        // 1.2
//        } else if (outherSecond.getLatitude() == innerSecond.getLatitude() &&
//                outherSecond.getLatitude() == Latitude.SOUTH &&
//                outherSecond.getLatitudePosition() > innerSecond.getLatitudePosition()
//                &&
//                outherSecond.getLongitude() == Longitude.EAST &&
//                innerSecond.getLongitude() == Longitude.WEST
//        ) {
//            secondGood = true;
//        // 1.3
//        } else if (
//                outherSecond.getLatitude() == Latitude.SOUTH &&
//                    innerSecond.getLatitude() == Latitude.NORTH &&
//                    outherSecond.getLongitude() == Longitude.EAST &&
//                    innerSecond.getLongitude() == Longitude.WEST
//        ) {
//            secondGood = true;
//        // 1.4
//        } else if (
//                outherSecond.getLatitude() == Latitude.SOUTH &&
//                        innerSecond.getLatitude() == Latitude.NORTH
//                        &&
//                        outherSecond.getLongitude() == innerSecond.getLongitude() &&
//                        outherSecond.getLongitude() == Longitude.EAST &&
//                        outherSecond.getLongitudePosition() > innerSecond.getLongitudePosition()
//        ) {
//           secondGood = true;
//       // 2.1
//        } else if (outherSecond.getLatitude() == innerSecond.getLatitude() &&
//                outherSecond.getLatitude() == Latitude.NORTH &&
//                outherSecond.getLatitudePosition() < innerSecond.getLatitudePosition()
//                                &&
//        outherSecond.getLongitude() == innerSecond.getLongitude() &&
//                outherSecond.getLongitude() == Longitude.EAST &&
//                outherSecond.getLongitudePosition() > innerSecond.getLongitudePosition()
//        ) {
//            secondGood = true;
//        // 2.2
//        } else if (outherSecond.getLatitude() == innerSecond.getLatitude() &&
//                outherSecond.getLatitude() == Latitude.NORTH &&
//                outherSecond.getLatitudePosition() < innerSecond.getLatitudePosition()
//                &&
//                outherSecond.getLongitude() == Longitude.EAST &&
//                innerSecond.getLongitude() == Longitude.WEST
//        ) {
//            secondGood = true;
//        // 3.1
//        } else if (outherSecond.getLatitude() == innerSecond.getLatitude() &&
//                outherSecond.getLatitude() == Latitude.SOUTH &&
//                outherSecond.getLatitudePosition() > innerSecond.getLatitudePosition()
//                &&
//                outherSecond.getLongitude() == innerSecond.getLongitude() &&
//                outherSecond.getLongitude() == Longitude.WEST &&
//                outherSecond.getLatitudePosition() < innerSecond.getLatitudePosition()
//        ) {
//            secondGood = true;
//        // 3.2
//        } else if (outherSecond.getLatitude() == Latitude.SOUTH &&
//                innerSecond.getLatitude() == Latitude.NORTH
//                &&
//            outherSecond.getLongitude() == innerSecond.getLongitude() &&
//                outherSecond.getLongitude() == Longitude.WEST &&
//                outherSecond.getLatitudePosition() < innerSecond.getLatitudePosition()
//        ) {
//            secondGood = true;
//        // 4.1
//        } else if (outherSecond.getLatitude() == innerSecond.getLatitude() &&
//                outherSecond.getLatitude() == Latitude.NORTH &&
//                outherSecond.getLatitudePosition() < innerSecond.getLatitudePosition()
//                                &&
//        outherSecond.getLongitude() == innerSecond.getLongitude() &&
//                outherSecond.getLongitude() == Longitude.WEST &&
//                outherSecond.getLatitudePosition() < innerSecond.getLatitudePosition()
//        ) {
//            secondGood = true;
//        }
//        return firstGood && secondGood;
    }

    public static boolean belongsToArea(Coordinates[] parCoordinatesOfArea, Coordinates[] parCoordinatesOfQuadrant) {
        // definiction of pseudoCoordinates
        double lowerXOfArea;
        if (parCoordinatesOfArea[FIRST].getLongitude() == Longitude.WEST) {
            lowerXOfArea = - parCoordinatesOfArea[FIRST].getLongitudePosition();
        } else {
            lowerXOfArea = parCoordinatesOfArea[FIRST].getLongitudePosition();
        }

        double upperXOfArea;
        if (parCoordinatesOfArea[SECOND].getLongitude() == Longitude.WEST) {
            upperXOfArea = - parCoordinatesOfArea[SECOND].getLongitudePosition();
        } else {
            upperXOfArea = parCoordinatesOfArea[SECOND].getLongitudePosition();
        }

        double lowerYOfArea;
        if (parCoordinatesOfArea[FIRST].getLatitude() == Latitude.NORTH) {
            lowerYOfArea = - parCoordinatesOfArea[FIRST].getLatitudePosition();
        } else {
            lowerYOfArea = parCoordinatesOfArea[FIRST].getLatitudePosition();
        }

        double upperYOfArea;
        if (parCoordinatesOfArea[SECOND].getLatitude() == Latitude.NORTH) {
            upperYOfArea = - parCoordinatesOfArea[SECOND].getLatitudePosition();
        } else {
            upperYOfArea = parCoordinatesOfArea[SECOND].getLatitudePosition();
        }

        double lowerXOfQuadrant;
        if (parCoordinatesOfQuadrant[FIRST].getLongitude() == Longitude.WEST) {
            lowerXOfQuadrant = - parCoordinatesOfQuadrant[FIRST].getLongitudePosition();
        } else {
            lowerXOfQuadrant = parCoordinatesOfQuadrant[FIRST].getLongitudePosition();
        }

        double upperXfQuadrant;
        if (parCoordinatesOfQuadrant[SECOND].getLongitude() == Longitude.WEST) {
            upperXfQuadrant = - parCoordinatesOfQuadrant[SECOND].getLongitudePosition();
        } else {
            upperXfQuadrant = parCoordinatesOfQuadrant[SECOND].getLongitudePosition();
        }

        double lowerYfQuadrant;
        if (parCoordinatesOfQuadrant[FIRST].getLatitude() == Latitude.NORTH) {
            lowerYfQuadrant = - parCoordinatesOfQuadrant[FIRST].getLatitudePosition();
        } else {
            lowerYfQuadrant = parCoordinatesOfQuadrant[FIRST].getLatitudePosition();
        }

        double upperYfQuadrant;
        if (parCoordinatesOfQuadrant[SECOND].getLatitude() == Latitude.NORTH) {
            upperYfQuadrant = - parCoordinatesOfQuadrant[SECOND].getLatitudePosition();
        } else {
            upperYfQuadrant = parCoordinatesOfQuadrant[SECOND].getLatitudePosition();
        }

        //1
        return ((lowerXOfArea > lowerXOfQuadrant &&
                lowerXOfArea < upperXfQuadrant &&
                lowerYOfArea > lowerYfQuadrant &&
                lowerYOfArea < upperYfQuadrant) ||
                //2
                (upperXOfArea > lowerXOfQuadrant &&
                        upperXOfArea < upperXfQuadrant &&
                        lowerYOfArea > lowerYfQuadrant &&
                        lowerYOfArea < upperYfQuadrant) ||
                //3
                (lowerXOfArea > lowerXOfQuadrant &&
                        lowerXOfArea < upperXfQuadrant &&
                        upperYOfArea > lowerYfQuadrant &&
                        upperYOfArea < upperYfQuadrant) ||
                //4
                (upperXOfArea > lowerXOfQuadrant &&
                        upperXOfArea < upperXfQuadrant &&
                        upperYOfArea > lowerYfQuadrant &&
                        upperYOfArea < upperYfQuadrant) ||
                //5
                (lowerYOfArea > lowerYfQuadrant &&
                        lowerYOfArea < upperYfQuadrant &&
                        lowerXOfArea < lowerXOfQuadrant &&
                        upperXOfArea > upperXfQuadrant) ||
                //6
                (upperYOfArea > lowerYfQuadrant &&
                        upperYOfArea < upperYfQuadrant &&
                        lowerXOfArea < lowerXOfQuadrant &&
                        upperXOfArea > upperXfQuadrant) ||
                //7
                (lowerXOfArea > lowerXOfQuadrant &&
                        lowerXOfArea < upperXfQuadrant &&
                        lowerYOfArea < lowerYfQuadrant &&
                        upperYOfArea > upperYfQuadrant) ||
                //8
                (upperXOfArea > lowerXOfQuadrant &&
                        upperXOfArea < upperXfQuadrant &&
                        lowerYOfArea < lowerYfQuadrant &&
                        upperYOfArea > upperYfQuadrant));
    }

 //   public static Coordinates[] checksCoordinatesOrder(Coordinates parFirstCoordinate, Coordinates parSecondCoordinate) {
//        if (parFirstCoordinate.getLatitude() == parSecondCoordinate.getLatitude() &&
//                parFirstCoordinate.getLongitude() == Latitude.SOUTH
//        parFirstCoordinate.getLatitudePosition() < parSecondCoordinate.getLatitudePosition()
//            &&
//        parFirstCoordinate.getLongitude() == parSecondCoordinate.getLongitude() &&
//                parFirstCoordinate.getLongitudePosition()
   // }

}