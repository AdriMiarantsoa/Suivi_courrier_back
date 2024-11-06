package adri.suivi_courrier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import adri.suivi_courrier.data.entity.CourriersRecu;
import adri.suivi_courrier.service.CourriersRecuService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class CourriersRecuController {
   
    @Autowired
    private CourriersRecuService CourriersRecuService;

    @GetMapping("/courriers_recu/{id_departement}")
    public ResponseEntity<List<CourriersRecu>> getNonApprouvesUtilisateurs(@PathVariable String id_departement,
    @RequestParam(required = false) String startDate,
    @RequestParam(required = false) String endDate,
    @RequestParam(required = false) String expediteur)
    {
        //     System.out.println("debut"+ startDate);
        List<CourriersRecu> courriers;
        if (startDate != null && endDate != null && expediteur != null) {
            String[] startDates = startDate.split(",");
            String[] endDates = endDate.split(",");
            String[] expediteurs = expediteur.split(",");

            courriers = CourriersRecuService.getCourriersBetweenDatesAndExpeditor(id_departement, startDates[0], endDates[0] , expediteurs[0]);
        }else if (startDate != null && endDate != null) {
            courriers = CourriersRecuService.getCourriersBetweenDates(id_departement, startDate, endDate);
        }else if (expediteur != null) {
            courriers = CourriersRecuService.getCourriersByExpeditor(id_departement, expediteur);
        } else {
            courriers = CourriersRecuService.getCourriersRecu(id_departement);
        }
        return ResponseEntity.ok(courriers);
    }


}
