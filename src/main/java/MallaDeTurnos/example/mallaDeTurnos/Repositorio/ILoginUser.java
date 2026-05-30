package MallaDeTurnos.example.mallaDeTurnos.Repositorio;

import MallaDeTurnos.example.mallaDeTurnos.Modelo.MLoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILoginUser extends JpaRepository<MLoginUser, Integer > {
    // Para el proceso de login — buscar por email
    Optional<MLoginUser> findByUserEmail(String userEmail);
    // Para verificar si un usuario ya tiene login
//    Optional<MLoginUser> findByMUser_UserIdAndMUser_Status(Integer userId,Boolean status);
}
