package QuadTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class QuadTreeTester {

    private QuadTree<Place> testTree;

    public QuadTreeTester(QuadTree<Place> parTree) {
        this.testTree = parTree;
    }

  //  public ArrayList<QuadTreeNode<Place>> myFakeLevelOrder() {
//        QuadTreeNode<Place> current = this.testTree.getRoot();
//
//        ArrayList<QuadTreeNode<Place>> list = new ArrayList<>();
//        list.add(current);
//
//        while (current != null) {
//            list.addAll(Arrays.asList(current.accessChildren()));
//        }
  //  }

        public void levelOrderTraversal() {
            QuadTreeNode<Place> root = this.testTree.getRoot();
            if (root == null) {
                return;
            }

            Queue<QuadTreeNode<Place>> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                QuadTreeNode<Place> currentNode = queue.poll();
                // Process the current node, e.g., add it to a list or perform some operation.
                System.out.println("V node sa nachadza:");
                for (Data<Place> data: currentNode.getListOfData()) {
                    System.out.println("Miesto: " + data.getData().getName()+
                            " x: " + data.getCoordinates().getLowerX() +
                            " x2: " + data.getCoordinates().getUpperX() +
                            " y: " + data.getCoordinates().getLowerY() +
                            " y2: " + data.getCoordinates().getUpperY());
                }

                //QuadTreeNode<Place>[] children = currentNode.accessChildren();
//                if (children != null) {
//                    for (int i = 0; i < 4; i++) {
//                        if (children[i] != null) {
//                            queue.add(children[i]);
//                        }
//                    }
//                }
            }
        }



    public void levelOrderTraversalWithNodeNumbers() {
        QuadTreeNode<Place> root = this.testTree.getRoot();

        if (root == null) {
            return;
        }

        Queue<QuadTreeNode<Place>> queue = new LinkedList<>();

        queue.add(root);

        int nodeNumber = 1; // Start with node 1 for the root

        while (!queue.isEmpty()) {
            int nodesAtCurrentLevel = queue.size(); // Number of nodes at the current level

            for (int i = 0; i < nodesAtCurrentLevel; i++) {
                QuadTreeNode<Place> currentNode = queue.poll();
                //currentNode.setNodeNumber(nodeNumber); // Assign a number to the node

                System.out.println("V node " + nodeNumber + " na urovni: " + currentNode.getLevel() + " sa nachadza:");
                for (Data<Place> data: currentNode.getListOfData()) {
                    System.out.println("Miesto: " + data.getData().getName()+
                            " x: " + data.getCoordinates().getLowerX() +
                            //" x2: " + data.getCoordinates().getUpperX() +
                            " y: " + data.getCoordinates().getLowerY());
                            //" y2: " + data.getCoordinates().getUpperY());
                }

                nodeNumber++;



                QuadTreeNode<Place>[] children = currentNode.getChildren();
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


}
