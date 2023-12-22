
public class AccountingApp {
	public static void main(String[] args) {
		double supplyCost = Double.parseDouble(args[0]);
		double VAT = getVAT(supplyCost);
		double Total = getTotal(supplyCost, VAT);
		double Expense = supplyCost*0.3;
		double Income = supplyCost - Expense;
		double[] Dividend= new double[3];
		if(Income > 10000) {
			Dividend[0] = Income/2;
			Dividend[1] = Income*0.3;
			Dividend[2] = Income*0.2;
		}else {
			Dividend[0] = Income;
			Dividend[1] = Income*0;
			Dividend[2] = Income*0;
		}
		
		System.out.println("Value of supply :" + supplyCost);
		System.out.println("VAT :" + VAT);
		System.out.println("Total :" + Total);
		System.out.println("Expense :" + Expense);
		System.out.println("Income :" + Income);
		for (int i = 0; i < 3; i++) {
			System.out.println("Dividend["+i+"] :" + Dividend[i]);
		}
	}

	private static double getTotal(double supplyCost, double VAT) {
		return supplyCost + VAT;
	}

	private static double getVAT(double supplyCost) {
		return supplyCost/10;
	}
}
