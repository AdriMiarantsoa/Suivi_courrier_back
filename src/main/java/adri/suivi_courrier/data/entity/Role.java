package adri.suivi_courrier.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    @Id
    private String id_role;

    private String nom_role;

    // Getters and Setters
    public String getId_role() {
        return id_role;
    }

    public void setId_role(String id_role) {
        this.id_role = id_role;
    }

    public String getNom_role() {
        return nom_role;
    }

    public void setNom_role(String nom_role) {
        this.nom_role = nom_role;
    }
}
