package com.SupriyaTests.ThoseBabyBasics.Structure;

public class ItemNotInCartException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemNotInCartException() {}
	
	public ItemNotInCartException(String message)
	{
		super(message);
	}
}
