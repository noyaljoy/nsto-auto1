package no20081994.bankAccount.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import no20081994.bankAccount.Entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>{

	
}
