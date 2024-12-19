package com.IlyasBensalemM2DFS.praticien_service.service;

import com.IlyasBensalemM2DFS.praticien_service.model.Praticien;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PraticienService {

    private static Set<Praticien> praticienDatabase = new HashSet<>();

    static{
        Praticien geraud = new Praticien(
                UUID.randomUUID().toString(),
                "Geraud",
                "Delaroche",
                "generaliste",
                "delaroche@gmail.com",
                "0658145563"
        );
        Praticien marc = new Praticien(
                UUID.randomUUID().toString(),
                "umbert",
                "marc",
                "generaliste",
                "marcUmbert@gmail.com",
                "0658145864"
        );
        praticienDatabase.add(geraud);
        praticienDatabase.add(marc);
    }

    public List<Praticien> getAllPraticiens(){
        return new ArrayList<>(praticienDatabase);
    }

    public List<Praticien> getPraticienBySpecialite(String specialite){
        return praticienDatabase.stream()
                .filter(praticien -> praticien.getSpecialite().equalsIgnoreCase(specialite))
                .toList();
    }

    public Praticien getPraticien(String praticienId){
        return praticienDatabase.stream()
                .filter(praticien -> praticien.getId().equals(praticienId.trim()))
                .findFirst()
                .orElse(null);
    }

    public void ajoutPraticien(Praticien praticien){
        praticien.setId(UUID.randomUUID().toString());
        praticienDatabase.add(praticien);
    }

    public boolean modifierPraticien(String praticienId, Praticien praticien){
        Optional<Praticien> praticienExistant = praticienDatabase.stream()
                .filter(p -> p.getId().equals(praticienId.trim()))
                .findFirst();
        if (praticienExistant.isPresent()) {
            praticienDatabase.remove(praticienExistant.get());
            praticienDatabase.add(praticien);
            return true;
        }
        return false;
    }

    public boolean supprimerPraticien(String id){
        return praticienDatabase.removeIf(praticien -> praticien.getId().equals(id));
    }

}
