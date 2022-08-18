package uz.pcmartket.apppcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pcmartket.apppcmarket.entity.Brand;
import uz.pcmartket.apppcmarket.entity.VideoCart;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.VideoCartDto;
import uz.pcmartket.apppcmarket.repository.BrandRepository;
import uz.pcmartket.apppcmarket.repository.VideoCartRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VideoCartService {
    @Autowired
    private VideoCartRepository videoCartRepository;
    @Autowired
    private BrandRepository brandRepository;


    public ApiResponse getVideoCart(Integer id) {
        Optional<VideoCart> optionalVideoCart = videoCartRepository.findById(id);
        return optionalVideoCart.map(videoCart -> new ApiResponse("VideoCart found", true, videoCart)).orElseGet(() ->
                new ApiResponse("Video Cart not found", false));
    }

    public List<VideoCart> getVideoCarts() {
        return videoCartRepository.findAll();
    }

    public ApiResponse addVideoCart(VideoCartDto videoCartDto) {
        Optional<Brand> optionalBrand = brandRepository.findById(videoCartDto.getBrandId());
        if (!optionalBrand.isPresent()) return new ApiResponse("Brand not found", false);

        boolean exists = videoCartRepository.existsByNameAndBrandIdAndRamSize(videoCartDto.getName(), videoCartDto.getBrandId(), videoCartDto.getRamSize());
        if (exists) return new ApiResponse("Such a Video Cart exists", false);

        VideoCart videoCart = new VideoCart();
        videoCart.setRamSize(videoCartDto.getRamSize());
        videoCart.setBrand(optionalBrand.get());
        videoCart.setName(videoCartDto.getName());
        videoCart = videoCartRepository.save(videoCart);

        return new ApiResponse("Video Cart seccessfully saved", true, videoCart);
    }

    public ApiResponse editVideoCart(Integer id, VideoCartDto videoCartDto) {
        Optional<VideoCart> optionalVideoCart = videoCartRepository.findById(id);
        if (!optionalVideoCart.isPresent()) return new ApiResponse("Video Cart not found", false);

        Optional<Brand> optionalBrand = brandRepository.findById(videoCartDto.getBrandId());
        if (!optionalBrand.isPresent()) return new ApiResponse("Brand not found", false);

        boolean exists = videoCartRepository.existsByNameAndBrandIdAndRamSizeAndIdNot(videoCartDto.getName(), videoCartDto.getBrandId(), videoCartDto.getRamSize(), id);
        if (exists) return new ApiResponse("Such a Video Cart exists", false);

        VideoCart videoCart = optionalVideoCart.get();
        videoCart.setRamSize(videoCartDto.getRamSize());
        videoCart.setBrand(optionalBrand.get());
        videoCart.setName(videoCartDto.getName());
        videoCart = videoCartRepository.save(videoCart);

        return new ApiResponse("Video Cart seccessfully edited", true, videoCart);
    }

    public ApiResponse deleteVideoCart(Integer id) {
        Optional<VideoCart> optionalVideoCart = videoCartRepository.findById(id);
        if (!optionalVideoCart.isPresent()) return new ApiResponse("Video Cart not found", false);

        videoCartRepository.deleteById(id);
        return new ApiResponse("Video Cart seccessfully deleted", true);
    }
}
