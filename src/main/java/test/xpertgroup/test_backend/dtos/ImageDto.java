package test.xpertgroup.test_backend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ImageDto {
    private String id;
    private String url;
    private List<BreedDto> breeds;
}
