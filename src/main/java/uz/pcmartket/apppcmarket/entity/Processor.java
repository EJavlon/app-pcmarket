package uz.pcmartket.apppcmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pcmartket.apppcmarket.entity.template.Technique;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "processor")
@EqualsAndHashCode(callSuper = true)
public class Processor extends Technique {
    //Количество ядер
    private Integer numberOfCores;

    //Количество потоков
    private Integer numberOfThreads;

    //частота
    private Double frequency;

    //max частота
    private Double maxFrequency;

    //Кэш-память
    private Double cache;

    //Частота системной шины
    private Double systemBusFrequency;

    //Расчетная мощность
    private Double estimatedPower;

    @Column(length = 80)
    private String about;
}
