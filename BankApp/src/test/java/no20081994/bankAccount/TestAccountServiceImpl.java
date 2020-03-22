package no20081994.bankAccount;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import no20081994.bankAccount.Entity.Account;
import no20081994.bankAccount.Entity.Customer;
import no20081994.bankAccount.Entity.Transaction;
import no20081994.bankAccount.Enums.AccountTypes;
import no20081994.bankAccount.Repository.AccountRepository;
import no20081994.bankAccount.Repository.CustomerRepository;
import no20081994.bankAccount.Service.BankAccountServiceImpl;

@RunWith(SpringRunner.class)
public class TestAccountServiceImpl {
	
	@InjectMocks
	BankAccountServiceImpl customerServiceImpl;
	
	@Mock
	CustomerRepository customerRepository;
	
	@Mock
	AccountRepository accountRepository;
	
	List<Account> accounts = new ArrayList<Account>();
	List<Customer> customers = new ArrayList<Customer>();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		Customer customerOne = new Customer(new Integer(1), "noyal.joy@wipro.com", "Noyal", "Joy", null);
		Customer customerTwo = new Customer(new Integer(1), "e.e@wipro.com", "EE", "Emmanuel", null);

		Account accountOne = new Account(100001l, 130000.33d, AccountTypes.Current, customerOne, new ArrayList<Transaction>());
		Account accountTwo = new Account(100002l, 140000.44d, AccountTypes.Savings, customerTwo,new ArrayList<Transaction>());
		customerOne.setAccount(accountOne);
		customerTwo.setAccount(accountTwo);
		accounts.add(accountOne);
		accounts.add(accountTwo);
		customers.add(customerOne);
		customers.add(customerTwo);
	}
	
	@Test
	public void getAllAccountDetailsTest() {
		when(accountRepository.findAll()).thenReturn(accounts);
		assertEquals(accounts, accountRepository.findAll());
	}
	
	/*
	 * @Test public void getAccountDetailsByAccountNumber(){
	 * when(accountRepository.findById(new
	 * Long(1000002)).get()).thenReturn(accounts.get(2)); }
	 */
	
	@Test
	public void createAccount() {
		Customer customerTwo =  new Customer(new Integer(1), "noyal.joy@wipro.com", "Noyal", "Joy", null);
		Account accountThree = new Account(100001l, 140000.44d, AccountTypes.Savings, customerTwo,new ArrayList<Transaction>());
		customerTwo.setAccount(accountThree);
		when(accountRepository.save(accountThree)).thenReturn(accounts.get(0));
	}
	
	/*
	 * @Test public void getBalanceInfo() { when(accountRepository.findById(new
	 * Long(1000002)).get()).thenReturn(accounts.get(2)); }
	 */
	
}
