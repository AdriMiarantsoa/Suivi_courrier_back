package adri.suivi_courrier.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "departement")
public class Departement {

    @Id
    private String id_departement;
    
    @Column
    private String nom_departement;

    @Column
    private String responsable;

    @ManyToOne
    @JoinColumn(name = "dept_parent")
    private Departement dept_parent;

    // Getters and Setters
    public String getId_departement() {
        return id_departement;
    }

    public void setId_departement(String id_departement) {
        this.id_departement = id_departement;
    }

    public String getNom_departement() {
        return nom_departement;
    }

    public void setNom_departement(String nom_departement) {
        this.nom_departement = nom_departement;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String resp) {
        this.responsable = resp;
    }

    public Departement getDept_parent() {
        return dept_parent;
    }

    public void setDept_parent(Departement parent) {
        this.dept_parent = parent;
    }

    public Departement(){}

}
