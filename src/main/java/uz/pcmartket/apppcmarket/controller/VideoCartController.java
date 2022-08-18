package uz.pcmartket.apppcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pcmartket.apppcmarket.entity.VideoCart;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.VideoCartDto;
import uz.pcmartket.apppcmarket.service.VideoCartService;

import java.util.List;

@RestController
@RequestMapping("/api/category/videocart")
public class VideoCartController {
    @Autowired
    private VideoCartService videoCartService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getVideoCart(@PathVariable Integer id){
        ApiResponse apiResponse = videoCartService.getVideoCart(id);
        return ResponseEntity.status(apiResponse.getSuccess()?200:409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<VideoCart>> getVideoCarts(){
        return ResponseEntity.status(videoCartService.getVideoCarts() != null ? 200 : 409).body(videoCartService.getVideoCarts());
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addVideoCart(@RequestBody VideoCartDto videoCartDto){
        ApiResponse apiResponse = videoCartService.addVideoCart(videoCartDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editVideoCart(@PathVariable Integer id, @RequestBody VideoCartDto videoCartDto){
        ApiResponse apiResponse = videoCartService.editVideoCart(id,videoCartDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteVideoCart(@PathVariable Integer id){
        ApiResponse apiResponse = videoCartService.deleteVideoCart(id);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(apiResponse);
    }
}
