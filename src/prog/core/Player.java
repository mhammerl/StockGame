package prog.core;
public class Player {

	private final String NAME;
	private final CashAccount ACCOUNT;
	private final ShareDepositAccount SHARE_DEPOSIT_ACCOUNT;
	
	public Player(String name){
		NAME = name;
		ACCOUNT = new CashAccount(name);
		SHARE_DEPOSIT_ACCOUNT = new ShareDepositAccount(name);
	}
	
	public String getName(){
		return NAME;
	}
	
	public CashAccount getCashAccount(){
		return ACCOUNT;
	}
	
	public ShareDepositAccount getShareDepositAccount(){
		return SHARE_DEPOSIT_ACCOUNT;
	}
	
	public long value(){
		long o = ACCOUNT.value() + SHARE_DEPOSIT_ACCOUNT.value();
		return o;
	}
	
	public String toString(){
		String out = "[" + NAME + "]: \nCash Account: " + ACCOUNT.toString() + "\n Share Deposit: " + SHARE_DEPOSIT_ACCOUNT.toString() + "\n Player value:" + value();
		return out;
	}
	
//	public String toString(){
//		String out = NAME + ": " + "\n" + ACCOUNT.toString() + "\n" + SHARE_DEPOSIT_ACCOUNT.toString();
//		return out;
//	}
	
//	private String name;
//	private CashAccount account;
//	private ShareDepositAccount shareDepositAccount;
//	
//	public Player(String name){
//		this.name = name;
//		account = new CashAccount(name);
//		shareDepositAccount = new ShareDepositAccount(name);
//	}
//	
//	public String getName(){
//		return name;
//	}
//	
//	public CashAccount getCashAccount(){
//		return account;
//	}
//	
//	public ShareDepositAccount getShareDepositAccount(){
//		return shareDepositAccount;
//	}
}
