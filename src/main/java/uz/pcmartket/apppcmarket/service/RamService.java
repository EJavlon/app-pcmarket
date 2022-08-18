package uz.pcmartket.apppcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pcmartket.apppcmarket.entity.Brand;
import uz.pcmartket.apppcmarket.entity.Ram;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.RamDto;
import uz.pcmartket.apppcmarket.repository.BrandRepository;
import uz.pcmartket.apppcmarket.repository.RamRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RamService {
    @Autowired
    private RamRepository ramRepository;
    @Autowired
    private BrandRepository brandRepository;


    public ApiResponse getRam(Integer id) {
        Optional<Ram> optionalRam = ramRepository.findById(id);
        return optionalRam.map(ram -> new ApiResponse("Ram found", true, ram)).orElseGet(() ->
                new ApiResponse("Ram not found", false));
    }

    public List<Ram> getRams() {
        return ramRepository.findAll();
    }

    public ApiResponse addRam(RamDto ramDto) {
        Optional<Brand> optionalBrand = brandRepository.findById(ramDto.getBrandId());
        if (!optionalBrand.isPresent()) return new ApiResponse("Brand not found", false);

        boolean exists = ramRepository.existsByNameAndBrandIdAndSize(ramDto.getName(), ramDto.getBrandId(), ramDto.getSize());
        if (exists) return new ApiResponse("Such a ram exists",false);

        Ram ram = new Ram();
        ram.setBrand(optionalBrand.get());
        ram.setName(ramDto.getName());
        ram.setSize(ramDto.getSize());
        ram = ramRepository.save(ram);

        return new ApiResponse("Ram seccessfully saved", true, ram);
    }

    public ApiResponse editRam(Integer id, RamDto ramDto) {
        Optional<Ram> optionalRam = ramRepository.findById(id);
        if (!optionalRam.isPresent()) return new ApiResponse("Ram not found", false);

        boolean exists = ramRepository.existsByNameAndBrandIdAndSizeAndIdNot(ramDto.getName(), ramDto.getBrandId(), ramDto.getSize(), id);
        if (exists) return new ApiResponse("Such a Ram exists", false);

        Optional<Brand> optionalBrand = brandRepository.findById(ramDto.getBrandId());
        if (!optionalBrand.isPresent()) return new ApiResponse("Brand not found", false);

        Ram ram = optionalRam.get();
        ram.setBrand(optionalBrand.get());
        ram.setName(ramDto.getName());
        ram.setSize(ramDto.getSize());
        ram = ramRepository.save(ram);

        return new ApiResponse("Ram seccessfully edited", true, ram);
    }

    public ApiResponse deleteRam(Integer id) {
        Optional<Ram> optionalRam = ramRepository.findById(id);
        if (!optionalRam.isPresent()) return new ApiResponse("Ram not found", false);

        ramRepository.deleteById(id);
        return new ApiResponse("Ram seccessfully deleted", true);
    }
}
