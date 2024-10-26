package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			     
			     film = new Film(id, title, description, year, languageId, rentalDuration, rentalRate, 
			    		 		length, replacementCost, rating, specialFeatures);
			     
			     // the film exists now
			     // now set its actors
			     
			    //film.setActors(finish this);
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
		return null;
	}  // end method Actor


	@Override
	public List<Actor> findActorsByFilmId(int filmId) 
	{
		// TODO Auto-generated method stub
		return null;
	} // end method List actor


}  // end class DatabaseAccessorObject
