package teamyc.recordpet.domain.pet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    public CustomResponse<PetResponse> addPet(
            @RequestPart("req") PetRegisterRequest req,  // JSON 데이터
            @RequestPart("profileImage") MultipartFile profileImage) {
        return CustomResponse
                .created(petService.PetProfileSave(req, profileImage));
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
    public CustomResponse<PetUpdateResponse> updatePet(@PathVariable long id,
        @RequestPart("req") PetUpdateRequest req,
        @RequestPart("profileImage") MultipartFile profileImage){
        return CustomResponse
                .success(petService.PetProfileUpdate(id, req, profileImage));
    }

    @DeleteMapping("/{id}")
    public CustomResponse<Void> deletePet(@PathVariable Long id) {
        petService.PetProfileDelete(id);

        return CustomResponse.success(null);
    }
}
