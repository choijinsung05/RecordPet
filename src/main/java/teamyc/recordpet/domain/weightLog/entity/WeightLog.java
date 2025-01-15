package teamyc.recordpet.domain.weightLog.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamyc.recordpet.domain.pet.entity.Pet;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class WeightLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    private Double weight;

    @Temporal(TemporalType.DATE)
    private Date date = new Date();

    @Builder
    WeightLog(Pet pet, Double weight, Date date){
        this.pet = pet;
        this.weight = weight;
        this.date = date;
    }

}
