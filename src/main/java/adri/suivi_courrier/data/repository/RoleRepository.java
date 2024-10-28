package adri.suivi_courrier.data.repository;

import adri.suivi_courrier.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, String> {

    @Modifying
    @Query("DELETE FROM Role d WHERE d.id_role = :id_role")
    void deleteRoleById(@Param("id_role") String id_role);

    @Modifying
    @Query("UPDATE Role r SET r.nom_role = :nom_role WHERE r.id_role = :id_role")
    void updateRole(@Param("id_role") String id_role,@Param("nom_role") String nom_role);

}
