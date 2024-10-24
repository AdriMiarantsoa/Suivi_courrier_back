package adri.suivi_courrier.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import adri.suivi_courrier.data.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {

    @Query("SELECT u FROM Admin u WHERE u.nom_admin = :nom_admin")
    Admin getAdminByName(@Param("nom_admin") String nom_admin);

}
