package uz.pcmartket.apppcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pcmartket.apppcmarket.entity.Order;
import uz.pcmartket.apppcmarket.entity.Product;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.OrderDto;
import uz.pcmartket.apppcmarket.repository.OrderRepository;
import uz.pcmartket.apppcmarket.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    public ApiResponse getOrder(Integer id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.map(order -> new ApiResponse("Order found", true, order)).orElseGet(() ->
                new ApiResponse("Order not found", false));
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public ApiResponse addOrder(OrderDto orderDto) {
        if (!checkOrder(orderDto)) return new ApiResponse("Error", false);

        List<Product> products = setProduct(orderDto.getProductId());
        if (Objects.isNull(products)) return new ApiResponse("Prodcut not found", false);

        Order order = new Order();
        order.setProducts(products);
        order.setAddress(orderDto.getAddress());
        order.setEmail(orderDto.getEmail());
        order.setFirstName(orderDto.getFirstName());
        order.setLastName(orderDto.getLastName());
        order.setTell(orderDto.getTell());
        order.setOrderPrice(getOrderPrice(products));
        order = orderRepository.save(order);

        return new ApiResponse("Order seccessfully saved", true, order);
    }

    public ApiResponse editOrder(Integer id, OrderDto orderDto) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) return new ApiResponse("Order not found", false);

        if (!checkOrder(orderDto)) return new ApiResponse("Error", false);

        List<Product> products = setProduct(orderDto.getProductId());
        if (Objects.isNull(products)) return new ApiResponse("Prodcut not found", false);

        Order order = optionalOrder.get();
        order.setProducts(products);
        order.setAddress(orderDto.getAddress());
        order.setEmail(orderDto.getEmail());
        order.setFirstName(orderDto.getFirstName());
        order.setLastName(orderDto.getLastName());
        order.setTell(orderDto.getTell());
        order.setOrderPrice(getOrderPrice(products));
        order = orderRepository.save(order);

        return new ApiResponse("Order seccessfully edited", true, order);
    }

    public ApiResponse deleteOrder(Integer id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (!optionalOrder.isPresent()) return new ApiResponse("Order not found", false);

        return new ApiResponse("Order seccessfully deleted", true);
    }

    private boolean checkOrder(OrderDto orderDto) {
        if (Objects.isNull(orderDto.getAddress()) || orderDto.getAddress().isEmpty()) return false;
        if (Objects.isNull(orderDto.getEmail()) || orderDto.getEmail().isEmpty()) return false;
        if (Objects.isNull(orderDto.getFirstName()) || orderDto.getFirstName().isEmpty()) return false;
        if (Objects.isNull(orderDto.getLastName()) || orderDto.getLastName().isEmpty()) return false;
        if (Objects.isNull(orderDto.getTell()) || orderDto.getTell().isEmpty()) return false;
        return true;
    }

    private List<Product> setProduct(Integer[] productId) {
        List<Product> products = new ArrayList<>(1);
        for (Integer id : productId) {
            Optional<Product> optionalProduct = productRepository.findById(id);
            if (!optionalProduct.isPresent()) return null;
            products.add(optionalProduct.get());
        }
        return products;
    }

    private Double getOrderPrice(List<Product> products) {
        Double price = 0d;
        for (Product product : products) price += product.getPrice();
        return price;
    }
}
