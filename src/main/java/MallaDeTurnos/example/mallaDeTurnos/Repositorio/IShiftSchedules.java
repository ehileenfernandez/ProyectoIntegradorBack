package MallaDeTurnos.example.mallaDeTurnos.Repositorio;

import MallaDeTurnos.example.mallaDeTurnos.Modelo.MShiftSchedules;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IShiftSchedules extends JpaRepository<MShiftSchedules,Integer> {
    List<MShiftSchedules>findByMUser_UserIdInAndDateBetween (List<Integer> userIDs,LocalDate mondayDate, LocalDate sundayDate);
}
