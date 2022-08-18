package uz.pcmartket.apppcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pcmartket.apppcmarket.entity.Brand;
import uz.pcmartket.apppcmarket.entity.Memory;
import uz.pcmartket.apppcmarket.enums.ConnectionInterfaces;
import uz.pcmartket.apppcmarket.enums.DriveType;

public interface MemoryRepository extends JpaRepository<Memory,Integer> {
}
