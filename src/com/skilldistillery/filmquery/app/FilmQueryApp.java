package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
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
	
		/*
		Film film = db.findFilmById(theFilmId);
		System.out.println("\n\n The film you selected is:");
		System.out.println(film);
		System.out.println("\n\n");
		*/
		
		for (int theFilmId = 1; theFilmId <= 1000; theFilmId++)
		{
			Film film = db.findFilmById(theFilmId);
			db.findFilmById(theFilmId);
			System.out.println("\n\nFilm title " + film.getTitle() + " description " + film.getDescription());
		}
		
		
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

}  // end class Film Query App
