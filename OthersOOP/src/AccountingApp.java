package OthersOOP.src;
class Accounting{
    public double valueOfSupply;
    public double vatRate;
    public Accounting(double valueOfSupply,double vatRate){
        this.valueOfSupply = valueOfSupply;
        this.vatRate = vatRate;
    }

    public double getVAT() {
        return this.valueOfSupply * this.vatRate;
    }
    public double getTotal() {
        return this.valueOfSupply + this.getVAT();
    }
}
public class AccountingApp {
    public static void main(String[] args) {
        Accounting a1 = new Accounting(10000,0.1);
        System.out.println("Value of supply : " + a1.valueOfSupply);
        System.out.println("vatRate" + a1.vatRate);
        System.out.println("VAT : " + a1.getVAT());
        System.out.println("Total : " + a1.getTotal());
    }
}