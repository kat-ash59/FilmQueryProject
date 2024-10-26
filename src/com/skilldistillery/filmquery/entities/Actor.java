package com.skilldistillery.filmquery.entities;

import java.util.Objects;

public class Actor 
{
	private int id;
	private String firstName;
	private String lastName;
	
	// constructors
	public Actor() 
	{
	} // end no arg constructor


	public Actor(int sagMemeberNumber) 
	{
		super();
		this.id = sagMemeberNumber;
	} // end constructor


	public Actor(String fn, String ln) 
	{
		super();
		this.firstName = fn;
		this.lastName = ln;
	}  // end constructor


	public Actor(int sagMemeberNumber, String fn, String ln) 
	{
		super();
		this.id = sagMemeberNumber;
		this.firstName = fn;
		this.lastName = ln;
	}  // end constructor


	// getters and setters
	public int getId() 
	{
		return id;
	} // end getId


	public void setId(int id) 
	{
		this.id = id;
	}  // end setId


	public String getFirstName() 
	{
		return firstName;
	}


	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}


	public String getLastName() 
	{
		return lastName;
	}


	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}


	@Override
	public int hashCode() 
	{
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		return id == other.id;
	}


	@Override
	public String toString() 
	{
		return "Actor [id= " + id + ", firstName= " + firstName + ", lastName=" + lastName + ", getId() = " + getId()
				+ ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", hashCode()="
				+ hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}
	

}  // end class actor
