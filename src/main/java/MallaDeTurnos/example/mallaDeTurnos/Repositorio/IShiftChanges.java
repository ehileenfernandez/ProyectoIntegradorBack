package MallaDeTurnos.example.mallaDeTurnos.Repositorio;


import MallaDeTurnos.example.mallaDeTurnos.Modelo.MShiftChanges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IShiftChanges extends JpaRepository<MShiftChanges,Integer> {

    List<MShiftChanges>findByMShiftSchedules_ShiftIdIn(
            List<Integer> shiftIds
    );
    List<MShiftChanges> findByShiftDateBetweenAndCampaignId(
            LocalDate mondayDate,
            LocalDate sundayDate,
            Integer campaignId
    );
}
