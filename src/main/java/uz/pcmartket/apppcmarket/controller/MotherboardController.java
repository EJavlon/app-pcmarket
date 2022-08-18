package uz.pcmartket.apppcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pcmartket.apppcmarket.entity.Motherboard;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.MotherboardDto;
import uz.pcmartket.apppcmarket.service.MotherboardService;

import java.util.List;

@RestController
@RequestMapping("/api/category/motherboard")
public class MotherboardController {
    @Autowired
    private MotherboardService motherboardService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getMotherboard(@PathVariable Integer id){
        ApiResponse apiResponse = motherboardService.getMotherboard(id);
        return ResponseEntity.status(apiResponse.getSuccess()?200:409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Motherboard>> getMotherboards(){
        return ResponseEntity.status(motherboardService.getMotherboards() != null ? 200 : 409).body(motherboardService.getMotherboards());
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addMotherboard(@RequestBody MotherboardDto motherboardDto){
        ApiResponse apiResponse = motherboardService.addMotherboard(motherboardDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editMotherboard(@PathVariable Integer id, @RequestBody MotherboardDto motherboardDto){
        ApiResponse apiResponse = motherboardService.editMotherboard(id,motherboardDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMotherboard(@PathVariable Integer id){
        ApiResponse apiResponse = motherboardService.deleteMotherboard(id);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(apiResponse);
    }
}
