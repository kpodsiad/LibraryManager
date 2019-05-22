package pl.kamil.utils;

import java.util.regex.Pattern;

public class Regexes
{
	public static final Pattern YEAR = Pattern.compile("\\d{4}");
	public static final Pattern PHONE = Pattern.compile("\\d{9}");
	public static final Pattern EMAIL = Pattern.compile("^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");
	public static final Pattern ALPHANUMERIC = Pattern.compile("\\w+");
	public static final Pattern ONLY_LETTERS = Pattern.compile("^[a-zA-Z]*$");

}
