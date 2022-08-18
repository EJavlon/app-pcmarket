package uz.pcmartket.apppcmarket.payload;

import lombok.Data;

@Data
public class MonitorDto {

    private String name;

    private Integer brandId;

    private String screenDiagonal;

    private String screenResolution;

    private String videoConnectors;

    private Integer matritsaId;

    private Boolean curvedScreen;

    private Double screenRefreshRate;

    private Double responseTime;
}
