package adri.suivi_courrier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import adri.suivi_courrier.data.entity.UtilisateurNonApprouves;
import adri.suivi_courrier.service.UtilisateurNonApprouvesService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class UtilisateurNonApprouvesController {
   
    @Autowired
    private UtilisateurNonApprouvesService utilisateurService;

    @GetMapping("/utilisateurs_non_approuves")
    public ResponseEntity<List<UtilisateurNonApprouves>> getNonApprouvesUtilisateurs() {
        List<UtilisateurNonApprouves> users = utilisateurService.getUsersNotApproved();
        return ResponseEntity.ok(users);
    }


}
