package teamyc.recordpet.domain.pet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamyc.recordpet.domain.pet.dto.PetRegisterRequest;
import teamyc.recordpet.domain.pet.dto.PetResponse;
import teamyc.recordpet.domain.pet.dto.PetUpdateRequest;
import teamyc.recordpet.domain.pet.service.PetService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    public ResponseEntity<PetResponse> addPet(@RequestBody PetRegisterRequest req) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(petService.save(req));
    }

    @GetMapping
    public ResponseEntity<List<PetResponse>> findAllPets() {
        return ResponseEntity
                .ok()
                .body(petService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetResponse> findPetById(@PathVariable Long id) {
        PetResponse res = petService.findById(id);

        return ResponseEntity.ok()
                .body(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetResponse> updatePet(@PathVariable Long id, @RequestBody PetUpdateRequest req) {
        PetResponse res = petService.update(id, req);

        return ResponseEntity.ok()
                .body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.delete(id);

        return ResponseEntity.ok()
                .build();
    }
}
