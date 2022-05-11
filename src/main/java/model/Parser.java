package model;

import java.util.regex.*;

public class Parser {


    private String str;
    private Monomial newMon;
    private Polynomial newPoly;

    public  Parser() {
        this.str = "";
    }

    public Parser(String str) {
        this.str = str;
    }

    //ne folosim de regex pentru a parsa si delimita string-ul primit
    public Polynomial parse() {
        newPoly = new Polynomial();
        Pattern p = Pattern.compile("([-+]?)(\\d*\\.?\\d*)?([xX](\\^-?\\d*\\.?\\d*)?)?"); //[+-]?[^-+]+)
        Matcher m = p.matcher(str);
        newPoly.clearPolynom();
        while (m.find()) {
            String s1 = m.group(1); String s2 = m.group(2); String s3 = m.group(3);
            System.out.println("G1: " + s1+" G2: "+s2+" G3: "+s3);
            int semn = 1; //1 => semnul + si -1 => semnul -1
            double coeff = 1f;
            int pow = 0;

            if(s1.equals("-")){
                semn = -1;
            }
            if (!s2.equals("")){
                coeff = Double.parseDouble(s2) * semn;
                if(coeff == 0){
                    continue;
                }
            }else{
                coeff = 0;
            }
            if(s3 != null){
                if(s3.length() > 2) {
                    pow = Integer.parseInt(s3.substring(2));
                }
                else{
                    pow = 1;
                }
                if(s2.equals("")){
                    coeff = semn;
                }
            }
            if(coeff != 0) {
                newMon = new Monomial(coeff, pow);
                newPoly.addMonomial(newMon);
            }
        }
        System.out.println(newPoly);
        return newPoly;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
