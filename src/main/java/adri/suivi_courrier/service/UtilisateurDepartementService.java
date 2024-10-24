package adri.suivi_courrier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import adri.suivi_courrier.data.entity.Departement;
import adri.suivi_courrier.data.entity.UtilisateurDepartement;
import adri.suivi_courrier.data.repository.UtilisateurDepartementRepository;

@Service
public class UtilisateurDepartementService {
    
    @Autowired
    private UtilisateurDepartementRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<Departement> getAllDeptsByUtilisateurId(String idUtilisateur) {
        return repository.findAllDeptsByUtilisateurId(idUtilisateur);
    }

    public UtilisateurDepartement saveUtilisateurDepartement(UtilisateurDepartement utilisateurDepartement) {
        String sql = "INSERT INTO utilisateur_departement (id_utilisateur, id_departement) VALUES (?, ?)";
        jdbcTemplate.update(sql, utilisateurDepartement.getUtilisateur().getId_utilisateur(), utilisateurDepartement.getDepartement().getId_departement());
        return utilisateurDepartement;
    }


}