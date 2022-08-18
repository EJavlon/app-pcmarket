package uz.pcmartket.apppcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pcmartket.apppcmarket.entity.Monitor;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.MonitorDto;
import uz.pcmartket.apppcmarket.service.MonitorService;

import java.util.List;

@RestController
@RequestMapping("/api/category/monitor")
public class MonitorController {
    @Autowired
    private MonitorService monitorService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getMonitor(@PathVariable Integer id){
        ApiResponse apiResponse = monitorService.getMonitor(id);
        return ResponseEntity.status(apiResponse.getSuccess()?200:409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Monitor>> getMonitors(){
        return ResponseEntity.status(monitorService.getMonitors() != null ? 200 : 409).body(monitorService.getMonitors());
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addMonitor(@RequestBody MonitorDto monitorDto){
        ApiResponse apiResponse = monitorService.addMonitor(monitorDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editMonitor(@PathVariable Integer id, @RequestBody MonitorDto monitorDto){
        ApiResponse apiResponse = monitorService.editMonitor(id,monitorDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMonitor(@PathVariable Integer id){
        ApiResponse apiResponse = monitorService.deleteMonitor(id);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(apiResponse);
    }
}
