package uz.pcmartket.apppcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pcmartket.apppcmarket.entity.Brand;
import uz.pcmartket.apppcmarket.entity.Processor;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.ProcessorDto;
import uz.pcmartket.apppcmarket.repository.BrandRepository;
import uz.pcmartket.apppcmarket.repository.ProcessorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessorService {
    @Autowired
    private ProcessorRepository processorRepository;
    @Autowired
    private BrandRepository brandRepository;


    public ApiResponse getProcessor(Integer id) {
        Optional<Processor> optionalProcessor = processorRepository.findById(id);
        return optionalProcessor.map(processor -> new ApiResponse("Processor found", true, processor)).orElseGet(() ->
                new ApiResponse("Processor not found", false));
    }

    public List<Processor> getProcessors() {
        return processorRepository.findAll();
    }

    public ApiResponse addProcessor(ProcessorDto processorDto) {
        Optional<Brand> optionalBrand = brandRepository.findById(processorDto.getBrandId());
        if (!optionalBrand.isPresent()) return new ApiResponse("Brand not found", false);

        Processor processor = new Processor();
        processor.setName(processorDto.getName());
        processor.setBrand(optionalBrand.get());
        processor.setFrequency(processorDto.getFrequency());
        processor.setCache(processorDto.getCache());
        processor.setMaxFrequency(processorDto.getMaxFrequency());
        processor.setEstimatedPower(processor.getEstimatedPower());
        processor.setNumberOfCores(processorDto.getNumberOfCores());
        processor.setSystemBusFrequency(processor.getSystemBusFrequency());
        processor.setNumberOfThreads(processorDto.getNumberOfThreads());
        processor.setAbout(processorDto.getAbout());
        processor = processorRepository.save(processor);

        return new ApiResponse("Processor secccessfully saved", true, processor);
    }

    public ApiResponse editProcessor(Integer id, ProcessorDto processorDto) {
        Optional<Processor> optionalProcessor = processorRepository.findById(id);
        if (!optionalProcessor.isPresent()) return new ApiResponse("Processor not found", false);

        Optional<Brand> optionalBrand = brandRepository.findById(processorDto.getBrandId());
        if (!optionalBrand.isPresent()) return new ApiResponse("Brand not found", false);

        Processor processor = optionalProcessor.get();
        processor.setName(processorDto.getName());
        processor.setBrand(optionalBrand.get());
        processor.setFrequency(processorDto.getFrequency());
        processor.setCache(processorDto.getCache());
        processor.setMaxFrequency(processorDto.getMaxFrequency());
        processor.setEstimatedPower(processor.getEstimatedPower());
        processor.setNumberOfCores(processorDto.getNumberOfCores());
        processor.setSystemBusFrequency(processor.getSystemBusFrequency());
        processor.setNumberOfThreads(processorDto.getNumberOfThreads());
        processor.setAbout(processorDto.getAbout());
        processor = processorRepository.save(processor);

        return new ApiResponse("Processor secccessfully edited", true, processor);
    }

    public ApiResponse deleteProcessor(Integer id) {
        Optional<Processor> optionalProcessor = processorRepository.findById(id);
        if (!optionalProcessor.isPresent()) return new ApiResponse("Processor not found", false);

        processorRepository.deleteById(id);
        return new ApiResponse("Processor seccessfully deleted", true);
    }
}
