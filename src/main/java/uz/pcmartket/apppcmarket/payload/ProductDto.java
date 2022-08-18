package uz.pcmartket.apppcmarket.payload;

import lombok.Data;

import java.sql.Date;

@Data
public class ProductDto {

    private String name;

    private Integer categoryId;

    private Integer []imagesId;

    private Double price;

    private Date guarantee;

    private Integer memoryId;

    private Integer monitorId;

    private Integer monoblocId;

    private Integer motherboardId;

    private Integer notebookId;

    private Integer pcId;

    private Integer powerUnitId;

    private Integer processorId;

    private Integer ramId;

    private Integer videoCartId;

    private Integer amount;
}
