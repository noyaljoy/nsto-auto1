package no20081994.bankAccount.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import no20081994.bankAccount.Entity.Account;
import no20081994.bankAccount.Entity.Customer;
import no20081994.bankAccount.Entity.Transaction;
import no20081994.bankAccount.Enums.CurrencyTypes;
import no20081994.bankAccount.Enums.TransactionTypes;
import no20081994.bankAccount.Model.Accounts;
import no20081994.bankAccount.Model.FundTransfer;
import no20081994.bankAccount.Model.Transactions;
import no20081994.bankAccount.Repository.AccountRepository;
import no20081994.bankAccount.Repository.CustomerRepository;
import no20081994.bankAccount.Repository.TransactionRepository;
import no20081994.bankAccount.Util.BankTransactionException;
import no20081994.bankAccount.Util.Constants;
import no20081994.bankAccount.Util.RecordNotFoundException;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	AccountRepository repository;

	@Autowired
	CustomerRepository customerRep;

	@Autowired
	TransactionRepository transactionRepository;

	public List<Account> getAllAccounts() {
		List<Account> accounts = (List<Account>) repository.findAll();
		if (accounts.size() <= 0) {
			new ArrayList<Account>();
		}
		return accounts;
	}

	public List<Customer> getAllCustomers() {
		List<Customer> customers = (List<Customer>) customerRep.findAll();
		if (customers.size() <= 0) {
			new ArrayList<Account>();
		}
		return customers;
	}

	public Customer getCustomerById(Integer id) throws RecordNotFoundException {

		Customer student = customerRep.findById(id).get();
		return student;

	}

	public Account getAccountById(Long id) throws RecordNotFoundException {
		Optional<Account> employee = repository.findById(id);
		if (employee.isPresent()) {
			Account account = employee.get();
			return account;
		} else {
			throw new RecordNotFoundException("No account record exist for given id");
		}
	}

	public Account addAccount(Account model) throws RecordNotFoundException {
		model.setId(repository.getMaxId() + 1);
		model = repository.save(model);

		return model;
	}

	public Customer UpdateCustomer(Customer model) throws RecordNotFoundException {
		Optional<Customer> customer = customerRep.findById(model.getId());
		Customer customerEntity = null;
		if (customer.isPresent()) {
			customerEntity = customer.get();
			customerEntity.setFirstName(model.getFirstName());
			customerEntity.setLastName(model.getLastName());
			customerEntity.setEmail(model.getEmail());
			customerEntity.setPhone(model.getPhone());
			Account newAccount = customerEntity.getAccount();
			newAccount.setAccountType(model.getAccount().getAccountType());
			customerEntity.setAccount(newAccount);
			customerEntity = customerRep.save(customerEntity);

		}
		return customerEntity;
	}

	public void deleteCustomerById(Long id) throws RecordNotFoundException {
		Optional<Account> employee = repository.findById(id);

		if (employee.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankTransactionException.class)
	public String transferFunds(FundTransfer fundTransfer) throws BankTransactionException {
		try {
			Optional<Account> fromAccountObj = repository.findById(fundTransfer.getFromAccountId());
			Optional<Account> toAccountObj = repository.findById(fundTransfer.getToAccountId());
			if (!fromAccountObj.isPresent() || !toAccountObj.isPresent() || fundTransfer.getFromAccountId() == fundTransfer.getToAccountId()) {
				throw new BankTransactionException("Please select valid debit & credit account");
				//return Constants.TRANSFER_MESSAGE_ID_MISATCH;
			}
			if (fromAccountObj.isPresent() && toAccountObj.isPresent()) {
				if (fundTransfer.getAmount() <= 0) {
					throw new BankTransactionException("Amount should be more than zero");
				}
				Account fromAccount = fromAccountObj.get();
				Double newFromBalance = fromAccount.getBalance() - fundTransfer.getAmount();
				if (fromAccount.getBalance() - fundTransfer.getAmount() < 0) {
					throw new BankTransactionException("INSUFFICIENT FUNDS");
					//return Constants.TRANSFER_MESSAGE_INSUFFICIENT_FUNDS;
				}

				Account toAccount = toAccountObj.get();
				Double newToBalance = toAccount.getBalance() + fundTransfer.getAmount();

				fromAccount.setBalance(newFromBalance);
				toAccount.setBalance(newToBalance);

				List<Transaction> transList = new ArrayList<Transaction>();
				transList.add(new Transaction(fundTransfer.getAmount(), fundTransfer.getDescription(), TransactionTypes.Debit, fromAccount));
				transList.add(new Transaction(fundTransfer.getAmount(), fundTransfer.getDescription(), TransactionTypes.Credit, toAccount));

				transactionRepository.saveAll(transList);

				List<Account> accountList = new ArrayList<Account>();
				accountList.add(fromAccount);
				accountList.add(toAccount);
				repository.saveAll(accountList);
			}
		} catch (Exception e) {
			throw new BankTransactionException(e.getMessage());
		}
		return Constants.TRANSFER_MESSAGE_SUCCESS;
	}

	public Account getBalanceOf(Long accountNumber) throws RecordNotFoundException {
		Optional<Account> employee = repository.findById(accountNumber);
		if (null != employee.get()) {
			return employee.get();
		} else {
			throw new RecordNotFoundException("No customer record exist for given account number");
		}
	}

	public Customer getCustomerByFirstName(String firstName) {
		return customerRep.getCustomerByFirstName(firstName);
	}

	@Override
	public List<Accounts> getAccountList() {
		List<Account> accountList = (List<Account>) repository.findAll();
		List<Accounts> accounts = null;
		if (!accountList.isEmpty()) {
			accounts = new ArrayList<Accounts>();
			for (Account account : accountList) {
				accounts.add(new Accounts(account.getId(),
						account.getCustomer().getFirstName() + " " + account.getCustomer().getLastName(),
						account.getBalance(), new Date(), CurrencyTypes.INR, account.getAccountType()));
			}
		}
		return accounts;
	}

	@Override
	public List<Transactions> getAllTransactions(Long accountNumber) {
		Optional<Account> account = repository.findById(accountNumber);
		List<Transactions> transactions = null;
		if (account.isPresent()) {
			List<Transaction> transactionList = account.get().getTransactions();
			if (!transactionList.isEmpty()) {
				transactions = new ArrayList<Transactions>();
				for (Transaction transaction : transactionList) {
					transactions.add(new Transactions(account.get().getId(),
							account.get().getCustomer().getFirstName() + " "
									+ account.get().getCustomer().getLastName(),
							transaction.getDate(), CurrencyTypes.INR, transaction.getTransactionType(),
							transaction.getTransactionType() == TransactionTypes.Debit ? transaction.getAmount() : 0.0,
							transaction.getTransactionType() == TransactionTypes.Credit ? transaction.getAmount() : 0.0,
							transaction.getDescription()));
				}
			}
		}
		return transactions;
	}

	@Override
	public Map<Long, String> getAccountDropDownList() {
		Map<Long, String> accountList=new HashMap<Long, String>();;
		List<Account> accounts = (List<Account>) repository.findAll();
		for(Account account:accounts) {
			accountList.put(account.getId(), account.getCustomer().getFirstName()+" "+account.getCustomer().getLastName());
		}
		return accountList;
	}

}
