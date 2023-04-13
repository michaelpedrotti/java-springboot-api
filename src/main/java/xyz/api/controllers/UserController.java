package xyz.api.controllers;

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
import xyz.api.entities.UserEntity;
import xyz.api.repositories.UserRepository;
import xyz.api.responses.bodies.CreateBody;
import xyz.api.responses.bodies.InterfaceBody;
import xyz.api.responses.bodies.PaginateBody;
import xyz.api.responses.bodies.DataBody;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserRepository repository;

    @GetMapping(value={"", "/"})
    public ResponseEntity<InterfaceBody> index(@PageableDefault(size = 10) Pageable pageable){
        
        var page = this.repository.findAll(pageable);
        var body = new PaginateBody(page.getTotalElements(), page.getContent());

        return ResponseEntity.ok(body);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<InterfaceBody> show(@PathVariable Long id){

        var body = new DataBody();
        var entity = this.repository.getReferenceById(id);

        body.setData(entity);

        return ResponseEntity.ok(body);
    }

    @GetMapping(value="/new")
    public ResponseEntity<InterfaceBody> create(){

        var body = new CreateBody();

        return ResponseEntity.ok(body);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<InterfaceBody> store(@RequestBody @Valid UserEntity entity){

        entity = this.repository.save(entity);
        
        var body = new DataBody();
        body.setData(entity);
        body.setMessage("User was created");

        return ResponseEntity.ok(body);
    }

    @GetMapping(value="/{id}/edit")
    public ResponseEntity<InterfaceBody> edit(@PathVariable Long id){

        var entity = this.repository.getReferenceById(id);
        var body = new DataBody();

        body.setData(entity);

        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<InterfaceBody> update(@RequestBody @Valid UserEntity data, @PathVariable Long id){

        var entity = this.repository.getReferenceById(id);
        var body = new DataBody();

        data.setId(entity.getId());
        entity = this.repository.save(data);

        body.setData(entity);
        body.setMessage("User was updated");
 
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<InterfaceBody> destroy(@PathVariable Long id){

        var entity = this.repository.getReferenceById(id);
        var body = new DataBody();

        this.repository.deleteById(entity.getId());

        body.setData(entity);
        body.setMessage("User was removed");

        return ResponseEntity.ok(body);
    }
}
