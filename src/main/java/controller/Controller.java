package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import  model.*;
import view.*;

import javax.swing.*;

public class Controller implements ActionListener{

    private View view;

    private Polynomial logic;
    private Polynomial polynomial1;
    private Polynomial polynomial2;
    private Polynomial resultedPolynomial;
    private ArrayList<Polynomial> resultedPolynomials;
    private  Parser parser;

    public Controller(View v){
        v.addAddListener(this);
        v.addSubListener(this);
        v.addMulListener(this);
        v.addDivListener(this);
        v.addIntgrListener(this);
        v.addDerListener(this);
        this.view = v;
        parser = new Parser();
        polynomial1 = new Polynomial();
        polynomial2 = new Polynomial();
        resultedPolynomial = new Polynomial();
    }


    public void actionPerformed(ActionEvent e) {
        String p1 = view.getText1();
        String p2 = view.getText2();
        parser.setStr(p1);
        polynomial1 = parser.parse(); polynomial1.simplify();
        parser.setStr(p2);
        polynomial2 = parser.parse(); polynomial2.simplify();
        boolean intgr = false;
        JButton button = (JButton)e.getSource();

            switch (button.getText()){
                case "Add":
                    resultedPolynomial = polynomial1.add(polynomial2);
                    view.getTextResult().setText(resultedPolynomial.toString());
                    break;
                case "Subtract":
                    resultedPolynomial = polynomial1.sub(polynomial2);
                    view.getTextResult().setText(resultedPolynomial.toString());
                    break;
                case "Multiply":
                    resultedPolynomial = polynomial1.mul(polynomial2);
                    view.getTextResult().setText(resultedPolynomial.toString());
                    break;
                case "Divide":
                    if(polynomial1.getPolynoms().get(0).getPow() > 0 && polynomial2.getPolynoms().get(0).getPow() > 0) {
                        resultedPolynomials = polynomial1.div(polynomial2);
                        //System.out.println(resultedPolynomials.get(0));
                        view.getTextResult().setText("Quotient: " + resultedPolynomials.get(0).toString() + "  " +
                                "Remainder: " + resultedPolynomials.get(1).toString());
                    }
                    else{
                        resultedPolynomials = polynomial1.div2(polynomial2);
                        System.out.println(resultedPolynomials.get(0));
                        view.getTextResult().setText("Quotient: " + resultedPolynomials.get(0).toString() + "  " +
                                "Remainder: " + resultedPolynomials.get(1).toString());
                    }
                    break;
                case "Integrate":
                    view.getTextResult().setText(polynomial1.intgr().toString()); intgr = true;
                    break;
                case  "Derive":
                    resultedPolynomial = polynomial1.der();
                    view.getTextResult().setText(resultedPolynomial.toString());
                    break;
            }
            if(resultedPolynomial.toString() == "" && intgr == false){
                view.getTextResult().setText("0");
            }
           System.out.println(resultedPolynomial);
    }


}
