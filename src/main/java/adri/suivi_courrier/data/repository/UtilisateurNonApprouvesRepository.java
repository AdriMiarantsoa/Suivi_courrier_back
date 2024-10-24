package adri.suivi_courrier.data.repository;

import adri.suivi_courrier.data.entity.UtilisateurNonApprouves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UtilisateurNonApprouvesRepository extends JpaRepository<UtilisateurNonApprouves, String> {

    @Query(value = "SELECT * FROM v_utilisateurs_non_approuves", nativeQuery = true)
    List<UtilisateurNonApprouves> getUsersNotApproved();
}