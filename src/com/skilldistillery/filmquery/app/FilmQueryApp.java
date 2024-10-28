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
		// app.test();
		app.launch();
	}  // end method main

	private void test()
	{
	
		int filmId = 1;
		
		System.out.println("\n\nTesting all new methods one by one");
		System.out.println("=======================================================================");
		System.out.println("Printing a film with a specific film id = 1");
		getAndPrintFilmByFilmId(filmId);
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
		int userChoice = 0;
		
		System.out.println("\nWelcome to the Film Query App!\n");
		userChoice = printMenu(input);
		
		while (userChoice != 3)
		{
			if (userChoice == 1)
			{
				int filmId = 0;
				filmId = printLookUpFilmByIdMenu(input);
				getAndPrintFilmByFilmId(filmId);
				getAndPrintAllActorsByFilmId(filmId);
				
			} // end else
			else if (userChoice == 2)
			{
				System.out.println("\n\nthis is where the film will be looked up by a keyword ");
				System.out.println("not implemented yet\n\n");
			} // end else if
		
				userChoice = printMenu(input);

		} // end while loop
		
		System.out.println("\n\nThank-you for visiting our Film Query App!");
		System.out.println("We hope you enjoyed the information we provided.");
		System.out.println("Please visit us again soon.");
		input.close();

	}  // end method startUserInterface
	
	
	private int printMenu(Scanner input)
	{
		int userChoice = 0;
		
		System.out.println("\n\nPlease enter the number of the option from the following selection:");
		System.out.println("=========================================================================");
		System.out.println("1. Look up a film by its id.");
		System.out.println("2. Look up a film title by any keyword ");
		System.out.println("3. Exit the application ");
		System.out.println("=========================================================================");
		System.out.print("Enter your choice here : ");
		userChoice = input.nextInt();
		
		if ((userChoice < 1) || (userChoice > 3))
		{
			System.out.println("\n\nInvalid choice please try again and enter a number 1 thru 3\n\n");
		}
			
		return userChoice;
	} // end method printMenu
	
	private int printLookUpFilmByIdMenu(Scanner input)
	{
		int userChoice = 0;
		int numFilms = 0;
		
		Film films = new Film();
		numFilms = films.getNumberOfFilms();
		
		System.out.println("\n\nWe have " + numFilms + " film titles to choose from!");
		System.out.println("Please enter the number the id number for the film you would like:");
		System.out.println("=========================================================================");
		System.out.print("Enter your choice here : ");
		userChoice = input.nextInt();
		
		if ((userChoice < 1) || (userChoice > numFilms))
		{
			System.out.println("\n\nInvalid choice please try again and enter a number 1 thru " + numFilms + "\n\n");
		}
		
		return userChoice;
	} // end method printLookUpFilmByIdMenu
	
	
	private void getAndPrintAllActorsInAllMovies()
	{

		Film film = db.findFilmById(1);
		int numFilms = film.getNumberOfFilms();
		
		for (int filmId = 1; filmId <= numFilms; filmId++)
		{
			db.findFilmById(filmId);
			getAndPrintAllActorsByFilmId(filmId);
		}
		
	} // end method getAndPrintAllActorsInAllMovies
	
	private void getAndPrintAllActorsByFilmId(int filmId)
	{

		List<Actor> tmpActorsList = new ArrayList<Actor>();
		
		
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
		
	} // end method getAndPrintAllActorsByFilmId

	private void getAndPrintAllActorsByActorId()
	{

		// for testing get actor by actor id
		for (int theActorId = 1; theActorId <= 201; theActorId++)
		{
			Actor actor = db.findActorById(theActorId);
			System.out.println("\nActor id " + actor.getId() + " First Name " + actor.getFirstName() +
								" Last Name " + actor.getLastName());
		}
	
	}  // end method getAndPrintAllActorsByActorId
	
	private void getAndPrintAllFilmsAndFilmIds()
	{
		// for testing get film by film id
		for (int theFilmId = 1; theFilmId <= 1000; theFilmId++)
		{
			Film film = db.findFilmById(theFilmId);
			db.findFilmById(theFilmId);
			System.out.println("\n\nFilm title " + film.getTitle() + " description " + film.getDescription());
		}

	} // end getAndPrintAllFilmsAndFilmIds
	
	private void getAndPrintFilmByFilmId(int filmId)
	{

		// for testing film by using specific film id
		Film film = db.findFilmById(filmId);
		System.out.println("\n\nThe film you selected is:");
		System.out.println(film);
		System.out.println("\n\n");

	}  // end method getAndPrintAllFilmsAndFilmIds

	
}  // end class Film Query App
