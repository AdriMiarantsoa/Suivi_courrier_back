package adri.suivi_courrier.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "utilisateur_departement")
public class UtilisateurDepartement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", referencedColumnName = "id_utilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_departement", referencedColumnName = "id_departement")
    private Departement departement;

    // getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    public Departement getDepartement() {
        return departement;
    }
    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
}