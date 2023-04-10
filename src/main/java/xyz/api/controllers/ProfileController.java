package xyz.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileRepository repository;
 
    @GetMapping
    public ResponseEntity<Page<ProfileEntity>> index(@PageableDefault(size = 10) Pageable pageable){
        
        Page<ProfileEntity> page = this.repository.findAll(pageable);

        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProfileEntity> create(@RequestBody @Valid ProfileEntity record){

        this.repository.save(record);

        return ResponseEntity.ok(record);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProfileEntity> update(@RequestBody @Valid ProfileEntity record, @PathVariable Long id){

        if(!this.repository.existsById(id)){

            return ResponseEntity.notFound().build();
        }

        record.setId(id);

        record = this.repository.save(record);

        return ResponseEntity.ok(record);  
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ProfileEntity> destroy(@PathVariable Long id){

        if(!this.repository.existsById(id)){

            return ResponseEntity.notFound().build();
        }

        ProfileEntity entity = this.repository.getReferenceById(id);

        this.repository.deleteById(entity.getId());

        return ResponseEntity.ok(entity);
    }
}
