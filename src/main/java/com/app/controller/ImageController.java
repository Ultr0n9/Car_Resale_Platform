package com.app.controller;

import com.app.entity.cars.Car;
import com.app.entity.cars.CarImage;
import com.app.payload.ImageDto;
import com.app.repository.CarImageRepository;
import com.app.repository.CarRepository;
import com.app.service.BucketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private BucketService bucketService;
    private CarRepository carRepository;
    private CarImageRepository carImageRepository;

    public ImageController(BucketService bucketService, CarRepository carRepository, CarImageRepository carImageRepository) {
        this.bucketService = bucketService;
        this.carRepository = carRepository;
        this.carImageRepository = carImageRepository;
    }

    @PostMapping(path = "/upload/file/{bucketName}/car/{carId}")
    public ResponseEntity<CarImage> uploadCarPhotos
            (@RequestParam MultipartFile file,
             @PathVariable String bucketName,
             @PathVariable Long carId
             ) {


        String url = bucketService.uploadFile(file, bucketName);
            Car car = carRepository.findById(carId).get();
            CarImage image = new CarImage();
            image.setUrl(url);
            image.setCar(car);
        CarImage saveImage = carImageRepository.save(image);
        return new ResponseEntity<>(saveImage, HttpStatus.OK);
    }
}
