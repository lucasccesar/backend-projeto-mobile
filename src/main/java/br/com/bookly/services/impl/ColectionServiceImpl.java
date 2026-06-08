package br.com.bookly.services.impl;

import br.com.bookly.entities.Colection;
import br.com.bookly.entities.Users;
import br.com.bookly.entities.dtos.ColectionDTO;
import br.com.bookly.entities.dtos.ColectionRespondeDTO;
import br.com.bookly.exceptions.ExistingColectionByUserException;
import br.com.bookly.exceptions.InexistentColectionException;
import br.com.bookly.exceptions.InexistentIdUserException;
import br.com.bookly.repositories.ColectionRepository;
import br.com.bookly.exceptions.BadRequestException;
import br.com.bookly.services.ColectionService;
import br.com.bookly.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ColectionServiceImpl implements ColectionService {

    @Autowired
    ColectionRepository colectionRepository;

    @Autowired
    UsersService usersService;

    @Override
    public Colection createColection(ColectionDTO colectionDTO) {

        if(colectionDTO.getName() == null || colectionDTO.getName().isBlank()) {
            throw new BadRequestException("Colection name is required");
        }

        if(colectionDTO.getUserId() == null){
            throw new BadRequestException("Id User is required");
        }

        Users user = usersService.getUsersRepository().findById(colectionDTO.getUserId()).orElse(null);

        if (user == null) {
            throw new InexistentIdUserException();
        }

        if (colectionRepository.existsByNameIgnoreCaseAndUser_Id(colectionDTO.getName(), user.getId())) {
            throw new ExistingColectionByUserException();
        }

        Colection newColection = new Colection();
        newColection.setName(colectionDTO.getName());
        newColection.setDescription(colectionDTO.getDescription());
        newColection.setUser(user);

        return colectionRepository.save(newColection);
    }


    @Override
    public boolean deleteColectionById(UUID idColection) {
        if(!colectionRepository.existsById(idColection))
            throw new InexistentColectionException();

        colectionRepository.deleteById(idColection);
        return true;
    }

    @Override
    public Colection updateColection(UUID idColection, Colection colection) {
        Colection exists = colectionRepository.findById(idColection).orElse(null);

        if(exists == null)
            throw new InexistentColectionException();

        if(colection.getName() == null || colection.getName().isBlank())
            throw new BadRequestException("Colection name is required");

        if(colection.getDescription() == null || colection.getDescription().isBlank())
            throw new BadRequestException("Colection description is required");

        exists.setName(colection.getName());
        exists.setDescription(colection.getDescription());
        exists.setBooks(colection.getBooks());

        return  colectionRepository.save(exists);
    }

    @Override
    public Colection findColectionById(UUID idColection) {
        Colection exists = colectionRepository.findById(idColection).orElse(null);
        if(exists == null)
            throw new InexistentColectionException();

        return exists;
    }

    @Override
    public Colection findColectionByName(String name) {
        Colection exists = colectionRepository.findByNameIgnoreCase(name);
        if(exists == null)
            throw new InexistentColectionException();
        return exists;
    }


    @Override
    public Page<Colection> findByUser_Id(UUID idUser, Pageable pageable) {
        if (idUser == null)
            return colectionRepository.findAll(pageable);

        Users user = usersService.getUsersRepository().findById(idUser).orElse(null);
        if (user == null){
            throw new InexistentIdUserException("Error: User id Not Found");
        }

        return colectionRepository.findByUser_Id(idUser, pageable);
    }

    @Override
    public boolean existsByNameIgnoreCaseAndUser_Id(String name, UUID idUser) {
        boolean exists = colectionRepository.existsByNameIgnoreCaseAndUser_Id(name, idUser);
        if(!exists){
            throw new InexistentColectionException("Colection not found");
        }
        return exists;
    }


    @Override
    public Page<ColectionRespondeDTO> findAllColections(Pageable pageable) {
        Page<Colection> colections = colectionRepository.findAll(pageable);
        return (colections.map(ColectionRespondeDTO::new));
    }
}
