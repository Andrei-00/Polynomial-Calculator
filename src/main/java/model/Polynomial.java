package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Polynomial{
    private ArrayList<Monomial> polynom;

    public Polynomial() {
        polynom = new ArrayList<Monomial>();
    }

    public void addMonomial(Monomial mon) {
        polynom.add(mon);
        Collections.sort(polynom);
    }

    public Polynomial add(Polynomial polynom2){ //adaugam fiecare monom al celor doua polinoame in doua hashmap-uri
        Polynomial result = new Polynomial();
        HashMap<Integer, Double> map1 = new HashMap<>();
        HashMap<Integer, Double> map2 = new HashMap<>();
        Monomial resultedMonomial;

        for (Monomial mon : this.polynom){
            map1.put(mon.getPow(), mon.getCoeff());
        }
        for (Monomial mon : polynom2.polynom){
            map2.put(mon.getPow(), mon.getCoeff());
        }

        for(Integer pow : map1.keySet()){ //adunam monomii cu aceeiasi putere din ambele polinoame
            if(map2.containsKey(pow)){
                if(map1.get(pow) + map2.get(pow) !=0) {
                    resultedMonomial = new Monomial(map1.get(pow) + map2.get(pow), pow);
                    map2.remove(pow);
                    result.addMonomial(resultedMonomial);
                } else{
                        map2.remove(pow);
                    }
            }else{
                resultedMonomial = new Monomial(map1.get(pow), pow);
                result.addMonomial(resultedMonomial);
            }
        }

        for(Integer pow : map2.keySet()){ //adaugam restul monoamelor
            resultedMonomial = new Monomial(map2.get(pow), pow);
            result.addMonomial(resultedMonomial);
        }
        return result;
    }

    public Polynomial sub(Polynomial polynom2){ //adaugam fiecare monom al celor doua polinoame in doua hashmap-uri
        Polynomial result = new Polynomial();
        HashMap<Integer, Double> map1 = new HashMap<>();
        HashMap<Integer, Double> map2 = new HashMap<>();
        Monomial resultedMonomial;

        for (Monomial mon : this.polynom){
            map1.put(mon.getPow(), mon.getCoeff());
        }
        for (Monomial mon : polynom2.polynom){
            map2.put(mon.getPow(), mon.getCoeff());
        }

        for(Integer pow : map1.keySet()){ //scadem monomii cu aceeiasi putere din ambele polinoame
            if(map2.containsKey(pow)){
                if(map1.get(pow) - map2.get(pow) !=0) {
                    resultedMonomial = new Monomial(map1.get(pow) - map2.get(pow), pow);
                    map2.remove(pow);
                    result.addMonomial(resultedMonomial);
                }
                else{
                    map2.remove(pow);
                }
            }else{
                resultedMonomial = new Monomial(map1.get(pow), pow);
                result.addMonomial(resultedMonomial);
            }
        }

        for(Integer pow : map2.keySet()){ //adaugam restul monoamelor
            resultedMonomial = new Monomial((-1)*map2.get(pow), pow);
            result.addMonomial(resultedMonomial);
        }
        return result;
    }

    public Polynomial mul(Polynomial polynom2){
        Polynomial result = new Polynomial();
        Monomial resultedMonomial;
        for (Monomial mon1 : this.polynom) {
            for (Monomial mon2 : polynom2.polynom) {
                resultedMonomial = new Monomial(mon1.getCoeff() * mon2.getCoeff(), mon1.getPow() + mon2.getPow());
                result.addMonomial(resultedMonomial);
            }
        }

        result.simplify();
        return result;
    }

    public Polynomial der(){
        Polynomial result = new Polynomial();

        for(Monomial mon : this.polynom){ //pentru orice monom cu gradul mai mare de 0 vom aplica formula derivatei
            int pow = mon.getPow();
            if (pow != 0) {
                result.addMonomial(new Monomial(mon.getCoeff()*pow,  pow-1));
            }
        }
        return result;
    }

    public String intgr() {
        String result = "";
        for (Monomial mon : this.polynom) {
            int pow = mon.getPow();
            String coeff = convCoeff(mon.getCoeff());
            double coeffD = mon.getCoeff();
            int res = pow + 1;

            if (res > 1) {
                if (coeffD < 0) {
                    result += coeff + "(x^" + res + "/" + res + ")";
                } else {
                    result += "+" + coeff + "(x^" + res + "/" + res + ")";
                }
            } else if (res < 1){
                if(coeffD < 0) {
                    result += coeff;
                } else{
                        result += "+" + coeff;
                    }
            }else if(res == 1){
                if(coeffD < 0) {
                    result += coeff;
                } else{
                    result += "+" + coeff;
                }
                result += "x";
            }
        }
        return result;
    }

    public ArrayList<Polynomial> div(Polynomial polynom2){
        ArrayList<Polynomial> result;
        Polynomial polyTemp, remainder;
        Polynomial quotient = new Polynomial();
        Polynomial s = new Polynomial();
        Polynomial p, q;
        Monomial mon = new Monomial();
        int powQ , powP, powTemp;
        double coeffQ , coeffP, coeffTemp;
        result = this.compareDegrees(polynom2);
        powP = result.get(0).polynom.get(0).getPow(); coeffP = result.get(0).polynom.get(0).getCoeff();
        powQ = result.get(1).polynom.get(0).getPow(); coeffQ = result.get(1).polynom.get(0).getCoeff();
        p = result.get(0); q = result.get(1); result.clear();
        polyTemp = p;
        do{
            powTemp = powP-powQ;
            coeffTemp = coeffP/coeffQ;
            mon.setCoeff(coeffTemp);
            mon.setPwr(powTemp);
            quotient.addMonomial(new Monomial(coeffTemp, powTemp));
            s.addMonomial(mon);
            s = s.mul(q);
            remainder = polyTemp.sub(s);
            if(remainder.toString() == ""){break;}
            polyTemp = remainder;
            powP = polyTemp.polynom.get(0).getPow();
            coeffP = polyTemp.polynom.get(0).getCoeff();
            s.clearPolynom();
       } while (polyTemp.polynom.get(0).getPow() >= powQ);
        result.add(quotient);
        result.add(remainder);
        return result;
    }

    public ArrayList<Polynomial> div2(Polynomial polynom2){
        ArrayList<Polynomial> result = new ArrayList<>();
        Polynomial quoAndRem = new Polynomial(); //quotient si remainder ce vor fi adaugate in rezultat
        double coeffP, coeffQ, coeff, modulo;
        coeffP = this.polynom.get(0).getCoeff(); coeffQ = polynom2.polynom.get(0).getCoeff();
        if(coeffP >= coeffQ){
            coeff = coeffP/coeffQ; modulo = coeffP%coeffQ;
        }else{
            coeff = coeffQ/coeffP; modulo = coeffP%coeffQ;
        }
        quoAndRem.addMonomial(new Monomial(coeff, 0));
        result.add(quoAndRem);
        quoAndRem.clearPolynom();
        quoAndRem.addMonomial(new Monomial(modulo, 0));
        result.add(quoAndRem);
        return result;
    }

    public void simplify(){
        ArrayList<Monomial> toRemove = new ArrayList<>(); // adaugam monoamele celor doua polinoame in doua hash-seturi
        ArrayList<Monomial> toAdd = new ArrayList<>(); // cele care vor fi eliminate si cele simplificate care se adauga anterior
        boolean isSimplified = false;

        while(!isSimplified) {
            isSimplified = true;
            toRemove.clear();
            toAdd.clear();
            for (Monomial mon1 : this.polynom) { // cautam daca in interiorul polinomului avem doi termeni cu acelasi grad
                for (Monomial mon2 : this.polynom) { // daca da, simplificam pana obtinem un singur termen de un anumit grad
                    if (mon1.equals(mon2) == false && toRemove.contains(mon1) == false && toRemove.contains(mon2) == false && mon1.getPow() == mon2.getPow()) {
                        isSimplified = false;
                        toAdd.add(new Monomial(mon1.getCoeff()+mon2.getCoeff(), mon1.getPow()));
                        toRemove.add(mon1);
                        toRemove.add(mon2);
                    }
                }
            }
            this.polynom.removeAll(toRemove);
            this.polynom.addAll(toAdd);
        }
        Collections.sort(this.polynom);
    }

    ArrayList<Polynomial> compareDegrees (Polynomial p2){//returneaza monomul mai mare pe prima pozitie si monomul mai mic pe a doua pozitie
        int powQ = this.polynom.get(0).getPow();
        int powP = p2.polynom.get(0).getPow();
        double coeffQ = this.polynom.get(0).getCoeff();
        double coeffP = p2.polynom.get(0).getCoeff();
        ArrayList<Polynomial> result = new ArrayList<Polynomial>();

        if(powP > powQ){
            result.add(p2);
            result.add(this);
        }
        else if (powP == powQ){
            if(coeffP > coeffQ) {
                result.add(p2);
                result.add(this);
            }
            else{
                result.add(this);
                result.add(p2);
            }
        }else{
            result.add(this);
            result.add(p2);
        }
        return result;
    }

    public ArrayList<Monomial> getPolynoms (){return this.polynom;}

    @Override
    public String toString() {
        String retString = "";
        for (Monomial mon : polynom) { //parcurgem polinomul
            String retCoeff="";
            retCoeff = convCoeff(mon.getCoeff());

            if (mon.getCoeff() > 0 && mon.getPow() != 0) {  //cazurile in care avem un polinom cu puterea > 0
                if(retString == ""){ //adugarea primului termen al polinomului
                    retString += retCoeff + "x" + "^" + mon.getPow();
                }else {
                    retString += "+" + retCoeff + "x" + "^" + mon.getPow(); //adaugarea celorlalti termenti
                }
            }
            if (mon.getCoeff() < 0 && mon.getPow() != 0) { //adaugarea unui termen cu coeficient negativ
                retString += retCoeff + "x" + "^" + mon.getPow();
            }
            if (mon.getCoeff() > 0 && mon.getPow() == 0){ //adaugarea unui termen cu puterea egala cu 0
                if(retString == ""){
                    retString += retCoeff;
                }else {
                    retString += "+" + retCoeff;
                }
            }
            if (mon.getCoeff() < 0 && mon.getPow() == 0){
                retString += retCoeff;
            }
        }
        return retString;
    }

    public void clearPolynom(){
        this.polynom.clear();
    }

    public String convCoeff(double coeff){ //Elimina partea fractionara daca ea este egala cu 0
        String retCoeff;
        if (coeff % 1.0 != 0)
            retCoeff = String.format("%s", coeff);
        else
            retCoeff = String.format("%.0f", coeff);
        return retCoeff;
    }

    @Override
    public boolean equals(Object p2){
        for(Monomial mon1 : this.polynom){
            for(Monomial mon2: ((Polynomial) p2).polynom){
                if(mon1.getCoeff() != mon2.getCoeff() || mon1.getPow() != mon2.getPow()){
                    return false;
                }
                continue;
            }
        }
        return true;
    }
}