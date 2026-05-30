package MallaDeTurnos.example.mallaDeTurnos.Repositorio;


import MallaDeTurnos.example.mallaDeTurnos.Modelo.MCampaigns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICampaigns extends JpaRepository<MCampaigns,Integer> {

}
