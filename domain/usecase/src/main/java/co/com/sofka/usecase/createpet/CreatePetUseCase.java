package co.com.sofka.usecase.createpet;

import co.com.sofka.model.pet.Pet;
import co.com.sofka.model.pet.gateways.PetRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreatePetUseCase {
    private final PetRepository petRepository;

    public Mono<Pet> createPet(Pet pet){
        return petRepository.save(pet);
    }


}
