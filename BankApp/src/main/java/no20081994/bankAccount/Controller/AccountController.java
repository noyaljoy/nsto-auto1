package no20081994.bankAccount.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no20081994.bankAccount.Entity.Account;
import no20081994.bankAccount.Model.Accounts;
import no20081994.bankAccount.Model.FundTransfer;
import no20081994.bankAccount.Model.Transactions;
import no20081994.bankAccount.Service.BankAccountService;
import no20081994.bankAccount.Util.BankTransactionException;
import no20081994.bankAccount.Util.RecordNotFoundException;

@RestController
@RequestMapping(path = "/account")
public class AccountController {

	@Autowired
	private BankAccountService accountService;

	@GetMapping(path = "/list", produces = "application/json")
	public ResponseEntity<List<Accounts>> getAccountList() {
		List<Accounts> list = accountService.getAccountList();
		return new ResponseEntity<List<Accounts>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/transactions/{accountNumber}", produces = "application/json")
	public ResponseEntity<List<Transactions>> getAllTransactions(@PathVariable("accountNumber") Long accountNumber) throws RecordNotFoundException {
		List<Transactions> list = accountService.getAllTransactions(accountNumber);
		return new ResponseEntity<List<Transactions>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping(path = "/transfer", produces = "application/json")
	public String transferFunds(@RequestBody FundTransfer fundTransfer) throws RecordNotFoundException, BankTransactionException {
		String message = accountService.transferFunds(fundTransfer);
		return message;
		// return new ResponseEntity<Customer>(entity, new HttpHeaders(),
		// HttpStatus.OK);
		//return ResponseEntity.ok().body(message);
	}
	
	@GetMapping
	public ResponseEntity<List<Account>> getAllAccounts() {
		List<Account> list = accountService.getAllAccounts();
		return new ResponseEntity<List<Account>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<Account> getAccountById(@PathVariable("id") Long id) throws RecordNotFoundException {
		Account entity = accountService.getAccountById(id);
		//return entity;
		return new ResponseEntity<Account>(entity, new HttpHeaders(),HttpStatus.OK);
		//return ResponseEntity.ok().body(entity);
	}
	
	@PostMapping
	public ResponseEntity<Account> addBankAccount(@RequestBody Account employee) throws RecordNotFoundException {
		employee.getCustomer().setAccount(employee);
		employee.setCustomer(employee.getCustomer());
		Account created = accountService.addAccount(employee);

		return new ResponseEntity<Account>(created, new HttpHeaders(), HttpStatus.CREATED);
	}

	@GetMapping(path = "/balance/{balance}", produces = "application/json")
	public ResponseEntity<Account> getBalanceOf(@PathVariable("balance") Long id) throws RecordNotFoundException {
		Account balance = accountService.getBalanceOf(id);
		balance.setCustomer(null);
		// return new ResponseEntity<Customer>(entity, new HttpHeaders(),
		// HttpStatus.OK);
		return ResponseEntity.ok().body(balance);
	}
	
	
	

}