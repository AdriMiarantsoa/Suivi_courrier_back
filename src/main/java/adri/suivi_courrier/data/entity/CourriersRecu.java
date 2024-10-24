package adri.suivi_courrier.data.entity;

import java.sql.Date;
import jakarta.persistence.*;

@Entity
public class CourriersRecu {

    @Id
    @Column(name = "id_reception")
    private String id_reception;

    @Column(name = "date_traitement")
    private Date date_traitement;

    @Column(name = "nom_utilisateur")
    private String nom_utilisateur;

    @Column(name = "id_courrier")
    private String id_courrier;

    @Column(name = "date_reception")
    private Date date_reception;

    @Column(name = "description")
    private String description;

    @Column(name = "expediteur")
    private String expediteur;

    @Column(name = "nom_courrier")
    private String nom_courrier;

    @Column(name = "statut")
    private String statut;

    @Column(name = "dept_destinataire")
    private String id_departement;

    // Getters and Setters
    public String getId_reception() {
        return id_reception;
    }

    public void setId_reception(String id_reception) {
        this.id_reception = id_reception;
    }

    public Date getDate_traitement() {
        return date_traitement;
    }

    public void setDate_traitement(Date date_traitement) {
        this.date_traitement = date_traitement;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }

    public String getId_courrier() {
        return id_courrier;
    }

    public void setId_courrier(String id_courrier) {
        this.id_courrier = id_courrier;
    }

    public Date getDate_reception() {
        return date_reception;
    }

    public void setDate_reception(Date date_reception) {
        this.date_reception = date_reception;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur;
    }

    public String getNom_courrier() {
        return nom_courrier;
    }

    public void setNom_courrier(String nom_courrier) {
        this.nom_courrier = nom_courrier;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getId_departement() {
        return id_departement;
    }

    public void setId_departement(String id_departement) {
        this.id_departement = id_departement;
    }
    public CourriersRecu() {
    }

    public CourriersRecu(String id_reception, Date date_traitement, String nom_utilisateur, String id_courrier,
                        Date date_reception, String description, String expediteur, String nom_courrier,
                        String statut, String id_departement)
    {
        this.id_reception = id_reception;
        this.date_traitement = date_traitement;
        this.nom_utilisateur = nom_utilisateur;
        this.id_courrier = id_courrier;
        this.date_reception = date_reception;
        this.description = description;
        this.expediteur = expediteur;
        this.nom_courrier = nom_courrier;
        this.statut = statut;
        this.id_departement = id_departement;
    }
}
