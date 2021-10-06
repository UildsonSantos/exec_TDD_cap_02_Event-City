package com.devsuperior.exec.tdd.cap02.eventcity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.exec.tdd.cap02.eventcity.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{

}
