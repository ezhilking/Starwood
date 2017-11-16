package functions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.log4j.Level;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.openqa.selenium.Cookie;

import com.relevantcodes.extentreports.LogStatus;

public class FileUtil {
	protected String SourcePath;
	protected String DestinationPath;
	private String ParsedText = "Error";
	private String ActualTextFile = "";
	private String DownloadedFileName = "";
	private int ValidateText = 0;

	public boolean DispatchFile(){
		URL fileToDownload;
		File downloadedFile;
		HttpGet httpget;
		CloseableHttpClient client = null;
		HttpClientContext localContext;
		HttpResponse response;
		try{
			fileToDownload = new URL(this.SourcePath.replaceAll(" ", "%20").replaceAll("\\^", "%5E"));
			downloadedFile = new File(DestinationPath);
			if (!downloadedFile.canWrite() ) 
				downloadedFile.setWritable(true);
			RequestConfig requestConfig = RequestConfig.custom().setRedirectsEnabled(true).build();

			httpget = new HttpGet(fileToDownload.toURI());
			httpget.setConfig(requestConfig);

			localContext = HttpClientContext.create();
			localContext.setCookieStore(mimicCookieState(Utility.driver.manage().getCookies()));

			client = HttpClients.custom().build();
			client = WrapClient(client);

			//Environment.loger.log(Level.DEBUG,"Sending GET request for: " + httpget.getURI());
			response = client.execute(httpget,localContext);

			//Environment.logger.log(Level.DEBUG,"HTTP GET request status: " + response.getStatusLine().getStatusCode());
			int StatusCode = response.getStatusLine().getStatusCode();
			if(StatusCode == 200){
				//				String ActualFileName = response.getFirstHeader("Content-disposition").getElements()[0].getParameter(0).getValue();
				FileUtils.copyInputStreamToFile(response.getEntity().getContent(), downloadedFile);
				response.getEntity().getContent().close();
				this.DownloadedFileName = downloadedFile.getName();
				Environment.loger.log(Level.INFO, "URL-"+SourcePath);
				Environment.loger.log(Level.INFO, "File downloaded to-"+downloadedFile.getAbsolutePath());
				return true;
			}else{
				Environment.loger.log(Level.INFO, "Status code:"+StatusCode);
				return false;
			}

		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occured",e);
			return false;
		}finally{
			fileToDownload = null;
			downloadedFile = null;
			httpget = null;
			client = null;
			localContext = null;
			response = null;
		}
	}
	public static CloseableHttpClient WrapClient(CloseableHttpClient base){
		try{
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				@Override
				public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {}
				@Override
				public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {                }
				@Override
				public X509Certificate[] getAcceptedIssuers() {return null;}
			};

			X509HostnameVerifier verifier = new X509HostnameVerifier() {
				@Override
				public void verify(String string, SSLSocket ssls) throws IOException {
				}
				@Override
				public void verify(String string, X509Certificate xc) throws SSLException {
				}
				@Override
				public void verify(String string, String[] strings, String[] strings1) throws SSLException {
				}
				@Override
				public boolean verify(String string, SSLSession ssls) {
					return true;
				}
			};

			ctx.init(null, new TrustManager[]{tm}, null);
			//            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
			//            ssf.setHostnameVerifier(verifier);
			SSLConnectionSocketFactory ssf = new SSLConnectionSocketFactory(ctx,verifier);

			//            ClientConnectionManager ccm = base.getConnectionManager();
			//            SchemeRegistry sr = ccm.getSchemeRegistry();//here
			//            sr.register(new Scheme("https", ssf, 443));

			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
					.register("http", PlainConnectionSocketFactory.INSTANCE) 
					.register("https", ssf)
					.build();

			PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			CloseableHttpClient httpclient = HttpClients.custom()
					.setConnectionManager(connManager)
					.build();

			//            return new DefaultHttpClient(ccm, base.getParams());
			return httpclient;
		} catch (Exception ex) {
			return null;
		}
	}

	private BasicCookieStore mimicCookieState(Set<Cookie> seleniumCookieSet) {
		BasicCookieStore mimicWebDriverCookieStore = new BasicCookieStore();
		URL CurrenetURL = null;

		try {
			CurrenetURL = new URL(Utility.driver.getCurrentUrl());
		} catch (MalformedURLException e) {

		}

		for (Cookie seleniumCookie : seleniumCookieSet) {

			BasicClientCookie duplicateCookie = new BasicClientCookie(seleniumCookie.getName(), seleniumCookie.getValue());

			//Set Cookie Domain
			if (seleniumCookie.getDomain() != null){
				duplicateCookie.setDomain(seleniumCookie.getDomain());
			}else{
				String Domain = CurrenetURL.getAuthority();
				int PortIndex = Domain.lastIndexOf(":");
				if (PortIndex != -1){
					Domain = Domain.substring(0,PortIndex);
				}
				duplicateCookie.setDomain(Domain);
			}

			//Set Cookie Path
//			if (seleniumCookie.getPath() != "/"){
				duplicateCookie.setPath(seleniumCookie.getPath());
//			}else{
//				duplicateCookie.setPath("/sgr");
//			}

			//Set Security
			duplicateCookie.setSecure(seleniumCookie.isSecure());

			//Set Expiry
			duplicateCookie.setExpiryDate(null);

			//Add the cookie
			mimicWebDriverCookieStore.addCookie(duplicateCookie);
		}

		return mimicWebDriverCookieStore;
	}

	public ExcelUtil OpenExcel(){
		return new ExcelUtil(this.DestinationPath, "");//Always Select the 1st sheet 
	}

	public void ExtractText(){
		File FileToExtract;
		PDFParser parser;
		PDFTextStripper pdfStripper;
		BufferedReader ExpectedBuffer;
		StringBuffer sb;
		BufferedWriter writer;
		File ActualFile;
		FileInputStream fin = null;
		COSDocument cosDoc = null;
		PDDocument pdDoc =null;	

		try {
			FileToExtract = new File(this.DestinationPath);
			if (this.DestinationPath.endsWith("pdf")){
				//For PDF
				fin = new FileInputStream(FileToExtract);
				parser = new PDFParser(fin);
				parser.parse();
				cosDoc = parser.getDocument();
				pdfStripper = new PDFTextStripper();
				pdDoc = new PDDocument(cosDoc);
				this.ParsedText=  pdfStripper.getText(pdDoc);     // Takes time, to extract text for a Big file.
			}else{
				//For other type of files
			}
			ActualFile = new File(this.DestinationPath.replace(".", "-") + ".txt");
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ActualFile),Charset.forName("UTF-8")));
			ActualTextFile = ActualFile.getName();
			writer.write(ParsedText);
			writer.flush();
			writer.close();
			writer = null;

			ParsedText = ParsedText.replaceAll("\\n", " ");
			ParsedText = ParsedText.replaceAll("\\r", " ");
			//			ParsedText = ParsedText.replaceAll("\u00a0", " ");
			ParsedText = ParsedText.replaceAll(" {1,}", "");
		}catch (Exception e){
			Environment.loger.log(Level.ERROR, "Error occured while extracting text from " + this.DownloadedFileName);
		}finally{
			try {
				if (fin != null){
					fin.close();
					fin = null;
				}
				if (pdDoc != null){
					pdDoc.close();
					pdDoc = null;
				}
				if (cosDoc != null){
					cosDoc.close();
					cosDoc = null;
				}
				FileToExtract = null;
				parser = null;
				cosDoc = null;
				pdfStripper = null;
				pdDoc = null;
				ExpectedBuffer = null;
				sb = null;
				writer = null;
				ActualFile = null;
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
	}
	public boolean ValidateText(String StepID, String ExpectedText){
		String TempParsedText = ParsedText.substring(ValidateText);
		if (FileContainsText(TempParsedText,ExpectedText.replaceAll(" ", ""))){
			FileReporter(StepID, ExpectedText, "PASS");
//			Reporter.WriteLog(Level.DEBUG,DebugMessage);
//			Environment.loger.log(Level.INFO,StepID+": Pass-Expected:"+ExpectedText);
			return true;
		}else{
			FileReporter(StepID, ExpectedText, "FAIL");
			Environment.loger.log(Level.ERROR,StepID+": Fail-Expected:"+ExpectedText);
			return false;
		}
	}

	private boolean FileContainsText(String BiggerText, String SmallerText){
		boolean isPass = false;
		if(BiggerText.contains(SmallerText)){
			int IndexLocation = BiggerText.indexOf(SmallerText) + SmallerText.length();
			ValidateText = ValidateText + IndexLocation;
			isPass = true;
		}
		return isPass;
	}
	
	public void FileReporter(String StepID, String ExpectedText,String Status) {
		Write(StepID, ExpectedText, this.ActualTextFile,Status, this.DownloadedFileName);
	}
	
	
	void Write(String StepName,String Expected,String Actual,String Status,String ScreenshotName){
		String DebugMessage = (new StringBuffer()).append(Status).append(">").append(StepName).append(">").append(Expected).append(">").append(Actual).toString();
		if(Status.equalsIgnoreCase("FAIL")){
			Reporter.logger.log(LogStatus.FAIL, "<br>"+StepName+"</br><b><font color='blue'>Expected:</font></b>"+Expected+"<br><b><font color='blue'>Actual:</font></b></br><a target='_blank' href='Screenshot\\"+Actual+"'> "+Actual+"</a></br>","<a target='_blank' href='Screenshot\\"+ScreenshotName+"'> "+ScreenshotName+"</a>");
			Reporter.WriteLog(Level.ERROR, DebugMessage);
		}else{
			Reporter.logger.log(LogStatus.PASS, "<br>"+StepName+"</br><b><font color='blue'>Expected:</font></b>"+Expected+"<br><b><font color='blue'>Actual:</font></b></br><a target='_blank' href='Screenshot\\"+Actual+"'> "+Actual+"</a></br>","<a target='_blank' href='Screenshot\\"+ScreenshotName+"'> "+ScreenshotName+"</a>");
			Reporter.WriteLog(Level.DEBUG,DebugMessage);
		}
	}
}
