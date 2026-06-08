package br.com.bookly.services;


import br.com.bookly.entities.Colection;
import br.com.bookly.entities.dtos.ColectionDTO;
import br.com.bookly.entities.dtos.ColectionRespondeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface ColectionService {

    public Colection createColection(ColectionDTO colectionDTO);
    public boolean deleteColectionById(UUID idColection);
    Colection updateColection(UUID idColection, Colection colection);
    public Colection findColectionById(UUID idColection);
    public Colection findColectionByName(String name);
    public Page<Colection> findByUser_Id(UUID userId, Pageable pageable);
    boolean existsByNameIgnoreCaseAndUser_Id(String name, UUID userId);
    public Page<ColectionRespondeDTO> findAllColections(Pageable pageable);
}
