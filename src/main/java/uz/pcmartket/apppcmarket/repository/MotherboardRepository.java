package uz.pcmartket.apppcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pcmartket.apppcmarket.entity.Brand;
import uz.pcmartket.apppcmarket.entity.Motherboard;

public interface MotherboardRepository extends JpaRepository<Motherboard,Integer> {

    boolean existsByNameAndBrandIdAndAmountMemorySlots(String name, Integer brand_id, Integer amountMemorySlots);

    boolean existsByNameAndBrandIdAndAmountMemorySlotsAndIdNot(String name, Integer brand_id, Integer amountMemorySlots, Integer id);
}
