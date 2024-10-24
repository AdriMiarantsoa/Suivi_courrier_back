package adri.suivi_courrier.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

@Entity
@Table(name = "reception")
public class Reception {

    @Id
    @Column( nullable = false, unique = true)
    private String id_reception;

    @ManyToOne
    @JoinColumn(name = "id_courrier")
    @NotNull
    private Courrier id_courrier;

    @Column
    private Date date_traitement;

    @ManyToOne
    @JoinColumn(name = "recu_par")
    @NotNull
    private Utilisateur recu_par;


    public String getId_reception() {
        return id_reception;
    }
    public Courrier getId_courrier() {
        return id_courrier;
    }
    public Date getDate_traitement() {
        return date_traitement;
    }
    public Utilisateur getRecu_par() {
        return recu_par;
    }

    public void setId_reception(String idReception) {
        this.id_reception = idReception;
    }
    public void setId_courrier(Courrier idCourrier) {
        this.id_courrier = idCourrier;
    }
    public void setDate_traitement(Date dateTraitement) {
        this.date_traitement = dateTraitement;
    }
    public void setRecu_par(Utilisateur recuPar) {
        this.recu_par = recuPar;
    }

    public Reception() {
        this.date_traitement = new Date(System.currentTimeMillis());
    }



}
