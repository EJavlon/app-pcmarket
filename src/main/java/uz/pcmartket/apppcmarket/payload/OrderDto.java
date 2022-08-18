package uz.pcmartket.apppcmarket.payload;

import lombok.Data;

@Data
public class OrderDto {

    private String firstName;

    private String lastName;

    private String address;

    private String tell;

    private String email;

    private Integer []productId;

    private Double orderPrice;
}
