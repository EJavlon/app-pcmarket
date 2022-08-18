package uz.pcmartket.apppcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pcmartket.apppcmarket.entity.*;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.PCDto;
import uz.pcmartket.apppcmarket.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PcService {
    @Autowired
    private PCRepository pcRepository;
    @Autowired
    private MemoryRepository memoryRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private MotherboardRepository motherboardRepository;
    @Autowired
    private PowerUnitRepository powerUnitRepository;
    @Autowired
    private RamRepository ramRepository;
    @Autowired
    private ProcessorRepository processorRepository;
    @Autowired
    private VideoCartRepository videoCartRepository;


    public ApiResponse getPC(Integer id) {
        Optional<PC> optionalPC = pcRepository.findById(id);
        return optionalPC.map(pc -> new ApiResponse("PC found", true, pc)).orElseGet(() ->
                new ApiResponse("PC not found", false));
    }

    public List<PC> getPCs() {
        return pcRepository.findAll();
    }

    public ApiResponse addPC(PCDto pcDto) {
        Optional<Memory> optionalMemory = memoryRepository.findById(pcDto.getMemoryId());
        if (!optionalMemory.isPresent()) return new ApiResponse("Memory not found", false);

        Optional<Brand> optionalBrand = brandRepository.findById(pcDto.getBrandId());
        if (!optionalBrand.isPresent()) return new ApiResponse("Brand not found", false);

        Optional<Motherboard> optionalMotherboard = motherboardRepository.findById(pcDto.getMotherboardId());
        if (!optionalMotherboard.isPresent()) return new ApiResponse("Motherboard not found", false);

        Optional<PowerUnit> optionalPowerUnit = powerUnitRepository.findById(pcDto.getPowerUnitId());
        if (!optionalPowerUnit.isPresent()) return new ApiResponse("Power Unit not found", false);

        Optional<Ram> optionalRam = ramRepository.findById(pcDto.getRamId());
        if (!optionalRam.isPresent()) return new ApiResponse("Ram not found", false);

        Optional<Processor> optionalProcessor = processorRepository.findById(pcDto.getProcessorId());
        if (!optionalProcessor.isPresent()) return new ApiResponse("Processor not found", false);

        PC pc = new PC();
        pc.setName(pcDto.getName());
        pc.setBrand(optionalBrand.get());
        pc.setMemory(optionalMemory.get());
        pc.setMotherboard(optionalMotherboard.get());
        pc.setOperatingSystem(pcDto.getOperatingSystem());
        pc.setProcessor(optionalProcessor.get());
        pc.setRam(optionalRam.get());
        pc.setPowerUnit(optionalPowerUnit.get());
        pc.setVideoCarts(getVideoCards(pcDto.getVideoCartsId()));
        pc = pcRepository.save(pc);

        return new ApiResponse("PC seccessfully saved", true, pc);
    }

    public ApiResponse editPC(Integer id, PCDto pcDto) {
        Optional<PC> optionalPC = pcRepository.findById(id);
        if (!optionalPC.isPresent()) return new ApiResponse("PC not found", false);

        Optional<Memory> optionalMemory = memoryRepository.findById(pcDto.getMemoryId());
        if (!optionalMemory.isPresent()) return new ApiResponse("Memory not found", false);

        Optional<Brand> optionalBrand = brandRepository.findById(pcDto.getBrandId());
        if (!optionalBrand.isPresent()) return new ApiResponse("Brand not found", false);

        Optional<Motherboard> optionalMotherboard = motherboardRepository.findById(pcDto.getMotherboardId());
        if (!optionalMotherboard.isPresent()) return new ApiResponse("Motherboard not found", false);

        Optional<PowerUnit> optionalPowerUnit = powerUnitRepository.findById(pcDto.getPowerUnitId());
        if (!optionalPowerUnit.isPresent()) return new ApiResponse("Power Unit not found", false);

        Optional<Ram> optionalRam = ramRepository.findById(pcDto.getRamId());
        if (!optionalRam.isPresent()) return new ApiResponse("Ram not found", false);

        Optional<Processor> optionalProcessor = processorRepository.findById(pcDto.getProcessorId());
        if (!optionalProcessor.isPresent()) return new ApiResponse("Processor not found", false);

        PC pc = optionalPC.get();
        pc.setName(pcDto.getName());
        pc.setBrand(optionalBrand.get());
        pc.setMemory(optionalMemory.get());
        pc.setMotherboard(optionalMotherboard.get());
        pc.setOperatingSystem(pcDto.getOperatingSystem());
        pc.setProcessor(optionalProcessor.get());
        pc.setRam(optionalRam.get());
        pc.setPowerUnit(optionalPowerUnit.get());
        pc.setVideoCarts(getVideoCards(pcDto.getVideoCartsId()));
        pc = pcRepository.save(pc);

        return new ApiResponse("PC seccessfully edited", true, pc);
    }

    public ApiResponse deletePC(Integer id) {
        Optional<PC> optionalPC = pcRepository.findById(id);
        if (!optionalPC.isPresent()) return new ApiResponse("PC not found", false);

        pcRepository.delete(optionalPC.get());
        return new ApiResponse("PC seccessfully deleted", true, optionalPC.get());
    }

    private List<VideoCart> getVideoCards(Integer[] videoCartsId) {
        List<VideoCart> videoCarts = new ArrayList<>(2);
        for (Integer id : videoCartsId) {
            Optional<VideoCart> optionalVideoCart = videoCartRepository.findById(id);
            if (!optionalVideoCart.isPresent()) return null;
            videoCarts.add(optionalVideoCart.get());
        }
        return videoCarts;
    }
}
