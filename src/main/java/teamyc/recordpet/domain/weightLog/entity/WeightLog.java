package teamyc.recordpet.domain.weightLog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import teamyc.recordpet.domain.pet.entity.Pet;

import java.util.Date;

@Entity
@Getter
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

}
