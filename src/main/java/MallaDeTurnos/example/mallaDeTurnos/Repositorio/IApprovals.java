package MallaDeTurnos.example.mallaDeTurnos.Repositorio;

import MallaDeTurnos.example.mallaDeTurnos.Modelo.MApprovals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IApprovals extends JpaRepository<MApprovals, Integer> {

}
