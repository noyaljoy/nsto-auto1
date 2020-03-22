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
public class TestCustomerServiceImpl {
	
	@InjectMocks
	BankAccountServiceImpl customerServiceImpl;
	
	@Mock
	CustomerRepository customerRepository;
	
	@Mock
	AccountRepository accountRepository;
	
	List<Customer> customerList=new ArrayList<Customer>();
	List<Account> accountList = new ArrayList<Account>();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		Customer customerOne = new Customer(new Integer(1), "noyal.joy@wipro.com", "Noyal", "Joy", null);
		Customer customerTwo = new Customer(new Integer(1), "e.e@wipro.com", "EE", "Emmanuel", null);
		Account accountOne = new Account(100001l, 130000.33d, AccountTypes.Current, customerOne, new ArrayList<Transaction>());
		Account accountTwo = new Account(100002l, 140000.44d, AccountTypes.Savings, customerTwo,new ArrayList<Transaction>());
		customerOne.setAccount(accountOne);
		customerTwo.setAccount(accountTwo);
		accountList.add(accountOne);
		accountList.add(accountTwo);
		customerList.add(customerOne);
		customerList.add(customerTwo);
	}
	
	@Test
	public void getAllCustomers() {
		when(customerRepository.findAll()).thenReturn(customerList);
		assertEquals(customerList, customerRepository.findAll());
	}

	@Test
	public void getCustomerByFirstName() {
		when(customerServiceImpl.getCustomerByFirstName(customerList.get(0).getFirstName())).thenReturn(customerList.get(0));
	}

	@Test
	public void addCustomerDetails() {
		Customer customerThree =  new Customer(new Integer(1), "e.e@wipro.com", "EE", "Emmanuel", null);
		when(customerRepository.save(customerThree)).thenReturn(customerList.get(0));
	}

}
