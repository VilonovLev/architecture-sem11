package org.example.handlers;

import org.example.models.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component("PetHandler")
public class PetHandler {

        @Autowired
        private RestTemplate restTemplate;

        private final static String URL = "http://localhost:8080/api/pet";

        public  List<Pet> getAllPet() {
            ResponseEntity<List<Pet>> response = restTemplate.exchange(
                    URL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Pet>>() {
                    });
            List<Pet> petList = response.getBody();
            return petList;
        }

        public Pet getPet(int id) {
            Pet pet = restTemplate.getForObject(String.format("%s/%d", URL, id), Pet.class);
            return pet;
        }

        public void savePet(Pet pet) {
            int id = pet.getId();
            if (id == 0) {
                ResponseEntity<String> response = restTemplate.postForEntity(URL, pet, String.class);
                System.out.println(response.getBody());
            } else {
                restTemplate.put(URL,HttpMethod.PUT, pet);
            }
        }

        public void deletePet(int id) {
            restTemplate.delete(String.format("%s/%d", URL, id));
        }
}
