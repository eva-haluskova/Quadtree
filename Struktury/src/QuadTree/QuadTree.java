package QuadTree;

import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Iterator;

// TODO zoptimalizuj insertik
// TODO vyries zahadu ze preco je vkladanie polygonov jednoduchsie...asi preto ze sa vklada do oneho ved jasne
// je to uz jasne!!!
// TODO mas osetrenie ked ti padne bod na hranicu?? HMM??? pochybujem.
// TODO ohranicenie stromu refactorni tiez do Coordinates

// findujes podla sekundarnych klucov...vrati ti to vsetky zaznami s danymi suradnicami,
// no a deletujes asi podla primarneho kluca, proste ides vymazat speci zaznam, akoze aj iny moze mat
// rovnake suradnice, ale to neznamena ze ho chces vymazat :)

public class QuadTree<T extends Comparable<T>> {

    private double minimumX;
    private double maximumX;
    private double minimumY;
    private double maximumY;

    private final int maxDepth;

    private int depth;

    private QuadTreeNode<T> root;

    public QuadTree(
            double parMinimumX,
            double parMaximumX,
            double parMinimumY,
            double parMaximumY,
            int parDepth
    ){
        this.minimumX = parMinimumX;
        this.maximumX = parMaximumX;
        this.minimumY = parMinimumY;
        this.maximumY = parMaximumY;
        this.maxDepth = parDepth;
        this.root = null;

        this.depth = 0;
    }

    // TODO decide if boolean return statement is necessarily

    // TODO one more final debug of insert

    public boolean insert(Data<T> parData) {

        if (!this.checkIfFitsToTree(parData.getCoordinates())) {
            return false;
        }

        if (this.isEmpty()) {
            QuadTreeNode<T> newNode = new QuadTreeNode<T>(parData);
            Coordinates newCoordinates = new Coordinates(this.minimumX,this.maximumX,this.minimumY, this.maximumY);
            newNode.setCoordinates(newCoordinates);
            this.root = newNode;
            this.depth = 1;
            return true;
        }

        QuadTreeNode<T> current = this.root;
        ArrayList<Data<T>> dataToInsert = new ArrayList<Data<T>>();
        dataToInsert.add(parData);

        while (!dataToInsert.isEmpty()) {

            int quadrant = current.isFitsToQuadrant(dataToInsert.get(0).getCoordinates());
            QuadTreeNode<T>[] sons = current.accessChildren();

            if (quadrant == -1) {
                current.addData(dataToInsert.remove(0));
            } else if (!current.isLeaf()) {
                if (sons[quadrant] == null) {
                    QuadTreeNode<T> newNode = new QuadTreeNode<T>(dataToInsert.remove(0));
                    newNode.setCoordinates(this.newCoordinatesOfNode(current.getCoordinates(),quadrant));
                    sons[quadrant] = newNode;
                } else if (sons[quadrant] != null) {
                    current = sons[quadrant];
                }
            } else if (current.isLeaf() && !current.isEmpty()) {
                if (current.hasSameCoordinates(dataToInsert.get(0))) {
                    current.addData(dataToInsert.remove(0));
                    System.out.println("----");
                } else {
                    dataToInsert.addAll(current.accessToData());
                    current.clearData();
                }
            } else if (current.isLeaf() && current.isEmpty()) {
                if (this.depth >= this.maxDepth) {
                    current.addAllData(dataToInsert);
                    dataToInsert.clear();
                } else {
                    Iterator<Data<T>> iterator = dataToInsert.iterator();
                    while (iterator.hasNext()) {
                        Data<T> data = iterator.next();
                        int newQuadrant = current.isFitsToQuadrant(data.getCoordinates());

                        if (newQuadrant == -1) {
                            current.addData(data);
                            iterator.remove();
                        } else if (sons[newQuadrant] == null) {
                            if (current.isLeaf()) {
                                this.depth = this.depth + 1;
                            }
                            QuadTreeNode<T> newNode = new QuadTreeNode<T>(data);
                            newNode.setCoordinates(newCoordinatesOfNode(current.getCoordinates(), newQuadrant));
                            sons[newQuadrant] = newNode;
                            iterator.remove();
                        } else if (sons[newQuadrant] != null &&
                                sons[newQuadrant].hasSameCoordinates(dataToInsert.get(0))) {
                            sons[newQuadrant].addData(dataToInsert.get(0));
                            iterator.remove();
                            System.out.println("som tu");
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Return all data which have given coordinates
     * @param parCoordinates - coordinates of data which we want to find
     */
    public ArrayList<Data<T>> find(Coordinates parCoordinates) {

        QuadTreeNode<T> current = this.root;

        ArrayList<Data<T>> dataToReturn = new ArrayList<>();

        boolean dataAreFind = false;

        while(!dataAreFind) {
            int quadrant = current.isFitsToQuadrant(parCoordinates);
            QuadTreeNode<T>[] sons = current.accessChildren();

            if (quadrant == -1) {
                // znamena ze sa nachadza niekde v currenotvi, prehladam cely jeho zoznam co za data tam mam
                for (Data<T> data : current.accessToData()) {
                    if (data.getCoordinates().equals(parCoordinates)) {
                        dataToReturn.add(data);
                    }
                }
                dataAreFind = true;
            } else {
                // ak sa nenachadza v currenotvi z predosleho ifu, schadzam o uroven nizsie :)  ALE!!!
                if (current.isLeaf()) {
                    for (Data<T> data : current.accessToData()) {
                        if (data.getCoordinates().equals(parCoordinates)) {
                            dataToReturn.add(data);
                        }
                    }
                    dataAreFind = true;
                } else {
                    current = sons[quadrant];
                }
            }
        }
        return dataToReturn;
    }

    /**
     * Returns all data in given area
     * @param parCoordinates - coordinates of area in which we want to find data
     */
    public ArrayList<Data<T>> findInArea(Coordinates parCoordinates) {

        QuadTreeNode<T> current = this.root;

        ArrayList<Data<T>> dataToReturn = new ArrayList<>();

        boolean dataAreFind = false;


        return null;
    }

    public QuadTreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(QuadTreeNode<T> root) {
        this.root = root;
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

    private Coordinates newCoordinatesOfNode(Coordinates parOldCoordinates, int parQuadrant) {

        Coordinates newCoordinates = new Coordinates(0,0,0,0);

        switch (parQuadrant) {
            case 0 -> {
                newCoordinates.setLowerX(parOldCoordinates.getLowerX());
                newCoordinates.setUpperX((parOldCoordinates.getUpperX() + parOldCoordinates.getLowerX()) / 2);
                newCoordinates.setLowerY(parOldCoordinates.getLowerY());
                newCoordinates.setUpperY((parOldCoordinates.getUpperY() + parOldCoordinates.getLowerY()) / 2);
            }
            case 1 -> {
                newCoordinates.setLowerX((parOldCoordinates.getUpperX() + parOldCoordinates.getLowerX()) / 2);
                newCoordinates.setUpperX(parOldCoordinates.getUpperX());
                newCoordinates.setLowerY(parOldCoordinates.getLowerY());
                newCoordinates.setUpperY((parOldCoordinates.getUpperY() + parOldCoordinates.getLowerY()) / 2);
            }
            case 2 -> {
                newCoordinates.setLowerX((parOldCoordinates.getUpperX() + parOldCoordinates.getLowerX()) / 2);
                newCoordinates.setUpperX(parOldCoordinates.getUpperX());
                newCoordinates.setLowerY((parOldCoordinates.getUpperY() + parOldCoordinates.getLowerY()) / 2);
                newCoordinates.setUpperY(parOldCoordinates.getUpperY());
            }
            case 3 -> {
                newCoordinates.setLowerX(parOldCoordinates.getLowerX());
                newCoordinates.setUpperX((parOldCoordinates.getUpperX() + parOldCoordinates.getLowerX()) / 2);
                newCoordinates.setLowerY((parOldCoordinates.getUpperY() + parOldCoordinates.getLowerY()) / 2);
                newCoordinates.setUpperY(parOldCoordinates.getUpperY());
            }
        }
        return newCoordinates;
    }

    /**
     * Before inserting of data this method checks if data can be inserted,
     * if coordinates are in range of tree
     */
    private boolean checkIfFitsToTree(Coordinates parCoordinates) {
        return !(parCoordinates.getLowerX() <= this.minimumX) &&
                !(parCoordinates.getUpperX() >= this.maximumX) &&
                !(parCoordinates.getLowerY() <= this.minimumY) &&
                !(parCoordinates.getUpperY() >= this.maximumY);
    }

}
