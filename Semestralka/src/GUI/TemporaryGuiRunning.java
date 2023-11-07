package GUI;

import MainLogic.Cadaster;

public class TemporaryGuiRunning {

    public static void main(String[] args) {
        View testView = new View();
        Cadaster testCadaster = new Cadaster();
        Controller controller = new Controller(testCadaster,testView);
        testView.setVisible(true);
        testView.setContentPane(testView.getRootPanel());

        testView.setResizable(false);
        testView.setSize(600,400);
        testView.setTitle("Cadaster");
        testView.setLocationRelativeTo(null);
        testView.setDefaultCloseOperation(testView.EXIT_ON_CLOSE);
        testView.pack();


//        JFrame frame = new JFrame("Kataster");
//        frame.setContentPane(new View().RootPanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
    }
}
