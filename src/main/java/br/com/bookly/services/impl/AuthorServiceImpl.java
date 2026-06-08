package br.com.bookly.services.impl;

import br.com.bookly.entities.Author;
import br.com.bookly.exceptions.BadRequestException;
import br.com.bookly.exceptions.ExistentAuthorException;
import br.com.bookly.exceptions.InexistentAuthorException;
import br.com.bookly.repositories.AuthorRepository;
import br.com.bookly.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author createAuthor(Author author) {
        if(author.getName() == null || author.getName().isBlank())
            throw new BadRequestException("Author name is required");
        return authorRepository.save(author);
    }

    @Override
    public boolean deleteAuthorById(UUID idAuthor) {
        if(authorRepository.existsById(idAuthor) == false)
            throw new InexistentAuthorException("Error: Author not found");

        authorRepository.deleteById(idAuthor);
        return true;

    }

    @Override
    public Author updateAuthor(UUID idAuthor, Author author) {
        Author exists = authorRepository.findById(idAuthor).orElse(null);

        if(exists == null)
            throw new InexistentAuthorException("Error: Author not found");

        if(author.getName() == null || author.getName().isBlank())
            throw new BadRequestException("Error: Author name is required");

        if(exists.getName().equals(author.getName())){
            throw new ExistentAuthorException("Error: Author name already exists");
        }

        exists.setName(author.getName());
        return authorRepository.save(exists);
    }

    @Override
    public Author findAuthorById(UUID idAuthor) {
        if(idAuthor == null){
            throw new BadRequestException("Error: Id is required");
        }

        Author author = authorRepository.findById(idAuthor).orElse(null);

        if(author == null){
            throw new InexistentAuthorException("Error: Author not found");
        }

        return author;
    }

    @Override
    public Page<Author> findAuthorByName(String name, Pageable pageable) {
        if(name == null || name.isBlank()){
            throw new BadRequestException("Author name is required");
        }

        Page<Author> exists = authorRepository.findAuthorByNameIgnoreCase(name, pageable);

        if(exists == null || exists.getContent().isEmpty()){
            throw new InexistentAuthorException("Error: Author not found");
        }
        return exists;
    }

    @Override
    public Author getOrCreateAuthorByName(String name) {
        List<Author> authors = authorRepository.findByNameContainingIgnoreCase(name);
        if (authors.isEmpty()) {
            Author newAuthor = new Author();
            newAuthor.setName(name.trim());
            return authorRepository.save(newAuthor);
        } else {
            return authors.get(0); // pega o primeiro encontrado
        }
    }

    @Override
    public Page<Author> findAllAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }
}

