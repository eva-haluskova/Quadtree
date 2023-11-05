package GUI;

import MainLogic.Cadaster;
import QuadTree.Coordinates;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class View {

    private Controller controller;
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

    private Cadaster cadaster;

    public View() {

        this.cadaster = new Cadaster();

        CoordinatesOnePanel.setBorder(BorderFactory.createTitledBorder("Prva suradnica"));
        CoordinatesTwoPanel.setBorder(BorderFactory.createTitledBorder("Druha suradnica"));
        AddObjectPanel.setBorder(BorderFactory.createTitledBorder("Dalsie informacie"));
        OutputPanel.setBorder(BorderFactory.createTitledBorder("Zoznam vysledkov"));




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
            public void addUpdateListener(ActionListener listener) {
                ConfirmButton.addActionListener(listener);
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



    public String getDesc() {
        return Description.getText();
    }
    public int getSerialNumb() {
        return Integer.valueOf(NumberOfObject.getText());
    }

    public Coordinates getCoordinateOne() {
        if (LangitudeOne.getText() == "Vychodna") {
            return new Coordinates(0,0,2,4);
        } else {
            return new Coordinates(9,3,4,2);
        }

    }



    public void setDisplay(String value) {
        ListOfOutput.add(LangitudeOne);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }


}
