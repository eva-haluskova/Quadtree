package QuadTree;

import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

// TODO Majo
// univerzalny pseudofind ktory ti najde cestu k nodu...lebo tu pouzivas vsade :)
// v intervalovom finde prehladavas vsetky intervali, ked patri cela oblast, vlozis tam vsetky synov
// delete vymazavas cyklicky nahor, kukas si mas bratov...resp. ci si prazdy. K parentovi sa dostanes bud
// z uchvatneho zozonamu z pseudofindu alebo budes mat jednoducho ulozenych parentov :)

// TODO uschvatny pseudofind:
// najdi node podla suradnic? by som povedala... ako param ti pojde suradnica a vrati ti to
// node do ktoreho vkladas...no, hej alebo v ktorom sa nachadzaju data. Len ako zaroven dostanes aj
// nejaky linked list parentov?


// TODO zoptimalizuj insertik
// TODO vyries zahadu ze preco je vkladanie polygonov jednoduchsie...asi preto ze sa vklada do oneho ved jasne
// je to uz jasne!!!
// TODO mas osetrenie ked ti padne bod na hranicu?? HMM??? pochybujem.
// TODO ohranicenie stromu refactorni tiez do Coordinates
// TODO ten insert mas stale totalne nahovno, po novom ti insert polygonov vobec negungiruje

// findujes podla sekundarnych klucov...vrati ti to vsetky zaznami s danymi suradnicami,
// no a deletujes asi podla primarneho kluca, proste ides vymazat speci zaznam, akoze aj iny moze mat
// rovnake suradnice, ale to neznamena ze ho chces vymazat :)

public class QuadTree<T extends Comparable<T>> {

    private final int maxDepth;
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

    private class Result {

        private QuadTreeNode<T> searchedNode;
        private LinkedList<QuadTreeNode<T>> parents;
        private int level;

        private Result(
                QuadTreeNode<T> parSearchedNode,
                LinkedList<QuadTreeNode<T>> parParents,
                int parLevel
        ){
            this.searchedNode = parSearchedNode;
            this.level = parLevel;
            this.parents = parParents;

        }
        public void addParent(QuadTreeNode<T> parNode) {
            this.parents.add(parNode);
        }

        public QuadTreeNode<T> getSearchedNode() {
            return this.searchedNode;
        }
    }

    /**
     * Method will find appropriate node for next precessing - inserting, finding, deleting.
     */
    // TODO at this time you dont need to put there parcurrent, thing about it in futur if you fil need it
    private Result findAppropriateNodeData(Coordinates parCoordinates, QuadTreeNode<T> parCurrent) {

        LinkedList<QuadTreeNode<T>> parents = new LinkedList<>();

        if (this.depth == 1) {
            return new Result(this.root,null,1);
        }

        int level = parCurrent.getLevel();
        QuadTreeNode<T> current = parCurrent;
        boolean nodeIsFind = false;

        while (!nodeIsFind) {
            int quadrant = current.isFitsToQuadrant(parCoordinates);

            if (quadrant == -1) {
                nodeIsFind = true;
            } else if (!current.isLeaf()) {
                if (current.hasNthChild(quadrant)) {
                    current = current.accessToNthSon(quadrant);
                    level = level + 1;
                    parents.add(current);
                } else if (!current.hasNthChild(quadrant)) {
                    nodeIsFind = true;
                }
            } else if (current.isLeaf()) {
                    nodeIsFind = true;
            }
        }
        return new Result(current,parents,level);
    }

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

    // TODO decide if boolean return statement is necessarily
    // TODO rename
    public boolean insertSimpleTwo(Data<T> parData) {
        if (!this.checkIfFitsToTree(parData.getCoordinates())) {
            return false;
        }

        if (this.isEmpty()) {
            Coordinates newCoordinates = new Coordinates(this.rangeOfTree);
            QuadTreeNode<T> newNode = new QuadTreeNode<T>(parData, newCoordinates,1);
            this.root = newNode;
            this.depth = 1;
            return true;
        }

        ArrayList<Data<T>> dataToInsert = new ArrayList<Data<T>>();
        dataToInsert.add(parData);
        QuadTreeNode<T> searchedNode = this.findAppropriateNode(dataToInsert.get(0).getCoordinates(), this.root);

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
        return true;
    }



    /**
     * Return all data which have given coordinates
     * @param parCoordinates - coordinates of data which we want to find
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
     * @param parCoordinates - coordinates of area in which we want to find data
     */
    public ArrayList<Data<T>> findInArea(Coordinates parCoordinates) {

    }

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

    /**
     * Before inserting of data this method checks if data can be inserted,
     * if coordinates are in range of tree. Outer points of tree are not accepted.
     */
    private boolean checkIfFitsToTree(Coordinates parCoordinates) {
        return !(parCoordinates.getLowerX() <= this.rangeOfTree.getLowerX()) &&
                !(parCoordinates.getUpperX() >= this.rangeOfTree.getUpperX()) &&
                !(parCoordinates.getLowerY() <= this.rangeOfTree.getLowerY()) &&
                !(parCoordinates.getUpperY() >= this.rangeOfTree.getUpperY());
    }

}
