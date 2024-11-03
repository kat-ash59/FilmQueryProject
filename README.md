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

note: with user story #4 added language to printout

note: with user story #5 added actor list to printout


#### User story #3
completed 10/29/24

If the user looks up a film by search keyword, they are prompted to enter it. If no matching films are found, they see a message saying so. Otherwise, they see a list of films for which the search term was found anywhere in the title or description, with each film displayed exactly as it is for User Story 2.

note: with user story #4 added language to printout

note: with user story #5 added actor list to printout

#### User story #4
completed 10/29/24

When a film is displayed, its language (English,Japanese, etc.) is displayed, in addition to the film's title, year, rating, and description.

note: with user story #5 added actor list to printout

#### User story #5
completed 10/29/24

When a film is displayed, the list of actors in its cast is displayed, in addition to the film's title, year, rating, description, and language.


#### Stretch Goal #1
completed 11/1/24 - but only for singleton film selection not for keyword films

When a film is displayed, the user can choose from a sub-menu whether to:

1) Return to the main menu.

2) View all film details.

If they choose to view all details, they will see all column values of the film record they chose.

#### Stretch Goal #2
completed 11/1/24

When viewing film details, the user will see a list of all the film's categories (Family, Comedy, etc.) for the film.

#### Stretch Goal #3
did not complete

When viewing film details, the user will see a list of all copies of the film in inventory, with their condition.

#### Stretch Goal #4
did not complete

Write JUnit tests for DatabaseAccessorObject's methods.


### Homework portion of work part duex!
This portion continues forward with CRUD (Create, Read, Update, and Delete data in a database) for this project.


#### User Story #1B
completed in lab class 11/2/24

In your DAO (This Film Query App) code, implement a createFilm(Film aFilm) method that takes a newly created Film object and inserts it into the database.

required fields are:

	title, language_id - must have

	description -  desired

The method should return the persisted Film object on success, or null if the insert fails.

For now ignore a Film's categories and actors, and just hardcode the Film's language_id to 1 (English).

Be certain to capture the newly generated id of the persisted Film and assign it to the returned Film object before returning from the method.


In your app, add an "Add New Film" item to your menu. When chosen, prompt the user to input the required film attributes.

The required attributes are those marked in the database as being both non-Nullable and having no default value provided.

Use the user's inputs to create a new Film object, and pass the new Film object to your DAO's createFilm().

On a successful return, display a success message with the generated id of the newly persisted film.


#### User Story #2B
completed 11/2/24

In your DAO code, implement deleteFilm(Film aFilm) that takes a Film as its parameter.

The method should return true upon successful deletion or false if the delete fails.
In your app, add a "Delete Film" item to your menu. When chosen, prompt the user to input the id of the film to delete.

How will your code provide the Film object associated with the requested id?

To avoid parent-child (foreign key issues), test your delete using only ids of films your program has created.


#### User Story #3B

In your DAO code, implement updateFilm(Film aFilm) that takes a Film as its parameter.

The method should return true upon successful update or false if the update fails.
In your app, add an "Update Film" item to your menu. When chosen, prompt the user to input the id of the film to update, allowing (for simplicity) updates on either the film's title and/or the film's description.

How will your code provide the Film object associated with the requested id?
