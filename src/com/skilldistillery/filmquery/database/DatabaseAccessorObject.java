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
	public Film findFilmById(int filmId) 
	{
		Film film = null;
		String user = "student";
		String pass = "student";
		Connection conn = null;
		List<Actor> listOfActors = null;

		
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
			     listOfActors = findActorsByFilmId(filmId);
			     
			     film = new Film(id, title, description, year, languageId, rentalDuration, rentalRate, 
			    		 		length, replacementCost, rating, specialFeatures,listOfActors);
			     
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
	public Actor findActorById(int actorId) 
	{
		Actor actor = null;
		String user = "student";
		String pass = "student";
		Connection conn = null;

		
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
	public void countNumberOfFilms()
	{
		Film film = null;
		String user = "student";
		String pass = "student";
		Connection conn = null;

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
		
		film.setNumberOfFilms(numberOfFilms);
	}
	
	@Override
	public List<Actor> findActorsByFilmId(int filmId) 
	{
		List<Actor> actorList = new ArrayList<Actor>();
		Actor actor = null;
		String user = "student";
		String pass = "student";
		Connection conn = null;

		
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

	} // end method List actor


}  // end class DatabaseAccessorObject
