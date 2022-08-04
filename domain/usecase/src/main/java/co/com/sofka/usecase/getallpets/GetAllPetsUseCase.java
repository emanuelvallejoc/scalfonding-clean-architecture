package co.com.sofka.usecase.getallpets;

import co.com.sofka.model.pet.Pet;
import co.com.sofka.model.pet.gateways.PetRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllPetsUseCase {

    private final PetRepository petRepository;

    public Flux<Pet> getAllPets(){
        return petRepository.findAll();
    }
}
