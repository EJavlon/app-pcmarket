package uz.pcmartket.apppcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pcmartket.apppcmarket.entity.Order;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.OrderDto;
import uz.pcmartket.apppcmarket.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOrder(@PathVariable Integer id){
        ApiResponse apiResponse = orderService.getOrder(id);
        return ResponseEntity.status(apiResponse.getSuccess()?200:409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders(){
        return ResponseEntity.status(orderService.getOrders() != null ? 200 : 409).body(orderService.getOrders());
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addOrder(@RequestBody OrderDto orderDto){
        ApiResponse apiResponse = orderService.addOrder(orderDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editOrder(@PathVariable Integer id, @RequestBody OrderDto orderDto){
        ApiResponse apiResponse = orderService.editOrder(id,orderDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable Integer id){
        ApiResponse apiResponse = orderService.deleteOrder(id);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(apiResponse);
    }
}
