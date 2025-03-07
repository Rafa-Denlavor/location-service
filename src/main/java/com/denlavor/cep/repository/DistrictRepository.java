package com.denlavor.cep.repository;

//import org.springframework.data.jpa.repository.JpaRepository;

import com.denlavor.cep.domain.Distrito;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface DistrictRepository extends CrudRepository<Distrito, UUID> {
}

