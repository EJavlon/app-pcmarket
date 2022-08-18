package uz.pcmartket.apppcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pcmartket.apppcmarket.entity.Brand;
import uz.pcmartket.apppcmarket.entity.Motherboard;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.MotherboardDto;
import uz.pcmartket.apppcmarket.repository.BrandRepository;
import uz.pcmartket.apppcmarket.repository.MotherboardRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MotherboardService {
    @Autowired
    private MotherboardRepository motherboardRepository;
    @Autowired
    private BrandRepository brandRepository;

    public ApiResponse getMotherboard(Integer id) {
        Optional<Motherboard> optionalMotherboard = motherboardRepository.findById(id);
        return optionalMotherboard.map(motherboard -> new ApiResponse("Motherboard found", true, motherboard)).orElseGet(() ->
                new ApiResponse("Motherboard not found", false));
    }

    public List<Motherboard> getMotherboards() {
        return motherboardRepository.findAll();
    }

    public ApiResponse addMotherboard(MotherboardDto motherboardDto) {
        Optional<Brand> optionalBrand = brandRepository.findById(motherboardDto.getBrandId());
        if (!optionalBrand.isPresent()) return new ApiResponse("Brand not found", false);

        boolean exists = motherboardRepository.existsByNameAndBrandIdAndAmountMemorySlots(motherboardDto.getName(), motherboardDto.getBrandId(), motherboardDto.getAmountMemorySlots());
        if (exists) return new ApiResponse("Such a monitor exists", false);

        Motherboard motherboard = new Motherboard();
        motherboard.setName(motherboardDto.getName());
        motherboard.setBrand(optionalBrand.get());
        motherboard.setChipset(motherboardDto.getChipset());
        motherboard.setCoket(motherboardDto.getCoket());
        motherboard.setWifiAdapter(motherboardDto.getWifiAdapter());
        motherboard.setAmountMemorySlots(motherboardDto.getAmountMemorySlots());
        motherboard = motherboardRepository.save(motherboard);

        return new ApiResponse("Motherboard seccessfully saved", true, motherboard);
    }

    public ApiResponse editMotherboard(Integer id, MotherboardDto motherboardDto) {
        Optional<Motherboard> optionalMotherboard = motherboardRepository.findById(id);
        if (!optionalMotherboard.isPresent()) return new ApiResponse("Motherboard not found", false);

        Optional<Brand> optionalBrand = brandRepository.findById(motherboardDto.getBrandId());
        if (!optionalBrand.isPresent()) return new ApiResponse("Brand not found", false);

        boolean exists = motherboardRepository.existsByNameAndBrandIdAndAmountMemorySlotsAndIdNot(motherboardDto.getName(), motherboardDto.getBrandId(), motherboardDto.getAmountMemorySlots(), id);
        if (exists) return new ApiResponse("Such a monitor exists", false);

        Motherboard motherboard = optionalMotherboard.get();
        motherboard.setName(motherboardDto.getName());
        motherboard.setBrand(optionalBrand.get());
        motherboard.setChipset(motherboardDto.getChipset());
        motherboard.setCoket(motherboardDto.getCoket());
        motherboard.setWifiAdapter(motherboardDto.getWifiAdapter());
        motherboard.setAmountMemorySlots(motherboardDto.getAmountMemorySlots());
        motherboard = motherboardRepository.save(motherboard);

        return new ApiResponse("Motherboard seccessfully edited", true, motherboard);
    }

    public ApiResponse deleteMotherboard(Integer id) {
        Optional<Motherboard> optionalMotherboard = motherboardRepository.findById(id);
        if (!optionalMotherboard.isPresent()) return new ApiResponse("Motherboard not found", false);

        return new ApiResponse("Motherboard seccessfully deleted", true);
    }
}
