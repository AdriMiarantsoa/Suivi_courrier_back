package adri.suivi_courrier.data.entity;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "utilisateur")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur implements UserDetails {

    @Id
    private String id_utilisateur;

    @Column
    private String nom_utilisateur;

    @Column
    private String email;

    @Column
    private String mot_de_passe;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    @Column
    private boolean is_approved;

    // Getters and Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean getApproved() {
        return is_approved;
    }

    public void setApproved(boolean approved) {
        this.is_approved = approved;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getUsername() {
        return id_utilisateur;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return mot_de_passe;
    }
}
