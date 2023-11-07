package GUI;

import Data.CadastralObject;
import MainLogic.Cadaster;
import QuadTree.Coordinates;
import QuadTree.Data;
import Data.GPS;
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
    }

    class InsertButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.getInsertEditPanel().setVisible(true);
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
            view.getInsertEditPanel().setVisible(true);
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
            view.getInsertEditPanel().setVisible(true);
            view.getOutputPanel().setVisible(true);
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
            view.getInsertEditPanel().setVisible(true);
            view.getTypeOfObjectPanel().setVisible(true);
            view.getOutputPanel().setVisible(true);
            view.getAddObjectPanel().setVisible(false);
            view.setTypeOfObject("Type of find");
            view.setTypeOfObjectChoose("Find by point","Find in area");
            view.getIOPanel().setBorder(BorderFactory.createTitledBorder("Find data"));
            view.getConfirmButton().setText("Find");
            view.getConfirmButton().setVisible(true);
            // TODO nezabudni opravit
            ArrayList<String> salala = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                salala.add(newItem());
            }
            view.addMultipleItemsToOutput(salala);
        }
    }

    class TreeButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.getTreePanel().setVisible(true);
            view.getInsertEditPanel().setVisible(false);

        }
    }

    class OtherButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.getOtherPanel().setVisible(true);
            view.getInsertEditPanel().setVisible(false);
        }
    }

    // TODO
    class ConfirmButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (view.getConfirmButton().getText().equals("Find")) {
                if (view.getMainComboBoxValue().equals("Find in area")) {
                    Coordinates coors = new Coordinates(
                            view.getLatitudeOnePosition(),view.getLatitudeTwoPosition(),
                            view.getLongitudeOnePosition(),view.getLongitudeTwoPosition()
                    );
                    ArrayList<Data<CadastralObject>> list = cadaster.findInArea(coors);
                    // change to string
                    //view.addMultipleItemsToOutput();
                } else if (view.getMainComboBoxValue().equals("Find by point")) {

                }
            } else if (view.getConfirmButton().getText().equals("Get objects to delete")) {

            } else if (view.getConfirmButton().getText().equals("Get objects to edit")) {

            }
        }
    }

    class ConfirmButtonDownListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (view.getConfirmButtonDown().getText().equals("Create")) {
                if (view.getMainComboBoxValue().equals("Real Estate")) {
                    addRealEstate();
                } else if (view.getMainComboBoxValue().equals("Land Parcel")) {
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

    class OutputListSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                Object selectedValue = view.getListOfOutput().getSelectedValue();
                if (view.getConfirmButton().getText().equals("Get objects to edit")) {
                    view.getInsertEditPanel().setVisible(true);
                    view.getAddObjectPanel().setVisible(true);
                    view.getTypeOfObjectPanel().setVisible(false);
                    view.getOutputPanel().setVisible(false);
                    view.getIOPanel().setBorder(BorderFactory.createTitledBorder("Edit data"));
                    view.getConfirmButtonDown().setText("Edit");
                    view.getConfirmButton().setVisible(false);
                    manageCoordinateTwoPanel(true);
                } else if (view.getConfirmButton().getText().equals("Get objects to delete")) {
                    view.removeData(selectedValue);
                }
            }
        }
    }

    public void returnAction(String parString) {
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

    public void manageCoordinateTwoPanel(Boolean setValue) {
        view.getCoordinatesTwoPanel().setEnabled(setValue);
        for (Component cp : view.getCoordinatesTwoPanel().getComponents() ) {
            cp.setEnabled(setValue);
        }
    }

    // bullshit
    public String newItem() {
        Random random = new Random();
        return "Objekt" + random.nextInt();
    }


    /**
     * work with model
     */
    public void addRealEstate() {
        this.cadaster.createRealEstate(view.getNumberOfObject(),this.returnGPSFromView(),view.getAddDescription());
    }


    public void addLandParcel() {
        this.cadaster.createLandParcel(view.getNumberOfObject(),this.returnGPSFromView(),view.getAddDescription());
    }



//    public ArrayList<Data<CadastralObject>> returnListOfReadEstates() {
//        return this.cadaster.findInArea();
//    }
//
//    public ArrayList<Data<CadastralObject>> returnFindDataArea() {
//        return this.cadaster.findInArea();
//    }
//
//    public ArrayList<Data<CadastralObject>> returnFindData() {
//        return this.cadaster.findAccordingCoordinates();
//    }


    /**
     * extract GPS array from
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

}