package uz.pcmartket.apppcmarket.payload;

import lombok.Data;

@Data
public class MonoblocDto {

    private String name;

    private Integer brandId;

    private Integer []videoCartsId;

    private String screenDiagonal;

    private String screenResolution;

    private Integer processorId;

    private Integer ramId;

    private Integer memoryId;

    private String operatingSystem;
}
