package uz.pcmartket.apppcmarket.payload;

import lombok.Data;

@Data
public class MotherboardDto {

    private String name;

    private Integer brandId;

    private String chipset;

    private Integer amountMemorySlots;

    private String coket;

    private Boolean wifiAdapter;
}
