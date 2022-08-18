package uz.pcmartket.apppcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pcmartket.apppcmarket.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
