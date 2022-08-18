package uz.pcmartket.apppcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pcmartket.apppcmarket.entity.Memory;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.MemoryDto;
import uz.pcmartket.apppcmarket.service.MemoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category/memory")
public class MemoryController {
    @Autowired
    private MemoryService memoryService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getMemory(@PathVariable Integer id) {
        ApiResponse apiResponse = memoryService.getMemory(id);
        return ResponseEntity.status(apiResponse.getSuccess()?200:409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Memory>> getMemorys() {
        return ResponseEntity.status(memoryService.getMemorys() != null ? 200:409).body(memoryService.getMemorys());
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addMemory(@RequestBody MemoryDto memoryDto) {
        ApiResponse apiResponse = memoryService.addMemory(memoryDto);
        return ResponseEntity.status(apiResponse.getSuccess()?201:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editMemory(@PathVariable Integer id, @RequestBody MemoryDto memoryDto) {
        ApiResponse apiResponse = memoryService.editMemory(id, memoryDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMemory(@PathVariable Integer id) {
        ApiResponse apiResponse = memoryService.deleteMemory(id);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(apiResponse);
    }
}
