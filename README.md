# FilmQueryProject

### Description
This project is learning about jdbc and how to interact with mssql databases via java.  It
opens the door into the world of storing data and getting data into a database.  Java in 
it infinite wisdom has taken work from multiple people and sources and added it into its
vocabulary and allowed the world to work towards cleaner code without having to re-invent
the wheel. 

### Technologies Used
mssql database sdvid -  using queries, joins, inserts, updates and all 
the wonderful world of sql which was introduced earlier in labs.  The earlier work
laid the foundation to understanding how it works and now expanding it into working 
with java.

jdbc - although this is light-weight knowledge it has given me a better understanding
of some of the ways that I currently use it at work for connections into the databases
I work with on a daily basis.


### Lessons Learned
always always always test queries in myssql or some other tool before you start using
them in code.  make sure to thoroughly understand table format, data structures, column names, field dependencies......

and when you are cutting and pasting you queries from mysql workbench and putting over
multiple lines make sure you have spaces betwen each word!!!!



### Lab portion of work
#### User Story #1
Completed in class

FilmQuery/com.skilldistillery.filmquery.entities

Complete the Film class with attributes that map to the columns of the film table. Use appropriate datatypes and Java naming conventions. Provide getters and setters, and appropriate constructors. Also add a good toString, and equals and hashCode methods.

Define an Actor class with id, first name, and last name fields. Include the usual getters, setters, toString, etc (of course!), and appropriate constructor(s).

#### User Story #2
Completed in class

FilmQuery/com.skilldistillery.filmquery.database.DatabaseAccessor

With the Actor class implemented, uncomment the two commented methods in the DatabaseAccessor interface.

#### User Story #3
Completed 10/27/24

FilmQuery/com.skilldistillery.filmquery.database.DatabaseAccessorObject

All JDBC code will be in methods of the DatabaseAccessorObject class.

1) Implement the findFilmById method that takes an int film ID, and returns a Film object (or null, if the film ID returns no data.)

2) Implement findActorById method that takes an int actor ID, and returns an Actor object (or null, if the actor ID returns no data.)

3) Implement findActorsByFilmId with an appropriate List implementation that will be populated using a ResultSet and returned.

Make sure your JDBC code uses PreparedStatement with bind variables instead of concatenating values into SQL strings.

#### User Story #4
Completed 10/27/24

Modify your Film class to include a List of actors for the film's cast.

When a film is retrieved from the database, its actors are also retrieved and included in the Film object.


#### User Story #5
Completed 10/27/24

FilmQuery/com.skilldistillery.filmquery.app.FilmQueryApp

Test your code by running FilmQueryApp.


### Homework portion of work
Now down to the nitty gritty homework that is going to be graded!

#### User story #1
completed 10/29/24

The user is presented with a menu in which they can choose to:

	1) Look up a film by its id.
	2) Look up a film by a search keyword.
	3) Exit the application.


#### User story #2
completed 10/29/24

If the user looks up a film by id, they are prompted to enter the film id. If the film is not found, they see a message saying so. If the film is found, its title, year, rating, and description are displayed.


#### User story #3
completed 10/29/24

If the user looks up a film by search keyword, they are prompted to enter it. If no matching films are found, they see a message saying so. Otherwise, they see a list of films for which the search term was found anywhere in the title or description, with each film displayed exactly as it is for User Story 2.

#### User story #4
completed 10/29/24

When a film is displayed, its language (English,Japanese, etc.) is displayed, in addition to the film's title, year, rating, and description.

#### User story #5
completed 10/29/24

When a film is displayed, the list of actors in its cast is displayed, in addition to the film's title, year, rating, description, and language.
