package adri.suivi_courrier.service;

import adri.suivi_courrier.data.entity.Courrier;
import adri.suivi_courrier.data.entity.Departement;
import adri.suivi_courrier.data.entity.StatCourrierDept;
import adri.suivi_courrier.data.entity.Transfert;
import adri.suivi_courrier.data.generator.CourrierIdGenerator;
import adri.suivi_courrier.data.repository.CourrierRepository;
import adri.suivi_courrier.data.repository.TransfertRepository;
import jakarta.transaction.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CourrierService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CourrierRepository courrierRepository;

    @Autowired
    private CourrierIdGenerator courrierIdGenerator;

    @Autowired
    private TransfertRepository transfertRepository;

    public void saveCourrier(Courrier courrier) {
        try {
           String nextId = courrierIdGenerator.generateId();
           String etatbase ="en attente";
           courrier.setId_courrier(nextId);
           courrier.setStatut(etatbase);
           
           Departement dept = courrierRepository.findDepartementById(courrier.getDept_destinataire().getId_departement());
           courrier.setDept_destinataire(dept);
           String sql = "INSERT INTO Courrier (id_courrier, nom_courrier,expediteur,description,date_reception,statut,dept_destinataire) VALUES (?,?,?,?,?,?,?)";
           jdbcTemplate.update(sql, courrier.getId_courrier(), courrier.getNom_courrier(),courrier.getExpediteur(),courrier.getDescription(),courrier.getDate_reception(),courrier.getStatut(),courrier.getDept_destinataire().getId_departement());
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Transactional
    public void updateCourrier(Courrier cour) {
        courrierRepository.updateCourrier(cour.getId_courrier(), cour.getDate_reception(),cour.getNom_courrier(), cour.getDescription(), cour.getExpediteur(),cour.getDept_destinataire());
    }

    @Transactional
    public void enregistrerCourrier(Courrier courrier) {
        courrier.setStatut("enregistre");
        courrierRepository.save(courrier);
    }

    public List<Courrier> getAllCourriers() {
        return courrierRepository.findAll();
    }

    public Courrier getCourrierByIdAndDept(String id_courrier,Departement dept_destinataire) {
        return courrierRepository.findByIdAndDept(id_courrier, dept_destinataire);
    }

    public List<Courrier> getCourriersEnAttente() {
        return courrierRepository.findByStatut("en attente");
    }

    public List<Courrier> getCourriersDepartement(String id_departement) {
        return courrierRepository.findByDepartementIdAndStatut(id_departement);
    }

//courrier plus de 24h
    public List<Courrier> getCourrierAlerte(String id_departement) {
        return courrierRepository.findCourrierAlerte(id_departement);
    }

    @Transactional
    public void transferCourrier(String id_courrier,Departement departement) {
        String statut ="transfer";
        courrierRepository.Transfer(departement, statut, id_courrier);
    }

    public Departement findDepartementById(String id_departement) {
        return courrierRepository.findDepartementById(id_departement);
    }

    //stat nbcourrier par dept
    public List<StatCourrierDept> getStatsByDeptDestinataire(String deptDestinataire,int an) {
        List<Object[]> results = courrierRepository.findByDeptDestinataireAndMonth(deptDestinataire,an);
        List<StatCourrierDept> stats = new ArrayList<>();
        for (Object[] result : results) {
            int year = ((Number) result[0]).intValue();
            int month = ((Number) result[1]).intValue();
            String deptDest = (String) result[2];
            int courrierCount = ((Number) result[3]).intValue();
            StatCourrierDept stat = new StatCourrierDept(year, month,deptDest, courrierCount);
            stats.add(stat);
        }
        return stats;
    }

    //stat general
    public List<StatCourrierDept> getCurrentMonthAndYearStatistics(String deptDestinataire) {
        List<Object[]> results = courrierRepository.findCurrentYearStatistics(deptDestinataire);
        List<StatCourrierDept> stats = new ArrayList<>();
        for (Object[] result : results) {
            int year = ((Number) result[0]).intValue();
            int month = ((Number) result[1]).intValue();
            String deptDest = (String) result[2];
            int courrierCount = ((Number) result[3]).intValue();
            StatCourrierDept stat = new StatCourrierDept(year, month, deptDest, courrierCount);
            stats.add(stat);
        }
        return stats;
    }

    public void VerifCourrier(String id_courrier,Departement  dept_destinataire) throws Exception
    {
        Courrier c = courrierRepository.findByIdAndDept(id_courrier, dept_destinataire);
        Transfert t = transfertRepository.findByIdAndDept(id_courrier, dept_destinataire);
        Optional<Courrier> cr = courrierRepository.findById(id_courrier);
        if (!cr.isPresent()) throw new Exception("Courrier inexistant!");
        if(c==null && t==null) throw new Exception("Vous n'avez pas accès à ce courrier.");
        
    }

}
