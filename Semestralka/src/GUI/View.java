package GUI;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Objects;

public class View extends JFrame {

    private JPanel RootPanel;

    private JPanel MenuPanel;
    private JButton InsertButton;
    private JButton FindButton;
    private JButton FindAreaButton;
    private JButton EditButton;
    private JButton DeleteButton;

    private JPanel InputPanel;

    private JPanel CoordinatesOnePanel;
    private JComboBox LatitudeOneOption;
    private JTextField LatitudeOnePosition;
    private JComboBox LongitudeOneOption;
    private JTextField LongitudeOnePosition;

    private JPanel CoordinatesTwoPanel;
    private JComboBox LatitudeTwoOption;
    private JTextField LatitudeTwoPosition;
    private JComboBox LongitudeTwoOption;
    private JTextField LongitudeTwoPosition;

    private JPanel AddObjectPanel;
    private JLabel TitleNumber;
    private JTextField NumberOfObject;
    private JTextField AddDescription;

    private JButton ConfirmButton;
    private JButton TreeButton;
    private JButton OtherButton;
    private JPanel IOPanel;
    private JPanel InsertEditPanel;
    private JPanel FindPanel;
    private JPanel DeleteEditPanel;
    private JPanel TreePanel;
    private JPanel OtherPanel;
    private JLabel CoordinateOneLatitudeText;
    private JComboBox CoordinateOneLatitudeOption;
    private JTextField CoordinateOneLatitudePosition;
    private JLabel CoordinateOneLongitudeText;
    private JComboBox CoordinateOneLongitudeOption;
    private JTextField CoordinateOneLongitudePosition;
    private JPanel TypeOfObjectPanel;
    private JLabel TypeOfObject;
    private JComboBox TypeOfObjectChoose;
    private JPanel NEObjectInfoPanel;
    private JLabel CoordinateTwoLatitudeText;
    private JComboBox CoordinateTwoLatitudeOption;
    private JTextField CoordinateTwoLatitudePosition;
    private JLabel CoordinatesTwoLongitudeText;
    private JTextField CoordinateTwoLongitudePosition;
    private JComboBox CoordinateTwoLongitudeOption;
    private JPanel CoorsPanel;


    private JPanel LowerPanel;
    private JLabel ObjectNumber;
    private JTextField ObjectNumberText;
    private JLabel ObjectDescription;
    private JTextArea ObjectDescriptionText;
    private JPanel OutputPanel;
    private JButton ConfirmButtonDown;
    private JList ListOfOutput;
    private JScrollPane ScrollPane;
    JList<String> outputList;
    DefaultListModel outputModel;

    public View() {
        CoordinatesOnePanel.setBorder(BorderFactory.createTitledBorder("Coordinate number one"));
        CoordinatesTwoPanel.setBorder(BorderFactory.createTitledBorder("Coordinate number two"));
        outputModel = new DefaultListModel<>();
        outputList = new JList<>(outputModel);
        ListOfOutput.setModel(outputModel);
    }

    /**
     * Listeners for different actions
     */
    void addInsertButtonListener(ActionListener insertButtonListener) {
        InsertButton.addActionListener(insertButtonListener);
    }

    void addEditButtonListener(ActionListener editButtonListener) {
        EditButton.addActionListener(editButtonListener);
    }

    void addDeleteButtonListener(ActionListener deleteButtonListener) {
        DeleteButton.addActionListener(deleteButtonListener);
    }

    void addTreeButtonListener(ActionListener treeButtonListener) {
        TreeButton.addActionListener(treeButtonListener);
    }

    void addFindButtonListener(ActionListener findButtonListener) {
        FindButton.addActionListener(findButtonListener);
    }

    void addOtherButtonListener(ActionListener otherButtonListener) {
        OtherButton.addActionListener(otherButtonListener);
    }

    void addMainComboBoxListener(ItemListener comboBoxListener) {
        TypeOfObjectChoose.addItemListener(comboBoxListener);
    }

    void addOutputListSelectionListener(ListSelectionListener listSelectionListener) {
        ListOfOutput.addListSelectionListener(listSelectionListener);
    }


    /**
     * Getters of JPanels
     */
    public JPanel getRootPanel() {
        return RootPanel;
    }

    public JPanel getInsertEditPanel() {
        return InsertEditPanel;
    }

    public JPanel getDeleteEditPanel() {
        return DeleteEditPanel;
    }

    public JPanel getFindPanel() {
        return FindPanel;
    }

    public JPanel getTreePanel() {
        return TreePanel;
    }

    public JPanel getOtherPanel() {
        return OtherPanel;
    }

    public JPanel getMenuPanel() {
        return MenuPanel;
    }

    public JPanel getInputPanel() {
        return InputPanel;
    }

    public JPanel getCoordinatesOnePanel() {
        return CoordinatesOnePanel;
    }

    public JPanel getCoordinatesTwoPanel() {
        return CoordinatesTwoPanel;
    }

    public JPanel getAddObjectPanel() {
        return AddObjectPanel;
    }

    public JPanel getIOPanel() {
        return IOPanel;
    }

    public JPanel getTypeOfObjectPanel() {
        return TypeOfObjectPanel;
    }

    public JPanel getCoorsPanel() {
        return CoorsPanel;
    }

    public JPanel getLowerPanel() {
        return LowerPanel;
    }

    public JPanel getOutputPanel() {
        return OutputPanel;
    }

    /**
     * Getters of JButtons
     */
    public JButton getInsertButton() {
        return InsertButton;
    }

    public JButton getFindButton() {
        return FindButton;
    }

    public JButton getEditButton() {
        return EditButton;
    }

    public JButton getDeleteButton() {
        return DeleteButton;
    }

    public JButton getTreeButton() {
        return TreeButton;
    }

    public JButton getOtherButton() {
        return OtherButton;
    }

    public JButton getConfirmButton() {
        return ConfirmButton;
    }

    public JButton getConfirmButtonDown() {
        return ConfirmButtonDown;
    }

    public void setConfirmButtonText(String text) {
        ConfirmButton.setText(text);
    }

    /**
     * Getters and setters of JTextfields
     */
    public Integer getLatitudeOnePosition() {
        return Integer.parseInt(LatitudeOnePosition.getText());
    }

    public void setLatitudeOnePosition(int parLatitudeOnePosition) {
        LatitudeOnePosition.setText(Integer.toString(parLatitudeOnePosition));
    }

    public Integer getLongitudeOnePosition() {
        return Integer.parseInt(LongitudeOnePosition.getText());
    }

    public void setLongitudeOnePosition(int parLongitudeOnePosition) {
        LongitudeOnePosition.setText(Integer.toString(parLongitudeOnePosition));
    }

    public Integer getLatitudeTwoPosition() {
        return Integer.parseInt(LatitudeTwoPosition.getText());
    }

    public void setLatitudeTwoPosition(int parLatitudeTwoPosition) {
        LatitudeTwoPosition.setText(Integer.toString(parLatitudeTwoPosition));
    }

    public Integer getLongitudeTwoPosition() {
        return Integer.parseInt(LongitudeTwoPosition.getText());
    }

    public void setLongitudeTwoPosition(int parLongitudeTwoPosition) {
        LongitudeTwoPosition.setText(Integer.toString(parLongitudeTwoPosition));
    }

    public Integer getNumberOfObject() {
        return Integer.parseInt(NumberOfObject.getText());
    }

    public void setNumberOfObject(int parNumberOfObject) {
        NumberOfObject.setText(Integer.toString(parNumberOfObject));
    }

    public String getAddDescription() {
        return AddDescription.getText();
    }

    public void setAddDescription(String parAddDescription) {
        AddDescription.setText(parAddDescription);
    }


    public JLabel getObjectNumber() {
        return ObjectNumber;
    }

//    public void setListOfOutput() {
//
//    }

    public void addItemToOutput(String param) {
        this.outputModel.addElement(param);
    }

    public void addMultipleItemsToOutput(ArrayList<String> param) {
        this.outputModel.addAll(param);
    }

    public void removeData(Object o) {
        this.outputModel.removeElement(o);
    }

    public void removeAllData() {
        this.outputModel.removeAllElements();
    }

//    public DefaultListModel<String> createScrollPanel() {
//
//        return listModel;
//    }




    /**
     *
     * Getters of Comboboxes
     */
    public String getLatitudeOneOption() {
        return Objects.requireNonNull(LatitudeOneOption.getSelectedItem()).toString();
    }

    public String getLongitudeOneOption() {
        return LongitudeOneOption.getSelectedItem().toString();
    }

    public String getLatitudeTwoOption() {
        return LatitudeTwoOption.getSelectedItem().toString();
    }

    public String getLongitudeTwoOption() {
        return LongitudeTwoOption.getSelectedItem().toString();
    }

    public String getMainComboBoxValue() {
        return TypeOfObjectChoose.getSelectedItem().toString();
    }

    /**
     *  Setters of some needed JLables
     */

    public void setTypeOfObject(String text) {
        TypeOfObject.setText(text);
    }

    public void setTypeOfObjectChoose(String value1, String value2) {
        TypeOfObjectChoose.removeAllItems();
        TypeOfObjectChoose.addItem(value1);
        TypeOfObjectChoose.addItem(value2);
    }

    public JList getListOfOutput() {
        return ListOfOutput;
    }

    /**
     * Setter for JList
     */


}
