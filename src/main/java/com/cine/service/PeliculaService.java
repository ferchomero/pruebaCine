package com.cine.service;

import com.cine.request.PeliculaRequest;
import com.cine.response.Response;


public interface PeliculaService {

	public Response findByID(Long ID);
	public Response findByTitle(String titulo);
	public Response insert(PeliculaRequest pelicula);
	public Response update(PeliculaRequest pelicula);
	public Response delete(Long ID);
}
