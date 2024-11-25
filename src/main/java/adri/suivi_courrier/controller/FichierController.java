package adri.suivi_courrier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adri.suivi_courrier.data.entity.Fichier;
import adri.suivi_courrier.service.FichierService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class FichierController {
    
    @Autowired
    private FichierService fichierService;

    @GetMapping("/courriers/{id_courrier}/fichiers")
    public ResponseEntity<List<Fichier>> getFichiersByCourrierId(@PathVariable String id_courrier) {
        System.out.println("id : " + id_courrier);
        List<Fichier> fichiers = fichierService.findByCourrierId(id_courrier);
        System.out.println("fichiers length :" + fichiers.size());
        return ResponseEntity.ok(fichiers);
    }

}
