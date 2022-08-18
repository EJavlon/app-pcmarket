package uz.pcmartket.apppcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pcmartket.apppcmarket.entity.*;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.ProductDto;
import uz.pcmartket.apppcmarket.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MemoryRepository memoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AttachementRepository attachementRepository;
    @Autowired
    private MonitorRepository monitorRepository;
    @Autowired
    private MonoblocRepository monoblocRepository;
    @Autowired
    private MotherboardRepository motherboardRepository;
    @Autowired
    private NotebookRepository notebookRepository;
    @Autowired
    private PCRepository pcRepository;
    @Autowired
    private PowerUnitRepository powerUnitRepository;
    @Autowired
    private ProcessorRepository processorRepository;

    public ApiResponse getProduct(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.map(product -> new ApiResponse("Product found", true, product)).orElseGet(() ->
                new ApiResponse("Product not found", false));
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public ApiResponse addMemoryProduct(ProductDto productDto) {
        Optional<Memory> optionalMemory = memoryRepository.findById(productDto.getMemoryId());
        if (!optionalMemory.isPresent()) return new ApiResponse("Memory not found", false);

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) return new ApiResponse("Category not found", false);

        Product product = new Product();
        product.addMemoryProduct(optionalMemory.get());
        product.setCategory(optionalCategory.get());
        product.setGuarantee(productDto.getGuarantee());
        product.setImages(getImages(productDto.getImagesId()));
        product.setPrice(productDto.getPrice());
        product.setPrice(productDto.getPrice());
        product = productRepository.save(product);

        return new ApiResponse("Product seccessfully saved", true, product);
    }

    public ApiResponse addMonitorProduct(ProductDto productDto) {
        Optional<Monitor> optionalMonitor = monitorRepository.findById(productDto.getMonitorId());
        if (!optionalMonitor.isPresent()) return new ApiResponse("Monitor not found", false);

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) return new ApiResponse("Category not found", false);

        Product product = new Product();
        product.addMotnitorProduct(optionalMonitor.get());
        product.setCategory(optionalCategory.get());
        product.setGuarantee(productDto.getGuarantee());
        product.setImages(getImages(productDto.getImagesId()));
        product.setPrice(productDto.getPrice());
        product.setPrice(productDto.getPrice());
        product = productRepository.save(product);

        return new ApiResponse("Product seccessfully saved", true, product);
    }

    public ApiResponse addMonoblocProduct(ProductDto productDto) {
        Optional<Monobloc> optionalMonobloc = monoblocRepository.findById(productDto.getMonoblocId());
        if (!optionalMonobloc.isPresent()) return new ApiResponse("Monobloc not found", false);

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) return new ApiResponse("Category not found", false);

        Product product = new Product();
        product.addMonoblocProduct(optionalMonobloc.get());
        product.setCategory(optionalCategory.get());
        product.setGuarantee(productDto.getGuarantee());
        product.setImages(getImages(productDto.getImagesId()));
        product.setPrice(productDto.getPrice());
        product.setPrice(productDto.getPrice());
        product = productRepository.save(product);

        return new ApiResponse("Product seccessfully saved", true, product);
    }

    public ApiResponse addMotherboardProduct(ProductDto productDto) {
        Optional<Motherboard> optionalMotherboard = motherboardRepository.findById(productDto.getMotherboardId());
        if (!optionalMotherboard.isPresent()) return new ApiResponse("Motherboard not found", false);

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) return new ApiResponse("Category not found", false);

        Product product = new Product();
        product.addMotherboardProduct(optionalMotherboard.get());
        product.setCategory(optionalCategory.get());
        product.setGuarantee(productDto.getGuarantee());
        product.setImages(getImages(productDto.getImagesId()));
        product.setPrice(productDto.getPrice());
        product.setPrice(productDto.getPrice());
        product = productRepository.save(product);

        return new ApiResponse("Product seccessfully saved", true, product);
    }

    public ApiResponse addNotebookProduct(ProductDto productDto) {
        Optional<Notebook> optionalNotebook = notebookRepository.findById(productDto.getNotebookId());
        if (!optionalNotebook.isPresent()) return new ApiResponse("Notebook not found", false);

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) return new ApiResponse("Category not found", false);

        Product product = new Product();
        product.addNotebookProduct(optionalNotebook.get());
        product.setCategory(optionalCategory.get());
        product.setGuarantee(productDto.getGuarantee());
        product.setImages(getImages(productDto.getImagesId()));
        product.setPrice(productDto.getPrice());
        product.setPrice(productDto.getPrice());
        product = productRepository.save(product);

        return new ApiResponse("Product seccessfully saved", true, product);
    }

    public ApiResponse addPCProduct(ProductDto productDto) {
        Optional<PC> optionalPC = pcRepository.findById(productDto.getPcId());
        if (!optionalPC.isPresent()) return new ApiResponse("Pc not found", false);

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) return new ApiResponse("Category not found", false);

        Product product = new Product();
        product.addPCProduct(optionalPC.get());
        product.setCategory(optionalCategory.get());
        product.setGuarantee(productDto.getGuarantee());
        product.setImages(getImages(productDto.getImagesId()));
        product.setPrice(productDto.getPrice());
        product.setPrice(productDto.getPrice());
        product = productRepository.save(product);

        return new ApiResponse("Product seccessfully saved", true, product);
    }

    public ApiResponse addPowerUnitProduct(ProductDto productDto) {
        Optional<PowerUnit> optionalPowerUnit = powerUnitRepository.findById(productDto.getPowerUnitId());
        if (!optionalPowerUnit.isPresent()) return new ApiResponse("Power unit not found", false);

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) return new ApiResponse("Category not found", false);

        Product product = new Product();
        product.addPowerUnitProduct(optionalPowerUnit.get());
        product.setCategory(optionalCategory.get());
        product.setGuarantee(productDto.getGuarantee());
        product.setImages(getImages(productDto.getImagesId()));
        product.setPrice(productDto.getPrice());
        product.setPrice(productDto.getPrice());
        product = productRepository.save(product);

        return new ApiResponse("Product seccessfully saved", true, product);
    }

    public ApiResponse addProcessorProduct(ProductDto productDto) {
        Optional<Processor> optionalProcessor = processorRepository.findById(productDto.getProcessorId());
        if (!optionalProcessor.isPresent()) return new ApiResponse("Processor not found", false);

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) return new ApiResponse("Category not found", false);

        Product product = new Product();
        product.addProcessorProduct(optionalProcessor.get());
        product.setCategory(optionalCategory.get());
        product.setGuarantee(productDto.getGuarantee());
        product.setImages(getImages(productDto.getImagesId()));
        product.setPrice(productDto.getPrice());
        product.setPrice(productDto.getPrice());
        product = productRepository.save(product);

        return new ApiResponse("Product seccessfully saved", true, product);
    }

    public ApiResponse editProduct(Integer id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) return new ApiResponse("Product not found", false);

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) return new ApiResponse("Category not found", false);

        Product product = optionalProduct.get();
        product.setCategory(optionalCategory.get());
        product.setGuarantee(productDto.getGuarantee());
        product.setImages(getImages(productDto.getImagesId()));
        product.setPrice(productDto.getPrice());
        product.setPrice(productDto.getPrice());
        product = productRepository.save(product);

        return new ApiResponse("Product seccessfully edited", true, product);
    }

    public ApiResponse deleteProduct(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) return new ApiResponse("Product not found", false);

        productRepository.delete(optionalProduct.get());
        return new ApiResponse("Product seccessfully deleted", true);
    }

    private List<Attachment> getImages(Integer[] imagesId) {
        List<Attachment> images = new ArrayList<>();
        for (Integer id : imagesId) {
            Optional<Attachment> optionalAttachment = attachementRepository.findById(id);
            if (!optionalAttachment.isPresent()) return null;
            images.add(optionalAttachment.get());
        }
        return images;
    }
}
