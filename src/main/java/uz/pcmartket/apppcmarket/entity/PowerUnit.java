package uz.pcmartket.apppcmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pcmartket.apppcmarket.entity.template.Technique;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "power_unit")
@EqualsAndHashCode(callSuper = true)
public class PowerUnit extends Technique {
}
