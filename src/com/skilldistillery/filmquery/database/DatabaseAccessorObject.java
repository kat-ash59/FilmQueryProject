package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor 
{
	public static final String URL = "jdbc:mysql://localhost:3306/sdvid";
	
	private Film film = null;
	private Actor actor = null;
	private String user = "student";
	private String pass = "student";
	private Connection conn = null;

	
	public DatabaseAccessorObject() 
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public Film findFilmAndActorsByFilmId(int filmId)
	{
		Film tmpFilm = null;
		List<Actor> listOfActors = new ArrayList<Actor>();
		tmpFilm = findFilmById(filmId);
		listOfActors = findActorsByFilmId(filmId);
		String language = getLanguageByFilmId(filmId);
		String category = getFilmCategoryByFilmId(filmId);
	    
	    film = new Film(tmpFilm.getId(), tmpFilm.getTitle(), tmpFilm.getDescription(), 
	    				tmpFilm.getReleaseYear(), tmpFilm.getLanguageId(), tmpFilm.getRentalDuration(),
	    				tmpFilm.getRentalRate(), tmpFilm.getLength(), tmpFilm.getReplacementCost(), 
	    				tmpFilm.getRating(), tmpFilm.getSpecialFeatures(),listOfActors, language, category);
	    return film;
	     
	}


	@Override
	public Film findFilmById(int filmId) 
	{
		
		try 
		{
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltext = "select * from film where id = ?";
			PreparedStatement stmt = conn.prepareStatement(sqltext);
			stmt.setInt(1, filmId);
			
			ResultSet results = stmt.executeQuery();
			
			
			if(results.next()) 
			{
			     int id = results.getInt("id");
			     String title = results.getString("title");
			     String description = results.getString("description");
			     Integer year = results.getInt("release_year");
			     int languageId = results.getInt("language_id");        
			     int rentalDuration = results.getInt("rental_duration");     
			     double rentalRate = results.getDouble("rental_rate"); 
			     int length = results.getInt("length");
			     double replacementCost = results.getDouble("replacement_cost");  
			     String rating = results.getString("rating");
			     String specialFeatures = results.getString("special_features");
			     List<Actor> actorList = findActorsByFilmId(id);
			     String language = getLanguageByFilmId(filmId);
			     String category = getFilmCategoryByFilmId(filmId);
			     
			     film = new Film(id, title, description, year, languageId, rentalDuration, rentalRate, 
			    		 		length, replacementCost, rating, specialFeatures, actorList, language, category );
			     
			}
	
			results.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return film;
		
	}  // end method findFilmById

	

	@Override
	public List<Film> findFilmsByKeyword(String keyword) 
	{

		List<Film> filmList = new ArrayList<Film>();
		List<Actor> actorList = new ArrayList<Actor>();
		
		try 
		{
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltext = "select * from film where film.title like ? OR film.description like ?";
			PreparedStatement stmt = conn.prepareStatement(sqltext);
			stmt.setString(1, "%" +  keyword + "%");
			stmt.setString(2, "%" +  keyword + "%");

			ResultSet results = stmt.executeQuery();
			
			
			while (results.next()) 
			{
			     int id = results.getInt("id");
			     String title = results.getString("title");
			     String description = results.getString("description");
			     Integer year = results.getInt("release_year");
			     int languageId = results.getInt("language_id");        
			     int rentalDuration = results.getInt("rental_duration");     
			     double rentalRate = results.getDouble("rental_rate"); 
			     int length = results.getInt("length");
			     double replacementCost = results.getDouble("replacement_cost");  
			     String rating = results.getString("rating");
			     String specialFeatures = results.getString("special_features");
			     String language = getLanguageByFilmId(id);
			     actorList = findActorsByFilmId(id);
			     String category = getFilmCategoryByFilmId(id);
			     
			     film = new Film(id, title, description, year, languageId, rentalDuration, rentalRate, 
			    		 		length, replacementCost, rating, specialFeatures, actorList, language, category);
			     
			     filmList.add(film);
			     
			}  // end while loop creating filmList
	
			results.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filmList;
		
	}  // end method findFilmById


	@Override
	public String getFilmCategoryByFilmId(int filmId) 
	{
		String category = null;
		
		try 
		{
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltext = "select category.name "
					+ "from category "
					+ "join film_category on category.id = film_category.category_id "
					+ "join film on film.id = film_category.film_id "
					+ "where film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sqltext);
			stmt.setInt(1, filmId);
			
			ResultSet results = stmt.executeQuery();
			
		
			while (results.next()) 
			{
			     category = results.getString("category.name"); 
			}
	
			results.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return category;
}

	@Override
	public Actor findActorByActorId(int actorId) 
	{

		try 
		{
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltext = "select * from actor where id = ?";
			PreparedStatement stmt = conn.prepareStatement(sqltext);
			stmt.setInt(1, actorId);
			
			ResultSet results = stmt.executeQuery();
			
			if(results.next()) 
			{
			     int id = results.getInt("id");
			     String firstName = results.getString("first_name");
			     String lastName = results.getString("last_name");
			     
			     actor = new Actor(id, firstName, lastName);
			     
			}
	
			results.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return actor;
	}  // end method Actor
		
	@Override
	public List<Actor> findActorsByFilmId(int filmId) 
	{
		List<Actor> actorList = new ArrayList<Actor>();
		
		try 
		{
			conn = DriverManager.getConnection(URL, user, pass);

			String sqltext = "select actor.id, actor.first_name, actor.last_name "
					+ "from actor "
					+ "join film_actor on film_actor.actor_id = actor.id "
					+ "join film on film.id = film_actor.film_id "
					+ "where film.id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sqltext);
			stmt.setInt(1, filmId);
			
			ResultSet results = stmt.executeQuery();
				
			while (results.next()) 
			{
			     int id = results.getInt("actor.id");
			     String firstName = results.getString("actor.first_name");
			     String lastName = results.getString("actor.last_name");
			     
			     actor = new Actor(id, firstName, lastName);
			     actorList.add(actor);
			}
			
			results.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		     
				
		return actorList;

	} // end method find actors by film id
	

	@Override
	public String getLanguageByFilmId(int filmId)
	{
		String language = null;
		
		try 
		{
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltext = "select language.name "
					+ "from language "
					+ "join film on film.language_id = language.id "
					+ "where film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sqltext);
			stmt.setInt(1, filmId);
			
			ResultSet results = stmt.executeQuery();
			
		
			while (results.next()) 
			{
			     language = results.getString("language.name");  
			}
	
			
			results.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return language;

		
	}

	@Override
	public int countNumberOfAllActors()
	{

		int numberOfActors = 0;
		
		try 
		{
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltext = "select * from actor";
			PreparedStatement stmt = conn.prepareStatement(sqltext);
			
			ResultSet results = stmt.executeQuery();
			
			
			
			while(results.next()) 
			{
			     numberOfActors++;
			}
	
			results.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		actor.setNumberOfActors(numberOfActors);
		return numberOfActors;
		
	} // end method countNumberOfActors

	@Override
	public int countNumberOfAllFilms()
	{

		int numberOfFilms = 0;

		try 
		{
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltext = "select * from film";
			PreparedStatement stmt = conn.prepareStatement(sqltext);
			
			ResultSet results = stmt.executeQuery();

			
			while(results.next()) 
			{
			     numberOfFilms++;
			}
	

			results.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return numberOfFilms;
	} // end method countNumberOfFilms
	

}  // end class DatabaseAccessorObject
