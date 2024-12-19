package teamyc.recordpet.domain.pet.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamyc.recordpet.domain.pet.dto.PetResponse;
import teamyc.recordpet.domain.pet.dto.PetUpdateResponse;
import teamyc.recordpet.domain.pet.entity.Pet;
import teamyc.recordpet.domain.pet.dto.PetUpdateRequest;
import teamyc.recordpet.domain.pet.dto.PetRegisterRequest;
import teamyc.recordpet.domain.pet.repository.PetRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PetService {

    private final PetRepository petRepository;

    //조회
    public List<PetResponse> findAll(){
        return petRepository.findAll().stream()
                .map(PetResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public PetResponse findById(Long id){
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found " + id));
        return PetResponse.fromEntity(pet);
    }
    //등록
    public PetResponse save(PetRegisterRequest req){
        Pet savedPet = petRepository.save(req.toEntity());

        return PetResponse.fromEntity(savedPet);
    }
    //수정
    @Transactional
    public PetUpdateResponse update(long id, PetUpdateRequest req){
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found " + id));

        pet.update(req.getName(), req.getAge(),req.isVaccinated(), req.getPhotoUrl());


        return PetUpdateResponse.fromEntity(pet);
    }
    //삭제
    public void delete(long id){
        petRepository.deleteById(id);
    }
}
