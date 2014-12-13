package io.lincu.repositories;

import io.lincu.domains.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Keeun Baik
 */
public interface SettingsRepository extends JpaRepository<Settings, Long> {
    Settings findByKey(String admin);
}
