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
	private int firstLaunch = 0;
	

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
		// actor stuff
		System.out.println("\n\n=======================================================================");
		System.out.println("\n\nPrinting out all movies and their actors");
		getAndPrintAllActorsInAllMovies();
		System.out.println("\n\n=======================================================================");
		System.out.println("\n\nPrinting out all actors by film id");
		getAndPrintAllActorsByFilmId(filmId); 
		System.out.println("\n\n=======================================================================");
		System.out.println("\n\nPrinting out all actors and their actor id\'s");
		getAndPrintAllActorsByActorId();
		

		
		// film stuff
		System.out.println("\n\n=======================================================================");
		System.out.println("\n\nPrinting out all films and their film id\'s");
		getAndPrintAllFilmsAndFilmIds();
		System.out.println("=======================================================================");
		System.out.println("Printing a film with a specific film id = 1");
		getAndPrintFilmByFilmId(filmId);
		System.out.println("=======================================================================");
		System.out.println("Printing a film and actors with a specific film id = 1");
		getAndPrintFilmAndActorsByFilmId(filmId);
		System.out.println("=======================================================================");
		System.out.println("Printing a film and actors with a keyword");
		getAndPrintFilmByKeyword("Bull");
		
		
		
		
	}  // end method test

	private void launch() 
	{
		Scanner input = new Scanner(System.in);

		startUserInterface(input, firstLaunch);
		
		// System.out.println("Just before input.close()");
		input.close();
	} // end method launch

	private void startUserInterface(Scanner input, int firstLaunch)
	{
		int userChoice = 0;
		
		if (firstLaunch == 0)
		{
			System.out.println("\nWelcome to the Film Query App!\n");
			firstLaunch = 1;
		}
		userChoice = printMainMenu(input);
		
		while (userChoice != 5)
		{
			if (userChoice == 1)
			{
				int filmId = 0;
				filmId = printLookUpFilmByIdMenu(input);
				getAndPrintFilmAndActorsByFilmId(filmId);
				//System.out.println("before call to printFullFilmDescriptionMenu");
				printFullFilmDescriptionMenu(input,filmId);
			} // end if choose to get film by film id  and print full film description
			else if (userChoice == 2)
			{
				String keyword = null;
				keyword = printKeywordMenu(input);
				getAndPrintFilmByKeyword(keyword);				
			} // end else if choose keyword search
			else if (userChoice == 3)
			{
				Film film = new Film();
				film = printAddFilmMenu(input, film);
				film = db.createFilm(film);
				int filmId = film.getId();
				getAndPrintFilmByFilmId(filmId);
				
			} // end if choose add film
			else if (userChoice == 4)
			{
				//System.out.println("\nJust inside user choice to delete film");
				List<Film> filmList = null;
				filmList = db.getListOfAllFilms();
				//System.out.println("Just after get list of all films in delete films");
				//System.out.println("filmList = " + filmList);
				if (filmList != null)
				{
					
					//System.out.println("\nJust after get list of films");
					
					boolean deleteSuccessful = false;
					int filmIdToDelete = 0;
					String filmTitleToDelete = null;
					
					Film film = new Film();
					//System.out.println("\njust before print delete film menu\n");
					film = printDeleteFilmMenu(input, filmList);
					//System.out.println("\nJust after print delete film menu");
					
					filmIdToDelete = film.getId();
					//System.out.println("\njust after get id for film to delete");
					
					filmTitleToDelete = film.getTitle();
							
					deleteSuccessful = db.deleteFilm(film);
					
					if (deleteSuccessful == true)
					{
						System.out.println("You have successfully deleted the film titlee = " + filmTitleToDelete);
					}
					else
					{
						System.out.println("There was an error deleting the film with film id = " + filmIdToDelete  + "\n" 
										+ "\tand title = " + filmTitleToDelete);
					}
				} // end if filmList = null
				
			} // end else if choose delete
			else
			{
				userChoice = 5;
			} // end default user exit
		
			userChoice = printMainMenu(input);

		} // end while loop
		
		System.out.println("\n\nThank-you for visiting our Film Query App!");
		System.out.println("We hope you enjoyed the information we provided.");
		System.out.println("Please visit us again soon.");

	}  // end method startUserInterface
	
	

	private int printMainMenu(Scanner input)
	{
		int userChoice = 0;
		boolean validChoice = false;
		
		while (validChoice == false)
		{
			System.out.println("\n\nPlease enter the number of the option from the following selection:");
			System.out.println("=========================================================================");
			System.out.println("1. Look up a film by its id.");
			System.out.println("2. Look up a film title by any keyword ");
			System.out.println("3. Add a film to the library");
			System.out.println("4. Choose a film to delete from the library");
			System.out.println("5. Exit the application ");
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
			
			if ((userChoice < 1) || (userChoice > 5))
			{
				System.out.println("\n\nInvalid choice please try again and enter a number 1 thru 4\n\n");
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
	

	private void printFullFilmDescriptionMenu(Scanner input,int filmId)
	{
		int userChoice = 0;
		boolean validChoice = false;
		
		
		while (validChoice == false)
		{
			System.out.println("You can see the full list of the film\'s available information or return to the Main Menu");
			System.out.println("Please enter the number 1 if you would like to see the full list of available film information:");
			System.out.println("Please enter the number 2 if you would like to return to the Main Menu");
			System.out.print("Enter your choice here 1 or 2 : ");
			try
			{
				userChoice = Integer.parseInt(input.nextLine());
			}
			catch (NumberFormatException e)
			{
				// throwing error above ignoring now only used for debug
				//e.printStackTrace();
			}
				
			
			if ((userChoice < 1) || (userChoice > 2))
			{
				System.out.println("\n\nInvalid choice please try again and enter a number 1 or 2\n\n");
			}
			else if (userChoice == 1)
			{
				printFullFilmDescription(filmId);
				validChoice = true;			
			}
			else if (userChoice == 2)
			{
				validChoice = true;
				startUserInterface(input, 1);
			}
		}
		
	} // end method printFullFilmDescriptionMenu


	private Film printAddFilmMenu(Scanner input, Film film) 
	{
		boolean userChoice = false;
		String title = null;
		String description = null;
		
		System.out.println("\n\nYou will be asked to enter your Film\'s title and description:");
		System.out.println("=========================================================================");
		
		
		
		while (userChoice == false)
		{
			
			System.out.print("Please enter your title here : ");
			
			title = input.nextLine();
			
			if ((title.length() > 255) || (title.isEmpty()))
			{
				System.out.println("Your title must be less than or equal to 255 characters");
				System.out.println("and must not be blank or just contain blank spaces)");
				System.out.println("Please try again");
			}
			else
			{
				film.setTitle(title);
				userChoice = true;
			}
		} // end while loop for setting title
		
		userChoice = false;
		
		while (userChoice == false)
		{
			
			System.out.print("Please enter your film description here : ");
			
			description = input.nextLine();
			if (description.isEmpty())
			{
				System.out.println("Your description must not be blank or just contain blank spaces)");
				System.out.println("Please try again");
			}
			else
			{
				film.setDescription(description);
				userChoice = true;
			}
		} // end while loop for setting description
		
		return film;
	} // end method printAddFilmMenu
	
	
	private Film printDeleteFilmMenu(Scanner input, List<Film> filmList) 
	{
		//System.out.println("just inside printdeletefilmmenu");
		boolean userChoice = false;
		int filmId = 0;
		int numberOfFilms = db.countNumberOfAllFilms()+1;
		//System.out.println("\njust after getting the number of films in printDeleteFilmMenu");
		//System.out.println("number of films is " + numberOfFilms);
		Film selectedFilm = new Film();
		
		System.out.println("\n\nThe list of Films and their Titles that you can choose to delete are: \n");
		System.out.println("======================================================================================\n");
				
		printFilmListByIdAndTitle(filmList);
		
		System.out.println("\n\nFrom the list of films above please enter the film id you would like to delete:");
		System.out.println("======================================================================================");
		
		while (userChoice == false)
		{
			
			System.out.print("Please enter the Film Id that you want to delete here : ");
			
			userChoice = false;
		
			try
			{
				filmId = Integer.parseInt(input.nextLine());
			}
			catch (NumberFormatException e)
			{
				// throwing error above ignoring now only used for debug
				//e.printStackTrace();
			}
			int  maxFilmId = db.getMaxIdFromFilmTable();
			if ((filmId < 1000 ) || (filmId > maxFilmId))
			{
				System.out.println("Your selected an invalid film id");
				System.out.println("Please try again");
			}
			else
			{
				selectedFilm = db.findFilmAndActorsByFilmId(filmId);
				userChoice = true;
			}
		} // end while loop for getting film to delete
		
		return selectedFilm;

	}

	
	private void printFilmListByIdAndTitle(List<Film> filmList) 
	{
		//System.out.println("\n\nJust inside printFilmListByIdAndTitle\n");
		//System.out.println("the size of the filmlist is " + filmList.size());
		for (Film film : filmList)
		{
			// when building the film list may have null entries for some film id's
			// don't want to print those out
			if (film != null)
			{
				System.out.println("\tThe films\'s id = " + film.getId() + 
									", the film\'s title = " + film.getTitle() + "\n");
			}
		}
		
	} // end printFilmListIdAndTitle

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
	
	
	private void printActorsList(List<Actor> tmpActorsList)
	{
		for (Actor actor : tmpActorsList)
		{
			printActorInformation(actor);
		}
	} // end method printActorsList
	
	
	private void printActorInformation(Actor actor)
	{
		System.out.println("\tThe actor\'s id = " + actor.getId() + 
				", the actor\'s first name = " + actor.getFirstName() +
				", the actor\'s last name = " + actor.getLastName() + "\n");
	
	}
	
	private void getAndPrintAllFilmsAndFilmIds()
	{
		// for testing get film by film id
		int maxNumberOfFilms = db.countNumberOfAllFilms();
		
		for (int theFilmId = 1; theFilmId <= maxNumberOfFilms; theFilmId++)
		{
			Film film = db.findFilmAndActorsByFilmId(theFilmId);
			printFilmInformation(film);
		}
		

	} // end getAndPrintAllFilmsAndFilmIds
	
	
	
	private void getAndPrintFilmByFilmId(int filmId)
	{
		Film film = db.findFilmAndActorsByFilmId(filmId);
		if (film != null)
		{
			System.out.println("\n\nYou have successfully added your film to our libary\n");
			printFilmInformation(film);
		}
		
		
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
		int filmID = 0;
		
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
				System.out.println("There are " + filmList.size() + " films found in your selection.\n");

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
							+ "\n\tThe film\'s category is = " + film.getCategory()
							+ "\n\tThe description of your film is = \n\t\t" + film.getDescription());
		System.out.println("\n");
	}
	
	private void printFullFilmDescription(int filmId)
	{
		Film film = new Film();
		film = db.findFilmAndActorsByFilmId(filmId);
		System.out.println("\n\nThe available information for your film is: \n\n");
		System.out.println(film.toString());
		System.out.println("\n\n");
	}
	

	
}  // end class Film Query App
