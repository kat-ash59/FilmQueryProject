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
		System.out.println("\n\n=======================================================================");
		System.out.println("\n\nPrinting out all actors by film id");
		getAndPrintAllActorsByFilmId(filmId);
		
		
		
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
				getAndPrintFilmAndActorsByFilmId(filmId);
			} // end else
			else if (userChoice == 2)
			{
				String keyword = null;
				keyword = printKeywordMenu(input);
				getAndPrintFilmByKeyword(keyword);
				
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
		boolean validChoice = false;
		
		while (validChoice == false)
		{
			System.out.println("\n\nPlease enter the number of the option from the following selection:");
			System.out.println("=========================================================================");
			System.out.println("1. Look up a film by its id.");
			System.out.println("2. Look up a film title by any keyword ");
			System.out.println("3. Exit the application ");
			System.out.println("=========================================================================");
			System.out.print("Enter your choice here : ");
			try
			{
				userChoice = Integer.parseInt(input.nextLine());
			}
			catch (NumberFormatException e)
			{
				// throwing error above ignoring now only used for debug
				//e.printStackTrace();
			}
			
			if ((userChoice < 1) || (userChoice > 3))
			{
				System.out.println("\n\nInvalid choice please try again and enter a number 1 thru 3\n\n");
			}
			else
			{
				validChoice = true;
			}
		}
				
		return userChoice;
	} // end method printMenu
	
	private int printLookUpFilmByIdMenu(Scanner input)
	{
		int userChoice = 0;
		int numFilms = 0;
		boolean validChoice = false;
		
		Film films = new Film();
		numFilms = films.getNumberOfFilms();
		
		while (validChoice == false)
		{
			System.out.println("\n\nWe have " + numFilms + " film titles to choose from!");
			System.out.println("Please enter the number the id number for the film you would like:");
			System.out.println("=========================================================================");
			System.out.print("Enter your choice here : ");
			try
			{
				userChoice = Integer.parseInt(input.nextLine());
			}
			catch (NumberFormatException e)
			{
				// throwing error above ignoring now only used for debug
				//e.printStackTrace();
			}
				
			
			if ((userChoice < 1) || (userChoice > numFilms))
			{
				System.out.println("\n\nInvalid choice please try again and enter a number 1 thru " + numFilms + "\n\n");
			}
			else
			{
				validChoice = true;
				
			}
		}
		
		return userChoice;
	} // end method printLookUpFilmByIdMenu
	
	private String printKeywordMenu(Scanner input)
	{
		String userKeyword = null;
		boolean validChoice = false;
		
		while (validChoice == false)
		{
			System.out.println("\n\nWe can search the Titles and Descriptions for any word you would like");
			System.out.println("Note: this search will return a List of films that have the"
								+ "\n\tword alone or as part of another word in it.");
			System.out.println( "Please enter a word you would like to search for");
			System.out.println("=========================================================================");
			System.out.print("Enter your choice here : ");
			userKeyword = input.nextLine();
			
			if (userKeyword.isEmpty())
			{
				System.out.println("\n\nInvalid choice please try again and enter a word you would like to search for.\n\n");
			}
			else
			{
				validChoice = true;
				
			}
		}
		
		return userKeyword;
	} // end method printKeywordMenu
	
	
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
		tmpActorsList = db.findActorsByFilmId(filmId);
		
		System.out.println("\n\nActors for the film id " + filmId +  " titled " +
						film.getTitle() + " are:");
		printActorsList(tmpActorsList);
		
	} // end method getAndPrintAllActorsByFilmId


	
	private void printActorsList(List<Actor> tmpActorsList)
	{
		for (Actor actor : tmpActorsList)
		{
			printActorInformation(actor);
		}
	} // end method printActorsList
	
	
	private void getAndPrintAllActorsByActorId()
	{

		// for testing get actor by actor id
		int maxNumberOfActors = db.countNumberOfAllActors();
		
		for (int theActorId = 1; theActorId <= maxNumberOfActors ; theActorId++)
		{
			Actor actor = db.findActorByActorId(theActorId);
			printActorInformation(actor);
		}
	
	}  // end method getAndPrintAllActorsByActorId
	
	private void getAndPrintAllFilmsAndFilmIds()
	{
		// for testing get film by film id
		int maxNumberOfFilms = db.countNumberOfAllFilms();
		String language = null;
		
		for (int theFilmId = 1; theFilmId <= maxNumberOfFilms; theFilmId++)
		{
			Film film = db.findFilmAndActorsByFilmId(theFilmId);
			printFilmInformation(film);
		}

	} // end getAndPrintAllFilmsAndFilmIds
	
	
	
	private void getAndPrintFilmByFilmId(int filmId)
	{
		Film film = db.findFilmAndActorsByFilmId(filmId);
		printFilmInformation(film);
		
	}  // end method getAndPrintAllFilmsAndFilmIds
	
	private void getAndPrintFilmAndActorsByFilmId(int filmId)
	{
		Film film = db.findFilmAndActorsByFilmId(filmId);
		List<Actor> listOfActors = film.getActors();
		System.out.println("\n\nThe film id you selected is: " + filmId);
		getAndPrintFilmByFilmId(filmId);
		System.out.println("\nThe Actors in the film " + film.getTitle() + " are:");
		printActorsList(listOfActors);
	}
	
	private void getAndPrintFilmByKeyword(String keyword)
	{
		List<Film> filmList = db.findFilmsByKeyword(keyword);
		if ((filmList.isEmpty()) || (filmList == null))
		{
			System.out.println("\n\nI am sorry we could not find any films with your word \"" + keyword
								+ "\" anywhere in the title or description\n");
			System.out.println("Please try again");
		}
		else
		{
			System.out.println("\n\nThe keyword you chose is \"" + keyword + "\"");
			if (filmList.size() == 1)
			{
				System.out.println("There is " + filmList.size() + " film found in your selection.\n");
			}
			else 
			{
				System.out.println("There were " + filmList.size() + " films found in your selection.\n");

			}
			printFilmList(filmList);
		}
	}
	
	private void printFilmList(List<Film> tmpFilmList)
	{
				
		for (Film film : tmpFilmList)
		{
			printFilmInformation(film);
			printActorsList(film.getActors());
		}
	}
	

	private void printFilmInformation(Film film)
	{
		System.out.println("\n\nThe information for film " + film.getId() + " is");
		System.out.println("\tThe title of your film is = " + film.getTitle() 
							+ "\n\tThe year the film was released = " + film.getReleaseYear()
							+ "\n\tThe rating of your film is  = " + film.getRating()
							+ "\n\tThe language your film was created in = " + film.getLanguage()
							+ "\n\tThe description of your film is = \n\t\t" + film.getDescription());
		System.out.println("\n");
	}
	
	private void printActorInformation(Actor actor)
	{
		System.out.println("\tThe actor\'s id = " + actor.getId() + 
				", the actor\'s first name = " + actor.getFirstName() +
				", the actor\'s last name = " + actor.getLastName() + "\n");
	
	}
	
}  // end class Film Query App
