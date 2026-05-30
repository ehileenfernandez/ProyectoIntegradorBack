package MallaDeTurnos.example.mallaDeTurnos.Repositorio;

import MallaDeTurnos.example.mallaDeTurnos.Modelo.MRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoles extends JpaRepository<MRoles,Integer > {
    @Query("SELECT u FROM MRoles u WHERE "+
            "(:addShift IS NULL OR u.addShift =:addShift) AND "+
            "(:addLateShift IS NULL OR u.addLateShift =:addLateShift) AND "+
            "(:addChanges IS NULL OR u.addChanges=:addChanges) AND "+
            "(:addFullChanges is NULL OR u.addFullChanges=:addFullChanges)")
    List<MRoles>findRolesByAddShiftAndAddLateShiftAndAddChangesAndAddFullChanges(
            @Param("addShift") Boolean addShift,
            @Param("addLateShift") Boolean addLateShift,
            @Param("addChanges") Boolean addChanges,
            @Param("addFullChanges") Boolean addFullChanges
            );
}
