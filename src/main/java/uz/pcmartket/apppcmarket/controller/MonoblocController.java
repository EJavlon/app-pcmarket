package uz.pcmartket.apppcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pcmartket.apppcmarket.entity.Monobloc;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.MonoblocDto;
import uz.pcmartket.apppcmarket.service.MonoblocService;

import java.util.List;

@RestController
@RequestMapping("/api/category/monobloc")
public class MonoblocController {
    @Autowired
    private MonoblocService monoblocService;


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getMonobloc(@PathVariable Integer id){
        ApiResponse apiResponse = monoblocService.getMonobloc(id);
        return ResponseEntity.status(apiResponse.getSuccess()?200:409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Monobloc>> getMonoblocs(){
        return ResponseEntity.status(monoblocService.getMonoblocs() != null ? 200 : 409).body(monoblocService.getMonoblocs());
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addMonobloc(@RequestBody MonoblocDto monoblocDto){
        ApiResponse apiResponse = monoblocService.addMonobloc(monoblocDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editMonobloc(@PathVariable Integer id, @RequestBody MonoblocDto monoblocDto){
        ApiResponse apiResponse = monoblocService.editMonobloc(id,monoblocDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMonobloc(@PathVariable Integer id){
        ApiResponse apiResponse = monoblocService.deleteMonobloc(id);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(apiResponse);
    }
}
