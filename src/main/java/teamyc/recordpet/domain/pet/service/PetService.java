package teamyc.recordpet.domain.pet.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamyc.recordpet.domain.pet.dto.PetRegisterRequest;
import teamyc.recordpet.domain.pet.dto.PetResponse;
import teamyc.recordpet.domain.pet.dto.PetUpdateRequest;
import teamyc.recordpet.domain.pet.dto.PetUpdateResponse;
import teamyc.recordpet.domain.pet.entity.Pet;
import teamyc.recordpet.domain.pet.repository.PetRepository;
import teamyc.recordpet.global.exception.GlobalException;

import java.util.List;
import java.util.stream.Collectors;

import static teamyc.recordpet.global.exception.ResultCode.NOT_FOUND_PET_PROFILE;

@RequiredArgsConstructor
@Service
public class PetService {

    private final PetRepository petRepository;

    //조회
    public List<PetResponse> PetProfileFindAll(){
        return petRepository.findAll().stream()
                .map(PetResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public PetResponse PetProfileFindById(Long id){
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new GlobalException(NOT_FOUND_PET_PROFILE));
        return PetResponse.fromEntity(pet);
    }

    //등록
    public PetResponse PetProfileSave(PetRegisterRequest req){
        Pet savedPet = petRepository.save(req.toEntity());

        return PetResponse.fromEntity(savedPet);
    }
    //수정
    @Transactional
    public PetUpdateResponse PetProfileUpdate(long id, PetUpdateRequest req){
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new GlobalException(NOT_FOUND_PET_PROFILE));

        pet.update(req.getName(), req.getAge(), req.isNeutered(), req.getPhotoUrl());


        return PetUpdateResponse.fromEntity(pet);
    }
    //삭제
    public void PetProfileDelete(long id){
        petRepository.deleteById(id);
    }
}
