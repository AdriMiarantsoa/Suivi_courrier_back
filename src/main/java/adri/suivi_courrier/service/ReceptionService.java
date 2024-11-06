package adri.suivi_courrier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import adri.suivi_courrier.data.entity.Reception;
import adri.suivi_courrier.data.repository.ReceptionRepository;

@Service
public class ReceptionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ReceptionRepository receptionRepository;

    public void saveReception(Reception reception) {
        try{String nextId = jdbcTemplate.queryForObject("SELECT generate_id('Reception',5, 'seqReception')", String.class);
        System.out.println("haha " + reception.getId_courrier().getId_courrier());
        reception.setId_reception(nextId);
        String sql = "INSERT INTO reception (id_reception,id_courrier,date_traitement,recu_par) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, reception.getId_reception(), reception.getId_courrier().getId_courrier(),reception.getDate_traitement(), reception.getRecu_par().getId_utilisateur());}
        catch(Exception e){e.printStackTrace();}
    }

    public Reception getReceptionByIdcourrier(String id_courrier) {
        return receptionRepository.findByIdCourrier(id_courrier);
    }


}
