package no20081994.bankAccount.Model;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonInclude;

import no20081994.bankAccount.Enums.CurrencyTypes;
import no20081994.bankAccount.Enums.TransactionTypes;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transactions
{	
	
	private Long accountNumber;
	
	private String accountName;
	  
	private Date transactionDate;
	
	@Enumerated(EnumType.ORDINAL)
	private CurrencyTypes currencyType;
	 
	@Enumerated(EnumType.ORDINAL)
	private TransactionTypes transactionType;
	
	private Double debitAmount;
	
	private Double creditAmount;
	
	private String description;
	
	public Transactions()
	{
		super();
	}
	
	public Transactions(Long accountNumber, String accountName, Date transactionDate, CurrencyTypes currencyType,
			TransactionTypes transactionType, Double debitAmount, Double creditAmount, String description) {
		super();
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.transactionDate = transactionDate;
		this.currencyType = currencyType;
		this.transactionType = transactionType;
		this.debitAmount = debitAmount;
		this.creditAmount = creditAmount;
		this.description = description;
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

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public CurrencyTypes getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyTypes currencyType) {
		this.currencyType = currencyType;
	}

	public TransactionTypes getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionTypes transactionType) {
		this.transactionType = transactionType;
	}

	public Double getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(Double debitAmount) {
		this.debitAmount = debitAmount;
	}

	public Double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(Double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}	
