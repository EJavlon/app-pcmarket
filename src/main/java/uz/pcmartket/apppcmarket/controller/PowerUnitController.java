package uz.pcmartket.apppcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pcmartket.apppcmarket.entity.PowerUnit;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.PowerUnitDto;
import uz.pcmartket.apppcmarket.service.PowerUnitService;

import java.util.List;

@RestController
@RequestMapping("/api/category/powerunit")
public class PowerUnitController {
    @Autowired
    private PowerUnitService powerUnitService;


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getPowerUnit(@PathVariable Integer id){
        ApiResponse apiResponse = powerUnitService.getPowerUnit(id);
        return ResponseEntity.status(apiResponse.getSuccess()?200:409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<PowerUnit>> getPowerUnits(){
        return ResponseEntity.status(powerUnitService.getPowerUnits() != null ? 200 : 409).body(powerUnitService.getPowerUnits());
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addPowerUnit(@RequestBody PowerUnitDto powerUnitDto){
        ApiResponse apiResponse = powerUnitService.addPowerUnit(powerUnitDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editPowerUnit(@PathVariable Integer id, @RequestBody PowerUnitDto powerUnitDto){
        ApiResponse apiResponse = powerUnitService.editPowerUnit(id,powerUnitDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePowerUnit(@PathVariable Integer id){
        ApiResponse apiResponse = powerUnitService.deletePowerUnit(id);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(apiResponse);
    }
}
