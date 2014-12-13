package io.lincu.interceptors;

import io.lincu.domains.Account;
import io.lincu.domains.Settings;
import io.lincu.repositories.AccountRepository;
import io.lincu.repositories.SettingsRepository;
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
    private SettingsRepository settingsRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Settings admin = settingsRepository.findByKey("admin");
        if (admin == null) {
            response.sendRedirect("/form/accounts/owner");
            return false;
        }

        return true;
    }
}
