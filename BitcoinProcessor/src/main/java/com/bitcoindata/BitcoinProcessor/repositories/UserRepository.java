package com.bitcoindata.BitcoinProcessor.repositories;

import com.bitcoindata.BitcoinProcessor.models.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

//    Optional<Object> findByUser_id(Long id);
}
