package adri.suivi_courrier.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import adri.suivi_courrier.data.entity.Departement;
import adri.suivi_courrier.data.entity.Transfert;

@Repository
public interface TransfertRepository extends JpaRepository<Transfert, String> {
    
    @Query("SELECT t FROM Transfert t WHERE t.id_courrier.id_courrier = :id_courrier AND t.departement = :dept_destinataire")
    Transfert findByIdAndDept(@Param("id_courrier") String id_courrier,@Param("dept_destinataire") Departement dept_destinataire);

}
