package uz.pcmartket.apppcmarket.payload;

import lombok.Data;

@Data
public class CategoryDto {

    private Integer id;

    private Integer parentId;

    private String name;
}
