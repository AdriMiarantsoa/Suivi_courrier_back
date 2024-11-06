package adri.suivi_courrier.data.repository;
import adri.suivi_courrier.data.entity.Reception;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptionRepository extends JpaRepository<Reception, String> {
    @Query("SELECT t FROM Reception t WHERE t.id_courrier.id_courrier = :id_courrier")
    Reception findByIdCourrier(@Param("id_courrier") String id_courrier);

}
