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


    public List<Cow> getAllCows() {
        List<Cow> cows = restClient
                .get()
                .uri(URL + "/showCow")
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
        return cows;
    }
    public Cow getCowById(Long id) {
        ResponseEntity<Cow> responseEntity = restClient
                .get()
                .uri(URL + "/cow/{id}", id)
                .retrieve()
                .toEntity(Cow.class);

        return responseEntity.getBody();
    }

    public void addCow(Cow cow){
        restClient.post()
                .uri(URL + "/cowNew")
                .contentType(MediaType.APPLICATION_JSON)
                .body(cow)
                .retrieve()
                .toBodilessEntity();
    }
    public void editCow(Long id, Cow cow) {
        restClient.put()
                .uri(URL + "/editCow/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(cow)
                .retrieve()
                .toBodilessEntity();
    }

    public void deleteCow(Long id) {
        restClient.delete()
                .uri(URL + "/deleteCow/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }

}
