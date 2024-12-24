package teamyc.recordpet.domain.pet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@NoArgsConstructor
public class PetUpdateRequest {
    private String name;
    private int age;
    @JsonProperty("isNeutered")
    private boolean isNeutered;
    private String photoUrl;

    @Builder
    public PetUpdateRequest(String name, int age, boolean isNeutered, String photoUrl) {
        this.name = name;
        this.age = age;
        this.isNeutered = isNeutered;
        this.photoUrl = photoUrl;
    }

}
