package uz.pcmartket.apppcmarket.entity.template;

import lombok.Data;
import uz.pcmartket.apppcmarket.entity.Brand;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class Technique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    private Brand brand;

}
