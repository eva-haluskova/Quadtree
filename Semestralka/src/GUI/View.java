package GUI;

import javax.swing.*;

public class View {
    private JPanel RootPanel;
    private JPanel MenuPanel;
    private JButton InsertButton;
    private JButton FindButton;
    private JButton EditButton;
    private JButton DeleteButton;
    private JPanel InputPanel;
    private JPanel CoordinatesOnePanel;
    private JComboBox LatitudeOneOption;
    private JComboBox LongitudeOneOption;
    private JTextField LongitudeOnePosition;
    private JPanel CoordinatesTwoPanel;
    private JComboBox LatitudeTwoOption;
    private JTextField LatitudeTwoPosition;
    private JComboBox LongitudeTwoOption;
    private JTextField LongitudeTwoPosition;
    private JLabel LatitudeOne;
    private JLabel LangitudeOne;
    private JLabel LatitudeTwo;
    private JLabel LongitudeTwo;
    private JTextField LatitudeOnePosition;
    private JTextField NumberOfObject;
    private JTextField AddDescription;
    private JLabel Description;
    private JLabel TitleNumber;
    private JPanel AddObjectPanel;
    private JPanel OutputPanel;
    private JList ListOfOutput;

    public static void main(String[] args) {
        JFrame frame = new JFrame("View");
        frame.setContentPane(new View().RootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
