package adri.suivi_courrier.data.repository;

import adri.suivi_courrier.data.entity.Courrier;
import adri.suivi_courrier.data.entity.Departement;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourrierRepository extends JpaRepository<Courrier, String> {

    @Query("SELECT d FROM Departement d WHERE d.id_departement = :id_departement")
    Departement findDepartementById(@Param("id_departement") String id_departement);

    @Query("SELECT d FROM Departement d WHERE d.nom_departement = :nom_departement")
    Departement findDepartementByName(@Param("nom_departement") String nom_departement);

    @Modifying
    @Query("UPDATE Courrier c SET c.date_reception = :date_reception, c.nom_courrier = :nom_courrier, c.description = :description, c.expediteur = :expediteur, c.dept_destinataire = :dept_destinataire WHERE c.id_courrier = :id_courrier")
    void updateCourrier(@Param("id_courrier") String id_courrier, @Param("date_reception") Date date_reception, @Param("nom_courrier") String nom_courrier, @Param("description") String description, @Param("expediteur") String expediteur,@Param("dept_destinataire") Departement departement);

    @Modifying
    @Query("UPDATE Courrier c SET c.dept_destinataire = :dept_destinataire, c.statut = :statut WHERE c.id_courrier = :id_courrier")
    void Transfer(@Param("dept_destinataire") Departement departement,@Param("statut") String statut,@Param("id_courrier") String id_courrier);

    // List<Courrier> findByStatut(String status);
    @Query("SELECT c FROM Courrier c WHERE c.statut = :status ORDER BY c.id_courrier ASC")
    List<Courrier> findByStatut(@Param("status") String status);
    
    //@Query("SELECT c FROM Courrier c WHERE c.dept_destinataire.id_departement = :id_departement AND c.statut = 'enregistre'")
    @Query(value = "SELECT * FROM v_courriers_non_receptionnes c WHERE c.dept_destinataire = :id_departement", nativeQuery = true)
    List<Courrier> findByDepartementIdAndStatut(@Param("id_departement") String id_departement);

    @Query(value = "SELECT * FROM v_notification c WHERE c.dept_destinataire = :id_departement", nativeQuery = true)
    List<Courrier> findCourrierAlerte(@Param("id_departement") String id_departement);

    //statistique
    @Query(value = "SELECT year , month, dept_destinataire, SUM(courrier_count) AS courrier_count FROM v_courrier_par_mois WHERE dept_destinataire = :dept_destinataire AND year = :year  GROUP BY year, month, dept_destinataire", nativeQuery = true)
    List<Object[]> findByDeptDestinataireAndMonth(@Param("dept_destinataire") String deptDestinataire, @Param("year") int year);

    //stat generale mois
    @Query(value = "SELECT year, month, dept_destinataire, SUM(courrier_count) AS courrier_count FROM v_courrier_par_mois WHERE EXTRACT(YEAR FROM CURRENT_DATE) = year AND dept_destinataire = :dept_destinataire GROUP BY year, month, dept_destinataire", nativeQuery = true)
    List<Object[]> findCurrentYearStatistics(@Param("dept_destinataire") String dept_destinaire);

    @Query("SELECT c FROM Courrier c WHERE c.id_courrier = :id_courrier AND c.dept_destinataire = :dept_destinataire")
    Courrier findByIdAndDept(@Param("id_courrier") String id_courrier,@Param("dept_destinataire") Departement dept_destinataire);

    @Query("SELECT c FROM Courrier c WHERE c.id_courrier = :id_courrier")
    Optional<Courrier> findById(@Param("id_courrier") String id_courrier);

}

