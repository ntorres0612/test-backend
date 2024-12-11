package test.xpertgroup.test_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import test.xpertgroup.test_backend.dtos.BreedDto;
import test.xpertgroup.test_backend.dtos.ImageDto;

import java.util.List;

@Service
public class CatService {
    @Value("${cat-api-base-url}")
    private String baseUrl;


    private final WebClient webClient;

    @Autowired
    public CatService(WebClient webClient) {
        this.webClient = webClient;
    }


    public Mono<List<BreedDto>> getBreeds() {
        return webClient.get()
                .uri(baseUrl + "v1/breeds?limit=10&page=0")
                .retrieve()
                .bodyToFlux(BreedDto.class)
                .collectList();
    }

    public Mono<BreedDto> getBreedById(String id) {
        return webClient.get()
                .uri(baseUrl + "v1/breeds/{id}", id)
                .retrieve()
                .bodyToMono(BreedDto.class);
    }

    public Mono<List<BreedDto>> searBreeds(String q) {
        return webClient.get()
                .uri(baseUrl + "v1/breeds/search?q={q}", q )
                .retrieve()
                .bodyToFlux(BreedDto.class)
                .collectList();
    }

    public Mono<List<ImageDto>> getImageById(String id) {
        return webClient.get()
                .uri(baseUrl + "v1/images/{id}", id )
                .retrieve()
                .bodyToFlux(ImageDto.class)
                .collectList();
    }





}