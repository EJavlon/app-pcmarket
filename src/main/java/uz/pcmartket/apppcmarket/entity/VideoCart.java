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
@Table(name = "video_cart")
@EqualsAndHashCode(callSuper = true)
public class VideoCart extends Technique {

    @Column(nullable = false)
    private Integer ramSize;
}
