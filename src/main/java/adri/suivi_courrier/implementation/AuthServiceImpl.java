package adri.suivi_courrier.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import adri.suivi_courrier.data.entity.Admin;
import adri.suivi_courrier.data.entity.Utilisateur;
import adri.suivi_courrier.data.entity.UtilisateurDepartement;
import adri.suivi_courrier.data.repository.UtilisateurRepository;
import adri.suivi_courrier.request.SigninRequest;
import adri.suivi_courrier.request.SignupRequest;
import adri.suivi_courrier.response.JwtAuthentificationResponse;
import adri.suivi_courrier.service.AuthenticationService;
import adri.suivi_courrier.service.JwtService;
import adri.suivi_courrier.service.UtilisateurDepartementService;
import adri.suivi_courrier.service.UtilisateurService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthenticationService{
    @Autowired
    private UtilisateurService userService;
    
    @Autowired
    private UtilisateurRepository userRepository;
    
    @Autowired
    private UtilisateurDepartementService utilisateurDepartementService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired

    private BCryptPasswordEncoder crypt;

    @Override
    public JwtAuthentificationResponse signup(SignupRequest request) {
        try {
            Utilisateur u = new Utilisateur();
            UtilisateurDepartement ud = new UtilisateurDepartement();
            JwtAuthentificationResponse j = new JwtAuthentificationResponse();

            u.setNom_utilisateur(request.getNom_utilisateur());

            // Hacher le mot de passe avant de le stocker
            String hashedPassword = passwordEncoder.encode(request.getMot_de_passe());
            u.setMot_de_passe(hashedPassword); // Utiliser le mot de passe haché
            u.setEmail(request.getEmail());
            u.setRole(request.getRole());

            System.out.println("user: " + u.getNom_utilisateur());
            System.out.println("user_role: " + u.getRole().getId_role());

            Utilisateur savedUser = userService.saveUtilisateur(u);
            ud.setUtilisateur(savedUser);
            if(request.getDepartement()!=null){
                ud.setDepartement(request.getDepartement());
                System.out.println("ud: " + ud.getUtilisateur().getNom_utilisateur());
                System.out.println("ud_dept: " + ud.getDepartement().getId_departement());
                utilisateurDepartementService.saveUtilisateurDepartement(ud);
            }
            var jwt = jwtService.generateToken(savedUser);
            j.setToken(jwt);
            return j;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
    @Override
    public JwtAuthentificationResponse signin(SigninRequest request) {
        JwtAuthentificationResponse j = new JwtAuthentificationResponse();
        try {
            System.out.println("Login attempt for " + request.getNom_utilisateur());
            Utilisateur utilisateur = userRepository.getUsersByNameAndMail(request.getNom_utilisateur(),request.getEmail());
            if (utilisateur == null) {
                throw new Exception("Utilisateur inéxistant");
            }
                if (passwordEncoder.matches(request.getMot_de_passe(), utilisateur.getMot_de_passe())) {

                    if (!utilisateur.getApproved()) {
                        throw new Exception("Utilisateur non approuvé,réessayer plutard!");
                    }
    
                    System.out.println("mdp valide");
                    var jwt = jwtService.generateToken(utilisateur);
                    j.setToken(jwt);
                    return j;
                }
            // Utilisateur ou mot de passe invalide
            throw new Exception("Nom ou Mot de passe invalide!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
    

    @Override
    public JwtAuthentificationResponse signinadmin(SigninRequest request) {
        try {
            JwtAuthentificationResponse j = new JwtAuthentificationResponse();
            System.out.println("Login attempt for " + request.getNom_utilisateur());

            Admin admin = userRepository.getAdminByName(request.getNom_utilisateur());

            if (admin != null && crypt.matches(request.getMot_de_passe(), admin.getMot_de_passe())) {
                System.out.println("mdp admin valide");
                var jwt = jwtService.generateTokenAdmin(admin);
                j.setToken(jwt);
                return j;
            }
            throw new Exception("Invalid username or password");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
