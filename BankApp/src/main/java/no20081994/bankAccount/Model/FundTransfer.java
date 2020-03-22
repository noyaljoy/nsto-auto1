package no20081994.bankAccount.Model;

public class FundTransfer {

	private long fromAccountId;
	private long toAccountId;
	private double amount;
	private String description;
	
	
	
	public FundTransfer(long fromAccountId, long toAccountId, double amount, String description) {
		super();
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.amount = amount;
		this.description = description;
	}
	public FundTransfer() {
		// TODO Auto-generated constructor stub
	}
	public long getFromAccountId() {
		return fromAccountId;
	}
	public void setFromAccountId(long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	public long getToAccountId() {
		return toAccountId;
	}
	public void setToAccountId(long toAccountId) {
		this.toAccountId = toAccountId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
