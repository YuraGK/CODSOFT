package ATM_INTERFACE;



public class ATMimpl {

	private Account acc;
	
	public ATMimpl(String name, String password, double balance) {
		acc = new Account(name, password);
		acc.setBalance(balance);
		
	}
	
	public boolean withdraw(double amount) {
		if(acc.getBalance()<amount) {
			return false;
		}else {
			acc.setBalance(acc.getBalance()-amount);
			return true;
		}
		
	}
	public void deposit(double amount) {
		acc.setBalance(acc.getBalance()+amount);
	}
	public double checkBalance() {
		return acc.getBalance();
	}
}
