package edu.du.sb_thymeleaff.repository;

import edu.du.sb_thymeleaff.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByIdAndPassword(String id, String password);
}
