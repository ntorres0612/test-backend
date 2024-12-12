package test.xpertgroup.test_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.xpertgroup.test_backend.dtos.CatBreedDTO;
import test.xpertgroup.test_backend.dtos.ImageDto;
import test.xpertgroup.test_backend.services.CatService;

import java.util.List;

@RequestMapping("/breeds")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class BreedController {

    private static final Logger log = LoggerFactory.getLogger(BreedController.class);

    @Autowired
    CatService catService;

    @GetMapping
    public List<CatBreedDTO> getAllBreeds() {
        /*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Usuario autenticado: {}", authentication.getName());
        log.info("Roles: {}", authentication.getAuthorities());

         */
        return catService.getBreeds().block();
    }
    @GetMapping("/{breed_id}")
    public ResponseEntity<CatBreedDTO> getBreedById(@PathVariable("breed_id") String breedId) {
        return ResponseEntity.ok(catService.getBreedById(breedId).block());
    }

    @GetMapping("/search")
    public List<CatBreedDTO> searchBreeds(@RequestParam(value = "q", required = false) String q) {
        return catService.searBreeds(q).block();
    }

    @GetMapping("/images/{id}")
    public  ResponseEntity<List<ImageDto>> getImageById(@PathVariable("id") String id) {
        return ResponseEntity.ok(catService.getImageById(id).block());
    }


}
