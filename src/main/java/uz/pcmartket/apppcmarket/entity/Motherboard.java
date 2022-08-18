package uz.pcmartket.apppcmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pcmartket.apppcmarket.entity.template.Technique;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "motherboard")
@EqualsAndHashCode(callSuper = true)
public class Motherboard extends Technique {

    //Материнская плата
    @Column(length = 20)
    private String chipset;

    //Количество слотов памяти
    private Integer amountMemorySlots;

    @Column(length = 20)
    private String coket;

    @Column(name = "wifi_adapter")
    private Boolean wifiAdapter;
}
