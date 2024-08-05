
package com._thefull.dasom_app_demo.robot.repository;

import com._thefull.dasom_app_demo.robot.domain.Robot;
import com._thefull.dasom_app_demo.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RobotRepository extends JpaRepository<Robot, Long> {
    Optional<Robot> findByStore(Store store);

}

