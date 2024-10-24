package adri.suivi_courrier.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import adri.suivi_courrier.data.repository.AdminRepository;
import adri.suivi_courrier.request.SigninRequest;
import adri.suivi_courrier.response.JwtAuthentificationResponse;
import adri.suivi_courrier.service.AuthenticationService;
import adri.suivi_courrier.data.entity.Admin;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class AdminController {
    @Autowired
    private AuthenticationService authenticationService;
    
    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/admin/login_admin")
    public ResponseEntity<?> login(@RequestParam("nom_admin") String nom_admin, @RequestParam("mot_de_passe") String mot_de_passe) {
        SigninRequest request = new SigninRequest();
        request.setNom_utilisateur(nom_admin);
        request.setMot_de_passe(mot_de_passe);
        System.out.println("admin " + request.getMot_de_passe() + " " + request.getNom_utilisateur());

        try {
            JwtAuthentificationResponse jwtResponse = authenticationService.signinadmin(request);

            if (jwtResponse == null || jwtResponse.getToken() == null) {
                return ResponseEntity.badRequest().body("Invalid credentials");
            }
            Admin admin = adminRepository.getAdminByName(request.getNom_utilisateur());
            Map<String, Object> response = new HashMap<>();
            if (admin != null) {
                response.put("token", jwtResponse.getToken());
                response.put("redirect", "/adminboard/users");
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

}
