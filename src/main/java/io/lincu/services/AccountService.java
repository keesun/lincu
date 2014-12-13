package io.lincu.services;

import io.lincu.domains.Account;
import io.lincu.domains.Settings;
import io.lincu.repositories.AccountRepository;
import io.lincu.repositories.SettingsRepository;
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
    private AccountRepository repository;

    @Autowired
    private SettingsRepository settingsRepository;

    public Account createOwner(Account account) {
        Date now = new Date();
        account.setCreated(now);
        account.setUpdated(now);
        account.setUsername("admin");
        account.setSlug("admin-user");
        account.setOwner(true);
        Account newOwner = repository.save(account);

        Settings admin = new Settings();
        admin.setKey("admin");
        admin.setValue(newOwner.getId().toString());
        settingsRepository.save(admin);

        return newOwner;
    }
}
