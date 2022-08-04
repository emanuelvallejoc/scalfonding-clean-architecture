package co.com.sofka.api;

import co.com.sofka.model.pet.Pet;
import co.com.sofka.usecase.createpet.CreatePetUseCase;
import co.com.sofka.usecase.getallpets.GetAllPetsUseCase;
import co.com.sofka.usecase.getpetid.GetPetIdUseCase;
import co.com.sofka.usecase.updatepet.UpdatePetUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class Handler {

    private final CreatePetUseCase createPetUseCase;

    private final GetAllPetsUseCase getAllPetsUseCase;

    private final GetPetIdUseCase getPetIdUseCase;

    private final UpdatePetUseCase updatePetUseCase;

    public Mono<ServerResponse> createPet(ServerRequest serverRequest) {
       return serverRequest.bodyToMono(Pet.class)
               .flatMap(createPetUseCase::createPet)
               .flatMap(pet -> ServerResponse.ok()
                       .contentType(MediaType.APPLICATION_JSON)
                       .bodyValue(pet));

    }


    public Mono<ServerResponse> getAllPet(ServerRequest serverRequest) {

        Flux<Pet> pets = getAllPetsUseCase.getAllPets();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(pets, Pet.class);

    }


    public Mono<ServerResponse> getPet(ServerRequest serverRequest) {

        var idPet = serverRequest.pathVariable("idPet");

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getPetIdUseCase.getPetById(idPet), Pet.class);


    }

    public Mono<ServerResponse> updatePet(ServerRequest serverRequest) {

        var idPet = serverRequest.pathVariable("idPet");

        return serverRequest.bodyToMono(Pet.class)
                .flatMap(pet -> updatePetUseCase.updatePet(idPet, pet))
                .flatMap(pet -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(pet))
                .onErrorResume(e->Mono.just("Error"+e.getMessage())
                        .flatMap(s -> ServerResponse.badRequest()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(s)));

    }

}
