
package com._thefull.dasom_app_demo.domain.robot.repository;

import com._thefull.dasom_app_demo.domain.robot.domain.Robot;
import com._thefull.dasom_app_demo.domain.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RobotRepository extends JpaRepository<Robot, Long> {
    Optional<Robot> findByStore(Store store);

}

