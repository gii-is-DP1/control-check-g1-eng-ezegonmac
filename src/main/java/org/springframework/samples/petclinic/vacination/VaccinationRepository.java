package org.springframework.samples.petclinic.vacination;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VaccinationRepository extends CrudRepository<Vaccination, Integer>{

    List<Vaccination> findAll();
    
    @Query("SELECT V FROM Vaccine V")
    List<Vaccine> findAllVaccines();
    
    Optional<Vaccination> findById(int id);

    @Query("SELECT V FROM Vaccine V WHERE V.name = :name")
    Vaccine getVaccine(String name);
    
    Vaccination save(Vaccination v);
}
