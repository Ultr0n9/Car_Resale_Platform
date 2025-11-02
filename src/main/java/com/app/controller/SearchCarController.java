package com.app.controller;


import com.app.entity.cars.Car;
import com.app.repository.BrandRepository;
import com.app.repository.CarRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search-car")
public class SearchCarController {

    private BrandRepository brandRepository;

    private CarRepository carRepository;

    public SearchCarController(BrandRepository brandRepository, CarRepository carRepository) {
        this.brandRepository = brandRepository;
        this.carRepository = carRepository;
    }


    @GetMapping("/cars")
    public List<Car> searchCar(
           @RequestParam String param
    ){
//      Brand carBrandName = brandRepository.findByName(brand);
      return carRepository.searchCar(param);

    };

}
