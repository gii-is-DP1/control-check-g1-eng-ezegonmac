package org.springframework.samples.petclinic.vacination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VaccinationService {

    private VaccinationRepository vaccinationRepository;

    @Autowired
	public VaccinationService(VaccinationRepository vaccinationRepository) {
		this.vaccinationRepository = vaccinationRepository;
	}

    public List<Vaccination> getAll(){
        return vaccinationRepository.findAll();
    }

    public List<Vaccine> getAllVaccines(){
        return null;
    }

    public Vaccine getVaccine(String name) {
        return vaccinationRepository.getVaccine(name);
    }

    @Transactional(rollbackFor = UnfeasibleVaccinationException.class)
    public Vaccination save(Vaccination v) throws UnfeasibleVaccinationException {
        Pet pet = v.getVaccinatedPet();
        PetType petType = pet.getType();
        PetType petVaccineType = v.getVaccine().getPetType(); 

        if(petType != petVaccineType) {
            throw new UnfeasibleVaccinationException();
        } else {
            vaccinationRepository.save(v);
        }      
        return v;
    }

    
}
