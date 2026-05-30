package MallaDeTurnos.example.mallaDeTurnos.Repositorio;


import MallaDeTurnos.example.mallaDeTurnos.Modelo.MAbsenceReasons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAbsenceReasons extends JpaRepository<MAbsenceReasons, Integer> {

}
