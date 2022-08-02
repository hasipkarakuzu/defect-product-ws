package com.mhk.repairproductws.repository;

import com.mhk.repairproductws.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmailEqualsIgnoreCase(String username);
}

