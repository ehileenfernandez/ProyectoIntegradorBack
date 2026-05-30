package MallaDeTurnos.example.mallaDeTurnos.Repositorio;

import MallaDeTurnos.example.mallaDeTurnos.Modelo.MUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUser extends JpaRepository<MUser,Integer> {
    List<MUser> findByTeamLeaderIdAndStatus(Integer teamLeaderId,Boolean status);
    List<MUser> findByStatusAndRoleIdIn(Boolean status, List<Integer> roleId);
}
