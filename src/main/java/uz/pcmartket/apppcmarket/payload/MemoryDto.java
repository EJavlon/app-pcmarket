package uz.pcmartket.apppcmarket.payload;

import lombok.Data;

@Data
public class MemoryDto {

    private String name;

    private Integer brandId;

    private Double storageCapacity;

    private String driveType;

    private String formFactor;

    private String interfaces;
}
