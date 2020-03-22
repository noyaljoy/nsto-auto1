package no20081994.bankAccount.Service;

import java.util.List;
import java.util.Map;

import no20081994.bankAccount.Entity.Account;
import no20081994.bankAccount.Entity.Customer;
import no20081994.bankAccount.Model.Accounts;
import no20081994.bankAccount.Model.FundTransfer;
import no20081994.bankAccount.Model.Transactions;
import no20081994.bankAccount.Util.BankTransactionException;
import no20081994.bankAccount.Util.RecordNotFoundException;

public interface BankAccountService {

	public List<Account> getAllAccounts();

	public List<Customer> getAllCustomers();

	public Customer getCustomerById(Integer id) throws RecordNotFoundException;

	public Account getAccountById(Long id) throws RecordNotFoundException;

	public Account addAccount(Account model) throws RecordNotFoundException;

	public Customer UpdateCustomer(Customer model) throws RecordNotFoundException;

	public void deleteCustomerById(Long id) throws RecordNotFoundException;

	public String transferFunds(FundTransfer fundTransfer) throws BankTransactionException;

	public Account getBalanceOf(Long accountNumber) throws RecordNotFoundException;

	public List<Accounts> getAccountList();

	public List<Transactions> getAllTransactions(Long accountNumber);

	public Map<Long, String> getAccountDropDownList();

}
