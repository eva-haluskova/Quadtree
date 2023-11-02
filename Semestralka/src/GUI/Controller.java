package GUI;

import MainLogic.Cadaster;

public class Controller {

    private Cadaster cadaster;
    private View view;

    public Controller(View view) {
        this.cadaster = new Cadaster();
        this.view = view;
    }


}
