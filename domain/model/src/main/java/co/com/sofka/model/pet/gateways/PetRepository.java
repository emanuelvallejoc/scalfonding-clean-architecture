package co.com.sofka.model.pet.gateways;

import co.com.sofka.model.pet.Pet;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PetRepository {
    Mono<Pet> save(Pet pet);
    Flux<Pet> findAll();
    Mono<Pet> findById(String id);

}
