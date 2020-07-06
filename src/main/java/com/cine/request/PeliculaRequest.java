package com.cine.request;

public class PeliculaRequest {

	private String rating;
	private String fechaVista;
	private String comentario; 
	private String imdbID;
	private String titulo;
	private Long id;
	
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getImdbID() {
		return imdbID;
	}
	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getFechaVista() {
		return fechaVista;
	}
	public void setFechaVista(String fechaVista) {
		this.fechaVista = fechaVista;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
