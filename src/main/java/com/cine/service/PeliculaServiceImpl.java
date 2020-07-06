package com.cine.service;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cine.entity.PeliculaEntity;
import com.cine.model.Pelicula;
import com.cine.repository.PeliculaRepository;
import com.cine.request.PeliculaRequest;
import com.cine.response.PeliculaResponse;
import com.cine.response.Response;
import com.cine.service.PeliculaService;
import com.cine.util.Constantes;
@Service
public class PeliculaServiceImpl implements PeliculaService {

	@Autowired
	private PeliculaRepository peliRepository;

	@Autowired
	@Qualifier("restClient")
	private RestTemplate restClient;

	@Override
	public Response findByID(Long ID) {
		try {
		return converter(peliRepository.getOne(ID));
		}catch(EntityNotFoundException e) {
			return new Response(500,e.getMessage());
		}
	}

	@Override
	public Response findByTitle(String titulo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("t", titulo);
		ResponseEntity<Pelicula> peli = restClient.getForEntity(Constantes.URL_PELI_TITULO, Pelicula.class, params);
		System.out.println(peli.getBody().toString());
		return converter(converter(peli.getBody()));
	}

	@Override
	public Response insert(PeliculaRequest pelicula) {
		PeliculaEntity entidadNueva = peliRepository.save(converter(pelicula));
		return converter(entidadNueva);
	}

	@Override
	public Response update(PeliculaRequest pelicula) {
		Optional<PeliculaEntity> entidad = peliRepository.findById(pelicula.getId());
		if (entidad.isPresent()) {
			entidad.get().setComentario(pelicula.getComentario());
			entidad.get().setFechaVista(pelicula.getFechaVista());
			entidad.get().setRating(pelicula.getRating());
			return converter(peliRepository.save(entidad.get()));
		} else {
			Response resp = new Response(500, "No existe el elemento que se pretende actualizar");
			return resp;
		}

	}

	@Override
	public Response delete(Long ID) {
		peliRepository.deleteById(ID);
		return new Response(200, "OK");
	}

	private PeliculaResponse converter(PeliculaEntity ent) {
		if(ent==null) {
			return new PeliculaResponse(500,"No se encontro el elemento en base de datos");
		}else {
		PeliculaResponse retorno = new PeliculaResponse();
		retorno.setComentario(ent.getComentario());
		retorno.setFechaVista(ent.getFechaVista());
		retorno.setImdbID(ent.getImdbID());
		retorno.setRating(ent.getRating());
		retorno.setTitulo(ent.getTitulo());
		retorno.setId(ent.getId());
		return retorno;
		}
	}

	private PeliculaEntity converter(Pelicula pelicula) {
		PeliculaEntity retorno = new PeliculaEntity();
		retorno.setComentario("");
		retorno.setFechaVista("");
		retorno.setImdbID(pelicula.getImdbID());
		retorno.setRating(pelicula.getImdbRating());
		retorno.setTitulo(pelicula.getTitle());
		retorno.setId(0L);
		return retorno;
	}

	private PeliculaEntity converter(PeliculaRequest request) {
		PeliculaEntity ent = new PeliculaEntity();
		ent.setComentario(request.getComentario());
		ent.setFechaVista(request.getFechaVista());
		ent.setImdbID(request.getImdbID());
		ent.setRating(request.getRating());
		ent.setTitulo(request.getTitulo());
		ent.setId(request.getId());
		return ent;
	}

}
