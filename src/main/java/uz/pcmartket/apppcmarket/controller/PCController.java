package uz.pcmartket.apppcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pcmartket.apppcmarket.entity.PC;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.PCDto;
import uz.pcmartket.apppcmarket.service.PcService;

import java.util.List;

@RestController
@RequestMapping("/api/category/pc")
public class PCController {
    @Autowired
    private PcService pcService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getPC(@PathVariable Integer id){
        ApiResponse apiResponse = pcService.getPC(id);
        return ResponseEntity.status(apiResponse.getSuccess()?200:409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<PC>> getPCs(){
        return ResponseEntity.status(pcService.getPCs() != null ? 200 : 409).body(pcService.getPCs());
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addPC(@RequestBody PCDto pcDto){
        ApiResponse apiResponse = pcService.addPC(pcDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editPC(@PathVariable Integer id, @RequestBody PCDto pcDto){
        ApiResponse apiResponse = pcService.editPC(id,pcDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePC(@PathVariable Integer id){
        ApiResponse apiResponse = pcService.deletePC(id);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(apiResponse);
    }
}
