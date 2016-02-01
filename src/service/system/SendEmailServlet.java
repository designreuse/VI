package service.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Parent;
import model.Student;

import org.json.simple.JSONObject;

import system.Config;
import system.Key;
import system.Value;

/**
 * @author RaySong
 */
public class SendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendEmailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	protected void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject returnJson = new JSONObject();
		
		//send email to parent
		try {
			String inputStr = request.getParameter(Key.INPUT);
			JSONObject inputJson = (JSONObject) Config.JPARSER.parse(inputStr);
			System.out.println(inputJson.toJSONString() + " JSON");
			
			//Get the parent from the database base on student Id;
			String parentName = (String)inputJson.get(Key.PARENTNAME);
			String studentName = (String) inputJson.get(Key.STUDENTNAME);
			
			// Recipient's email
			String to = (String) inputJson.get(Key.EMAIL);
			String teacherName = (String)inputJson.get(Key.TEACHERNAME);
			
			//email server configuration
		   
			// Sender's email ID needs to be mentioned
//			String from = Config.OUTBOX;

			//set up the host of email server
			String host = Config.HOST;
			String username = Config.USERNAME;
			String password = Config.PASSWORD;

			Properties props = new Properties();
			props.setProperty("mail.smtp.ssl.enable", "true");
			// set any other needed mail.smtp.* properties here
			Session session = Session.getInstance(props);
			
//			props.put("mail.smtp.auth", "true");
//			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", Config.PORT);
			// Get the Session object.
//			Session session = Session.getInstance(props,
//					new javax.mail.Authenticator() {
//						protected PasswordAuthentication getPasswordAuthentication() {
//							return new PasswordAuthentication(username,
//									password);
//						}
//					});

			// Set response content type

			try {
				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(username));

				// Set To: header field of the header.
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(to));
				
				// Set Subject: header field
				message.setSubject("[Explore And Learn] Attendance notification.");
				
				//ADD IN TEACHER NAME
				// Now set the actual message in html format
				message.setText("Hello " + parentName + ", " +
						"\n\nYour child " + studentName + " has attended class.\n\n\n" +
						"\n\nPlease DO NOT reply to this email directly! As it is a computer genrated message.");
				
				//message.setContent(
				//	"<h1>This is actual message embedded in HTML tags</h1>"
				//	,"text/html");
				
				// Send message
				Transport.send(message, username, password);
				
				System.out.println("Sent message successfully....");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		out.println(returnJson.toJSONString());
	}
}
