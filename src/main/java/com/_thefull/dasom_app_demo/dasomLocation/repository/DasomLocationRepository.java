package com._thefull.dasom_app_demo.dasomLocation.repository;

import com._thefull.dasom_app_demo.dasomLocation.domain.DasomLocation;
import com._thefull.dasom_app_demo.robot.domain.Robot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DasomLocationRepository extends JpaRepository<DasomLocation, Long> {

    List<DasomLocation> findAllByRobot(Robot robot);
}
