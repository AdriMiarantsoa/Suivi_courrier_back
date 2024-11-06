package adri.suivi_courrier.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

@Entity
@Table(name = "transfert")
public class Transfert {

    @Id
    @Column( nullable = false, unique = true)
    private String id_transfert;

    @ManyToOne
    @JoinColumn(name = "id_courrier")
    @NotNull
    private Courrier id_courrier;

    @Column
    private Date date_transfert;

    @ManyToOne
    @JoinColumn(name = "id_departement")
    @NotNull
    private Departement departement;


    public String getId_transfert() {
        return id_transfert;
    }
    public Courrier getId_courrier() {
        return id_courrier;
    }
    public Date getDate_transfert() {
        return date_transfert;
    }
    public Departement getDepartement() {
        return departement;
    }

    public void setId_transfert(String idTransfert) {
        this.id_transfert = idTransfert;
    }
    public void setId_courrier(Courrier idCourrier) {
        this.id_courrier = idCourrier;
    }
    public void setDate_transfert(Date dateTransfert) {
        this.date_transfert = dateTransfert;
    }
    public void setDepartement(Departement dept) {
        this.departement = dept;
    }

    public Transfert() {
        this.date_transfert = new Date(System.currentTimeMillis());
    }



}
