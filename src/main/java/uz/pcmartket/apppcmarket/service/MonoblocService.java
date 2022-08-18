package uz.pcmartket.apppcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pcmartket.apppcmarket.entity.*;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.MonoblocDto;
import uz.pcmartket.apppcmarket.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MonoblocService {
    @Autowired
    private MonoblocRepository monoblocRepository;
    @Autowired
    private ProcessorRepository processorRepository;
    @Autowired
    private MemoryRepository memoryRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private RamRepository ramRepository;
    @Autowired
    private VideoCartRepository videoCartRepository;


    public ApiResponse getMonobloc(Integer id) {
        Optional<Monobloc> optionalMonobloc = monoblocRepository.findById(id);
        return optionalMonobloc.map(monobloc -> new ApiResponse("Monobloc found", true, monobloc)).orElseGet(() ->
                new ApiResponse("Monobloc not found", false));
    }

    public List<Monobloc> getMonoblocs() {
        return monoblocRepository.findAll();
    }

    public ApiResponse addMonobloc(MonoblocDto monoblocDto) {
        Optional<Brand> optionalBrand = brandRepository.findById(monoblocDto.getBrandId());
        if (!optionalBrand.isPresent()) return new ApiResponse("Brand not found", false);

        Optional<Memory> optionalMemory = memoryRepository.findById(monoblocDto.getMemoryId());
        if (optionalMemory.isPresent()) return new ApiResponse("Memory not found", false);

        Optional<Processor> optionalProcessor = processorRepository.findById(monoblocDto.getProcessorId());
        if (!optionalProcessor.isPresent()) return new ApiResponse("Processor not found", false);

        Optional<Ram> optionalRam = ramRepository.findById(monoblocDto.getRamId());
        if (!optionalRam.isPresent()) return new ApiResponse("Ram not found", false);

        List<VideoCart> videoCartList = setVideoCart(monoblocDto.getVideoCartsId());
        if (Objects.isNull(videoCartList)) return new ApiResponse("video cart not found", false);

        Monobloc monobloc = new Monobloc();
        monobloc.setName(monoblocDto.getName());
        monobloc.setBrand(optionalBrand.get());
        monobloc.setProcessor(optionalProcessor.get());
        monobloc.setMemory(optionalMemory.get());
        monobloc.setRam(optionalRam.get());
        monobloc.setVideoCarts(videoCartList);
        monobloc.setOperatingSystem(monoblocDto.getOperatingSystem());
        monobloc.setScreenDiagonal(monoblocDto.getScreenDiagonal());
        monobloc.setScreenResolution(monoblocDto.getScreenResolution());
        monobloc = monoblocRepository.save(monobloc);

        return new ApiResponse("Monobloc seccessfully saved", true, monobloc);
    }

    public ApiResponse editMonobloc(Integer id, MonoblocDto monoblocDto) {
        Optional<Monobloc> optionalMonobloc = monoblocRepository.findById(id);
        if (!optionalMonobloc.isPresent()) return new ApiResponse("Monobloc not found", false);

        Optional<Brand> optionalBrand = brandRepository.findById(monoblocDto.getBrandId());
        if (!optionalBrand.isPresent()) return new ApiResponse("Brand not found", false);

        Optional<Memory> optionalMemory = memoryRepository.findById(monoblocDto.getMemoryId());
        if (optionalMemory.isPresent()) return new ApiResponse("Memory not found", false);

        Optional<Processor> optionalProcessor = processorRepository.findById(monoblocDto.getProcessorId());
        if (!optionalProcessor.isPresent()) return new ApiResponse("Processor not found", false);

        Optional<Ram> optionalRam = ramRepository.findById(monoblocDto.getRamId());
        if (!optionalRam.isPresent()) return new ApiResponse("Ram not found", false);

        List<VideoCart> videoCartList = setVideoCart(monoblocDto.getVideoCartsId());
        if (Objects.isNull(videoCartList)) return new ApiResponse("Video cart not found", false);

        Monobloc monobloc = optionalMonobloc.get();
        monobloc.setName(monoblocDto.getName());
        monobloc.setBrand(optionalBrand.get());
        monobloc.setProcessor(optionalProcessor.get());
        monobloc.setMemory(optionalMemory.get());
        monobloc.setRam(optionalRam.get());
        monobloc.setVideoCarts(videoCartList);
        monobloc.setOperatingSystem(monoblocDto.getOperatingSystem());
        monobloc.setScreenDiagonal(monoblocDto.getScreenDiagonal());
        monobloc.setScreenResolution(monoblocDto.getScreenResolution());
        monobloc = monoblocRepository.save(monobloc);

        return new ApiResponse("Monobloc seccessfully saved", true, monobloc);
    }

    public ApiResponse deleteMonobloc(Integer id) {
        Optional<Monobloc> optionalMonobloc = monoblocRepository.findById(id);
        if (!optionalMonobloc.isPresent()) return new ApiResponse("Monobloc not found", false);

        return new ApiResponse("Monobloc seccessfully deleted", true);
    }

    private List<VideoCart> setVideoCart(Integer[] videoCartsId) {
        List<VideoCart> videoCartList = new ArrayList<>();
        for (Integer id : videoCartsId) {
            Optional<VideoCart> optionalVideoCart = videoCartRepository.findById(id);
            if (!optionalVideoCart.isPresent()) return null;
            videoCartList.add(optionalVideoCart.get());
        }
        return videoCartList;
    }
}
