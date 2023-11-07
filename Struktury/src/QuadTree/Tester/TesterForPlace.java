package QuadTree.Tester;

import QuadTree.QuadTree;
import QuadTree.Tester.QuadTreeTester;
import QuadTree.Coordinates;
import QuadTree.QuadTreeNode;
import QuadTree.Data;
import QuadTree.Place;

import java.util.Random;

/**
 * Class specified for testing Quad tree with data of type Place.
 */
public class TesterForPlace extends QuadTreeTester {

    public TesterForPlace(QuadTree parTree) {
        super(parTree);
        this.testTree = parTree;
    }

    /**
     * Specific method for inserting Place into quadTree, it means
     * specific creating of Place instances.
     */
    @Override
    public void generateInsert(int parNumber, Random random, Coordinates parCoordinatesForSearch) {

        int widthOfPolygon = 10;
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
            //Coordinates coors = new Coordinates(x1, y1);
            Place newObject = new Place("Miesto" + i);
            Data<Place> data = new Data(newObject, coors, i);

            this.testTree.insert(data, testTree.getRoot());
            super.testData.add(data);

            if (super.belongToArea(parCoordinatesForSearch,data.getCoordinates())) {
                dataToFindInArea.add(data);
            }

            if (parCoordinatesForSearch.equals(data.getCoordinates())) {
                dataToFindOfPoint.add(data);
            }
        }

        System.out.println("Insert of place is done!");
    }
}
