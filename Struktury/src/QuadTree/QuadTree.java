package QuadTree;

import jdk.jshell.spi.ExecutionControl;

import java.awt.*;
import java.util.*;

/**
 * Class represents QuadTree, into which can be inserted any data.
 * This data needs to be type class Data, which holds needed things for
 * data inserted in quadtree - coordinates and uniqe id - and of course
 * data itself.
 */
public class QuadTree<T> {

    private int maxDepth;
    private int depth;
    private Coordinates rangeOfTree;
    private QuadTreeNode<T> root;

    private Boolean optimalizationOn;
    private double scaleParameter;
    private int numberOfItems;

    public QuadTree(
            double parMinimumX,
            double parMaximumX,
            double parMinimumY,
            double parMaximumY,
            int parMaxDepth
    ){
        this.maxDepth = parMaxDepth;
        this.root = null;
        this.rangeOfTree = new Coordinates(parMinimumX,parMaximumX,parMinimumY,parMaximumY);
        this.depth = 0;

        this.optimalizationOn = false;
        this.scaleParameter = 0;
    }

    public QuadTree(
            Coordinates parCoordinates,
            int parMaxDepth
    ) {
        this.maxDepth = parMaxDepth;
        this.root = null;
        this.rangeOfTree = new Coordinates(parCoordinates);
        this.depth = 0;
        this.numberOfItems = 0;
    }

    /**
     * Getters and requests
     */
    public QuadTreeNode<T> getRoot() {
        return root;
    }

    public int getDepth() {
        return this.depth;
    }

    public int getMaxDepth() {
        return this.maxDepth;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public Coordinates getRangeOfTree() {
        return this.rangeOfTree;
    }

    public void setScaleParameter(double parScale) {
        this.scaleParameter = parScale;
    }

    /**
     * Private class used for obtaining data in method FindAppropriateNodeData,
     * for next usage in deleting data and another processing.
     */
    private class Result {

        private QuadTreeNode<T> searchedNode;
        private LinkedList<QuadTreeNode<T>> parents;
        private LinkedList<Integer> indices;

        private Result(
                QuadTreeNode<T> parSearchedNode,
                LinkedList<QuadTreeNode<T>> parParents,
                LinkedList<Integer> parIndices
        ){
            this.searchedNode = parSearchedNode;
            this.parents = parParents;
            this.indices = parIndices;

        }
        public void addParent(QuadTreeNode<T> parNode) {
            this.parents.add(parNode);
        }

        public QuadTreeNode<T> getSearchedNode() {
            return this.searchedNode;
        }

        public LinkedList<QuadTreeNode<T>> getParents() {
            return this.parents;
        }

        public LinkedList<Integer> getIndices() {
            return this.indices;
        }
    }

    public double health() {
        double weightDepth = 1;
        double weightNullptr = 1.25;
        double healthOfDepth = this.getHealthOfDepth();
        double healthOfNullptr = this.getHealthOfNullPtr();
        System.out.println("h: " + healthOfNullptr);
        double health = (weightDepth * healthOfDepth + weightNullptr * healthOfNullptr)/ (weightDepth + weightNullptr);
        return health;
    }


    /**
     * Method will find appropriate node for next precessing.
     */
    private QuadTreeNode<T> findAppropriateNode(Coordinates parCoordinates, QuadTreeNode<T> parCurrent) {

        QuadTreeNode<T> current = parCurrent;
        boolean nodeIsFind = false;

        while (!nodeIsFind) {
            int quadrant = current.isFitsToQuadrant(parCoordinates);

            if (quadrant == -1) {
                nodeIsFind = true;
            } else if (!current.isLeaf()) {
                if (current.hasNthChild(quadrant)) {
                    current = current.accessToNthSon(quadrant);
                } else if (!current.hasNthChild(quadrant)) {
                    nodeIsFind = true;
                }
            } else if (current.isLeaf()) {
                nodeIsFind = true;
            }
        }
        return current;
    }

    /**
     * Method will find appropriate node for next precessing. Difference between this method
     * and method about is in return value. In this method btained data are storage in Result,
     * where are list of parents, indicates of quadrant and of course searched node.
     */
    private Result findAppropriateNodeData(Coordinates parCoordinates, QuadTreeNode<T> parCurrent) {

        LinkedList<QuadTreeNode<T>> parents = new LinkedList<>();
        LinkedList<Integer> indices = new LinkedList<>();

        QuadTreeNode<T> current = parCurrent;
        boolean nodeIsFind = false;

        while (!nodeIsFind) {
            int quadrant = current.isFitsToQuadrant(parCoordinates);

            if (quadrant == -1) {
                nodeIsFind = true;
            } else if (!current.isLeaf()) {
                if (current.hasNthChild(quadrant)) {

                    indices.add(quadrant);
                    parents.add(current);
                    current = current.accessToNthSon(quadrant);

                } else if (!current.hasNthChild(quadrant)) {
                    nodeIsFind = true;
                }
            } else if (current.isLeaf()) {
                nodeIsFind = true;
            }
        }
        return new Result(current,parents,indices);
    }

    public void insert(Data<T> parData) {
        insert(parData, this.getRoot());
    }
    /**
     * Method ensure inserting data into tree.
     */
    public void insert(Data<T> parData, QuadTreeNode<T> parStartNode) {
        if (!this.checkIfFitsToTree(parData.getCoordinates())) {
            return;
        }

        this.numberOfItems ++;
        // TODO okontrolovat ci uz tam nemam rovnaky objekt...staci pretraverzovat dole?

        if (this.isEmpty()) {
            Coordinates newCoordinates = new Coordinates(this.rangeOfTree);
            QuadTreeNode<T> newNode = new QuadTreeNode<T>(parData, newCoordinates,1);
            this.root = newNode;
            this.depth = 1;
            return;
        }

        ArrayList<Data<T>> dataToInsert = new ArrayList<Data<T>>();
        dataToInsert.add(parData);
        QuadTreeNode<T> searchedNode = this.findAppropriateNode(dataToInsert.get(0).getCoordinates(), parStartNode);

        while (!dataToInsert.isEmpty()) {
            int quadrant = searchedNode.isFitsToQuadrant(dataToInsert.get(0).getCoordinates());

            if (quadrant == -1) {
                searchedNode.addData(dataToInsert.remove(0));
            } else if (!searchedNode.isLeaf()) {
                QuadTreeNode<T> newNode = new QuadTreeNode<T>(dataToInsert.remove(0),searchedNode.coordinatesOfNewNode(quadrant),searchedNode.getLevel() + 1);
                searchedNode.setChild(quadrant, newNode);
            } else if (searchedNode.isLeaf()) {
                if (searchedNode.getLevel() == this.maxDepth) {
                    searchedNode.addMultipleData(dataToInsert);
                    dataToInsert.clear();
                } else if (searchedNode.hasSameCoordinates(dataToInsert.get(0))) {
                    searchedNode.addData(dataToInsert.remove(0));
                } else {
                    dataToInsert.addAll(searchedNode.getListOfData());
                    searchedNode.removeAllData();

                    Iterator<Data<T>> iterator = dataToInsert.iterator();
                    while (iterator.hasNext()) {
                        Data<T> data = iterator.next();
                        int newQuadrant = searchedNode.isFitsToQuadrant(data.getCoordinates());
                        if (newQuadrant == -1) {
                            searchedNode.addData(data);
                            iterator.remove();
                        } else if (!searchedNode.hasNthChild(newQuadrant)) {

                            QuadTreeNode<T> newNode = new QuadTreeNode<T>(data, searchedNode.coordinatesOfNewNode(newQuadrant), searchedNode.getLevel() + 1);
                            searchedNode.setChild(newQuadrant, newNode);

                            if (searchedNode.accessToNthSon(newQuadrant).getLevel() > this.depth) {
                                this.depth = searchedNode.accessToNthSon(newQuadrant).getLevel();
                            }
                            iterator.remove();
                        } else if (searchedNode.hasNthChild(newQuadrant) &&
                                searchedNode.accessToNthSon(newQuadrant).hasSameCoordinates(data)) {
                            searchedNode.accessToNthSon(newQuadrant).addData(data);
                            iterator.remove();
                        }
                    }
                    if (!dataToInsert.isEmpty()) {
                        searchedNode = findAppropriateNode(dataToInsert.get(0).getCoordinates(), searchedNode);
                    }
                }
            }
        }
        System.out.println("data ar inserted!!!");
    }

    /**
     * Return list of all data which are in tree witch root is given node
     */
    public ArrayList<Data<T>> getAllDataInSubTree(QuadTreeNode<T> parNode) {

        ArrayList<Data<T>> listToReturn = new ArrayList<>();
        Stack<QuadTreeNode<T>> nodesToProcess = new Stack<>();

        nodesToProcess.push(parNode);

        while (!nodesToProcess.isEmpty()) {

            QuadTreeNode<T> current = nodesToProcess.pop();
            listToReturn.addAll(current.getListOfData());

            if (!current.isLeaf()) {
                for (QuadTreeNode<T> child: current.getChildren()) {
                    if (child != null) {
                        nodesToProcess.push(child);
                    }
                }
            }
        }
        return listToReturn;
    }

    /**
     * Return all data which have given coordinates
     * @param parCoordinates coordinates of data which we want to find
     */
//    public ArrayList<Data<T>> find(Coordinates parCoordinates) {
//        if (!this.checkIfFitsToTree(parCoordinates)) {
//            return null;
//        }
//        QuadTreeNode<T> searchedNode = this.findAppropriateNode(parCoordinates, this.root);
//        return searchedNode.getDataWithSameCoordinates(parCoordinates);
//    }
    public ArrayList<Data<T>> find(Coordinates parCoordinates) {
        if (!this.checkIfFitsToTree(parCoordinates)) {
            return null;
        }
        Stack<QuadTreeNode<T>> nodesToProcess = new Stack<>();
        nodesToProcess.push(this.root);
        ArrayList<Data<T>> listToReturn = new ArrayList<>();

        while (!nodesToProcess.isEmpty()) {
            QuadTreeNode<T> current = nodesToProcess.pop();
            for (int i = 0; i < current.getListOfData().size(); i++) {
                if (current.coordinatesAreIntoNote(current.getListOfData().get(i).getCoordinates(),parCoordinates)) {
                    listToReturn.add(current.getListOfData().get(i));
                }

            }
            if (!current.isLeaf()) {
                for (QuadTreeNode<T> child: current.getChildren()) {
                    if (child != null) {
                        nodesToProcess.push(child);
                    }
                }
            }
        }
        return listToReturn;
    }


    /**
     * Returns all data in given area
     * @param parCoordinates coordinates of area in which we want to find data
     */
    public ArrayList<Data<T>> findInArea(Coordinates parCoordinates) {
        if (!this.checkIfFitsToTree(parCoordinates)) {
            return null;
        }

        ArrayList<Data<T>> listToReturn = new ArrayList<>();
        Stack<QuadTreeNode> nodesToProcess = new Stack<>();

        nodesToProcess.push(this.root);

        while (!nodesToProcess.isEmpty()) {

            QuadTreeNode<T> current = nodesToProcess.pop();

            if (current.isIncludingWholeNodeArea(parCoordinates)) {
                listToReturn.addAll(this.getAllDataInSubTree(current));
            } else {
                if (!current.isLeaf()) {
                    ArrayList<Integer> indicesOfSons = current.getIncludingQuadrants(parCoordinates);
                    for (int index : indicesOfSons) {
                        nodesToProcess.push(current.accessToNthSon(index));
                    }
                }
                listToReturn.addAll(current.getAllAppropriateData(parCoordinates));
            }
        }
        return listToReturn;
    }

    // poslem tam tie triedy a porovnavam, ziedne idecko!!!

    /**
     * Method delete appropriate data from tree
     * @param parCoordinates of data we want to delete
     * @param parId of data. It's needed, because in tree could by more data with same coordinates
     */
    public void delete(Data<T> parData) {

        Result result = this.findAppropriateNodeData(parData.getCoordinates(),this.root);

        QuadTreeNode<T> nodeWithDataToDelete = result.getSearchedNode();
        LinkedList<QuadTreeNode<T>> parents = result.getParents();
        LinkedList<Integer> indices = result.getIndices();

        nodeWithDataToDelete.removeData(parData);

        boolean correctlyRemoved = false;

        while (!correctlyRemoved) {

            if (!nodeWithDataToDelete.isLeaf()) {
                correctlyRemoved = true;
            } else if (nodeWithDataToDelete.isLeaf() && !nodeWithDataToDelete.isEmpty()) {
                correctlyRemoved = true;
            } else if (nodeWithDataToDelete.isLeaf() && nodeWithDataToDelete.isEmpty()) {
                // vymazanie nodeWithDataToDelete
                if (nodeWithDataToDelete == this.root) {
                    correctlyRemoved = true;
                    this.root = null;
                } else {

                    QuadTreeNode<T> actualParent = parents.getLast();
                    // ak je tento node leaf a je prazdy mazem ho. Dole idem dalej zistovat ci ma brata.
                    actualParent.removeChild(indices.removeLast());

                    // ak ma brata chcem ho vymazat. ale pozor!! jedine ak je brat leaf!!! :)
                    if (actualParent.getCountOfChildren() == 1) {
                        QuadTreeNode<T> brother = actualParent.accessToNthSon(actualParent.getIndexOfOnlyOneChild());
                        if (brother.isLeaf()) {
                            ArrayList<Data<T>> dataToMigrate = brother.getListOfData();
                            brother.removeAllData();
                            actualParent.removeChild(actualParent.getIndexOfOnlyOneChild());
                            actualParent.addMultipleData(dataToMigrate);
                        }
                    }
                    nodeWithDataToDelete = parents.removeLast();
                }
            }
        }
    }

    /**
     * If data in tree has changed key atribute - what ar coordinates, this
     * data needs to be deleted and inserted again according to new coordinates.
     */
    public void edit(Data<T> parData, Coordinates newCoordinates) {
        this.delete(parData);
        parData.setCoordinates(newCoordinates);
        this.insert(parData,this.root);
    }

    /**
     * This method provide possibility of changing maximum depth of tree.
     */
    public void changeDepth(int parNewMaxDepth) {
        int oldMaxDepth = this.maxDepth;
        this.maxDepth = parNewMaxDepth;

        if (this.maxDepth > oldMaxDepth) {
            if (oldMaxDepth == this.depth) {
                ArrayList<QuadTreeNode<T>> nodesToProcess = this.findAllNodesInLevel(oldMaxDepth);
                for (int i = 0; i < nodesToProcess.size(); i++) {
                    QuadTreeNode<T> node = nodesToProcess.get(i);
                    ArrayList<Data<T>> dataToInsert = node.getListOfData();
                    node.removeAllData();
                    for (int j = 0; j < dataToInsert.size(); j++) {
                        this.insert(dataToInsert.get(j),node);
                    }
                }
            }
        } else if (this.maxDepth < oldMaxDepth) {
            if (this.maxDepth < this.depth) {
                ArrayList<QuadTreeNode<T>> nodesToProcess = this.findAllNodesInLevel(this.maxDepth);
                this.depth = 0;
                for (int i = 0; i < nodesToProcess.size(); i++) {
                    QuadTreeNode<T> node = nodesToProcess.get(i);
                    ArrayList<Data<T>> dataToInsert = this.getAllDataInSubTree(node);
                    node.removeAllData();
                    node.removeChildren();
                    node.addMultipleData(dataToInsert);
                    if (node.getLevel() > this.depth) {
                        this.depth = node.getLevel();
                    }
                }
            }
        }
    }

    /**
     * Before inserting data this method checks if data can be inserted,
     * if coordinates are in range of tree. Outer points of tree are not accepted.
     */
    private boolean checkIfFitsToTree(Coordinates parCoordinates) {
        return !(parCoordinates.getLowerX() < this.rangeOfTree.getLowerX()) &&
                !(parCoordinates.getUpperX() > this.rangeOfTree.getUpperX()) &&
                !(parCoordinates.getLowerY() < this.rangeOfTree.getLowerY()) &&
                !(parCoordinates.getUpperY() > this.rangeOfTree.getUpperY());
    }

    private ArrayList<QuadTreeNode<T>> findAllNodesInLevel(int parLevel) {
        QuadTreeNode<T> root = this.root;
        ArrayList<QuadTreeNode<T>> nodesToReturn = new ArrayList<>();

        if (root == null) {
            return null;
        }

        Queue<QuadTreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // Number of nodes at the current level
            int nodesAtCurrentLevel = queue.size();

            for (int i = 0; i < nodesAtCurrentLevel; i++) {
                QuadTreeNode<T> currentNode = queue.poll();
                if (currentNode.getLevel() == parLevel) {
                    nodesToReturn.add(currentNode);
                }

                QuadTreeNode<T>[] children = currentNode.getChildren();
                if (children != null) {
                    for (int j = 0; j < 4; j++) {
                        if (children[j] != null) {
                            queue.add(children[j]);
                        }
                    }
                }
            }
        }
        return nodesToReturn;
    }

    public void tryToOptimalize() {
        // to ze sa nezmenia suradnice v DATU nevadi, to bude take inkognito iba...

        // 1. najskor si vyberem vsetky data zo stromu
        // 2. idem postupne vkladat data - urcenou mierkou im zmenim suradnicu okolo strdu
        // 3. vlozim dato
        this.optimalizationOn = true;

        this.scaleParameter = this.health();

        ArrayList<Data<T>> dataToInsert = this.getAllDataInSubTree(this.root);
        this.root = null;
        for (int i = 0; i < dataToInsert.size(); i++) {
            Data<T> data = dataToInsert.get(i);
            data.getCoordinates().changeCoordinatesSize(this.scaleParameter);
            this.insert(data, this.root);
        }

    }

    private double optimalDepth() {
        double logOne = Math.log(Math.sqrt(this.numberOfItems));
        double logTwo = Math.log(2);
        return (logOne/logTwo) + 1; // ;)
    }

    private double optimalNumberOfNull() {
        if ((this.numberOfItems % 3) == 0) {
            return 1;
        } else if ((this.numberOfItems % 3) == 1) {
            return 0;
        } else if ((this.numberOfItems % 3) == 2) {
            return 2;
        }
        return -1;
    }

    private int getNumberOfNullPointer() {
        int count = 0;

        Stack<QuadTreeNode<T>> nodesToProcess = new Stack<>();
        nodesToProcess.push(this.root);

        while (!nodesToProcess.isEmpty()) {

            QuadTreeNode<T> current = nodesToProcess.pop();
            if (!current.isLeaf()) {
                count += current.numberOfEmptySons();
            }
            if (!current.isLeaf()) {
                for (QuadTreeNode<T> child: current.getChildren()) {
                    if (child != null) {
                        nodesToProcess.push(child);
                    }
                }
            }
        }
        return count;
    }

    private double getHealthOfDepth() {
        double pomLogOne = Math.log(this.optimalDepth());
        double pomLogTwo = Math.log(this.depth);
        double healthOfDepth = (pomLogOne / pomLogTwo); // * 100
        return healthOfDepth;
    }

    private double getHealthOfNullPtr() {
        double pomLogThree = Math.log(2 - this.optimalNumberOfNull() + getNumberOfNullPointer());
        double pomLogFour = Math.log(2);
        double healthOfNullptr = (pomLogFour / pomLogThree);
        return healthOfNullptr;
    }

    private ArrayList<Coordinates> returnListOfBoundary(int level) {

        ArrayList<Coordinates> result = new ArrayList<>();
        double lowerx = this.rangeOfTree.getLowerX();
        double upperx = this.rangeOfTree.getUpperX();
        double range = (lowerx + upperx)/2;

        if (level == 1) {
            result.add(new Coordinates(rangeOfTree));
           return result;
        }

        int denominator = 2;
        int sqare = 1;

        int count = (int)Math.pow(denominator,level-1) + 1;
        double[][] listOfBoundry = new double[level][count];


        for (int i = 2; i < level; i++) {
            double period = Math.pow(denominator,sqare); // perioda na urovni je 2 na ureoven minus prvu
            for (int j = 1; j < period; j++) {
                int index = (int) ((int) (count -1)/period);
                double v = listOfBoundry[i][index];

                // mam 6 urovni to znamena pole ma dlsku 33. ja chcem teraz count - 1 / 2 -> na to miesto zapisujem
                // na tretej urovni chcem count - 1 / 3 -> to je index a zaroven ho prechadzam nasobkami az do konca
                // na stvrtej urovni chcem count - 1 / 4
            }


            sqare++;
        }
        return null;
    }

}
