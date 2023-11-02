package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton ConfirmButton;
    private JButton FindAreaButton;

    public View() {
        InsertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // enable first coorinate
                LangitudeOne.setEnabled(true);
                LatitudeOne.setEnabled(true);
                LatitudeOnePosition.setEnabled(true);
                LatitudeOneOption.setEnabled(true);
                LongitudeOneOption.setEnabled(true);
                LongitudeOnePosition.setEnabled(true);
                // enable second coorinate
                LongitudeTwo.setEnabled(true);
                LatitudeTwo.setEnabled(true);
                LatitudeTwoOption.setEnabled(true);
                LatitudeTwoPosition.setEnabled(true);
                LongitudeTwoOption.setEnabled(true);
                LongitudeTwoPosition.setEnabled(true);
                // enable creating object
                Description.setEnabled(true);
                TitleNumber.setEnabled(true);
                AddDescription.setEnabled(true);
                NumberOfObject.setEnabled(true);

                ConfirmButton.setText("Pridaj");
                ConfirmButton.setEnabled(true);
            }
        });

        ConfirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LangitudeOne.setEnabled(false);
                LatitudeOne.setEnabled(false);
                LatitudeOnePosition.setEnabled(false);
                LatitudeOneOption.setEnabled(false);
                LongitudeOneOption.setEnabled(false);
                LongitudeOnePosition.setEnabled(false);
                // enable second coorinate
                LongitudeTwo.setEnabled(false);
                LatitudeTwo.setEnabled(false);
                LatitudeTwoOption.setEnabled(false);
                LatitudeTwoPosition.setEnabled(false);
                LongitudeTwoOption.setEnabled(false);
                LongitudeTwoPosition.setEnabled(false);
                // enable creating object
                Description.setEnabled(false);
                TitleNumber.setEnabled(false);
                AddDescription.setEnabled(false);
                NumberOfObject.setEnabled(false);

                ConfirmButton.setEnabled(false);

            }
        });

        FindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LangitudeOne.setEnabled(true);
                LatitudeOne.setEnabled(true);
                LatitudeOnePosition.setEnabled(true);
                LatitudeOneOption.setEnabled(true);
                LongitudeOneOption.setEnabled(true);
                LongitudeOnePosition.setEnabled(true);

                // enable second coorinate
                LongitudeTwo.setEnabled(false);
                LatitudeTwo.setEnabled(false);
                LatitudeTwoOption.setEnabled(false);
                LatitudeTwoPosition.setEnabled(false);
                LongitudeTwoOption.setEnabled(false);
                LongitudeTwoPosition.setEnabled(false);
                // enable creating object
                Description.setEnabled(false);
                TitleNumber.setEnabled(false);
                AddDescription.setEnabled(false);
                NumberOfObject.setEnabled(false);

                ConfirmButton.setText("Vyhladaj");
                ConfirmButton.setEnabled(true);
            }
        });

        CoordinatesOnePanel.setBorder(BorderFactory.createTitledBorder("Prva suradnica"));
        CoordinatesTwoPanel.setBorder(BorderFactory.createTitledBorder("Druha suradnica"));
        AddObjectPanel.setBorder(BorderFactory.createTitledBorder("Dalsie informacie"));
        OutputPanel.setBorder(BorderFactory.createTitledBorder("Zoznam vysledkov"));

        FindAreaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // enable first coorinate
                LangitudeOne.setEnabled(true);
                LatitudeOne.setEnabled(true);
                LatitudeOnePosition.setEnabled(true);
                LatitudeOneOption.setEnabled(true);
                LongitudeOneOption.setEnabled(true);
                LongitudeOnePosition.setEnabled(true);
                // enable second coorinate
                LongitudeTwo.setEnabled(true);
                LatitudeTwo.setEnabled(true);
                LatitudeTwoOption.setEnabled(true);
                LatitudeTwoPosition.setEnabled(true);
                LongitudeTwoOption.setEnabled(true);
                LongitudeTwoPosition.setEnabled(true);

                ConfirmButton.setText("Vyhladaj");
                ConfirmButton.setEnabled(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Kataster");
        frame.setContentPane(new View().RootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void collectDataForInsert() {

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
