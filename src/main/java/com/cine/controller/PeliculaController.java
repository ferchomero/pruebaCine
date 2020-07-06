package com.cine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cine.request.PeliculaRequest;
import com.cine.response.Response;
import com.cine.service.PeliculaService;

@RestController
@RequestMapping("/api/pelicula")
public class PeliculaController {
	
	@Autowired
	private PeliculaService service;
	
	@GetMapping("{titulo}")
	public Response getPeliculaTitulo(@PathVariable(value="titulo") String titulo) {
		return service.findByTitle(titulo);
	}
	
	@GetMapping("/id/{id}")
	public Response getPeliculaId(@PathVariable(value="id") Long id) {
		return service.findByID(id);
	}
	@PostMapping
	public Response insertPelicula(@RequestBody PeliculaRequest request) {
		return service.insert(request);
	}
	@PutMapping
	public Response updatePelicula(@RequestBody PeliculaRequest request) {
		return service.update(request);
	}
	@DeleteMapping("{id}")
	public Response deletePelicula(@PathVariable(value="id") Long id) {
		return service.delete(id);
	}

}
