package adri.suivi_courrier.service;

import java.util.List;

import adri.suivi_courrier.data.entity.UtilisateurNonApprouves;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adri.suivi_courrier.data.repository.UtilisateurNonApprouvesRepository;

@Service
public class UtilisateurNonApprouvesService {
    

    @Autowired
    private UtilisateurNonApprouvesRepository  userRepository;

    public List<UtilisateurNonApprouves> getUsersNotApproved() {
        return userRepository.getUsersNotApproved();
    }

}
