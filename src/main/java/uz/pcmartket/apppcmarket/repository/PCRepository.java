package uz.pcmartket.apppcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pcmartket.apppcmarket.entity.PC;

public interface PCRepository extends JpaRepository<PC,Integer> {
}
