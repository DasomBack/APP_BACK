package com._thefull.dasom_app_demo.user.repository;


import com._thefull.dasom_app_demo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
