package QuadTree;

import jdk.jshell.spi.ExecutionControl;

import java.util.*;

/**
 * Class represents QuadTree, into which can be inserted any data.
 * This data needs to be type class Data, which holds needed things for
 * data inserted in quadtree - coordinates and uniqe id - and of course
 * data itself.
 */
public class QuadTree<T extends Comparable<T>> {

    private int maxDepth;
    private int depth;
    private Coordinates rangeOfTree;
    private QuadTreeNode<T> root;

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

    /**
     * Method ensure inserting data into tree.
     */
    public void insert(Data<T> parData, QuadTreeNode<T> parStartNode) {
        if (!this.checkIfFitsToTree(parData.getCoordinates())) {
            return;
        }

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
    public ArrayList<Data<T>> find(Coordinates parCoordinates) {
        if (!this.checkIfFitsToTree(parCoordinates)) {
            return null;
        }
        QuadTreeNode<T> searchedNode = this.findAppropriateNode(parCoordinates, this.root);
        return searchedNode.getDataWithSameCoordinates(parCoordinates);
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

    /**
     * Method delete appropriate data from tree
     * @param parCoordinates of data we want to delete
     * @param parId of data. It's needed, because in tree could by more data with same coordinates
     */
    public void delete(Coordinates parCoordinates, int parId) {

        Result result = this.findAppropriateNodeData(parCoordinates,this.root);

        QuadTreeNode<T> nodeWithDataToDelete = result.getSearchedNode();
        LinkedList<QuadTreeNode<T>> parents = result.getParents();
        LinkedList<Integer> indices = result.getIndices();

        nodeWithDataToDelete.removeDataUsingPK(parId);

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
        return !(parCoordinates.getLowerX() <= this.rangeOfTree.getLowerX()) &&
                !(parCoordinates.getUpperX() >= this.rangeOfTree.getUpperX()) &&
                !(parCoordinates.getLowerY() <= this.rangeOfTree.getLowerY()) &&
                !(parCoordinates.getUpperY() >= this.rangeOfTree.getUpperY());
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

}
