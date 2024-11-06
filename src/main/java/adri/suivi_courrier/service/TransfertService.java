package adri.suivi_courrier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import adri.suivi_courrier.data.entity.Departement;
import adri.suivi_courrier.data.entity.Transfert;
import adri.suivi_courrier.data.entity.Courrier;
import adri.suivi_courrier.data.repository.CourrierRepository;
import adri.suivi_courrier.data.repository.TransfertRepository;

@Service
public class TransfertService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TransfertRepository transfertRepository;

    @Autowired
    private CourrierRepository courrierRepository;


    public void saveTransfert(Transfert transfert) {
        try{
        String nextId = jdbcTemplate.queryForObject("SELECT generate_id('Transfert',5, 'seqTransfert')", String.class);
        System.out.println("haha " + transfert.getId_courrier().getId_courrier());
        transfert.setId_transfert(nextId);
        String sql = "INSERT INTO Transfert (id_transfert,id_courrier,date_transfert,id_departement) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, transfert.getId_transfert(), transfert.getId_courrier().getId_courrier(),transfert.getDate_transfert(), transfert.getDepartement().getId_departement());
        
        Courrier courrier = courrierRepository.findById(transfert.getId_courrier().getId_courrier())

                    .orElseThrow(() -> new RuntimeException("Courrier non trouvé"));

            courrier.setStatut("transfert");
            courrier.setDept_destinataire(transfert.getDepartement());

            System.out.println("courrier id update " +courrier.getStatut());
            System.out.println("courrier dept update " +courrier.getDept_destinataire());
        courrierRepository.save(courrier);

     }catch(Exception e){e.printStackTrace();}

    }

    public Transfert getTransfertByIdAndDept(String id_courrier,Departement dept_destinataire) {
        return transfertRepository.findByIdAndDept(id_courrier, dept_destinataire);
    }

}
