package uz.pcmartket.apppcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pcmartket.apppcmarket.entity.Brand;
import uz.pcmartket.apppcmarket.projection.BrandProjection;

@RepositoryRestResource(path = "brand",collectionResourceRel = "brands",excerptProjection = BrandProjection.class)
public interface BrandRepository extends JpaRepository<Brand,Integer> {
}
