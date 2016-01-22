package service.system;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import controller.TeacherCtrl;
import model.Student;
import system.Config;
import system.Key;
import system.Value;

/**
 * Servlet implementation class GenerateQRCode
 */
@WebServlet("/GenerateQRCode")
public class GenerateQRCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenerateQRCode() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject returnJson = new JSONObject();
		try {
			String inputStr = request.getParameter(Key.INPUT);
			JSONObject inputJson = (JSONObject) Config.JPARSER.parse(inputStr);
			System.out.println(inputJson.toJSONString());
			//String qrCodeData, String filePath, String charset, Map hintMap, int qrCodeheight, int qrCodewidth
			Student student = (Student)inputJson.get(Key.STUDENT);
			String qrCodeData = (String) String.valueOf(student.getStudentId());
			String filePath = "";
			String charset = "UTF-8"; // or "ISO-8859-1"
			Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			int qrCodeheight = 200;
			int qrCodewidth = 200;

			BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset), charset),
					BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
			MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1),
					new File(filePath));

			// returnJson =
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		out.println(returnJson.toJSONString());
	}

}
