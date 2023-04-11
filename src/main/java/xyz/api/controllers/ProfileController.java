package xyz.api.controllers;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import xyz.api.entities.ProfileEntity;
import xyz.api.repositories.ProfileRepository;
import xyz.api.responses.ResponseJSON;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileRepository repository;

    // @Autowired
    // private ResponseJSON response;

    @GetMapping(value={"", "/"})
    public ResponseEntity<ResponseJSON> index(@PageableDefault(size = 10) Pageable pageable, @Autowired ResponseJSON response){
        
        var page = this.repository.findAll(pageable);

        return response.page(page).build();
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<ResponseJSON> show(@PathVariable Long id, @Autowired ResponseJSON response){

        var entity = this.repository.getReferenceById(id);

        return response.data(entity)
            .success()
            .build();
    }

    @GetMapping(value="/new")
    public ResponseEntity<ResponseJSON> create(@Autowired ResponseJSON response){

        var form = new Serializable() {};

        return response.form(form)
            .success()
            .build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseJSON> store(@RequestBody @Valid ProfileEntity entity, @Autowired ResponseJSON response){

        entity = this.repository.save(entity);

        return response.success("Profile was created")
            .data(entity)
            .build();
    }

    @GetMapping(value="/{id}/edit")
    public ResponseEntity<ResponseJSON> edit(@PathVariable Long id, @Autowired ResponseJSON response){

        var entity = this.repository.getReferenceById(id);
        var form = new Serializable() {};

        return response.data(entity)
            .form(form)
            .success()
            .build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseJSON> update(@RequestBody @Valid ProfileEntity data, @PathVariable Long id, @Autowired ResponseJSON response){

        if(this.repository.existsById(id)){

            data.setId(id);

            var entity = this.repository.save(data);

            response.data(entity).success("Profile was updated");
        }
        else {

            response.notFound("Profile was not found");
        }

        return response.build(); 
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseJSON> destroy(@PathVariable Long id, @Autowired ResponseJSON response){

        if(this.repository.existsById(id)){

            var entity = this.repository.getReferenceById(id);

            this.repository.deleteById(entity.getId());

            response.data(entity).success("Profile was removed");
        }
        else {

            response.notFound("Profile was not found");
        }

        return response.build();
    }
}
