package adri.suivi_courrier.data.repository;

import adri.suivi_courrier.data.entity.Admin;
import adri.suivi_courrier.data.entity.Utilisateur;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {

    @Query("SELECT u FROM Utilisateur u WHERE u.nom_utilisateur = :nom_utilisateur AND u.mot_de_passe = :mot_de_passe")
    Utilisateur getUserByNameAndMdp(@Param("nom_utilisateur") String nom_utilisateur, @Param("mot_de_passe") String mot_de_passe);

    @Query("SELECT u FROM Utilisateur u WHERE u.nom_utilisateur = :nom_utilisateur")
    List<Utilisateur> getUsersByName(@Param("nom_utilisateur") String nom_utilisateur);

    @Query("SELECT u FROM Utilisateur u WHERE LOWER((u.nom_utilisateur)) = LOWER((:nom_utilisateur)) AND u.email = :email")
    Utilisateur getUsersByNameAndMail(@Param("nom_utilisateur") String nom_utilisateur,String email);

    //authentification
    Optional<Utilisateur> findById(String id_utilisateur);

    @Query("SELECT u FROM Admin u WHERE u.nom_admin = :nom_admin")
    Admin getAdminByName(@Param("nom_admin") String nom_admin);

    @Query("SELECT u FROM Utilisateur u WHERE u.id_utilisateur = :id_utilisateur")
    Utilisateur findById_utilisateur(String id_utilisateur);

    @Modifying
    @Query("UPDATE Utilisateur u SET u.is_approved = true WHERE u.id_utilisateur = :id_utilisateur")
    void updateApprove(@Param("id_utilisateur") String id_utilisateur);

    @Modifying
    @Query("UPDATE Utilisateur u SET u.mot_de_passe = :mot_de_passe WHERE u.id_utilisateur = :id_utilisateur")
    void updateMdp(@Param("mot_de_passe") String mot_de_passe,@Param("id_utilisateur") String id_utilisateur);

    @Modifying
    @Query("DELETE FROM Utilisateur u WHERE u.id_utilisateur = :id_utilisateur")
    void deleteUtilisateurById(@Param("id_utilisateur") String id_utilisateur);

}
