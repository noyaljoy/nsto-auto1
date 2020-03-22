package no20081994.bankAccount.Model;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonInclude;

import no20081994.bankAccount.Enums.AccountTypes;
import no20081994.bankAccount.Enums.CurrencyTypes;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Accounts
{	
	
	private Long accountNumber;
	
	private String accountName;
	  
	private Double balance;
	
	private Date balanceDate;
	
	@Enumerated(EnumType.ORDINAL)
	private CurrencyTypes currencyType;
	 
	@Enumerated(EnumType.ORDINAL)
	private AccountTypes accountType;
	
	public Accounts()
	{
		super();
	}
	
	public Accounts(Long accountNumber, String accountName, Double balance, Date balanceDate,
			CurrencyTypes currencyType, AccountTypes accountType) {
		super();
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.balance = balance;
		this.balanceDate = balanceDate;
		this.currencyType = currencyType;
		this.accountType = accountType;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Date getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}

	public CurrencyTypes getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyTypes currencyType) {
		this.currencyType = currencyType;
	}

	public AccountTypes getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountTypes accountType) {
		this.accountType = accountType;
	}
	
	
	
}	
