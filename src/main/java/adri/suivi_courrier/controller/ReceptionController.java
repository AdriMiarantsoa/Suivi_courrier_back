package adri.suivi_courrier.controller;
import adri.suivi_courrier.data.entity.Reception;
import adri.suivi_courrier.service.ReceptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class ReceptionController {

    @Autowired
    private ReceptionService ReceptionService;


    @PostMapping("/insert_reception")
    public ResponseEntity<String> submitReception(@RequestBody Reception Reception) {
        try {
            ReceptionService.saveReception(Reception);
            return ResponseEntity.ok("Reception submitted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
}
