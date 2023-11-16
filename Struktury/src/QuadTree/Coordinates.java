package QuadTree;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents class of some objects coordinates. Holds four values -
 * two for x axis, two for y axis. If we want to have only point,
 * not polygon, class contain constructor just for point.
 */

public class Coordinates {

    private double lowerX;
    private double upperX;
    private double lowerY;
    private double upperY;

    private static final double EPSILON = 0.00000001;

    /**
     * Constructor for polygon
     */
    public Coordinates(
            double parLowerX,
            double parUpperX,
            double parLowerY,
            double parUpperY
    ) {
        this.lowerX = parLowerX;
        this.upperX = parUpperX;
        this.lowerY = parLowerY;
        this.upperY = parUpperY;
    }

    /**
     * Constructor for point
     */
    public Coordinates(
            double parX,
            double parY
    ) {
        this.lowerX = parX;
        this.upperX = parX;
        this.lowerY = parY;
        this.upperY = parY;
    }

    /**
     * Copy constructor
     */
    public Coordinates(
            @NotNull Coordinates otherCoordinates
    ) {
        this.lowerX = otherCoordinates.getLowerX();
        this.upperX = otherCoordinates.getUpperX();
        this.lowerY = otherCoordinates.getLowerY();
        this.upperY = otherCoordinates.getUpperY();
    }

    /**
     * getters and setters of specific coordinates
     */
    public double getLowerX() {
        return lowerX;
    }

    public void setLowerX(double lowerX) {
        this.lowerX = lowerX;
    }

    public double getUpperX() {
        return upperX;
    }

    public void setUpperX(double upperX) {
        this.upperX = upperX;
    }

    public double getLowerY() {
        return lowerY;
    }

    public void setLowerY(double lowerY) {
        this.lowerY = lowerY;
    }

    public double getUpperY() {
        return upperY;
    }

    public void setUpperY(double upperY) {
        this.upperY = upperY;
    }

    /**
     * Return true when coordinates belonging to this object are the "same"
     * with coordinates given as a parameter, false otherwise
     */
    public boolean equals(Coordinates parCoordinates) {
        return Math.abs(this.lowerX - parCoordinates.getLowerX()) < EPSILON &&
                Math.abs(this.upperX - parCoordinates.getUpperX()) < EPSILON &&
                Math.abs(this.lowerY - parCoordinates.getLowerY()) < EPSILON &&
                Math.abs(this.upperY - parCoordinates.getUpperY()) < EPSILON;
    }

    /**
     * Return string representation of coordinates
     */
    public String[] returnCoordinatesInString() {
        String[] stringToReturn = {Double.toString(this.getLowerY()),Double.toString(this.getUpperX())
                ,Double.toString(this.getLowerY()),Double.toString(this.getUpperY())};
        return stringToReturn;
    }

    /**
     * Static methods for outer usage
     */

    /**
     * Checks if some cooridnates whole belongs to some other coordinates
     */
    public static boolean isIncludingWholeData(Coordinates parCoordinatesOfArea, Coordinates parCoordinatesOfData) {
        return (parCoordinatesOfArea.getLowerX() <= parCoordinatesOfData.getLowerX() &&
                parCoordinatesOfArea.getUpperX() >= parCoordinatesOfData.getUpperX() &&
                parCoordinatesOfArea.getLowerY() <= parCoordinatesOfData.getLowerY() &&
                parCoordinatesOfArea.getUpperY() >= parCoordinatesOfData.getUpperY());
    }

    /**
     * Return true if some part of quadrant belong to area, or if some data belongs to area
     */
    public static boolean belongsToArea(Coordinates parCoordinatesOfArea, Coordinates parCoordinatesOfQuadrant) {
        return (
                //1
                (parCoordinatesOfArea.getLowerX() > parCoordinatesOfQuadrant.getLowerX() &&
                        parCoordinatesOfArea.getLowerX() < parCoordinatesOfQuadrant.getUpperX() &&
                        parCoordinatesOfArea.getLowerY() > parCoordinatesOfQuadrant.getLowerY() &&
                        parCoordinatesOfArea.getLowerY() < parCoordinatesOfQuadrant.getUpperY()) ||
                        //2
                        (parCoordinatesOfArea.getUpperX() > parCoordinatesOfQuadrant.getLowerX() &&
                                parCoordinatesOfArea.getUpperX() < parCoordinatesOfQuadrant.getUpperX() &&
                                parCoordinatesOfArea.getLowerY() > parCoordinatesOfQuadrant.getLowerY() &&
                                parCoordinatesOfArea.getLowerY() < parCoordinatesOfQuadrant.getUpperY()) ||
                        //3
                        (parCoordinatesOfArea.getLowerX() > parCoordinatesOfQuadrant.getLowerX() &&
                                parCoordinatesOfArea.getLowerX() < parCoordinatesOfQuadrant.getUpperX() &&
                                parCoordinatesOfArea.getUpperY() > parCoordinatesOfQuadrant.getLowerY() &&
                                parCoordinatesOfArea.getUpperY() < parCoordinatesOfQuadrant.getUpperY()) ||
                        //4
                        (parCoordinatesOfArea.getUpperX() > parCoordinatesOfQuadrant.getLowerX() &&
                                parCoordinatesOfArea.getUpperX() < parCoordinatesOfQuadrant.getUpperX() &&
                                parCoordinatesOfArea.getUpperY() > parCoordinatesOfQuadrant.getLowerY() &&
                                parCoordinatesOfArea.getUpperY() < parCoordinatesOfQuadrant.getUpperY()) ||
                        //5
                        (parCoordinatesOfArea.getLowerY() > parCoordinatesOfQuadrant.getLowerY() &&
                                parCoordinatesOfArea.getLowerY() < parCoordinatesOfQuadrant.getUpperY() &&
                                parCoordinatesOfArea.getLowerX() < parCoordinatesOfQuadrant.getLowerX() &&
                                parCoordinatesOfArea.getUpperX() > parCoordinatesOfQuadrant.getUpperX()) ||
                        //6
                        (parCoordinatesOfArea.getUpperY() > parCoordinatesOfQuadrant.getLowerY() &&
                                parCoordinatesOfArea.getUpperY() < parCoordinatesOfQuadrant.getUpperY() &&
                                parCoordinatesOfArea.getLowerX() < parCoordinatesOfQuadrant.getLowerX() &&
                                parCoordinatesOfArea.getUpperX() > parCoordinatesOfQuadrant.getUpperX()) ||
                        //7
                        (parCoordinatesOfArea.getLowerX() > parCoordinatesOfQuadrant.getLowerX() &&
                                parCoordinatesOfArea.getLowerX() < parCoordinatesOfQuadrant.getUpperX() &&
                                parCoordinatesOfArea.getLowerY() < parCoordinatesOfQuadrant.getLowerY() &&
                                parCoordinatesOfArea.getUpperY() > parCoordinatesOfQuadrant.getUpperY()) ||
                        //8
                        (parCoordinatesOfArea.getUpperX() > parCoordinatesOfQuadrant.getLowerX() &&
                                parCoordinatesOfArea.getUpperX() < parCoordinatesOfQuadrant.getUpperX() &&
                                parCoordinatesOfArea.getLowerY() < parCoordinatesOfQuadrant.getLowerY() &&
                                parCoordinatesOfArea.getUpperY() > parCoordinatesOfQuadrant.getUpperY())
        );
    }

    /**
     * Returns coordinates for new node created in specific node
     */
    public static Coordinates coordinatesOfNewNode(int parQuadrant, Coordinates parCoordinatesOfActual) {

        Coordinates newCoordinates = new Coordinates(0,0,0,0);

        switch (parQuadrant) {
            case 0 -> {
                newCoordinates.setLowerX(parCoordinatesOfActual.getLowerX());
                newCoordinates.setUpperX((parCoordinatesOfActual.getUpperX() + parCoordinatesOfActual.getLowerX()) / 2);
                newCoordinates.setLowerY(parCoordinatesOfActual.getLowerY());
                newCoordinates.setUpperY((parCoordinatesOfActual.getUpperY() + parCoordinatesOfActual.getLowerY()) / 2);
            }
            case 1 -> {
                newCoordinates.setLowerX((parCoordinatesOfActual.getUpperX() + parCoordinatesOfActual.getLowerX()) / 2);
                newCoordinates.setUpperX(parCoordinatesOfActual.getUpperX());
                newCoordinates.setLowerY(parCoordinatesOfActual.getLowerY());
                newCoordinates.setUpperY((parCoordinatesOfActual.getUpperY() + parCoordinatesOfActual.getLowerY()) / 2);
            }
            case 2 -> {
                newCoordinates.setLowerX((parCoordinatesOfActual.getUpperX() + parCoordinatesOfActual.getLowerX()) / 2);
                newCoordinates.setUpperX(parCoordinatesOfActual.getUpperX());
                newCoordinates.setLowerY((parCoordinatesOfActual.getUpperY() + parCoordinatesOfActual.getLowerY()) / 2);
                newCoordinates.setUpperY(parCoordinatesOfActual.getUpperY());
            }
            case 3 -> {
                newCoordinates.setLowerX(parCoordinatesOfActual.getLowerX());
                newCoordinates.setUpperX((parCoordinatesOfActual.getUpperX() + parCoordinatesOfActual.getLowerX()) / 2);
                newCoordinates.setLowerY((parCoordinatesOfActual.getUpperY() + parCoordinatesOfActual.getLowerY()) / 2);
                newCoordinates.setUpperY(parCoordinatesOfActual.getUpperY());
            }
        }
        return newCoordinates;
    }

    /**
     * In case, we need to split current node into new nodes, this method
     * assign to coordinates of some data, in which quadrant of current
     * node it fits. If it isn't fit to any, so it overlap another quadrant
     * or it lies on edge, it will return -1 what means, data should be inserted
     * into current node.
     */
    // todo osetirt vstupovanie okrajov
    public static int isFitsToQuadrant(Coordinates parCoordinatesOfActual, Coordinates parCoordinates) {

        if (
                parCoordinatesOfActual.getLowerX() <= parCoordinates.getLowerX() &&
                        (parCoordinatesOfActual.getLowerX() + parCoordinatesOfActual.getUpperX()) / 2
                                >= parCoordinates.getUpperX() &&
                        parCoordinatesOfActual.getLowerY() <= parCoordinates.getLowerY() &&
                        (parCoordinatesOfActual.getLowerY() + parCoordinatesOfActual.getUpperY()) / 2
                                >= parCoordinates.getUpperY()
        ) {
            return 0;
        } else if (
                (parCoordinatesOfActual.getLowerX() + parCoordinatesOfActual.getUpperX()) / 2
                        <= parCoordinates.getLowerX() &&
                        parCoordinatesOfActual.getUpperX() >= parCoordinates.getUpperX() &&
                        parCoordinatesOfActual.getLowerY() <= parCoordinates.getLowerY() &&
                        (parCoordinatesOfActual.getLowerY() + parCoordinatesOfActual.getUpperY()) / 2
                                >= parCoordinates.getUpperY()
        ) {
            return 1;
        } else if (
                (parCoordinatesOfActual.getLowerX() + parCoordinatesOfActual.getUpperX()) / 2
                        <= parCoordinates.getLowerX() &&
                        parCoordinatesOfActual.getUpperX() >= parCoordinates.getUpperX() &&
                        (parCoordinatesOfActual.getLowerY() + parCoordinatesOfActual.getUpperY()) / 2
                                <= parCoordinates.getLowerY() &&
                        parCoordinatesOfActual.getUpperY() >= parCoordinates.getUpperY()
        ) {
            return 2;
        } else if (
                parCoordinatesOfActual.getLowerX() <= parCoordinates.getLowerX() &&
                        (parCoordinatesOfActual.getLowerX() + parCoordinatesOfActual.getUpperX()) / 2
                                >= parCoordinates.getUpperX() &&
                        (parCoordinatesOfActual.getLowerY() + parCoordinatesOfActual.getUpperY()) / 2
                                <= parCoordinates.getLowerY() &&
                        parCoordinatesOfActual.getUpperY() >= parCoordinates.getUpperY()
        ) {
            return 3;
        } else if (
                parCoordinatesOfActual.getLowerX() <= parCoordinates.getLowerX() &&
                        parCoordinatesOfActual.getUpperX() >= parCoordinates.getUpperX() &&
                        parCoordinatesOfActual.getLowerY() <= parCoordinates.getLowerY() &&
                        parCoordinatesOfActual.getUpperY() >= parCoordinates.getUpperY()
        ) {
            return -1;
        } else {
            return -10;
        }

    }


    /**
     * Changes coorinates accoring to scale parameter.
     * @param parScale - pay attionton! If you want to minimize cooridnates, you need to
     *                 put here 1/parScale param, if you want to maximize, you put parScale
     */
    public void changeCoordinatesSize(double parScale) {
        double lowerX = this.lowerX;
        double lowerY = this.lowerY;
        double upperX = this.upperX;
        double upperY = this.upperY;

        double centreX = (upperX + lowerX) / 2;
        double centreY = (upperY + lowerY) / 2;

        double deltaX = centreX - lowerX;
        double deltaY = centreY - lowerY;

        this.lowerX = centreX - (deltaX * (parScale));
        this.lowerY = centreY - (deltaY * (parScale));
        this.upperX = centreX + (deltaX * (parScale));
        this.upperY = centreY + (deltaY * (parScale));
    }

    public Coordinates potencialChangedSize(double parScale) {
        double lowerX = this.lowerX;
        double lowerY = this.lowerY;
        double upperX = this.upperX;
        double upperY = this.upperY;

        double centreX = (upperX + lowerX) / 2;
        double centreY = (upperY + lowerY) / 2;

        double deltaX = centreX - lowerX;
        double deltaY = centreY - lowerY;

        lowerX = centreX - (deltaX * (parScale));
        lowerY = centreY - (deltaY * (parScale));
        upperX = centreX + (deltaX * (parScale));
        upperY = centreY + (deltaY * (parScale));
        Coordinates coors = new Coordinates(lowerX,upperX,lowerY,upperY);
        return coors;
    }


}