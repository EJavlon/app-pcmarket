package uz.pcmartket.apppcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pcmartket.apppcmarket.entity.Ram;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.RamDto;
import uz.pcmartket.apppcmarket.service.RamService;

import java.util.List;

@RestController
@RequestMapping("/api/category/ram")
public class RamController {
    @Autowired
    private RamService ramService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getRam(@PathVariable Integer id){
        ApiResponse apiResponse = ramService.getRam(id);
        return ResponseEntity.status(apiResponse.getSuccess()?200:409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Ram>> getRams(){
        return ResponseEntity.status(ramService.getRams() != null ? 200 : 409).body(ramService.getRams());
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addRam(@RequestBody RamDto ramDto){
        ApiResponse apiResponse = ramService.addRam(ramDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editRam(@PathVariable Integer id, @RequestBody RamDto ramDto){
        ApiResponse apiResponse = ramService.editRam(id,ramDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteRam(@PathVariable Integer id){
        ApiResponse apiResponse = ramService.deleteRam(id);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(apiResponse);
    }
}
