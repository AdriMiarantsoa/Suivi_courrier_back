package adri.suivi_courrier.service;

import adri.suivi_courrier.data.entity.Fichier;
import adri.suivi_courrier.data.repository.FichierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FichierService {

    @Autowired
    private FichierRepository fichierRepository;

    public Fichier saveFichier(Fichier fichier) {
        return fichierRepository.save(fichier);
    }

    public List<Fichier> getAllFichiers() {
        return fichierRepository.findAll();
    }

    public Optional<Fichier> getFichierById(int id) {
        return fichierRepository.findById(id);
    }

    public List<Fichier> findByCourrierId(String id_courrier) {
        return fichierRepository.findByIdCourrier(id_courrier);
    }

}