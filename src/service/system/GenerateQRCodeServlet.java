package service.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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

import system.Config;
import system.Key;
import system.Value;

/**
 * 
 */
public class GenerateQRCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int BUFFER_LENGTH = 4096;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenerateQRCodeServlet() {
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
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject returnJson = new JSONObject();
		try {
			String inputStr = request.getParameter(Key.INPUT);
			JSONObject inputJson = (JSONObject) Config.JPARSER.parse(inputStr);
			System.out.println(inputJson.toJSONString());
			String qrCodeData = String.valueOf((long)inputJson.get(Key.STUDENTID));
			System.out.println(qrCodeData);
			
			//String qrCodeData, String filePath, String charset, Map hintMap, int qrCodeheight, int qrCodewidth
			String charset = "UTF-8"; // or "ISO-8859-1"
			Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			int qrCodeheight = 200;
			int qrCodewidth = 200;
			
			String format = "png";
			String fileName = "QR" + qrCodeData +".png";
			FileOutputStream os = new FileOutputStream(new File(System.getenv("OPENSHIFT_DATA_DIR") + fileName));
			//+ uploadDirectory+"/"+folderName+File.separator+folderName+dateString+fileName;
			
			String qr = new String(qrCodeData.getBytes(charset));
			BitMatrix matrix = new MultiFormatWriter().encode(qr, BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
			
			MatrixToImageWriter.writeToStream(matrix, format, os);
			
//			 byte[] bytes = new byte[BUFFER_LENGTH];
//		     int read = 0;
//		     while ((read = is.read(bytes, 0, BUFFER_LENGTH)) != -1) {
//		    	 os.write(bytes, 0, read);
//		     }
//		     os.flush();
//		     os.close();
		     out.println(fileName + " was uploaded to " + System.getenv("OPENSHIFT_DATA_DIR"));
		     //<img src='/QR1.png'/>

			// returnJson =
		} catch (Exception e) {
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		out.println(returnJson.toJSONString());
	}

}
