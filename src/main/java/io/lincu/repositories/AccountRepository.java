package io.lincu.repositories;

import io.lincu.domains.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Keeun Baik
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByOwner(boolean owner);
}
