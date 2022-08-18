package uz.pcmartket.apppcmarket.payload;

import lombok.Data;

@Data
public class ProcessorDto {

    private String name;

    private Integer brandId;

    private Integer numberOfCores;

    private Integer numberOfThreads;

    private Double frequency;

    private Double maxFrequency;

    private Double cache;

    private Double systemBusFrequency;

    private Double estimatedPower;

    private String about;
}
