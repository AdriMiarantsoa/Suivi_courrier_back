package adri.suivi_courrier.data.entity;

import jakarta.persistence.*;

@Entity
public class UtilisateurNonApprouves {
    
    @Id
    @Column(name = "id_utilisateur")
    private String id_utilisateur;

    @Column(name = "nom_utilisateur")
    private String nom_utilisateur;

    @Column(name = "nom_departement")
    private String nom_departement;

    @Column(name = "nom_role")
    private String nom_role;

    public String getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(String id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }

    public String getNom_departement() {
        return nom_departement;
    }

    public void setNom_departement(String nom_departement) {
        this.nom_departement = nom_departement;
    }

    public String getNom_role() {
        return nom_role;
    }

    public void setNom_role(String nom_role) {
        this.nom_role = nom_role;
    }

    public UtilisateurNonApprouves(String id_utilisateur, String nom_utilisateur, String nom_departement, String nom_role) {
        this.id_utilisateur = id_utilisateur;
        this.nom_utilisateur = nom_utilisateur;
        this.nom_departement = nom_departement;
        this.nom_role = nom_role;
    }
    
    public UtilisateurNonApprouves() {
    }
}