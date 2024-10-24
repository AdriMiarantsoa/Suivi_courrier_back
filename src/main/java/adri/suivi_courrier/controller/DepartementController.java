package adri.suivi_courrier.controller;
import adri.suivi_courrier.data.entity.Departement;
import adri.suivi_courrier.service.DepartementService;
import adri.suivi_courrier.service.NotificationViewService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin

public class DepartementController {

    @Autowired
    private DepartementService DepartementService;

    @Autowired
    private NotificationViewService notifService;

    @PostMapping("/insert_departement")
    public ResponseEntity<String> submitDepartement(@RequestBody Departement Departement) {
        try {
            DepartementService.saveDepartement(Departement); 
            return ResponseEntity.ok("Departement submitted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/departements")
    public ResponseEntity<List<Departement>> getAllDepartements() {
        List<Departement> Departements = DepartementService.getAllDepartements();
        return ResponseEntity.ok(Departements);
    }
    
    @GetMapping("/departement/utilisateur/{id_utilisateur}")
    public ResponseEntity<String> getDepartementUser(@PathVariable String id_utilisateur) {
        String Departements = DepartementService.getDepartementUser(id_utilisateur);
        return ResponseEntity.ok(Departements);
    }

    @GetMapping("/departement/{id_departement}")
    public ResponseEntity<String> findNomDepartementByIdDepartement(@PathVariable String id_departement) {
        System.out.println("Entering findNomDepartementByIdDepartement method with idDepartement: " + id_departement);
        try {
            String nomDepartement = DepartementService.findNomDepartementByIdDepartement(id_departement);
            System.out.println(nomDepartement);
            return ResponseEntity.ok(nomDepartement);
        } catch (Exception e) {
            System.out.println("Error finding department name: " + e.getMessage());
            throw e;
        }
    }

    @DeleteMapping("/deleteDepartement/{id_departement}")
    public ResponseEntity<Void> deleteDepartement(@PathVariable String id_departement) {
        DepartementService.deleteDepartement(id_departement);
        return ResponseEntity.noContent().build();  // Renvoie un statut 204 No Content si tout va bien
    }


    //nb notification par departement
    @GetMapping("/notifications/{id_departement}")
    public Integer getNotificationCountByDept(@PathVariable String id_departement) {
        return notifService.countNotificationsByDept(id_departement);
    }
}
