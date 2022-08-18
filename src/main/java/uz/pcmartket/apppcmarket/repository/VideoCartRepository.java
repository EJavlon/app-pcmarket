package uz.pcmartket.apppcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pcmartket.apppcmarket.entity.VideoCart;

public interface VideoCartRepository extends JpaRepository<VideoCart,Integer> {

    boolean existsByNameAndBrandIdAndRamSize(String name, Integer brand_id, Integer ramSize);

    boolean existsByNameAndBrandIdAndRamSizeAndIdNot(String name, Integer brand_id, Integer ramSize, Integer id);
}
