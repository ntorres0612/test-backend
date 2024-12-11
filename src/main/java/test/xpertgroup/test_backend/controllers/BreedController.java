package test.xpertgroup.test_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import test.xpertgroup.test_backend.dtos.BreedDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.xpertgroup.test_backend.dtos.ImageDto;
import test.xpertgroup.test_backend.services.CatService;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/breeds")
@RestController
public class BreedController {

    private static final Logger log = LoggerFactory.getLogger(BreedController.class);

    @Autowired
    CatService catService;

    @GetMapping
    public List<BreedDto> getAllBreeds() {
        /*
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Usuario autenticado: {}", authentication.getName());
        log.info("Roles: {}", authentication.getAuthorities());

         */
        return catService.getBreeds().block();
    }
    @GetMapping("/{breed_id}")
    public ResponseEntity<BreedDto> getBreedById(@PathVariable("breed_id") String breedId) {
        return ResponseEntity.ok(catService.getBreedById(breedId).block());
    }

    @GetMapping("/search")
    public List<BreedDto> searchBreeds(@RequestParam(value = "query", required = false) String query) {
        return catService.searBreeds(query).block();
    }

    @GetMapping("/images/{id}")
    public  ResponseEntity<List<ImageDto>> getImageById(@PathVariable("id") String id) {
        return ResponseEntity.ok(catService.getImageById(id).block());
    }


}
