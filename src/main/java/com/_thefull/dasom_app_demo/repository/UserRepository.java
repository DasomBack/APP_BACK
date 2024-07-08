package com._thefull.dasom_app_demo.repository;


import com._thefull.dasom_app_demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
