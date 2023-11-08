package GUI;

import Data.CadastralObject;
import MainLogic.Cadaster;
import Data.GPS;
import QuadTree.Data;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Random;

public class Controller {

    private Cadaster cadaster;
    private View view;

    public Controller(Cadaster parCadaster, View parView) {
        this.cadaster = parCadaster;
        this.view = parView;

        this.view.addInsertButtonListener(new InsertButtonListener());
        this.view.addEditButtonListener(new EditButtonListener());
        this.view.addDeleteButtonListener(new DeleteButtonListener());
        this.view.addFindButtonListener(new FindButtonListener());
        this.view.addTreeButtonListener(new TreeButtonListener());
        this.view.addOtherButtonListener(new OtherButtonListener());
        this.view.addMainComboBoxListener(new MainComboBoxListener());
        this.view.addOutputListSelectionListener(new OutputListSelectionListener());
        this.view.addCreateTreeButtonListener(new CreateTreeButtonListener());
        this.view.addConfirmButtonListener(new ConfirmButtonListener());
        this.view.addConfirmButtonDownListener(new ConfirmButtonDownListener());
    }

    /**
     * Main menu listeners
     */
    class InsertButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.getMainPanel().setVisible(true);
            view.getTreePanel().setVisible(false);
            view.getTypeOfObjectPanel().setVisible(true);
            view.getAddObjectPanel().setVisible(true);
            view.setTypeOfObject("Type of object");
            view.setTypeOfObjectChoose("Real Estate","Land Parcel");
            view.getIOPanel().setBorder(BorderFactory.createTitledBorder("Insert data"));
            view.getConfirmButtonDown().setText("Create");
            view.getConfirmButton().setVisible(false);
            manageCoordinateTwoPanel(true);
        }
    }

    class EditButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.getMainPanel().setVisible(true);
            view.getTreePanel().setVisible(false);
            view.getTypeOfObjectPanel().setVisible(true);
            view.getOutputPanel().setVisible(true);
            view.getAddObjectPanel().setVisible(false);
            view.setTypeOfObject("Type of object");
            view.setTypeOfObjectChoose("Real Estate","Land Parcel");
            view.getIOPanel().setBorder(BorderFactory.createTitledBorder("Edit data"));
            view.getConfirmButton().setText("Get objects to edit");
            view.getConfirmButton().setVisible(true);
            manageCoordinateTwoPanel(false);
        }
    }

    class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.getMainPanel().setVisible(true);
            view.getOutputPanel().setVisible(true);
            view.getTreePanel().setVisible(false);
            view.getTypeOfObjectPanel().setVisible(true);
            view.getAddObjectPanel().setVisible(false);
            view.setTypeOfObject("Type of object");
            view.setTypeOfObjectChoose("Real Estate","Land Parcel");
            view.getIOPanel().setBorder(BorderFactory.createTitledBorder("Delete data"));
            view.getConfirmButton().setText("Get objects to delete");
            view.getConfirmButton().setVisible(true);
            manageCoordinateTwoPanel(false);

        }
    }

    class FindButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.getMainPanel().setVisible(true);
            view.getTreePanel().setVisible(false);
            view.getTypeOfObjectPanel().setVisible(true);
            view.getOutputPanel().setVisible(true);
            view.getAddObjectPanel().setVisible(false);
            view.setTypeOfObject("Type of find");
            view.setTypeOfObjectChoose("Find by point","Find in area");
            view.getIOPanel().setBorder(BorderFactory.createTitledBorder("Find data"));
            view.getConfirmButton().setText("Find");
            view.getConfirmButton().setVisible(true);
            System.out.println("som tu");
            view.addMultipleItemsToOutput(returnAllData());
        }
    }

    class TreeButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.getTreePanel().setVisible(true);
            view.getMainPanel().setVisible(false);
            view.getOutputPanel().setVisible(false);
        }
    }

    class OtherButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.getOtherPanel().setVisible(true);
            view.getMainPanel().setVisible(false);
        }
    }

    /**
     * Main Panel listeners
     */
    // TODO
    class ConfirmButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (view.getConfirmButton().getText().equals("Find")) {
                if (view.getTypeOfObjectValue().equals("Find in area")) {
                    view.addMultipleItemsToOutput(findInArea());

                    //    ArrayList<String> salala = new ArrayList<>();
//            for (int i = 0; i < 2; i++) {
//        salala.add(newItem());
//    }
//            view.addMultipleItemsToOutput(salala);

                } else if (view.getTypeOfObjectValue().equals("Find by point")) {
                    view.addMultipleItemsToOutput(findAccordingToCoordinates());
                }
            } else if (view.getConfirmButton().getText().equals("Get objects to delete")) {
                view.addMultipleItemsToOutput(findAccordingToCoordinates());
                // TODO najst choosnoty prvok

            } else if (view.getConfirmButton().getText().equals("Get objects to edit")) {

            }
        }
    }

    class ConfirmButtonDownListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (view.getConfirmButtonDown().getText().equals("Create")) {
                if (view.getTypeOfObjectValue().equals("Real Estate")) {
                    addRealEstate();
                } else if (view.getTypeOfObjectValue().equals("Land Parcel")) {
                    addLandParcel();
                }
            } else if (view.getConfirmButtonDown().getText().equals("Edit")) {
                // TODO
            }
        }
    }

    class MainComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Object item = e.getItem();
                returnAction(item.toString());

            }
        }
    }

    /**
     * Create Tree listeners
     */
    class CreateTreeButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (view.getTypeOfTreeOption().equals("Real Estate")) {
                createNewEstateTree();
            } else if (view.getTypeOfTreeOption().equals("Land Parcel")) {
                createNewParcelTree();
            }
        }
    }


    /**
     * Output panel listener
     */

    class OutputListSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                Object selectedValue = view.getListOfOutput().getSelectedValue();
                if (view.getConfirmButton().getText().equals("Get objects to edit")) {
                    view.getMainPanel().setVisible(true);
                    view.getAddObjectPanel().setVisible(true);
                    view.getTypeOfObjectPanel().setVisible(false);
                    view.getOutputPanel().setVisible(false);
                    view.getIOPanel().setBorder(BorderFactory.createTitledBorder("Edit data"));
                    view.getConfirmButtonDown().setText("Edit");
                    view.getConfirmButton().setVisible(false);
                    manageCoordinateTwoPanel(true);
                } else if (view.getConfirmButton().getText().equals("Get objects to delete")) {
                    view.removeData((Data<? extends CadastralObject>) selectedValue);
                }
            }
        }
    }

    /**
     * work with model
     */
    public void addRealEstate() {
        this.cadaster.insertRealEstate(view.getNumberOfObject(),this.returnGPSFromView(),view.getAddDescription());
    }


    public void addLandParcel() {
        this.cadaster.insertLandParcel(view.getNumberOfObject(),this.returnGPSFromView(),view.getAddDescription());
    }

    public void createNewParcelTree() {
        this.cadaster.createLandParcelTree(returnGPSForTreeFromView(),this.view.getMaxDepthOfTree());
    }

    public void createNewEstateTree() {
        this.cadaster.createRealEstateTree(returnGPSForTreeFromView(),this.view.getMaxDepthOfTree());
    }

    public ArrayList<Data<? extends CadastralObject>> findAccordingToCoordinates() {
        return this.cadaster.findAccordingCoordinates(this.returnGPSFromView());
    }

    public ArrayList<Data<? extends CadastralObject>> findInArea() {
        return this.cadaster.findInArea(this.returnGPSFromView());
    }

    public ArrayList<Data<? extends CadastralObject>> returnAllData() {
        return this.cadaster.returnAllData();
    }

    /**
     * Private methods for work with data
     */

    /**
     * extract GPS array for work with data
     */
    private GPS[] returnGPSFromView() {
        GPS.Latitude latOne;
        GPS.Longitude longOne;
        GPS.Latitude latTwo;
        GPS.Longitude longTwo;

        if (view.getLatitudeOneOption().equals("North")) {
            latOne = GPS.Latitude.NORTH;
        } else {
            latOne = GPS.Latitude.SOUTH;
        }

        if (view.getLongitudeOneOption().equals("West")) {
            longOne = GPS.Longitude.WEST;
        } else {
            longOne = GPS.Longitude.EAST;
        }

        if (view.getLatitudeTwoOption().equals("North")) {
            latTwo = GPS.Latitude.NORTH;
        } else {
            latTwo = GPS.Latitude.SOUTH;
        }

        if (view.getLongitudeTwoOption().equals("West")) {
            longTwo = GPS.Longitude.WEST;
        } else {
            longTwo = GPS.Longitude.EAST;
        }

        GPS gpsOne = new GPS(latOne,view.getLatitudeOnePosition(),longOne,view.getLongitudeOnePosition());
        GPS gpsTwo = new GPS(latTwo,view.getLatitudeTwoPosition(),longTwo,view.getLongitudeTwoPosition());
        GPS[] gps = {gpsOne,gpsTwo};
        return gps;
    }

    /**
     * extract GPS array for range of tree
     */
    private GPS[] returnGPSForTreeFromView() {
        GPS.Latitude latOne;
        GPS.Longitude longOne;
        GPS.Latitude latTwo;
        GPS.Longitude longTwo;
        if (view.getLatitudeOneTreeOption().equals("North")) {
            latOne = GPS.Latitude.NORTH;
        } else {
            latOne = GPS.Latitude.SOUTH;
        }

        if (view.getLongitudeOneTreeOption().equals("West")) {
            longOne = GPS.Longitude.WEST;
        } else {
            longOne = GPS.Longitude.EAST;
        }

        if (view.getLatitudeTwoTreeOption().equals("North")) {
            latTwo = GPS.Latitude.NORTH;
        } else {
            latTwo = GPS.Latitude.SOUTH;
        }

        if (view.getLongitudeTwoTreeOption().equals("West")) {
            longTwo = GPS.Longitude.WEST;
        } else {
            longTwo = GPS.Longitude.EAST;
        }

        GPS gpsOne = new GPS(latOne,view.getLatitudeOneTreePosition(),longOne,view.getLongitudeOneTreePosition());
        GPS gpsTwo = new GPS(latTwo,view.getLatitudeTwoTreePosition(),longTwo,view.getLongitudeTwoTreePosition());
        GPS[] gps = {gpsOne,gpsTwo};
        return gps;
    }

    /**
     * change view of some items according to value of combobox
     */
    private void returnAction(String parString) {
        switch (parString) {
            case "Real Estate" -> {
                view.getObjectNumber().setText("Serial number");
            }
            case "Land Parcel" -> {
                view.getObjectNumber().setText("Parcel number");
            }
            case "Find by point" -> {
                view.getCoordinatesTwoPanel().setEnabled(false);
                for (Component cp : view.getCoordinatesTwoPanel().getComponents() ) {
                    cp.setEnabled(false);
                }
            }
            case "Find in area" -> {
                manageCoordinateTwoPanel(true);
            }
        }
    }

    /**
     * managing visibility of panel
     */
    private void manageCoordinateTwoPanel(Boolean setValue) {
        view.getCoordinatesTwoPanel().setEnabled(setValue);
        for (Component cp : view.getCoordinatesTwoPanel().getComponents() ) {
            cp.setEnabled(setValue);
        }
    }
}