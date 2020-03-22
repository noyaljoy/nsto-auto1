package no20081994.bankAccount.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import no20081994.bankAccount.Entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{
	Customer getCustomerByFirstName(String firstName);
}
