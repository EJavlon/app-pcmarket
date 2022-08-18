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
public class NotebookService {
    @Autowired
    private NotebookRepository notebookRepository;
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

    public ApiResponse getNotebook(Integer id) {
        Optional<Notebook> optionalNotebook = notebookRepository.findById(id);
        return optionalNotebook.map(notebook -> new ApiResponse("Notebook found", true, notebook)).orElseGet(() ->
                new ApiResponse("Notebook not found", false));
    }

    public List<Notebook> getNotebooks() {
        return notebookRepository.findAll();
    }

    public ApiResponse addNotebook(MonoblocDto monoblocDto) {
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

        Notebook notebook = new Notebook();
        notebook.setName(monoblocDto.getName());
        notebook.setBrand(optionalBrand.get());
        notebook.setProcessor(optionalProcessor.get());
        notebook.setMemory(optionalMemory.get());
        notebook.setRam(optionalRam.get());
        notebook.setVideoCarts(videoCartList);
        notebook.setOperatingSystem(monoblocDto.getOperatingSystem());
        notebook.setScreenDiagonal(monoblocDto.getScreenDiagonal());
        notebook.setScreenResolution(monoblocDto.getScreenResolution());
        notebook = notebookRepository.save(notebook);

        return new ApiResponse("Notebook seccessfully saved", true, notebook);
    }

    public ApiResponse editNotebook(Integer id, MonoblocDto monoblocDto) {
        Optional<Notebook> optionalNotebook = notebookRepository.findById(id);
        if (!optionalNotebook.isPresent()) return new ApiResponse("Notebook not found", false);

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

        Notebook notebook = optionalNotebook.get();
        notebook.setName(monoblocDto.getName());
        notebook.setBrand(optionalBrand.get());
        notebook.setProcessor(optionalProcessor.get());
        notebook.setMemory(optionalMemory.get());
        notebook.setRam(optionalRam.get());
        notebook.setVideoCarts(videoCartList);
        notebook.setOperatingSystem(monoblocDto.getOperatingSystem());
        notebook.setScreenDiagonal(monoblocDto.getScreenDiagonal());
        notebook.setScreenResolution(monoblocDto.getScreenResolution());
        notebook = notebookRepository.save(notebook);

        return new ApiResponse("Notebook seccessfully edited", true, notebook);
    }

    public ApiResponse deleteNotebook(Integer id) {
        Optional<Notebook> optionalNotebook = notebookRepository.findById(id);
        if (!optionalNotebook.isPresent()) return new ApiResponse("Notebook not found", false);

        return new ApiResponse("Notebook seccessfully deleted", true);
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
