package adri.suivi_courrier.data.repository;
import adri.suivi_courrier.data.entity.Reception;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptionRepository extends JpaRepository<Reception, String> {

}
