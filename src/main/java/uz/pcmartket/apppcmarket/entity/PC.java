package uz.pcmartket.apppcmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pcmartket.apppcmarket.entity.template.Technique;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pc")
@EqualsAndHashCode(callSuper = true)
public class PC extends Technique {

    @OneToOne
    private Motherboard motherboard;

    @OneToOne
    private Processor processor;

    @OneToOne
    private Ram ram;

    @OneToOne
    private PowerUnit powerUnit;

    @OneToOne
    private Memory memory;

    @OneToMany
    private List<VideoCart> videoCarts = new ArrayList<>(1);

    @Column(length = 20)
    private String operatingSystem;
}
