package adri.suivi_courrier.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

@Entity
@Table(name = "courrier")
public class Courrier {
     
    @Id
    @Column( nullable = false, unique = true)
    private String id_courrier;
    
    @Column
    private String nom_courrier; 

    @Column
    private String expediteur;
    
    @Column(length = 10000)
    private String description;

    @Column
    private Date date_reception;

    @ManyToOne
    @JoinColumn(name = "dept_destinataire")
    @NotNull
    private Departement dept_destinataire;

    @Column
    private String statut;

    @PrePersist
    protected void onCreate() {
        date_reception = new Date(System.currentTimeMillis());
    
    }
    // Getters and Setters
    public String getId_courrier() {
        return id_courrier;
    }

    public void setId_courrier(String id_courrier) {
        
        this.id_courrier = id_courrier;
    }

    public String getNom_courrier() {
        return nom_courrier;
    }

    public void setNom_courrier(String nom_courrier) {
        this.nom_courrier = nom_courrier;
    }

    public String getExpediteur() {
        return expediteur;
    }
    public void setExpediteur(String expediteur2) {
        this.expediteur = expediteur2;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public String getDescription() {
        return description;
    }

    public void setDate_reception(Date date) {
        this.date_reception = date;
    }

    public Date getDate_reception() {
        return date_reception;
    }

    public void setDept_destinataire(Departement dep) {
        this.dept_destinataire= dep;
    }

    public Departement getDept_destinataire() {
        return dept_destinataire;
    }

    public void setStatut(String statu) {
        this.statut = statu;
    }

    public String getStatut() {
        return statut;
    }

    public Courrier() {
        this.date_reception = new Date(System.currentTimeMillis());
    }

}
