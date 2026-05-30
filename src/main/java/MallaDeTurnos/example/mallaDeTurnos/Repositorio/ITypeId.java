    package MallaDeTurnos.example.mallaDeTurnos.Repositorio;


    import MallaDeTurnos.example.mallaDeTurnos.Modelo.MTypeId;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    import java.util.List;

    @Repository
    public interface ITypeId extends JpaRepository<MTypeId,Integer> {
        List<MTypeId> findByStatus(Boolean status);
    }
