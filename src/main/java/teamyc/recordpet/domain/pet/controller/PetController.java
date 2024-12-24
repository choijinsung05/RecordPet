package teamyc.recordpet.domain.pet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import teamyc.recordpet.domain.pet.dto.PetRegisterRequest;
import teamyc.recordpet.domain.pet.dto.PetResponse;
import teamyc.recordpet.domain.pet.dto.PetUpdateRequest;
import teamyc.recordpet.domain.pet.dto.PetUpdateResponse;
import teamyc.recordpet.domain.pet.service.PetService;
import teamyc.recordpet.global.exception.CustomResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    public CustomResponse<PetResponse> addPet(@RequestBody PetRegisterRequest req) {
        return CustomResponse
                .created(petService.PetProfileSave(req));
    }

    @GetMapping
    public CustomResponse<List<PetResponse>> findAllPets() {
        return CustomResponse
                .success(petService.PetProfileFindAll());
    }

    @GetMapping("/{id}")
    public CustomResponse<PetResponse> findPetById(@PathVariable Long id) {
        PetResponse res = petService.PetProfileFindById(id);

        return CustomResponse
                .success(res);
    }

    @PutMapping("/{id}")
    public CustomResponse<PetUpdateResponse> updatePet(@PathVariable Long id, @RequestBody PetUpdateRequest req) {
        PetUpdateResponse res = petService.PetProfileUpdate(id, req);

        return CustomResponse
                .success(res);
    }

    @DeleteMapping("/{id}")
    public CustomResponse<Void> deletePet(@PathVariable Long id) {
        petService.PetProfileDelete(id);

        return CustomResponse.success(null);
    }
}
