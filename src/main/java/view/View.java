package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controller.*;

public class View extends JFrame{

    private static final long serialVersionUID = 1L;
    private JPanel pane = new JPanel(new BorderLayout());
    private  JPanel titlePane = new JPanel(new FlowLayout());
    private  JLabel titleLabel = new JLabel("Polynomial calculator");

    private  JPanel calcPane = new JPanel(new GridLayout(6,2));
    private JButton button = new JButton("OK");
    private JTextField textP1 = new JTextField(20);
    private JTextField textP2 = new JTextField(20);
    private JLabel p1Label = new JLabel("First polynomial=  ");
    private JLabel p2Label = new JLabel("Second polynomial=  ");
    private JLabel resultLabel = new JLabel("Result=  ");
    private  JTextField textResult = new JTextField(20);

    private  JButton addButton = new JButton("Add");
    private  JButton subButton = new JButton("Subtract");
    private  JButton mulButton = new JButton("Multiply");
    private  JButton divButton = new JButton("Divide");
    private  JButton intgrButton = new JButton("Integrate");
    private  JButton derButton = new JButton("Derive");


    Controller controller = new Controller(this);


    public View(String name) {
        super(name);
        pane.add(titleLabel, BorderLayout.PAGE_START);
        calcPane.add(p1Label);
        calcPane.add(textP1);
        calcPane.add(p2Label);
        calcPane.add(textP2);
        calcPane.add(resultLabel);
        calcPane.add(textResult);
        calcPane.add(addButton);
        calcPane.add(subButton);
        calcPane.add(mulButton);
        calcPane.add(divButton);
        calcPane.add(intgrButton);
        calcPane.add(derButton);

        pane.add(calcPane, BorderLayout.CENTER);
        this.add(pane);

    }

    public JButton getButton(){
        return button;
    }
    public JTextField getTextResult(){return textResult;}
    public String getText1(){
        return textP1.getText();
    }
    public String getText2() { return textP2.getText(); }

    public void addAddListener(ActionListener b){
        addButton.addActionListener(b);
    }
    public void addSubListener(ActionListener b){
        subButton.addActionListener(b);
    }
    public void addMulListener(ActionListener b){
        mulButton.addActionListener(b);
    }
    public void addDivListener(ActionListener b){
        divButton.addActionListener(b);
    }
    public void addIntgrListener(ActionListener b){ intgrButton.addActionListener(b); }
    public void addDerListener(ActionListener b){
        derButton.addActionListener(b);
    }

    public static void main(String args[]) {
        JFrame frame = new View("Polynomial Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 300);
        frame.setVisible(true);
    }


}
