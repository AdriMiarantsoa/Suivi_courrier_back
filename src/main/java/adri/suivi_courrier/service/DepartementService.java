package adri.suivi_courrier.service;

import adri.suivi_courrier.data.entity.Departement;
import adri.suivi_courrier.data.repository.DepartementRepository;
import adri.suivi_courrier.data.repository.UtilisateurDepartementRepository;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DepartementService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DepartementRepository departementRepository;
    
    @Autowired
    private UtilisateurDepartementRepository utilisateurDepartementRepository;
    
    public void saveDepartement(Departement departement) {
        String nextId = jdbcTemplate.queryForObject("SELECT generate_id('Departement',5, 'seqDepartement')", String.class);
        String sql;
        departement.setId_departement(nextId);

        if(departement.getDept_parent()==null){
            sql = "INSERT INTO Departement (id_departement, nom_departement,responsable) VALUES (?,?,?)";
            jdbcTemplate.update(sql, departement.getId_departement(), departement.getNom_departement(),departement.getResponsable());
        }
        else{
            sql = "INSERT INTO Departement (id_departement, nom_departement,responsable,dept_parent) VALUES (?,?,?,?)";
            jdbcTemplate.update(sql, departement.getId_departement(), departement.getNom_departement(),departement.getResponsable(),departement.getDept_parent().getId_departement());
        }
    }

    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    public String getDepartementUser(String id_utilisateur) {
        List<Departement> departements = utilisateurDepartementRepository.findAllDeptsByUtilisateurId(id_utilisateur);
        return departements.get(0).getId_departement();
    }

    public String findNomDepartementByIdDepartement(String idDepartement) {
        return departementRepository.findNomDepartementByIdDepartement(idDepartement);
    }

    @Transactional
    public void deleteDepartement(String id_departement) {
        utilisateurDepartementRepository.deleteByDepartementId(id_departement);

        departementRepository.deleteDepartementById(id_departement);
    }

    @Transactional
    public void updateDept(Departement d) {
        System.out.println(d);
        departementRepository.updateDepartement(d.getId_departement(),d.getNom_departement(),d.getResponsable(),d.getDept_parent());
    }
}
