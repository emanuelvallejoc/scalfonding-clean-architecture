package co.com.sofka.usecase.getpetid;

import co.com.sofka.model.pet.Pet;
import co.com.sofka.model.pet.gateways.PetRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetPetIdUseCase {

    private final PetRepository petRepository;

    public Mono<Pet> getPetById(String id){
        return petRepository.findById(id)
                .switchIfEmpty(Mono.error(new ClassNotFoundException()));
    }
}
