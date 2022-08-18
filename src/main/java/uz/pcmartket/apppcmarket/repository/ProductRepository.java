package uz.pcmartket.apppcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pcmartket.apppcmarket.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
