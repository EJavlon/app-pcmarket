package uz.pcmartket.apppcmarket.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pcmartket.apppcmarket.entity.Brand;

@Projection(types = Brand.class)
public interface BrandProjection {
    Integer getId();

    String getName();
}
