package io.application.cars.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.application.cars.entity.Car;
import io.application.cars.service.CarService;
import lombok.RequiredArgsConstructor;


@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequiredArgsConstructor
public class CarController {
    
    private final CarService carService;

    @PostMapping("/car")
    public Car addCar(@RequestBody Car car){
        return carService.addCar(car);
    }

    @GetMapping("/cars")
    public List<Car> getCars(){
        return carService.getCars();
    }

    @GetMapping("/car/{id}")
    public Car getCar(Long id){
        return carService.getCar(id);
    }

    @DeleteMapping("/cars")
    public void deleteCars(){
        carService.deleteCars();
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id) {
        boolean isDeleted = carService.deleteCar(id);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/car/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car carDetails) {
        Car updatedCar = carService.updateCar(id, carDetails);
        if(updatedCar == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedCar);
    }
}
