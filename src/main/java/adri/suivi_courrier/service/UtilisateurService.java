package adri.suivi_courrier.service;

import adri.suivi_courrier.data.entity.Utilisateur;
import adri.suivi_courrier.data.repository.UtilisateurDepartementRepository;
import adri.suivi_courrier.data.repository.UtilisateurRepository;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UtilisateurDepartementRepository utilisateurDepartementRepository;

    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        String nextId = jdbcTemplate.queryForObject("SELECT generate_id('USER',5, 'seqUtilisateur')", String.class);

        utilisateur.setId_utilisateur(nextId);
        utilisateur.setApproved(false);

        String sql = "INSERT INTO utilisateur (id_utilisateur, nom_utilisateur, mot_de_passe, id_role,is_approved,email) VALUES (?, ?, ?, ?, ? ,?)";
        jdbcTemplate.update(sql, utilisateur.getId_utilisateur(), utilisateur.getNom_utilisateur(), utilisateur.getMot_de_passe(), utilisateur.getRole().getId_role(),utilisateur.getApproved(),utilisateur.getEmail());
        return utilisateur;
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur getCurrentUtilisateur(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Utilisateur) authentication.getPrincipal();
    }

    @Transactional
    public void updateApprove(String id) {
        utilisateurRepository.updateApprove(id);
    }

    @Transactional
    public void updateMdp(String nom_utilisateur,String mot_de_passe,String email) {
        Utilisateur u = utilisateurRepository.getUsersByNameAndMail(nom_utilisateur,email);
        System.out.println("userID:"+ u.getId_utilisateur());
        System.out.println("mdp:"+ mot_de_passe);

        String hashedPassword = passwordEncoder.encode(mot_de_passe);

        utilisateurRepository.updateMdp(hashedPassword , u.getId_utilisateur());
    }

    @Transactional
    public void deleteUtilisateur(String id_utilisateur) {
        utilisateurDepartementRepository.deleteByUtilisateurId(id_utilisateur);

        utilisateurRepository.deleteUtilisateurById(id_utilisateur);;
    }

}
