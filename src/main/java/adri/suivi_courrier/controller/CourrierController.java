package adri.suivi_courrier.controller;
import adri.suivi_courrier.data.entity.Courrier;
import adri.suivi_courrier.data.entity.CourriersRecu;
import adri.suivi_courrier.data.entity.Departement;
import adri.suivi_courrier.data.entity.StatCourrierDept;
import adri.suivi_courrier.data.export.ExcelExporter;
import adri.suivi_courrier.service.CourrierService;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CourrierController {

    @Autowired
    private CourrierService CourrierService;

    @Autowired
    private ExcelExporter excelExporter;

    @PostMapping("/courriers/export")
    public ResponseEntity<byte[]> exportCourriers(@RequestBody List<CourriersRecu> courriers, @RequestParam String departementName) throws IOException {
        byte[] excelFile = excelExporter.exportToExcel(courriers, departementName);
        byte[] file = excelFile;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.set("Content-Disposition", "attachment; filename=courriers.xlsx");
        return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }

    @PostMapping("/insert_courrier")
    public ResponseEntity<String> submitCourrier(@RequestBody Courrier Courrier) {
        try {
            CourrierService.saveCourrier(Courrier); // Ensure this method handles Courrier objects correctly
            return ResponseEntity.ok("Courrier submitted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    @PutMapping("/confirm_courrier")
    public ResponseEntity<Void> confirmCourrier(@RequestBody Courrier courrier) {
        CourrierService.enregistrerCourrier(courrier);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/courriers")
    public ResponseEntity<List<Courrier>> getAllCourriers() {
        List<Courrier> Courriers = CourrierService.getAllCourriers();
        return ResponseEntity.ok(Courriers);
    }

    @GetMapping("/courrier/{id_courrier}/{departement}")
    public ResponseEntity <Courrier> getOneCourrier(@PathVariable String id_courrier,@PathVariable Departement departement) {
        Courrier Courriers = CourrierService.getCourrierByIdAndDept(id_courrier,departement);
        return ResponseEntity.ok(Courriers);
    }

    @GetMapping("/courriers_attente")
    public List<Courrier> getCourriersEnAttente() {
      return CourrierService.getCourriersEnAttente();
    }

    @PutMapping("/update_courrier/{id_courrier}")
    public ResponseEntity<String> updateCourrier(@RequestBody Courrier courrier) {
      try {
        CourrierService.updateCourrier(courrier);
        return ResponseEntity.ok("Courrier mis à jour avec succès");
      } catch (Exception e) {
        return ResponseEntity.badRequest().body("Erreur lors de la mise à jour du courrier : " + e.getMessage());
      }
    }

    @GetMapping("/departement/{id_departement}/courriers")
    public List<Courrier> getCourriersDepartement(@PathVariable String id_departement) {
      System.out.println("ito ilay id "+ id_departement);
      return CourrierService.getCourriersDepartement(id_departement);
    }
    
    @GetMapping("/departement/{id_departement}/alerte")
    public List<Courrier> getCourrierAlerte(@PathVariable String id_departement) {
      System.out.println("ito ilay id alerte : "+ id_departement);
      return CourrierService.getCourrierAlerte(id_departement);
    }

    @PutMapping("/transfer_courrier")
    public ResponseEntity<String> transfer(@RequestParam String id_courrier,@RequestParam Departement dept_destinataire) {
      try {
        System.out.println("idcourrier"+id_courrier);

        CourrierService.transferCourrier(id_courrier,dept_destinataire);
        return ResponseEntity.ok("Courrier transferer avec succès");
      } catch (Exception e) {
        return ResponseEntity.badRequest().body("Erreur lors du transfer de courrier : " + e.getMessage());
      }
    }

    @GetMapping("/statistique/courriers/{departementId}/{year}")
    public List<StatCourrierDept> getStatsByDeptDestinataire(@PathVariable String departementId,@PathVariable int year) {
      System.out.println("deptId:"+ departementId);
      System.out.println("deptId:"+ year);
        return CourrierService.getStatsByDeptDestinataire(departementId,year);
    }

    @GetMapping("/statistique-general/courriers/{departementId}")
    public List<StatCourrierDept> getCurrentMonthAndYearStatistics(@PathVariable String departementId) {
      return CourrierService.getCurrentMonthAndYearStatistics(departementId);
    }

    @GetMapping("/verification/{id_courrier}/{departement}")
    public ResponseEntity<?> verifAcces(@PathVariable("id_courrier") String id_courrier, @PathVariable("departement") Departement departement) {
      try {
          CourrierService.VerifCourrier(id_courrier, departement);
          return ResponseEntity.ok("ok");
      } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
      }
    }

}
