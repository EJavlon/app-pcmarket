package uz.pcmartket.apppcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pcmartket.apppcmarket.entity.Brand;
import uz.pcmartket.apppcmarket.entity.PowerUnit;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.PowerUnitDto;
import uz.pcmartket.apppcmarket.repository.BrandRepository;
import uz.pcmartket.apppcmarket.repository.PowerUnitRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PowerUnitService {
    @Autowired
    private PowerUnitRepository powerUnitRepository;
    @Autowired
    private BrandRepository brandRepository;


    public ApiResponse getPowerUnit(Integer id) {
        Optional<PowerUnit> optionalPowerUnit = powerUnitRepository.findById(id);
        return optionalPowerUnit.map(powerUnit -> new ApiResponse("Power unit found", true, powerUnit)).orElseGet(() ->
                new ApiResponse("Power unit not found", false));
    }

    public List<PowerUnit> getPowerUnits() {
        return powerUnitRepository.findAll();
    }

    public ApiResponse addPowerUnit(PowerUnitDto powerUnitDto) {
        Optional<Brand> optionalBrand = brandRepository.findById(powerUnitDto.getBrandId());
        if (!optionalBrand.isPresent()) return new ApiResponse("Brand not found", false);

        boolean exists = powerUnitRepository.existsByNameAndBrandId(powerUnitDto.getName(), powerUnitDto.getBrandId());
        if (exists) return new ApiResponse("Such a power unit exists", false);

        PowerUnit powerUnit = new PowerUnit();
        powerUnit.setBrand(optionalBrand.get());
        powerUnit.setName(powerUnitDto.getName());
        powerUnit = powerUnitRepository.save(powerUnit);

        return new ApiResponse("Power unit seccessfully saved", true, powerUnit);
    }

    public ApiResponse editPowerUnit(Integer id, PowerUnitDto powerUnitDto) {
        Optional<PowerUnit> optionalPowerUnit = powerUnitRepository.findById(id);
        if (!optionalPowerUnit.isPresent()) return new ApiResponse("Power unit not found", false);

        Optional<Brand> optionalBrand = brandRepository.findById(powerUnitDto.getBrandId());
        if (!optionalBrand.isPresent()) return new ApiResponse("Brand not found", false);

        boolean exists = powerUnitRepository.existsByNameAndBrandId(powerUnitDto.getName(), powerUnitDto.getBrandId());
        if (exists) return new ApiResponse("Such a power unit exists", false);

        PowerUnit powerUnit = optionalPowerUnit.get();
        powerUnit.setBrand(optionalBrand.get());
        powerUnit.setName(powerUnitDto.getName());
        powerUnit = powerUnitRepository.save(powerUnit);

        return new ApiResponse("Power unit seccessfully edited", true, powerUnit);
    }

    public ApiResponse deletePowerUnit(Integer id) {
        Optional<PowerUnit> optionalPowerUnit = powerUnitRepository.findById(id);
        if (!optionalPowerUnit.isPresent()) return new ApiResponse("Power unit not found", false);

        powerUnitRepository.deleteById(id);
        return new ApiResponse("Power unit seccessfully deleted", true);
    }
}
