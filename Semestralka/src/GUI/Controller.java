package GUI;

import Data.CadastralObject;
import MainLogic.Cadaster;
import QuadTree.Data;

import javax.swing.*;
import java.util.ArrayList;

public class Controller {

    private Cadaster cadaster;
    private View view;

    public Controller() {
        this.cadaster = new Cadaster();
        this.view = new View();
        //this.view.setController(this);
    }

    public void addRealEstate() {
       this.cadaster.createRealEstate(this.view.getSerialNumb(),this.view.getCoordinateOne(),this.view.getDesc());
    }

    public ArrayList<Data<CadastralObject>> returnListOfReadEstates() {
        return this.cadaster.findInArea(this.cadaster.totoMusimZmenit().getRangeOfTree());
    }

    public ArrayList<Data<CadastralObject>> returnFindDataArea() {
        return this.cadaster.findInArea(this.view.getCoordinateOne());
    }

    public ArrayList<Data<CadastralObject>> returnFindData() {
        return this.cadaster.findAccordingCoordinates(this.view.getCoordinateOne());
    }

    public View getView() {
        return this.view;
    }



}
