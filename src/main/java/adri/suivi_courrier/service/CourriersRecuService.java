package adri.suivi_courrier.service;

import java.time.LocalDate;
import java.util.List;

import adri.suivi_courrier.data.entity.CourriersRecu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adri.suivi_courrier.data.repository.CourriersRecuRepository;

@Service
public class CourriersRecuService {
    

    @Autowired
    private CourriersRecuRepository  courriersRecuRepository;

    public List<CourriersRecu> getCourriersRecu(String id_departement) {
        return courriersRecuRepository.getCourriersRecu(id_departement);
    }

    public List<CourriersRecu> getCourriersBetweenDates(String id_departement, String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return courriersRecuRepository.findByDepartementAndDateBetween(id_departement, start, end);
    }
    
    public List<CourriersRecu> getCourriersBetweenDatesAndExpeditor(String id_departement, String startDate, String endDate,String expediteur) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return courriersRecuRepository.findByDepartementAndDateBetweenAndExpeditor(id_departement, start, end,expediteur);
    }

    public List<CourriersRecu> getCourriersByExpeditor(String id_departement,String expediteur) {
        return courriersRecuRepository.findByDepartementAndExpeditor(id_departement,expediteur);
    }
}
