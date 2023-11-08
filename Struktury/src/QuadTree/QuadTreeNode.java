package QuadTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class QuadTreeNode<T> {

    private static final int CHILDREN = 4;

    private Coordinates coordinates;
    private QuadTreeNode<T>[] children;
    private ArrayList<Data<T>> listOfData;

    private int level;

    public QuadTreeNode(Data<T> parData, Coordinates parCoordinates, int parLevel) {
        this.children = new QuadTreeNode[CHILDREN];
        this.listOfData = new ArrayList<>();
        this.listOfData.add(parData);
        this.coordinates = parCoordinates;
        this.level = parLevel;
        this.initChildren();
    }

    /**
     * Basic reqests
     */
    public boolean isLeaf() {
        return this.getCountOfChildren() == 0;
    }

    public boolean isEmpty() {
        return listOfData.isEmpty();
    }

    public boolean hasNthChild(int parIndex) {
        return this.children[parIndex] != null ? true : false;
    }

    /**
     * Other reqests
     */
    public boolean isSKFits(Coordinates parCoordinates) {
        if (this.coordinates.getLowerX() < parCoordinates.getLowerX() &&
                this.coordinates.getUpperX() > parCoordinates.getUpperX() &&
                this.coordinates.getLowerY() < parCoordinates.getLowerY() &&
                this.coordinates.getUpperY() > parCoordinates.getUpperY()) {
            return true;
        }
        return false;
    }

    /**
     * return true if in data list are some data with same coordiantes as parameter
     */
    // TODO isnt it more reasonable to put as parameter directly coordinates?
    public boolean hasSameCoordinates(Data<T> parData) {
        for (Data<T> data: this.listOfData) {
            if (data.getCoordinates().equals(parData.getCoordinates())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Modifiers
     */
    public void addData(Data<T> parData) {
        this.listOfData.add(parData);
    }

    public void addMultipleData(ArrayList<Data<T>> parData) {
        this.listOfData.addAll(parData);
    }

    public Data<T> removeData(int parIndex) {
        return this.listOfData.remove(parIndex);
    }

    // pay attention if data with this id dont exist!!!
    public Data<T> removeData(Data<T> parData) {
        for (int i = 0; i < this.listOfData.size(); i++) {
            if (this.listOfData.get(i).equals(parData)) {
                return this.listOfData.remove(i);
            }
        }
        return null;
    }

    public void removeAllData() {
        this.listOfData.clear();
    }

    /**
     * Getters and setters
     */
    public ArrayList<Data<T>> getListOfData() {
        return new ArrayList<>(this.listOfData);
    }

    public QuadTreeNode<T>[] getChildren(){
        return Arrays.copyOf(this.children, this.children.length);
    }

    public ArrayList<Data<T>> getDataWithSameCoordinates(Coordinates parCoordinates) {

        ArrayList<Data<T>> listToReturn = new ArrayList<>();

        for (Data<T> data: this.listOfData) {
            if (data.getCoordinates().equals(parCoordinates)) {
                listToReturn.add(data);
            }
        }
        return listToReturn;
    }

    public QuadTreeNode<T> accessToNthSon(int parIndex) {
        return this.children[parIndex];
    }

    public void setChild(int parIndex, QuadTreeNode<T> parNewChildren) {
        this.children[parIndex] = parNewChildren;
    }

    public void removeChild(int parIndex) {
        this.children[parIndex] = null;
    }

    public void removeChildren() {
        for (int i = 0; i < CHILDREN; i++) {
            this.children[i] = null;
        }
    }

    public int getLevel() {
        return this.level;
    }

    public int getCountOfChildren() {
        int countOfChildren = 0;
        for (int i = 0; i < CHILDREN; i++) {
            if(this.children[i] != null) {
                countOfChildren++;
            }
        }
        return countOfChildren;
    }

    // use only if you know node has only one child!!
    public int getIndexOfOnlyOneChild() {
        for (int i = 0; i < CHILDREN; i++) {
            if(this.children[i] != null) {
                return i;
            }
        }
        return -1;
    }

    public void setCoordinates(Coordinates parCoordinates) {
        this.coordinates = parCoordinates;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    /**
     * This method is designed for finding area in tree. It returns
     * array of indexes which are connected with wanted area.
     */
    public ArrayList<Integer> getIncludingQuadrants(Coordinates parCoordinatesOfArea) {

        ArrayList<Integer> indices = new ArrayList<>();

        for (int i = 0; i < CHILDREN; i++) {
            if (this.children[i] != null){
                if (this.children[i].belongsToArea(parCoordinatesOfArea, this.children[i].getCoordinates())
                || this.children[i].isIncludingWholeData(parCoordinatesOfArea, this.children[i].getCoordinates()))
                {
                    indices.add(i);
                }
            }
        }
        return indices;
    }

    /**
     * Return true if some part of quadrant belong to area, or if some data belongs to area
     */
    private boolean belongsToArea(Coordinates parCoordinatesOfArea, Coordinates parCoordinatesOfQuadrant) {
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
     * Return true if whole area of node belongs to searched area
     */
    public boolean isIncludingWholeNodeArea(Coordinates parCoordinatesOfArea) {
        return (parCoordinatesOfArea.getLowerX() <= this.coordinates.getLowerX() &&
                parCoordinatesOfArea.getUpperX() >= this.coordinates.getUpperX() &&
                parCoordinatesOfArea.getLowerY() <= this.coordinates.getLowerY() &&
                parCoordinatesOfArea.getUpperY() >= this.coordinates.getUpperY());
    }

    public boolean isIncludingWholeData(Coordinates parCoordinatesOfArea, Coordinates parCoordinatesOfData) {
        return (parCoordinatesOfArea.getLowerX() <= parCoordinatesOfData.getLowerX() &&
                parCoordinatesOfArea.getUpperX() >= parCoordinatesOfData.getUpperX() &&
                parCoordinatesOfArea.getLowerY() <= parCoordinatesOfData.getLowerY() &&
                parCoordinatesOfArea.getUpperY() >= parCoordinatesOfData.getUpperY());
    }

    public ArrayList<Data<T>> getAllAppropriateData(Coordinates parCoordinates) {

        ArrayList<Data<T>> dataToReturn = new ArrayList<>();

        for (Data<T> data : this.listOfData) {
            if (isIncludingWholeData(parCoordinates, data.getCoordinates())) {
                dataToReturn.add(data);
            }
        }

        return dataToReturn;
    }

    /**
     * Returns coordinates for new node created in specific node
     */
    public Coordinates coordinatesOfNewNode(int parQuadrant) {

        Coordinates newCoordinates = new Coordinates(0,0,0,0);

        switch (parQuadrant) {
            case 0 -> {
                newCoordinates.setLowerX(this.coordinates.getLowerX());
                newCoordinates.setUpperX((this.coordinates.getUpperX() + this.coordinates.getLowerX()) / 2);
                newCoordinates.setLowerY(this.coordinates.getLowerY());
                newCoordinates.setUpperY((this.coordinates.getUpperY() + this.coordinates.getLowerY()) / 2);
            }
            case 1 -> {
                newCoordinates.setLowerX((this.coordinates.getUpperX() + this.coordinates.getLowerX()) / 2);
                newCoordinates.setUpperX(this.coordinates.getUpperX());
                newCoordinates.setLowerY(this.coordinates.getLowerY());
                newCoordinates.setUpperY((this.coordinates.getUpperY() + this.coordinates.getLowerY()) / 2);
            }
            case 2 -> {
                newCoordinates.setLowerX((this.coordinates.getUpperX() + this.coordinates.getLowerX()) / 2);
                newCoordinates.setUpperX(this.coordinates.getUpperX());
                newCoordinates.setLowerY((this.coordinates.getUpperY() + this.coordinates.getLowerY()) / 2);
                newCoordinates.setUpperY(this.coordinates.getUpperY());
            }
            case 3 -> {
                newCoordinates.setLowerX(this.coordinates.getLowerX());
                newCoordinates.setUpperX((this.coordinates.getUpperX() + this.coordinates.getLowerX()) / 2);
                newCoordinates.setLowerY((this.coordinates.getUpperY() + this.coordinates.getLowerY()) / 2);
                newCoordinates.setUpperY(this.coordinates.getUpperY());
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
    public int isFitsToQuadrant(Coordinates parCoordinates) {

        if (
                this.coordinates.getLowerX() < parCoordinates.getLowerX() &&
                        (this.coordinates.getLowerX() + this.coordinates.getUpperX()) / 2
                                > parCoordinates.getUpperX() &&
                        this.coordinates.getLowerY() < parCoordinates.getLowerY() &&
                        (this.coordinates.getLowerY() + this.coordinates.getUpperY()) / 2
                                > parCoordinates.getUpperY()
        ) {
            return 0;
        } else if (
                (this.coordinates.getLowerX() + this.coordinates.getUpperX()) / 2
                        < parCoordinates.getLowerX() &&
                        this.coordinates.getUpperX() > parCoordinates.getUpperX() &&
                        this.coordinates.getLowerY() < parCoordinates.getLowerY() &&
                        (this.coordinates.getLowerY() + this.coordinates.getUpperY()) / 2
                                > parCoordinates.getUpperY()
        ) {
            return 1;
        } else if (
                (this.coordinates.getLowerX() + this.coordinates.getUpperX()) / 2
                        < parCoordinates.getLowerX() &&
                        this.coordinates.getUpperX() > parCoordinates.getUpperX() &&
                        (this.coordinates.getLowerY() + this.coordinates.getUpperY()) / 2
                                < parCoordinates.getLowerY() &&
                        this.coordinates.getUpperY() > parCoordinates.getUpperY()
        ) {
            return 2;
        } else if (
                this.coordinates.getLowerX() < parCoordinates.getLowerX() &&
                        (this.coordinates.getLowerX() + this.coordinates.getUpperX()) / 2
                                > parCoordinates.getUpperX() &&
                        (this.coordinates.getLowerY() + this.coordinates.getUpperY()) / 2
                                < parCoordinates.getLowerY() &&
                        this.coordinates.getUpperY() > parCoordinates.getUpperY()
        ) {
            return 3;
        } else if (
                this.coordinates.getLowerX() < parCoordinates.getLowerX() &&
                        this.coordinates.getUpperX() > parCoordinates.getUpperX() &&
                        this.coordinates.getLowerY() < parCoordinates.getLowerY() &&
                        this.coordinates.getUpperY() > parCoordinates.getUpperY()
        ) {
            return -1;
        } else {
            return -10;
        }

    }

    /**
     * another private methods
     */
    private void initChildren() {
        for (int i = 0; i < CHILDREN; i++) {
            this.children[i] = null;
        }
    }

}
