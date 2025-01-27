package com.example.concessionaria_campos.repository.specification;

import com.example.concessionaria_campos.entity.Vehicle;
import org.springframework.data.jpa.domain.Specification;

public class VehicleEspecification {

    public static Specification<Vehicle> withBrand(Long brandId) {
        return (root, query, criteriaBuilder) ->
            brandId != null ? criteriaBuilder.equal(root.get("brand_id"), brandId) : null;
    }

    public static Specification<Vehicle> withModel(String model) {
        return (root, query, criteriaBuilder) ->
                model != null ? criteriaBuilder.equal(root.get("model"), "%" + model + "%") : null;
    }
    public static Specification<Vehicle> withYear(Integer year) {
        return (root, query, criteriaBuilder) ->
                year != null ? criteriaBuilder.equal(root.get("year"), year) : null;
    }

    public static Specification<Vehicle> withValue(Double maxValue) {
        return (root, query, criteriaBuilder) ->
                maxValue != null ? criteriaBuilder.between(root.get("value"), 0.0, maxValue) : null;
    }

    public static Specification<Vehicle> withCategory(Long categoryId) {
        return (root, query, criteriaBuilder) ->
                categoryId != null ? criteriaBuilder.equal(root.get("category_id"), categoryId) : null;
    }
}
