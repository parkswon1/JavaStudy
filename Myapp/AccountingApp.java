
public class AccountingApp {
	public static void main(String[] args) {
		double supplyCost = Double.parseDouble(args[0]);
		double VAT = supplyCost/10;
		double Total = supplyCost + VAT;
		double Expense = supplyCost*0.3;
		double Income = supplyCost - Expense;
		double Dividend1 = Income/2;
		double Dividend2 = Income*0.3;
		double Dividend3 = Income*0.2;
		System.out.println("Value of supply :" + supplyCost);
		System.out.println("VAT :" + VAT);
		System.out.println("Total :" + Total);
		System.out.println("Expense :" + Expense);
		System.out.println("Income :" + Income);
		System.out.println("Dividend :" + Dividend1);
		System.out.println("Dividend :" + Dividend2);
		System.out.println("Dividend :" + Dividend3);
	}
}
