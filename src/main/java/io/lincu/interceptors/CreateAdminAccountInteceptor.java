package io.lincu.interceptors;

import io.lincu.domains.Account;
import io.lincu.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Keeun Baik
 */
@Component
public class CreateAdminAccountInteceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<Account> accounts = accountRepository.findByOwner(true);
        if (accounts.isEmpty()) {
            response.sendRedirect("/form/accounts/owner");
            return false;
        }

        return true;
    }
}
