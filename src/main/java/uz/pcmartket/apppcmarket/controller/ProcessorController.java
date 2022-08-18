package uz.pcmartket.apppcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pcmartket.apppcmarket.entity.Processor;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.ProcessorDto;
import uz.pcmartket.apppcmarket.service.ProcessorService;

import java.util.List;

@RestController
@RequestMapping("/api/category/processor")
public class ProcessorController {
    @Autowired
    private ProcessorService processorService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProcessor(@PathVariable Integer id){
        ApiResponse apiResponse = processorService.getProcessor(id);
        return ResponseEntity.status(apiResponse.getSuccess()?200:409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Processor>> getProcessors(){
        return ResponseEntity.status(processorService.getProcessors() != null ? 200 : 409).body(processorService.getProcessors());
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addProcessor(@RequestBody ProcessorDto processorDto){
        ApiResponse apiResponse = processorService.addProcessor(processorDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editProcessor(@PathVariable Integer id, @RequestBody ProcessorDto processorDto){
        ApiResponse apiResponse = processorService.editProcessor(id,processorDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProcessor(@PathVariable Integer id){
        ApiResponse apiResponse = processorService.deleteProcessor(id);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(apiResponse);
    }
}
