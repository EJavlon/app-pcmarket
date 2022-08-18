package uz.pcmartket.apppcmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pcmartket.apppcmarket.entity.template.Technique;
import uz.pcmartket.apppcmarket.enums.ConnectionInterfaces;
import uz.pcmartket.apppcmarket.enums.DriveType;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "memory")
public class Memory extends Technique {

    @Column(name = "storage_capacity", nullable = false)
    private Double storageCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "drive_type", nullable = false)
    private DriveType driveType;

    @Column(name = "form_factor", length = 20)
    private String formFactor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConnectionInterfaces interfaces;
}
