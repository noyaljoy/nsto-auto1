package no20081994.bankAccount.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no20081994.bankAccount.Entity.Customer;
import no20081994.bankAccount.Service.BankAccountService;
import no20081994.bankAccount.Util.RecordNotFoundException;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

	@Autowired
	private BankAccountService accountService;

	@GetMapping
	public ResponseEntity<List<Customer>> getAllAccounts() {
		List<Customer> list = accountService.getAllCustomers();
		return new ResponseEntity<List<Customer>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable("id") Integer id) throws RecordNotFoundException {
		Customer student = accountService.getCustomerById(id);
		return student;
	}

	@PutMapping("/{id}")
	public Customer UpdateCustomer(@RequestBody Customer employee, @PathVariable("id") Integer id)
			throws RecordNotFoundException {
		employee.setId(id);
		//employee.getAccount().setId(id);
		Customer updated = accountService.UpdateCustomer(employee);
		return updated;
	}

	@DeleteMapping("/{id}")
	public HttpStatus deleteCustomerById(@PathVariable("id") Long id) throws RecordNotFoundException {
		accountService.deleteCustomerById(id);
		return HttpStatus.OK;
	}

}