package uz.pcmartket.apppcmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pcmartket.apppcmarket.entity.template.Technique;
import uz.pcmartket.apppcmarket.enums.Matritsa;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "monitor")
@EqualsAndHashCode(callSuper = true)
public class Monitor extends Technique {

    @Column(name = "screen_diogonal",length = 50)
    private String screenDiagonal;

    @Column(name = "screen_resoliton",length = 50)
    private String screenResolution;

    @Column(name = "video_connectors",length = 50)
    private String videoConnectors;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="matritsa")
    private Matritsa matritsa;

    @Column(name = "curved_scren")
    private Boolean curvedScreen;

    @Column(name = "screen_refresh_rate")
    private Double screenRefreshRate;

    @Column(name = "response_time")
    private Double responseTime;
}
