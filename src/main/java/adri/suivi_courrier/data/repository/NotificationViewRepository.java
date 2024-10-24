package adri.suivi_courrier.data.repository;

import adri.suivi_courrier.data.entity.NotificationView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface NotificationViewRepository extends JpaRepository<NotificationView, String> {
    
    @Query(value = "select nombre_notifications from v_count_notif_dept where dept_destinataire = :id_departement", nativeQuery = true)
    Integer countNotificationsByDept(String id_departement);
}