package co.com.sofka.usecase.updatepet;

import co.com.sofka.model.pet.Pet;
import co.com.sofka.model.pet.gateways.PetRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdatePetUseCase {

    private final PetRepository petRepository;

    public Mono<Pet> updatePet(String id, Pet pet){
        petRepository.findById(id)
                .switchIfEmpty(Mono.error(new ClassNotFoundException()));
        return petRepository.save(pet);
    }
}
