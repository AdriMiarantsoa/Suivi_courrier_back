package adri.suivi_courrier.data.entity;
import jakarta.persistence.*;

@Entity
public class NotificationView {

    @Id
    private String deptDestinataire;
    private Integer nombreNotifications;

    // Getters et Setters
    public String getDeptDestinataire() {
        return deptDestinataire;
    }

    public void setDeptDestinataire(String deptDestinataire) {
        this.deptDestinataire = deptDestinataire;
    }

    public Integer getNombreNotifications() {
        return nombreNotifications;
    }

    public void setNombreNotifications(Integer nombreNotifications) {
        this.nombreNotifications = nombreNotifications;
    }

    public NotificationView(String deptDestinataire, Integer nombreNotifications) {
        this.deptDestinataire = deptDestinataire;
        this.nombreNotifications = nombreNotifications;
    }

    public NotificationView() {
    }
    
}
