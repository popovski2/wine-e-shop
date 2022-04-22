package com.petarangela.wineeshop.repository;

import com.petarangela.wineeshop.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
