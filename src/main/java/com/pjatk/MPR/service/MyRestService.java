package com.pjatk.MPR.service;

import com.pjatk.MPR.AlreadyExists;
import com.pjatk.MPR.model.Cow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class MyRestService {

    public static final String URL = "http://localhost:8080";
    private RestClient restClient;
    public MyRestService() {this.restClient = RestClient.create();}

//    @Autowired
//    public MyRestService() {
//        this.restClient = RestClient.create();
//    }

//    public Cow getCowByName(String name) {
//        return cowRepository.findByName(name);
//    }

    public List<Cow> getAllCows() {
        List<Cow> cows = restClient
                .get()
                .uri(URL + "/showCow")
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
        return cows;
    }

    public void addCow(Cow cow){
        // Sprawdź, czy krowa o podanej nazwie już istnieje
//        if (getAllCows().stream().anyMatch(existingCow -> existingCow.getName().equals(cow.getName()))) {
//            throw new AlreadyExists("Cow with name " + cow.getName() + " already exists");
//        }

        // Wywołaj endpoint do dodawania krowy
        restClient.post()
                .uri(URL + "/cowNew")
                .contentType(MediaType.APPLICATION_JSON)
                .body(cow)
                .retrieve()
                .toBodilessEntity();
    }

//    public void deleteCowByName(String name) {
//        Cow cow = cowRepository.findByName(name);
//        if (cow != null) {
//            cowRepository.delete(cow);
//        }
//    }
}
