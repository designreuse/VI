package system;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.json.simple.parser.JSONParser;

public class Config {
	public static final JSONParser JPARSER = new JSONParser();
	public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	public static final SimpleDateFormat BIRTHDATE = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final String TIMEZONE = "Singapore";
	
	//Add x days to a Date
	public static Date addDaysToDate(final Date date, long noOfDays) {
	    Date newDate = new Date(date.getTime());

	    GregorianCalendar calendar = new GregorianCalendar();
	    calendar.setTime(newDate);
	    calendar.add(Calendar.DATE, (int)noOfDays);
	    newDate.setTime(calendar.getTime().getTime());

	    return newDate;
	}
	public static final int ONEWEEK = 7;
	public static final int TWOWEEK = 7;
	
	public static final String ROOT = "VI";
	
	//email server configuration
	public static final String HOST = "smtp.gmail.com";
//	public static final String PORT = "587";
	public static final String PORT = "465";
	public static final String USERNAME = "exploreandlearnvi@gmail.com";
	public static final String PASSWORD = "exploreAndLearn";
	
	//TODO change the inputs below
	public static final String ACCESSKEY = "AKIAI4KNTFI3L5YLROAQ";
	public static final String SECRETKEY = "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";
	public static final String BUKETNAME = "symplhrm";
	public static final String S3ROOT = "Sympl";
	public static final String S3URL = "https://s3-ap-southeast-1.amazonaws.com/";
	

}
