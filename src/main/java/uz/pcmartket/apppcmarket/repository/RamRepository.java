package uz.pcmartket.apppcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pcmartket.apppcmarket.entity.Ram;

public interface RamRepository extends JpaRepository<Ram,Integer> {

    boolean existsByNameAndBrandIdAndSizeAndIdNot(String name, Integer brand_id, String size, Integer id);

    boolean existsByNameAndBrandIdAndSize(String name, Integer brand_id, String size);
}
