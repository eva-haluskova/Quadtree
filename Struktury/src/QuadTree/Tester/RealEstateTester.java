package QuadTree.Tester;

import QuadTree.Coordinates;
import QuadTree.QuadTree;
import Data.RealEstate;
import Data.GPS;
import QuadTree.Data;

import java.util.Random;
import java.util.UUID;

public class RealEstateTester extends QuadTreeTester {

    public RealEstateTester(QuadTree parTree) {
        super(parTree);
        this.testTree = parTree;
    }


    @Override
    public void generateInsert(int parNumber, double parSizeOfObject,Random random, Coordinates parCoordinatesForSearch) {
        double widthOfPolygon = parSizeOfObject;
        dataToFindInArea.clear();
        dataToFindOfPoint.clear();
        testData.clear();

        // pay attention, this assume that tree starts with [0,0] point!!
        double sizeOfXAxes = this.testTree.getRangeOfTree().getUpperX() - this.testTree.getRangeOfTree().getLowerX();
        double sizeOfYAxes = this.testTree.getRangeOfTree().getUpperY() - this.testTree.getRangeOfTree().getLowerY();

        for (int i = 1; i <= parNumber; i++) {
            double x1 = random.nextDouble() * (sizeOfXAxes - widthOfPolygon - 1);
            double x2 = x1 + widthOfPolygon;
            double y1 = random.nextDouble() * (sizeOfYAxes - widthOfPolygon - 1);
            double y2 = y1 + widthOfPolygon;

            Coordinates coors = new Coordinates(x1, x2, y1, y2);
            GPS one = new GPS(GPS.Latitude.NORTH,x1,GPS.Longitude.EAST,40);
            GPS two = new GPS(GPS.Latitude.NORTH,x1,GPS.Longitude.WEST,30);
            GPS[] par = {one, two};
            RealEstate newObject = new RealEstate("RealEstate number " + i,par,i);
            Data<RealEstate> data = new Data(newObject, coors, UUID.randomUUID());

            this.testTree.insert(data);
            super.testData.add(data);

            if (super.belongToArea(parCoordinatesForSearch,data.getCoordinates())) {
                dataToFindInArea.add(data);
            }

            if (parCoordinatesForSearch.equals(data.getCoordinates())) {
                dataToFindOfPoint.add(data);
            }
        }

        System.out.println("Insert of RealEstates is done!");
    }
}
