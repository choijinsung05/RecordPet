package teamyc.recordpet.domain.pet.dto;

import lombok.Getter;

@Getter
public class PetUpdateRequest {
    private String name;
    private int age;
    private boolean isVaccinated;
    private String photoUrl;
}
