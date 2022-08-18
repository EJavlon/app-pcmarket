package uz.pcmartket.apppcmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pcmartket.apppcmarket.entity.template.Technique;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "monobloc")
@EqualsAndHashCode(callSuper = true)
public class Monobloc extends Technique {

    @OneToMany
    private List<VideoCart> videoCarts = new ArrayList<>(2);

    //Диагональ экрана
    @Column(name = "screen_diogonal", length = 20, nullable = false)
    private String screenDiagonal;

    //Разрешение экрана
    @Column(name = "screen_resolution", length = 20, nullable = false)
    private String screenResolution;

    @OneToOne
    private Processor processor;

    @OneToOne
    private Ram ram;

    @OneToOne
    private Memory memory;

    @Column(name = "operating_system", length = 20)
    private String operatingSystem;
}
