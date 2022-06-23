package com.example.BlogApp.payload;

//It is always a good thing to define constants in a seprate file. This helps to keep all constants at one place and no need to initialize default values again and again for each method
//We can simply access this constants using the classname.var_name syntax in our App imppementation code.
public class AppConstants {

	//we are initializing default constant values here, so that incase we use pagination and sorting in some other controller we dont have to set value again we can simply access this values.
	public static final String PAGE_SIZE = "5";
	public static final String PAGE_NUM = "1";
	public static final String SORTbY = "title";
}
