package com.generation.games.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.games.model.Categoria;
import com.generation.games.repository.CategoriaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class CategoriaController {
	 @Autowired
	    CategoriaRepository categoriaRepository;

	    @GetMapping
	    public ResponseEntity<List<Categoria>> getAll(){
	        try{
	            return ResponseEntity.ok(categoriaRepository.findAll());
	        }catch (Exception e ){
	            return ResponseEntity.noContent().build();
	        }
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Categoria> getById(@PathVariable Long id){
	        return categoriaRepository.findById(id)
	                .map(res -> ResponseEntity.ok(res))
	                .orElse(ResponseEntity.notFound().build());
	    }

	    @GetMapping("tipo/{tipo}")
	    public ResponseEntity<List<Categoria>> getAll(@PathVariable String tipo) {
	        try {
	            return ResponseEntity.ok(categoriaRepository.findAllByTipoContainingIgnoreCaseOrderByTipo(tipo));
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	        }
	    }

	    @PostMapping
	    public ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria categoria){
	        if(categoria.getId() == null)
	            return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
	        else
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    }

	    @PutMapping
	    public ResponseEntity<Categoria> putCategoria(@Valid @RequestBody Categoria categoria){
	        return categoriaRepository.findById(categoria.getId())
	                .map(res -> ResponseEntity.status(HttpStatus.OK)
	                        .body(categoriaRepository.save(categoria)))
	                .orElse(ResponseEntity.notFound().build());
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id){
	        try {
	            categoriaRepository.deleteById(id);
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	        }catch (Exception e){
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }
}
