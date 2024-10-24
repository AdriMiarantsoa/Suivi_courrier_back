package adri.suivi_courrier.request;

import adri.suivi_courrier.data.entity.Departement;
import adri.suivi_courrier.data.entity.Role;
import lombok.*;

public class SignupRequest {
    private String nom_utilisateur;
    private String mot_de_passe;
    private String email;
    private Role role;
    private Departement departement;

    public SignupRequest(){}

    public String getNom_utilisateur() {
        return nom_utilisateur;
    }

    public void setNom_utilisateur(String nom_utilisateur) {
        this.nom_utilisateur = nom_utilisateur;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

}
