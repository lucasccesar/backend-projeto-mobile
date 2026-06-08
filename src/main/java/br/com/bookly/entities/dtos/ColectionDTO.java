package br.com.bookly.entities.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ColectionDTO {

    private String name;
    private String description;
    private UUID userId;

    public ColectionDTO() {}

    public ColectionDTO(String name, String description, UUID userId) {
        this.name = name;
        this.description = description;
        this.userId = userId;
    }


}
