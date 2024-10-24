package adri.suivi_courrier.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import adri.suivi_courrier.data.entity.Departement;
import adri.suivi_courrier.data.entity.UtilisateurDepartement;

public interface UtilisateurDepartementRepository extends JpaRepository<UtilisateurDepartement, Long> {

    @Query("SELECT ud.departement FROM UtilisateurDepartement ud WHERE ud.utilisateur.id_utilisateur = :id_utilisateur")
    List<Departement> findAllDeptsByUtilisateurId(@Param("id_utilisateur") String id_utilisateur);

    @Modifying
    @Query("DELETE FROM UtilisateurDepartement ud WHERE ud.departement.id_departement = :id_departement")
    void deleteByDepartementId(@Param("id_departement") String id_departement);
}
