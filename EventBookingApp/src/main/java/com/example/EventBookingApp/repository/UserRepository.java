package com.example.EventBookingApp.repository;

import com.example.EventBookingApp.model.App;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<App, Long> {
    Optional<App> findByUsername(String username);
}
