package com.app.repository;

import com.app.entity.cars.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(
            "SELECT c FROM Car c " +
                    "JOIN c.brand b " +
                    "JOIN c.transmission t " +
                    "JOIN c.model m "+
                    "JOIN c.year y " +
                    "JOIN c.fuelType f "+
                    "WHERE b.name = :details or t.type = :details or m.name = :details or y.year = :details or f.fuelType = :details "
    )
    List<Car> searchCar(@Param("details") String details);

}
