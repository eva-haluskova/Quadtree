package QuadTree.Tester;


import QuadTree.QuadTree;
import QuadTree.Data;
import QuadTree.Coordinates;
import QuadTree.QuadTreeNode;

import java.util.*;
import java.util.function.Supplier;

/**
 * This abstract class is used for testing implementation of quad tree. Class provide
 * methods for testing bacis method - insert, delete, find and some others
 * features like level order traversal and some other advanced testing.
 * As long as this class is abstract, it's needed to inherit tester for every
 * type of data, because of generating uniqe data for every data type in method
 * insert. So, this class contain abstract method generateInsert which is override
 * in every inherit class.
 */
abstract class QuadTreeTester<T> {

    Random random = new Random();
    protected QuadTree<T> testTree;
    protected ArrayList<Data<T>> testData = new ArrayList<>();
    protected ArrayList<Data<T>> dataToFindInArea = new ArrayList<Data<T>>();
    protected ArrayList<Data<T>> dataToFindOfPoint = new ArrayList<>();

    public QuadTreeTester(QuadTree<T> parTree) {
        this.testTree = parTree;
    }

    /**
     * Abstract class in which is needed to generate specific data in inherit class.
     */
    public abstract void generateInsert(int parNumber,double parSizeOfObject, Random random, Coordinates parCoordinatesForSearch);

    /**
     * Method tries to delete all data which was inserted into tree.
     */
    public void generateDelete() {

        for (int i = 0; i < testData.size(); i++) {
            this.testTree.delete(testData.get(i));
        }
        if (this.testTree.isEmpty()) {
            System.out.println("Delete was success!!");
        } else {
            System.out.println("Delete failed! :(");
        }
    }

    /**
     * Tries to find all data in searches area of tree
     * @param parCoordinatesOfArea coordinates od searched area
     */
    public ArrayList<Data<T>> generateFind(Coordinates parCoordinatesOfArea) {
        ArrayList<Data<T>> findData = new ArrayList<>();
        findData = testTree.findInArea(parCoordinatesOfArea);
        if (findData.containsAll(this.dataToFindInArea)) { // pay attention
            System.out.println("Find was success!!");
        } else {
            System.out.println("Find failed! :(");
        }
//        for (int i = 0; i < findData.size(); i++) {
//            System.out.println(findData.get(i).toString());
//        }

        return findData;
    }

    /**
     * Tries to fine all data with specific coordinates
     * @param parCoordinatesOfData coordinates of some specific point/polygon
     */
    public ArrayList<Data<T>> generateSimpleFind(Coordinates parCoordinatesOfData) {
        ArrayList<Data<T>> findData = new ArrayList<>();
        findData = testTree.find(parCoordinatesOfData);
        if (findData.containsAll(this.dataToFindOfPoint)) { // pay attention
            System.out.println("Simple find was success!!");
        } else {
            System.out.println("Simple find failed! :(");
        }
//        for (int i = 0; i < findData.size(); i++) {
//            System.out.println(findData.get(i).toString());
//        }
        System.out.println("Find is done");
        return findData;
    }


    /**
     * For advanced testing of bacis method of tree, this method is used
     * for testing differnet seeds of numbers and test where is problem
     * in testing methods.
     */
    public void testOfSeed(int parNumberOfSeeds, int parCountOfReplications, Coordinates parCoordinatesForSearch, double sizeOfObject) {
        for (int i = 0; i < parNumberOfSeeds; i++) {
            this.random.setSeed(i);
            System.out.println("-----TEST OF SEED " + i);
            this.generateInsert(parCountOfReplications,sizeOfObject,this.random,parCoordinatesForSearch);

            System.out.println("Maximalna hlbka stromu: " + testTree.getMaxDepth());
            System.out.println("Aktualna hlbka stromu: " + testTree.getDepth());


            //this.generateFind(parCoordinatesForSearch);
            //this.generateDelete();
        }
    }

    /**
     * After find out in which seed program cause error, this method could be used for
     * more specific debugging of values of specific seed.
     */
    public void testForSeed(int parSeed, int parCountOfReplications, Coordinates parCoordinatesForSearch, double sizeOfObject) {

        this.random.setSeed(parSeed);
        System.out.println("-----TEST OF SEED " + parSeed);
        this.generateInsert(parCountOfReplications,sizeOfObject,this.random,parCoordinatesForSearch);
        this.generateDelete();
    }

    /**
     * testing method for change depth - if depth is change and at the same time, if its possible to correct
     * remove all data - testing if some data wasn't lost during the changing of depth.
     */
    public void testOfChangeOfDepth(int parNewDepth,int parNumber, Coordinates parCoordinatesOfSearch, double sizeOfObject) {
        this.generateInsert(parNumber,sizeOfObject,this.random,parCoordinatesOfSearch);

        System.out.println("maximalna hlbka: " + testTree.getMaxDepth());
        System.out.println("aktualna hlbka: " + testTree.getDepth());

        System.out.println("---- zmena hlbky ----");
        testTree.changeDepth(parNewDepth);

        System.out.println("maximalna hlbka: " + testTree.getMaxDepth());
        System.out.println("aktualna hlbka: " + testTree.getDepth());

        this.generateDelete();
    }

    /**
     * Method provide level order traversal and print on console
     * nodes with their level and data in it.
     */
    public void levelOrderTraversalWithNodeNumbers() {
        QuadTreeNode<T> root = this.testTree.getRoot();

        if (root == null) {
            return;
        }
        Queue<QuadTreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        int nodeNumber = 1; // numbers of nodes

        while (!queue.isEmpty()) {
            // Number of nodes at the current level
            int nodesAtCurrentLevel = queue.size();

            for (int i = 0; i < nodesAtCurrentLevel; i++) {
                QuadTreeNode<T> currentNode = queue.poll();

                System.out.println("V node " + nodeNumber + " na urovni: " + currentNode.getLevel() + " sa nachadza:");
                for (Data<T> data: currentNode.getListOfData()) {
                    data.toString();
                }
                nodeNumber++;

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
    }

    /**
     * Return if cooridnates belongs to specific area.
     */
    boolean belongToArea(Coordinates parCoordinatesOfArea, Coordinates parCoordinatesOfData) {
        return !(parCoordinatesOfData.getLowerX() <= parCoordinatesOfArea.getLowerX()) &&
                !(parCoordinatesOfData.getUpperX() >= parCoordinatesOfArea.getUpperX()) &&
                !(parCoordinatesOfData.getLowerY() <= parCoordinatesOfArea.getLowerY()) &&
                !(parCoordinatesOfData.getUpperY() >= parCoordinatesOfArea.getUpperY());

    }
}

