package adri.suivi_courrier.data.repository;

import adri.suivi_courrier.data.entity.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartementRepository extends JpaRepository<Departement, String> {
    
    @Query("SELECT d.nom_departement FROM Departement d WHERE d.id_departement = :id_departement")
    String findNomDepartementByIdDepartement(@Param("id_departement") String id_departement);

    @Modifying
    @Query("DELETE FROM Departement d WHERE d.id_departement = :id_departement")
    void deleteDepartementById(@Param("id_departement") String id_departement);

    @Query("SELECT d FROM Departement d WHERE d.id_departement = :id_departement")
    Departement findDepartementById(@Param("id_departement") String id_departement);

    
}
