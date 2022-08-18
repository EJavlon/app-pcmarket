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
@Table(name = "notebook")
@EqualsAndHashCode(callSuper = true)
public class Notebook extends Technique {

    @OneToMany
    private List<VideoCart> videoCarts = new ArrayList<>(1);

    //Диагональ экрана
    @Column(name = "screen_diagonal", nullable = false, length = 20)
    private String screenDiagonal;

    //Разрешение экрана
    @Column(name = "screen_resolution", nullable = false, length = 20)
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
