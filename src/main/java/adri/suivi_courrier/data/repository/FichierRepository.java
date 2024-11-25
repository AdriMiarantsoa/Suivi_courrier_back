package adri.suivi_courrier.data.repository;

import adri.suivi_courrier.data.entity.Fichier;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FichierRepository extends JpaRepository<Fichier, Integer> {

    @Query("SELECT c FROM Fichier c WHERE c.courrier.id_courrier = :id_courrier")
    List<Fichier> findByIdCourrier(@Param("id_courrier") String id_courrier);

}