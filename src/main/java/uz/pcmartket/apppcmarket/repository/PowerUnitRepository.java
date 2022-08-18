package uz.pcmartket.apppcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pcmartket.apppcmarket.entity.PowerUnit;

public interface PowerUnitRepository extends JpaRepository<PowerUnit,Integer> {

    boolean existsByNameAndBrandId(String name, Integer brand_id);
}
