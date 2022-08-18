package uz.pcmartket.apppcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pcmartket.apppcmarket.entity.Brand;
import uz.pcmartket.apppcmarket.entity.Memory;
import uz.pcmartket.apppcmarket.enums.ConnectionInterfaces;
import uz.pcmartket.apppcmarket.enums.DriveType;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.MemoryDto;
import uz.pcmartket.apppcmarket.repository.BrandRepository;
import uz.pcmartket.apppcmarket.repository.MemoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MemoryService {
    @Autowired
    private MemoryRepository memoryRepository;
    @Autowired
    private BrandRepository brandRepository;


    public ApiResponse getMemory(Integer id) {
        Optional<Memory> optionalMemory = memoryRepository.findById(id);
        return optionalMemory.map(memory -> new ApiResponse("Memory found", true, memory)).orElseGet(() -> new ApiResponse("Memory not found", false));
    }

    public List<Memory> getMemorys() {
        return memoryRepository.findAll();
    }

    public ApiResponse addMemory(MemoryDto memoryDto) {
        if (!containsDriveType(memoryDto.getDriveType())) return new ApiResponse("Drive type not found", false);
        if (!containsConnectionInterfaces(memoryDto.getInterfaces()))
            return new ApiResponse("Connection interface not found", false);

        Optional<Brand> optionalBrand = brandRepository.findById(memoryDto.getBrandId());
        if (!optionalBrand.isPresent()) return new ApiResponse("Brand not found", false);

        Memory memory = new Memory();
        memory.setDriveType(DriveType.valueOf(memoryDto.getDriveType()));
        memory.setInterfaces(ConnectionInterfaces.valueOf(memoryDto.getInterfaces()));
        memory.setFormFactor(memoryDto.getFormFactor());
        memory.setBrand(optionalBrand.get());
        memory.setName(memoryDto.getName());
        memory.setStorageCapacity(memoryDto.getStorageCapacity());
        memory = memoryRepository.save(memory);

        return new ApiResponse("Memory seccessfully saved", true, memory);
    }

    public ApiResponse editMemory(Integer id, MemoryDto memoryDto) {
        Optional<Memory> optionalMemory = memoryRepository.findById(id);
        if (!optionalMemory.isPresent()) return new ApiResponse("Memory not found", false);

        if (!containsDriveType(memoryDto.getDriveType())) return new ApiResponse("Drive type not found", false);
        if (!containsConnectionInterfaces(memoryDto.getInterfaces()))
            return new ApiResponse("Connection interface not found", false);

        Optional<Memory> optionalBrand = memoryRepository.findById(memoryDto.getBrandId());
        if (!optionalBrand.isPresent()) return new ApiResponse("Brand not found", false);

        Memory memory = optionalMemory.get();
        memory.setDriveType(DriveType.valueOf(memoryDto.getDriveType()));
        memory.setInterfaces(ConnectionInterfaces.valueOf(memoryDto.getInterfaces()));
        memory.setFormFactor(memoryDto.getFormFactor());
        memory.setBrand(optionalBrand.get().getBrand());
        memory.setName(memoryDto.getName());
        memory = memoryRepository.save(memory);

        return new ApiResponse("Memory seccessfully edited", true, memory);
    }

    public ApiResponse deleteMemory(Integer id) {
        try {
            memoryRepository.deleteById(id);
            return new ApiResponse("Memory seccessfully deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Memory not found", false);
        }
    }

    private boolean containsDriveType(String driveType) {
        for (DriveType type : DriveType.values()) {
            if (type.equals(DriveType.valueOf(driveType))) return true;
        }
        return false;
    }

    private boolean containsConnectionInterfaces(String interfaces) {
        for (ConnectionInterfaces value : ConnectionInterfaces.values()) {
            if (value.equals(ConnectionInterfaces.valueOf(interfaces))) return true;
        }
        return false;
    }
}
