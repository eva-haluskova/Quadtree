package QuadTree;

import java.util.ArrayList;

public class QuadTreeNode<T extends Comparable<T>> {

    private static final int CHILDREN = 4;

    private Coordinates coordinates;
    private QuadTreeNode<T>[] children;
    private ArrayList<Data<T>> listOfData;
    private int number;

    public QuadTreeNode(Data<T> parData){ //}, Coordinates parCoordinates) {
        this.children = new QuadTreeNode[CHILDREN];
        this.listOfData = new ArrayList<>();
        this.listOfData.add(parData);
        //this.coordinates = parCoordinates;
        this.initChildren();
    }

    public QuadTreeNode(Coordinates parCoordinates) {
        this.children = new QuadTreeNode[CHILDREN];
        this.listOfData = new ArrayList<>();
        this.coordinates = parCoordinates;
        this.initChildren();
    }

    public boolean isLeaf() {
        int countOfChildren = 0;
        for (int i = 0; i < CHILDREN; i++) {
            if(this.children[i] != null) {
                countOfChildren++;
            }
        }
        return countOfChildren == 0;
    }

    public boolean isEmpty() {
        return listOfData.isEmpty();
    }

    public void addData(Data<T> parData) {
        this.listOfData.add(parData);
    }

    public void addAllData(ArrayList<Data<T>> parData) {
        this.listOfData.addAll(parData);
    }

    public boolean isSKFits(Coordinates parCoordinates) {
        if (this.coordinates.getLowerX() < parCoordinates.getLowerX() &&
                this.coordinates.getUpperX() > parCoordinates.getUpperX() &&
                this.coordinates.getLowerY() < parCoordinates.getLowerY() &&
                this.coordinates.getUpperY() > parCoordinates.getUpperY()) {
            return true;
        }
        return false;
    }

    public void clearData() {
        this.listOfData.clear();
    }

    public boolean hasSameCoordinates(Data<T> parData) {
        for (Data<T> data: this.listOfData) {
            if (data.getCoordinates().equals(parData.getCoordinates())) {
                return true;
            }
        }
        return false;
    }

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

    public ArrayList<Data<T>> accessToData() {
        return this.listOfData;
    }

    public QuadTreeNode<T>[] accessChildren(){
        return this.children;
    }

    // son on position NW
    public QuadTreeNode<T> accessToFirstSon() {
    //public T accessToFirstSon() {
        return children[0];
    }

    // son on position NE
    public QuadTreeNode<T> accessToSecondSon() {
        return children[1];
    }

    // son on position SE
    public QuadTreeNode<T> accessToThirdSon() {
        return children[2];
    }

    // son on position SW
    public QuadTreeNode<T> accessToFourthSon() {
        return children[3];
    }

    public void setFirstSon(QuadTreeNode<T> param) {
        this.children[0] = param;
    }

    public void setSecondSon(QuadTreeNode<T> param) {
        this.children[1] = param;
    }

    public void setThirdSon(QuadTreeNode<T> param) {
        this.children[2] = param;
    }

    public void setFourthSon(QuadTreeNode<T> param) {
        this.children[3] = param;
    }

    public void setCoordinates(Coordinates parCoordinates) {
        this.coordinates = parCoordinates;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    private void initChildren() {
        for (int i = 0; i < CHILDREN; i++) {
            this.children[i] = null;
        }
    }

    public void splitNode() {

    }

    public void setNodeNumber(int nodeNumber) {
        this.number = nodeNumber;
    }

    public int getNumber(){
        return this.number;
    }

}
