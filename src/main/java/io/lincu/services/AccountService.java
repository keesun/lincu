package io.lincu.services;

import io.lincu.domains.Account;
import io.lincu.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * @author Keeun Baik
 */
@Service
@Transactional
public class AccountService {

    @Autowired
    AccountRepository repository;

    public Account createOwner(Account account) {
        Date now = new Date();
        account.setCreated(now);
        account.setUpdated(now);
        account.setUsername("admin");
        account.setSlug("admin-user");
        account.setOwner(true);
        return repository.save(account);
    }
}
