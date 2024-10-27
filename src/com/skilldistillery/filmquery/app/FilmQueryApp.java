package com.skilldistillery.filmquery.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp 
{

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args)
	{
		FilmQueryApp app = new FilmQueryApp();
		app.test();
//    app.launch();
	}  // 

	private void test()
	{
	
		int filmId = 1;
		
		System.out.println("\n\nTesting all new methods one by one");
		System.out.println("=======================================================================");
		System.out.println("Printing a film with a specific film id = 1");
		getAndPrintFilmbyFilmId(filmId);
		System.out.println("\n\n=======================================================================");
		System.out.println("\n\nPrinting out all films and their film id\'s");
		getAndPrintAllFilmsAndFilmIds();
		System.out.println("\n\n=======================================================================");
		System.out.println("\n\nPrinting out all actors and their actor id\'s");
		getAndPrintAllActorsByActorId();
		System.out.println("\n\n=======================================================================");
		System.out.println("\n\nPrinting out all movies and their actors");
		getAndPrintAllActorsInAllMovies();
		
		
		
	}  // end method test

	private void launch() 
	{
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	} // end method launch

	private void startUserInterface(Scanner input)
	{

	}  // end method startUserInterface
	
	
	private void getAndPrintAllActorsInAllMovies()
	{

		List<Actor> tmpActorsList = new ArrayList<Actor>();
		for (int filmId = 1; filmId <= 1000; filmId++)
		{
			Film film = db.findFilmById(filmId);
			db.findFilmById(filmId);
			tmpActorsList = db.findActorsByFilmId(filmId);
			System.out.println("\n\nActors for the film id " + filmId +  " titled " +
							film.getTitle() + " are:");
			
			for (Actor actor : tmpActorsList)
			{
				System.out.println("\t actor id " + actor.getId() + 
									", actor first name " + actor.getFirstName() +
									", actor last name " + actor.getLastName() + "\n");
			}
			
		}
		
	}
	
	private void getAndPrintAllActorsByActorId()
	{

		// for testing get actor by actor id
		for (int theActorId = 1; theActorId <= 201; theActorId++)
		{
			Actor actor = db.findActorById(theActorId);
			System.out.println("\nActor id " + actor.getId() + " First Name " + actor.getFirstName() +
								" Last Name " + actor.getLastName());
		}
	
	}
	
	private void getAndPrintAllFilmsAndFilmIds()
	{
		// for testing get film by film id
		for (int theFilmId = 1; theFilmId <= 1000; theFilmId++)
		{
			Film film = db.findFilmById(theFilmId);
			db.findFilmById(theFilmId);
			System.out.println("\n\nFilm title " + film.getTitle() + " description " + film.getDescription());
		}

	}
	
	private void getAndPrintFilmbyFilmId(int filmId)
	{

		// for testing film by using specific film id
		Film film = db.findFilmById(filmId);
		System.out.println("\n\n The film you selected is:");
		System.out.println(film);
		System.out.println("\n\n");

	}

}  // end class Film Query App
