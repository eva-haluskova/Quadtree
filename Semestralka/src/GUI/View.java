package GUI;

import Data.CadastralObject;
import QuadTree.Data;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;

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
    private JPanel MainPanel;
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
    private JTextField TextLoadData;
    private JTextField TextSaveData;
    private JPanel CoordinateOneOfTreePanel;
    private JLabel LatitudeOneTreeText;
    private JComboBox LatitudeOneTreeOption;
    private JTextField LatitudeOneTreePosition;
    private JLabel LongitudeOneTreeText;
    private JComboBox LongitudeOneTreeOption;
    private JTextField LongitudeOneTreePosition;
    private JPanel CoordinateTwoOfTreePanel;
    private JLabel LatitudeTwoTreeText;
    private JComboBox LatitudeTwoTreeOption;
    private JTextField LatitudeTwoTreePosition;
    private JLabel LongitudeTwoTreeText;
    private JTextField LongitudeTwoTreePosition;
    private JComboBox LongitudeTwoTreeOption;
    private JPanel TypeOfTreePanel;
    private JLabel TypeOfTreeText;
    private JComboBox TypeOfTreeOption;
    private JPanel DepthPanelPanel;
    private JLabel MaxDepthText;
    private JTextField MaxDepthNumber;
    private JPanel AutomatizationPanel;
    private JCheckBox AutoOptimalization;
    private JPanel LoadDataPanel;
    private JPanel MainTreePanel;
    private JLabel TitleTextNTree;
    private JLabel LoadText;
    private JLabel TextSave;
    private JButton LoadDataButton;
    private JButton SaveDataButton;
    private JButton CreateTreeButton;
    private JButton GenerateDataButton;
    private JTextField NumberOfObjects;
    private JTextField SizeOfGenerateObjects;
    private JList ListOfPointers;
    private JScrollPane ScrollPaneOfPointers;
    JList<Data<? extends CadastralObject>> outputList;
    DefaultListModel outputModel;

    DefaultListModel pointerModel;

    public View() {
        CoordinatesOnePanel.setBorder(BorderFactory.createTitledBorder("Coordinate number one"));
        CoordinatesTwoPanel.setBorder(BorderFactory.createTitledBorder("Coordinate number two"));
        outputModel = new DefaultListModel<>();
        outputList = new JList<>();
        pointerModel = new DefaultListModel<>();

        // TODO dbaj na to ze tu pridelujes veci dvom roznym veciam salalala
        //this.outputList.setCellRenderer(new CadastralObjectRender());
        ListOfOutput.setCellRenderer(new CadastralObjectRender());
        ListOfOutput.setModel(outputModel);
        LowerPanel.setPreferredSize(new Dimension(800,150));
        LoadDataButton.setPreferredSize(new Dimension(800,90));
        ListOfPointers.setModel(pointerModel);


        //outputList.setSelectionMode(SINGLE_SELECTION);

        TreePanel.setVisible(true);
        MainPanel.setVisible(false);
        OtherPanel.setVisible(false);
    }


    public class CadastralObjectRender extends JLabel implements ListCellRenderer<Data<? extends CadastralObject>> {
        public CadastralObjectRender() {
            setOpaque(true);
        }
        @Override
        public Component getListCellRendererComponent(JList<? extends Data<? extends CadastralObject>> list, Data<? extends CadastralObject> data, int index, boolean isSelected, boolean cellHasFocus) {
            setText(data.getData().toString());

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            return this;
        }
    }


    /**
     * Listeners for different actions
     */

    /**
     * Menu Button listeners
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

    void addGenerateDataButtonListener(ActionListener generateButtonListener) {
        GenerateDataButton.addActionListener(generateButtonListener);
    }

    /**
     * Main Panel Buttons
     */
    void addMainComboBoxListener(ItemListener comboBoxListener) {
        TypeOfObjectChoose.addItemListener(comboBoxListener);
    }

    void addConfirmButtonListener(ActionListener createConfirmButtonListener) {
        ConfirmButton.addActionListener(createConfirmButtonListener);
    }

    void addConfirmButtonDownListener(ActionListener createConfirmButtonDownListener) {
        ConfirmButtonDown.addActionListener(createConfirmButtonDownListener);
    }

    /**
     * Tree Panel Buttons
     */
    void addCreateTreeButtonListener(ActionListener createTreeButtonListener) {
        CreateTreeButton.addActionListener(createTreeButtonListener);
    }

    /**
     * List of output listener
     */
    void addOutputListSelectionListener(ListSelectionListener listSelectionListener) {
        ListOfOutput.addListSelectionListener(listSelectionListener);
    }

    /**
     * Getters of JPanels
     */
    public JPanel getRootPanel() {
        return RootPanel;
    }

    public JPanel getMainPanel() {
        return MainPanel;
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
     * Getters of needed Buttons
     */
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
     * Getters and setters of needed Text fields
     */

    /**
     * Coordinates One Panel
     */
    public Double getLatitudeOnePosition() {
        return Double.parseDouble(CoordinateOneLatitudePosition.getText());
    }

    public void setLatitudeOnePosition(double parLatitudeOnePosition) {
        CoordinateOneLatitudePosition.setText(Double.toString(parLatitudeOnePosition));
    }

    public Double getLongitudeOnePosition() {
        return Double.parseDouble(CoordinateOneLongitudePosition.getText());
    }

    public void setLongitudeOnePosition(double parLongitudeOnePosition) {
        CoordinateOneLongitudePosition.setText(Double.toString(parLongitudeOnePosition));
    }

    /**
     * Coordinates Two Panel
     */
    public Double getLatitudeTwoPosition() {
        return Double.parseDouble(CoordinateTwoLatitudePosition.getText());
    }

    public void setLatitudeTwoPosition(double parLatitudeTwoPosition) {
        CoordinateTwoLatitudePosition.setText(Double.toString(parLatitudeTwoPosition));
    }

    public Double getLongitudeTwoPosition() {
        return Double.parseDouble(CoordinateTwoLongitudePosition.getText());
    }

    public void setLongitudeTwoPosition(double parLongitudeTwoPosition) {
        CoordinateTwoLongitudePosition.setText(Double.toString(parLongitudeTwoPosition));
    }

    public JTextField getNumberOfObjects() {
        return NumberOfObjects;
    }

    public Integer getNumberOfGeneratedObjects() {
        return Integer.parseInt(NumberOfObjects.getText());
    }

    public JTextField getSizeOfGenerateObjects() {
        return SizeOfGenerateObjects;
    }

    public Integer getSizeOfGeneratedObjectsNumber() {
        return Integer.parseInt((SizeOfGenerateObjects.getText()));
    }

    /**
     * Add object Panel
     */
    public Integer getNumberOfObject() {
        return Integer.parseInt(ObjectNumberText.getText());
    }

    public void setNumberOfObject(int parNumberOfObject) {
        ObjectNumberText.setText(Integer.toString(parNumberOfObject));
    }

    public String getAddDescription() {
        return ObjectDescriptionText.getText();
    }

    public void setAddDescription(String parAddDescription) {
        ObjectDescriptionText.setText(parAddDescription);
    }

    public JLabel getObjectNumber() {
        return ObjectNumber;
    }


    /**
     * Coordinates for creating tree
     */
    public Integer getLatitudeOneTreePosition() {
        return Integer.parseInt(LatitudeOneTreePosition.getText());
    }

    public Integer getLongitudeOneTreePosition() {
        return Integer.parseInt(LongitudeOneTreePosition.getText());
    }

    public Integer getLatitudeTwoTreePosition() {
        return Integer.parseInt(LatitudeTwoTreePosition.getText());
    }

    public Integer getLongitudeTwoTreePosition() {
        return Integer.parseInt(LongitudeTwoTreePosition.getText());
    }

    public Integer getMaxDepthOfTree() {
        return Integer.parseInt(MaxDepthNumber.getText());
    }

    /**
     *
     * Getters  and setters of Comboboxes
     */

    /**
     * Coordinates One Panel
     */
    public String getLatitudeOneOption() {
        return CoordinateOneLatitudeOption.getSelectedItem().toString();
    }

    public String getLongitudeOneOption() {
        return CoordinateOneLongitudeOption.getSelectedItem().toString();
    }

    public void setLatitudeOneOption(int index) {
        CoordinateOneLatitudeOption.setSelectedIndex(index);
    }

    public void setLongitudeOneOption(int index) {
        CoordinateOneLongitudeOption.setSelectedIndex(index);
    }

    public void setLatitudeTwoOption(int index) {
        CoordinateTwoLatitudeOption.setSelectedIndex(index);
    }

    public void setLongitudeTwoOption(int index) {
        CoordinateTwoLongitudeOption.setSelectedIndex(index);
    }


    /**
     * Coordinates Two Panel
     */
    public String getLatitudeTwoOption() {
        return CoordinateTwoLatitudeOption.getSelectedItem().toString();
    }

    public String getLongitudeTwoOption() {
        return CoordinateTwoLongitudeOption.getSelectedItem().toString();
    }

    /**
     * Coordinates of tree
     */
    public String getLatitudeOneTreeOption() {
        return LatitudeOneTreeOption.getSelectedItem().toString();
    }

    public String getLongitudeOneTreeOption() {
        return LongitudeOneTreeOption.getSelectedItem().toString();
    }

    public String getLatitudeTwoTreeOption() {
        return LatitudeTwoTreeOption.getSelectedItem().toString();
    }

    public String getLongitudeTwoTreeOption() {
        return LongitudeTwoTreeOption.getSelectedItem().toString();
    }

    /**
     * Getters for types
     */
    public String getTypeOfObjectValue() {
        return TypeOfObjectChoose.getSelectedItem().toString();
    }

    public String getTypeOfTreeOption() {
        return TypeOfTreeOption.getSelectedItem().toString();
    }

    public boolean getAutoOptimalization() {
        return AutoOptimalization.isSelected();
    }

    /**
     *  Setters of some needed JLables
     */
    public void setTypeOfObject(String text) {
        TypeOfObject.setText(text);
    }

    public void setTypeOfObjectChoose(String value1, String value2, String both) {
        TypeOfObjectChoose.removeAllItems();
        TypeOfObjectChoose.addItem(value1);
        TypeOfObjectChoose.addItem(value2);
        if (both != null) {
            TypeOfObjectChoose.addItem(both);
        }
    }

    /**
     * Work with output list
     */
    public void addItemToOutput(Data<? extends CadastralObject> param) {
        this.outputModel.addElement(param);
    }

    public void addMultipleItemsToOutput(ArrayList<Data<? extends CadastralObject>> param) {
        this.outputModel.addAll(param);
    }

    public void removeData(Data<? extends CadastralObject> o) {
        this.outputModel.removeElement(o);
    }

    public void removeAllData() {
        this.outputModel.removeAllElements();
    }

    public JList getListOfOutput() {
        return ListOfOutput;
    }

    public void updateList(ArrayList<Data<? extends CadastralObject>> param) {
        this.outputModel.removeAllElements();
        this.outputModel.addAll(param);
    }

    public void updatePointerList(ArrayList<? extends CadastralObject> param) {
        this.pointerModel.removeAllElements();
        this.pointerModel.addAll(param);
    }

    public JList<Data<? extends CadastralObject>> getOutputListOfObj() {
        return this.outputList;
    }

    public JScrollPane getScrollPanePointer() {
        return ScrollPaneOfPointers;
    }
}
