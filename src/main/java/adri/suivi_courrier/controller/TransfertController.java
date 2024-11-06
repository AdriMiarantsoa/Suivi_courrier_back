package adri.suivi_courrier.controller;
import adri.suivi_courrier.data.entity.Departement;
import adri.suivi_courrier.data.entity.Transfert;
import adri.suivi_courrier.service.TransfertService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class TransfertController {

    @Autowired
    private TransfertService transfertService;


    @PostMapping("/insert_Transfert")
    public ResponseEntity<String> submitTransfert(@RequestBody Transfert transfert) {
        try {
            transfertService.saveTransfert(transfert);
            return ResponseEntity.ok("Transfert submitted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/courrier_transfert/{id_courrier}/{departement}")
    public ResponseEntity <Transfert> getOneTransfert(@PathVariable String id_courrier,@PathVariable Departement departement) {
        Transfert Courriers = transfertService.getTransfertByIdAndDept(id_courrier, departement);
        return ResponseEntity.ok(Courriers);
    }
    
}
