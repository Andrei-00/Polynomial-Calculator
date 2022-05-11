package model;

public class Monomial implements Comparable<Monomial>{

    private double coeff;
    private int pwr;

    Monomial(){
        this.coeff = 0;
        this.pwr = 0;
    }

    public Monomial(double coeff, int pwr){
        this.coeff = coeff;
        this.pwr = pwr;
    }

    public double getCoeff() {
        return coeff;
    }

    public void setCoeff(double coeff) {
        this.coeff = coeff;
    }

    public int getPow() { return pwr; }

    public void setPwr(int pwr) {
        this.pwr = pwr;
    }

    @Override
    public int compareTo(Monomial o) {
        return o.getPow() - this.pwr;
    }
}
