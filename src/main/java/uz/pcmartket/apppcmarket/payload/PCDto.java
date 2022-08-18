package uz.pcmartket.apppcmarket.payload;

import lombok.Data;

@Data
public class PCDto {

    private String name;

    private Integer brandId;

    private Integer motherboardId;

    private Integer processorId;

    private Integer ramId;

    private Integer powerUnitId;

    private Integer memoryId;

    private Integer []videoCartsId;

    private String operatingSystem;
}
