package adri.suivi_courrier.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import adri.suivi_courrier.data.entity.Departement;
import adri.suivi_courrier.data.entity.Utilisateur;
import adri.suivi_courrier.data.repository.UtilisateurDepartementRepository;
import adri.suivi_courrier.data.repository.UtilisateurRepository;
import adri.suivi_courrier.dto.EmailDetailsDto;
import adri.suivi_courrier.email.EmailService;
import adri.suivi_courrier.request.SigninRequest;
import adri.suivi_courrier.request.SignupRequest;
import adri.suivi_courrier.response.JwtAuthentificationResponse;
import adri.suivi_courrier.service.AuthenticationService;
import adri.suivi_courrier.service.UtilisateurService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class UtilisateurController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UtilisateurDepartementRepository utilisateurDepartementRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping("/utilisateurs")
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
        List<Utilisateur> users = utilisateurService.getAllUtilisateurs();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("nom_utilisateur") String nom_utilisateur, @RequestParam("mot_de_passe") String mot_de_passe, @RequestParam("email") String email) {
        SigninRequest request = new SigninRequest();
        request.setNom_utilisateur(nom_utilisateur);
        request.setMot_de_passe(mot_de_passe);
        request.setEmail(email);
        System.out.println("mdr " + request.getMot_de_passe() + " " + request.getNom_utilisateur()+ " " + request.getEmail());
        try {
            JwtAuthentificationResponse jwtResponse = authenticationService.signin(request);
            // Si le jeton est valide, créez la réponse
            Map<String, Object> response = new HashMap<>();
            response.put("token", jwtResponse.getToken());

            Utilisateur user = utilisateurRepository.getUsersByNameAndMail(request.getNom_utilisateur(),request.getEmail());
                if (user.getRole().getNom_role().equals("Secretaire")) {
                    response.put("redirect", "/dashboard/insertion_document");
                } else {
                    List<Departement> departements = utilisateurDepartementRepository.findAllDeptsByUtilisateurId(user.getId_utilisateur());
                    if (!departements.isEmpty()) {
                        Departement departement = departements.get(0);
                        response.put("redirect", "/board/departement/" + departement.getId_departement() + "/courriers");
                    }
                }

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }


    @PostMapping("/signup")
    public ResponseEntity<JwtAuthentificationResponse> signUp(@RequestBody SignupRequest signupRequest) {
        if (signupRequest == null) {
            return ResponseEntity.badRequest().body(null);
        }
        // System.out.println("Nom d'utilisateur: " + signupRequest.getNom_utilisateur());
        // System.out.println("Mot de passe: " + signupRequest.getMot_de_passe());
        // System.out.println("Département: " + signupRequest.getDepartement().getNom_departement());
        // System.out.println("Role: " + signupRequest.getRole().getNom_role());

        JwtAuthentificationResponse jwtResponse = authenticationService.signup(signupRequest);
        if (jwtResponse == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(jwtResponse);
    }


    @GetMapping("/utilisateur")
    public Utilisateur getCurrentUser(){
        return utilisateurService.getCurrentUtilisateur();
    }

    @PutMapping("/approve/{id_utilisateur}")
    public ResponseEntity<String> approve_user(@PathVariable String id_utilisateur) {
        try {
            Utilisateur user = utilisateurService.getUtilisateurById(id_utilisateur);
            utilisateurService.updateApprove(id_utilisateur);
            System.out.println("Envoi de l'email à : " + user.getEmail());

            // Créer un objet EmailDetailsDto
            EmailDetailsDto emailDetails = new EmailDetailsDto();
            emailDetails.setRecipient(user.getEmail());
            emailDetails.setEmailSubject("Votre compte a été approuvé");
            emailDetails.setEmailBody("Félicitations, votre compte a été approuvé !");

            emailService.sendApprovalEmail(emailDetails);
            return ResponseEntity.ok("Utilisateur mis à jour avec succès");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la mise à jour de l'utilisateur : " + e.getMessage());
        }
    }
 
    @DeleteMapping("/deleteUtilisateur/{id_utilisateur}")
    public ResponseEntity<String> deleteDepartement(@PathVariable String id_utilisateur) {
        try {
            Utilisateur user = utilisateurService.getUtilisateurById(id_utilisateur);
            utilisateurService.deleteUtilisateur(id_utilisateur);
            System.out.println("Envoi de l'email à : " + user.getEmail());

            // Créer un objet EmailDetailsDto
            EmailDetailsDto emailDetails = new EmailDetailsDto();
            emailDetails.setRecipient(user.getEmail());
            emailDetails.setEmailSubject("Votre compte a été supprimé");
            emailDetails.setEmailBody("Nous sommes désolés, votre compte a été supprimé.");
            System.out.println("Envoi de l'email à : " + user.getEmail());
            emailService.sendRejectionEmail(emailDetails);
            return ResponseEntity.ok("Utilisateur supprime avec succès");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
        }
    }

    @PutMapping("/update_mdp")
    public ResponseEntity<String> update_mdp(@RequestParam("nom_utilisateur") String nom_utilisateur,@RequestParam("mot_de_passe") String mot_de_passe,@RequestParam("email") String email) {
        try {
            System.out.println("io nom:"+nom_utilisateur);
            System.out.println("io mdp:"+mot_de_passe);
            System.out.println("io email:"+email);
            
            utilisateurService.updateMdp(nom_utilisateur, mot_de_passe, email);
            return ResponseEntity.ok("mot de passe mis à jour avec succès");
          } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la mise à jour du mot de passe : " + e.getMessage());
          }
    }

 

}