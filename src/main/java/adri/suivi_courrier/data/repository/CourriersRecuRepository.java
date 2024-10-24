package adri.suivi_courrier.data.repository;

import adri.suivi_courrier.data.entity.CourriersRecu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CourriersRecuRepository extends JpaRepository<CourriersRecu, String> {
    
    @Query(value = "SELECT * FROM v_courriers_recu WHERE dept_destinataire = :dept_destinataire", nativeQuery = true)
    List<CourriersRecu> getCourriersRecu(@Param("dept_destinataire") String dept_destinataire);

    @Query(value = "SELECT * FROM v_courriers_recu WHERE dept_destinataire = :id_departement AND date_traitement BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<CourriersRecu> findByDepartementAndDateBetween(@Param("id_departement") String id_departement, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT * FROM v_courriers_recu WHERE dept_destinataire = :id_departement AND date_traitement BETWEEN :startDate AND :endDate AND LOWER(TRIM(expediteur)) = LOWER(TRIM(:expediteur))", nativeQuery = true)
    List<CourriersRecu> findByDepartementAndDateBetweenAndExpeditor(@Param("id_departement") String id_departement, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,@Param("expediteur") String expediteur);

    @Query(value = "SELECT * FROM v_courriers_recu WHERE dept_destinataire = :id_departement AND LOWER(TRIM(expediteur)) = LOWER(TRIM(:expediteur))", nativeQuery = true)
    List<CourriersRecu> findByDepartementAndExpeditor(@Param("id_departement") String id_departement,@Param("expediteur") String expediteur);
}
