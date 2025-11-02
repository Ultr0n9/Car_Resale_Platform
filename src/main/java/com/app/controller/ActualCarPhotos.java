package com.app.controller;

import com.app.entity.cars.Car;
import com.app.entity.cars.CarImage;
import com.app.service.BucketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/actual-car-photos")
public class ActualCarPhotos {

    private BucketService bucketService;


    public ActualCarPhotos(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @PostMapping(path = "/upload/file/{bucketName}/car/{carId}")
    public String uploadCarPhotos
            (@RequestParam List<MultipartFile> files,
             @PathVariable String bucketName,
             @PathVariable Long carId
            ) {

        ArrayList<String> carImages = new ArrayList<>();


        for (MultipartFile file:files){
            String url = bucketService.uploadFile(file, bucketName);
            //carImages.add(url);
        }

       return null;
    }
}
