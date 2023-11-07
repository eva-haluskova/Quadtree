package GUI;

import Data.CadastralObject;
import MainLogic.Cadaster;
import QuadTree.Coordinates;
import QuadTree.Data;
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

            }
        }
    }

    class OtherButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.getOtherPanel().setVisible(true);
            view.getInsertEditPanel().setVisible(false);
        }
    }

    class MainComboBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Object item = e.getItem();
                //returnAction(view.getMainComboBoxValue());
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

    public String newItem() {
        Random random = new Random();
        return "Objekt" + random.nextInt();
    }


    /**
     * work with model
     */
    public void addRealEstate() {
        Coordinates coors = new Coordinates(20,30,30,30);
        this.cadaster.createRealEstate(view.getNumberOfObject(),coors,view.getAddDescription());
    }

    public void addLandParcel() {
        Coordinates coors = new Coordinates(20,30,30,30);
        this.cadaster.createLandParcel(view.getNumberOfObject(),coors,view.getAddDescription());
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

}