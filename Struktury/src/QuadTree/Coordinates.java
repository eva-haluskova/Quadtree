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
//
//    public void maximizeCoordinatesSize(double parScale) {
//        double lowerX = this.lowerX;
//        double lowerY = this.lowerY;
//        double upperX = this.upperX;
//        double upperY = this.upperY;
//
//        double centreX = (upperX + lowerX) / 2;
//        double centreY = (upperY + lowerY) / 2;
//
//        double deltaX = centreX - lowerX;
//        double deltaY = centreY - lowerY;
//
//        this.lowerX = centreX - (deltaX * parScale);
//        this.lowerY = centreY - (deltaY * parScale);
//        this.upperX = centreX + (deltaX * parScale);
//        this.upperY = centreY + (deltaY * parScale);
//    }

    public String[] returnCoordinatesInString() {
        String[] stringToReturn = {Double.toString(this.getLowerY()),Double.toString(this.getUpperX())
                ,Double.toString(this.getLowerY()),Double.toString(this.getUpperY())};
        return stringToReturn;
    }
}