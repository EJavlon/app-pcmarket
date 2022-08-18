package uz.pcmartket.apppcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pcmartket.apppcmarket.entity.Product;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.ProductDto;
import uz.pcmartket.apppcmarket.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProduct(@PathVariable Integer id){
        ApiResponse apiResponse = productService.getProduct(id);
        return ResponseEntity.status(apiResponse.getSuccess()?200:409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.status(productService.getProducts() != null ? 200 : 409).body(productService.getProducts());
    }

    @PostMapping("/memory")
    public ResponseEntity<ApiResponse> addMemoryProduct(@RequestBody ProductDto productDto){
        ApiResponse apiResponse = productService.addMemoryProduct(productDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PostMapping("/monitor")
    public ResponseEntity<ApiResponse> addMonitorProduct(@RequestBody ProductDto productDto){
        ApiResponse apiResponse = productService.addMonitorProduct(productDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PostMapping("/monobloc")
    public ResponseEntity<ApiResponse> addMonoblocProduct(@RequestBody ProductDto productDto){
        ApiResponse apiResponse = productService.addMonoblocProduct(productDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PostMapping("/motherboard")
    public ResponseEntity<ApiResponse> addMotherboardProduct(@RequestBody ProductDto productDto){
        ApiResponse apiResponse = productService.addMotherboardProduct(productDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PostMapping("/notebook")
    public ResponseEntity<ApiResponse> addNotebookProduct(@RequestBody ProductDto productDto){
        ApiResponse apiResponse = productService.addNotebookProduct(productDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PostMapping("/pc")
    public ResponseEntity<ApiResponse> addPCProduct(@RequestBody ProductDto productDto){
        ApiResponse apiResponse = productService.addPCProduct(productDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PostMapping("/powerunit")
    public ResponseEntity<ApiResponse> addPowerUnitProduct(@RequestBody ProductDto productDto){
        ApiResponse apiResponse = productService.addPowerUnitProduct(productDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PostMapping("/Processor")
    public ResponseEntity<ApiResponse> addProcessorProduct(@RequestBody ProductDto productDto){
        ApiResponse apiResponse = productService.addProcessorProduct(productDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editProduct(@PathVariable Integer id, @RequestBody ProductDto productDto){
        ApiResponse apiResponse = productService.editProduct(id,productDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer id){
        ApiResponse apiResponse = productService.deleteProduct(id);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(apiResponse);
    }
}
