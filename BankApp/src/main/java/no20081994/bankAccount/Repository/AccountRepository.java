package no20081994.bankAccount.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import no20081994.bankAccount.Entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>{

	@Query("SELECT coalesce(max(a.id), 0) FROM Account a")
	Long getMaxId();
	
	
}
