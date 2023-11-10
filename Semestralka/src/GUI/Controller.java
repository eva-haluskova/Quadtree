package GUI;

import Data.CadastralObject;
import MainLogic.Cadaster;
import Data.GPS;
import MainLogic.CadastralObjectGenerator;
import QuadTree.Data;
import Data.LandParcel;
import Data.RealEstate;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

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
        this.view.addGenerateDataButtonListener(new GenerateButtonListener());

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
            view.setTypeOfObjectChoose("Real Estate","Land Parcel", null);
            view.getIOPanel().setBorder(BorderFactory.createTitledBorder("Insert data"));
            view.getConfirmButtonDown().setText("Create");
            view.getConfirmButton().setVisible(false);
            manageCoordinateTwoPanel(true);
            view.getNumberOfObjects().setVisible(false);
            view.getSizeOfGenerateObjects().setVisible(false);
            view.getCoordinatesOnePanel().setBorder(BorderFactory.createTitledBorder("Coordinates number one:"));
            view.getCoordinatesTwoPanel().setBorder(BorderFactory.createTitledBorder("Coordinates number one:"));

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
            view.setTypeOfObjectChoose("Real Estate","Land Parcel", null);
            view.getIOPanel().setBorder(BorderFactory.createTitledBorder("Edit data"));
            view.getConfirmButton().setText("Get objects to edit");
            view.getConfirmButton().setVisible(true);
            manageCoordinateTwoPanel(false);
            view.getNumberOfObjects().setVisible(false);
            view.getSizeOfGenerateObjects().setVisible(false);
            view.getCoordinatesOnePanel().setBorder(BorderFactory.createTitledBorder("Coordinates number one:"));
            view.getCoordinatesTwoPanel().setBorder(BorderFactory.createTitledBorder("Coordinates number one:"));
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
            view.setTypeOfObjectChoose("Real Estate","Land Parcel", null);
            view.getIOPanel().setBorder(BorderFactory.createTitledBorder("Delete data"));
            view.getConfirmButton().setText("Get objects to delete");
            view.getConfirmButton().setVisible(true);
            manageCoordinateTwoPanel(false);
            view.getNumberOfObjects().setVisible(false);
            view.getSizeOfGenerateObjects().setVisible(false);
            view.getCoordinatesOnePanel().setBorder(BorderFactory.createTitledBorder("Coordinates number one:"));
            view.getCoordinatesTwoPanel().setBorder(BorderFactory.createTitledBorder("Coordinates number one:"));


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
            view.setTypeOfObjectChoose("Find by point","Find in area", null);
            view.getIOPanel().setBorder(BorderFactory.createTitledBorder("Find data"));
            view.getConfirmButton().setText("Find");
            view.getConfirmButton().setVisible(true);
            System.out.println("som tu");
            view.getNumberOfObjects().setVisible(false);
            view.getSizeOfGenerateObjects().setVisible(false);
            view.getCoordinatesOnePanel().setBorder(BorderFactory.createTitledBorder("Coordinates number one:"));
            view.getCoordinatesTwoPanel().setBorder(BorderFactory.createTitledBorder("Coordinates number one:"));
        }
    }

    class TreeButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.getTreePanel().setVisible(true);
            view.getMainPanel().setVisible(false);
            view.getOutputPanel().setVisible(false);
            view.getIOPanel().setBorder(BorderFactory.createTitledBorder("Create Tree"));
        }
    }

    class OtherButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.getOtherPanel().setVisible(true);
            view.getMainPanel().setVisible(false);
        }
    }

    class GenerateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.getMainPanel().setVisible(true);
            view.getAddObjectPanel().setVisible(false);
            view.getOutputPanel().setVisible(true);
            view.getTreePanel().setVisible(false);
            view.getIOPanel().setBorder(BorderFactory.createTitledBorder("Generate data"));
            view.setTypeOfObjectChoose("Real Estate","Land Parcel", "Both");
            view.getConfirmButton().setText("Generate");
            view.getNumberOfObjects().setVisible(true);
            manageCoordinateTwoPanel(true);
            view.getNumberOfObjects().setBorder(BorderFactory.createTitledBorder("Count"));
            view.getSizeOfGenerateObjects().setBorder(BorderFactory.createTitledBorder("Size"));
            view.getCoordinatesOnePanel().setBorder(BorderFactory.createTitledBorder("First coordinate of range:"));
            view.getCoordinatesTwoPanel().setBorder(BorderFactory.createTitledBorder("Second coordinate of range:"));
            view.getSizeOfGenerateObjects().setVisible(true);
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
                    view.updateList(findInArea());
                    System.out.println("hladam v arei");
                } else if (view.getTypeOfObjectValue().equals("Find by point")) {
                    view.updateList(findAccordingToCoordinates());
                    System.out.println("hladam by point");
                }
            } else if (view.getConfirmButton().getText().equals("Get objects to delete")) {
                view.updateList(findAccordingToCoordinates());
                // TODO najst choosnoty prvok

            } else if (view.getConfirmButton().getText().equals("Get objects to edit")) {
                view.updateList(findAccordingToCoordinates());
                // todo
            } else if (view.getConfirmButton().getText().equals("Generate")) {
                if (view.getTypeOfObjectValue().equals("Real Estate")) {
                    view.updateList(generateData(CadastralObjectGenerator.GenerateOption.REAL_ESTATE));
                } else if (view.getTypeOfObjectValue().equals("Land Parcel")) {
                    view.updateList(generateData(CadastralObjectGenerator.GenerateOption.LAND_PARCEL));
                } else {
                    view.updateList(generateData(CadastralObjectGenerator.GenerateOption.BOTH));
                }
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

                Data<? extends CadastralObject> selectedValueObject = view.getOutputListOfObj().getSelectedValue();
                System.out.println("obj ---" + selectedValueObject);
                Object selectedValue = view.getListOfOutput().getSelectedValue();
                System.out.println("sv---" + selectedValue);
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
                    System.out.println("sv " +selectedValue);
                    System.out.println("svobj " + selectedValueObject);
                    deleteData((Data<? extends CadastralObject>) selectedValue);
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
        return this.cadaster.findAccordingCoordinates(this.returnFirstGpsFromView());
    }

    public ArrayList<Data<? extends CadastralObject>> findInArea() {
        return this.cadaster.findInArea(this.returnGPSFromView());
    }

    public ArrayList<Data<? extends CadastralObject>> returnAllData() {
        return this.cadaster.returnAllData();
    }

    public ArrayList<Data<? extends CadastralObject>> generateData(CadastralObjectGenerator.GenerateOption type) {
        return this.cadaster.generateObjects(type, view.getNumberOfGeneratedObjects(),view.getSizeOfGeneratedObjectsNumber(),returnGPSFromView());
    }

    public void deleteData(Data<? extends CadastralObject> dataToDelete) {
        System.out.println("ale do metodky som sa dostala");
        System.out.println(dataToDelete.toString());
        if (dataToDelete.getData().isInstanceOf().equals(CadastralObject.TypeOfCadastralObject.LAND_PARCEL)) {
            this.cadaster.deleteLandParcel((Data<LandParcel>)dataToDelete);
        } else {
            this.cadaster.deleteRealEstate((Data<RealEstate>)dataToDelete);
        }

    }

    /**
     * Private methods for work with data
     */

    /**
     * extract GPS coordinates of first input of cooridnates
     */
    private GPS returnFirstGpsFromView() {
        GPS.Latitude latOne;
        GPS.Longitude longOne;

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

        GPS gpsOne = new GPS(latOne,view.getLatitudeOnePosition(),longOne,view.getLongitudeOnePosition());
        return gpsOne;
    }

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