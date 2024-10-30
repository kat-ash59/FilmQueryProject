package com.skilldistillery.filmquery.database;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public interface DatabaseAccessor 
{
	public Film findFilmById(int filmId);
	
	
	public Actor findActorByActorId(int actorId);
	
	
	public List<Actor> findActorsByFilmId(int filmId);


	public Film findFilmAndActorsByFilmId(int filmId);


	public int countNumberOfAllActors();


	public int countNumberOfAllFilms();


	public List<Film> findFilmsByKeyword(String keyword);


	public String getLanguageByFilmId(int filmId);


	
}  // end interface DatabaseAccessor
