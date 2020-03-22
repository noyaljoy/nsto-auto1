package no20081994.bankAccount.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import no20081994.bankAccount.Enums.AccountTypes;

@Entity
@Table(name = "ACCOUNT", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {

	@Id
	private Long id;

	private Double balance;

	@Enumerated(EnumType.ORDINAL)
	private AccountTypes accountType;

	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<Transaction> transactions;

	public Account() {
		super();
	}

	public Account(Long id, Double balance, AccountTypes accountType, Customer customer,
			List<Transaction> transactions) {
		super();
		this.id = id;
		this.balance = balance;
		this.accountType = accountType;
		this.customer = customer;
		this.transactions = transactions;
	}

	public Double getBalance() {
		return balance;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public AccountTypes getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountTypes accountType) {
		this.accountType = accountType;
	}

}
