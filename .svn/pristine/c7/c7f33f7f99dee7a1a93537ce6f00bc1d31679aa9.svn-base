package functions;
/**
 * Purpose		: Utility to handle SOAP requests
 * Author		: Pramod K
 * Created Date	: 21-Jan-2017
 * Modified	on	: 04-May-2017
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.xml.soap.Detail;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Level;
import org.w3c.dom.NodeList;

public class SoapUtility extends Utility{
	
protected static  String RequestFilePath=Environment.DataPath+"\\XML\\"; 
protected static  String ResponseFilePath=Reporter.ScreenshotPath;
static String XMLFileName;
	//Get the soap request and return the soap response
	public static SOAPMessage getSOAPResponse(SOAPMessage XMLSOAPRequest, String EndPointURL){
		try {
			ResponseFilePath=Reporter.ScreenshotPath;
			ResponseFilePath=ResponseFilePath+XMLFileName+"_"+Reporter.GetTimeStamp("ddMMMYYYYHHmmss")+"_Response.xml";
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			// Send SOAP Message to SOAP Server
			SOAPMessage soapResponse = soapConnection.call(XMLSOAPRequest, EndPointURL);
			writeSOAPResponseToFile(soapResponse, ResponseFilePath);
			
			soapConnection.close();
			return soapResponse;

		} catch (Exception e) {
			Environment.loger.log(Level.ERROR, "Exception Occured! "+e);
			
		} 
		return null;
	}
	//To get the SOAP message from the xml file 
		public static SOAPMessage getSOAPRequest(String XmlFileName){
			XMLFileName=XmlFileName.substring(0, XmlFileName.lastIndexOf("."));
			ResponseFilePath=Reporter.ScreenshotPath;
			try {
				byte[] encoded = Files.readAllBytes(Paths.get(RequestFilePath+XmlFileName));
				InputStream bStream = new ByteArrayInputStream(encoded);
				SOAPMessage request = MessageFactory.newInstance().createMessage(null, bStream);
				return request;
			} catch (Exception e) {
				Environment.loger.log(Level.ERROR, "Exception Occured! "+e);
			} 
			return null;
		}
	//To validate SOAP response for fault 
	public static boolean validateSOAPResponseForFault(SOAPMessage soapResponse){
		try {
			
			SOAPBody sbody= soapResponse.getSOAPBody();
			if(sbody.hasFault()){
				SOAPFault sfault=sbody.getFault();
				String FaultString=sfault.getFaultString();
				Environment.loger.log(Level.ERROR, "Error in Response -- "+ FaultString);
				if(sfault.hasDetail()){
					Detail sDetail=sfault.getDetail();
					Environment.loger.log(Level.ERROR, "Response Error Details-- "+ sDetail.getTextContent());
				}
				return false;
			}
		} catch (SOAPException e) {
			Environment.loger.log(Level.ERROR, "Exception Occured! "+e);
		}
		return true;
	}

	// TO get the Particular XML element from the SOAP Response/Request
	public static String getXMLElementText(SOAPMessage soapResponse, String ParentNodeName, String NodeName ){
		String Result="";
		try {
			NodeList returnList =soapResponse.getSOAPBody().getElementsByTagName(ParentNodeName);
			NodeList innerResultList = returnList.item(0).getChildNodes();
			for (int node = 0; node < innerResultList.getLength(); node++) {
				if (innerResultList.item(node).getNodeName().equalsIgnoreCase(NodeName)) {
					Result=innerResultList.item(node).getTextContent().trim();
					break;        
				}
			}

		} catch (Exception e) {
			Environment.loger.log(Level.ERROR, "Exception Occured! "+e);
		} 
		return Result;
	}
	//TO Set the Particular XML element from the SOAP Request 
	public static SOAPMessage setXMLElementText(SOAPMessage soapRequest, String ParentNodeName, String NodeName , String ValueToSet){

		try {
			NodeList returnList =soapRequest.getSOAPBody().getElementsByTagName(ParentNodeName);
			NodeList innerResultList = returnList.item(0).getChildNodes();
			for (int node = 0; node < innerResultList.getLength(); node++) {
				if (innerResultList.item(node).getNodeName().equalsIgnoreCase(NodeName)) {
					innerResultList.item(node).setTextContent(ValueToSet);
					break;        
				}
			}

		} catch (Exception e) {
			Environment.loger.log(Level.ERROR, "Exception Occured! "+e);
		} 
		return soapRequest;

	}
	//TO Set the All Occurrence of given XML element from the SOAP Request 
	public static SOAPMessage setXMLElementTextForAll(SOAPMessage soapRequest, String ParentNodeName, String NodeName , String ValueToSet){
		try {
			NodeList returnList =soapRequest.getSOAPBody().getElementsByTagName(ParentNodeName);
			for(int Pnode=0; Pnode <returnList.getLength();Pnode++){
				NodeList innerResultList = returnList.item(Pnode).getChildNodes();
				for (int node = 0; node < innerResultList.getLength(); node++) {
					if (innerResultList.item(node).getNodeName().equalsIgnoreCase(NodeName)) {
						innerResultList.item(node).setTextContent(ValueToSet);
					}
				}
			}
		} catch (Exception e) {
			Environment.loger.log(Level.ERROR, "Exception Occured! "+e);
		} 
		return soapRequest;

	}
	//To Print SOAP Response to console 
	public static void printSOAPResponse(SOAPMessage soapResponse){
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			Source sourceContent = soapResponse.getSOAPPart().getContent();
			System.out.print("\nResponse SOAP Message = ");
			StreamResult result = new StreamResult(System.out);
			transformer.transform(sourceContent, result);
			
		} catch (Exception e) {
			Environment.loger.log(Level.ERROR, "Exception Occured! "+e);
		}

	}	
	//to write SOAP response to file 
	public static void writeSOAPResponseToFile(SOAPMessage soapResponse, String filepath) {
		try {
			File file = new File(filepath);
			FileOutputStream os = new FileOutputStream(file);
			soapResponse.writeTo(os);
			Environment.loger.log(Level.INFO, "SOAP Response is saved to - "+filepath);
		} catch (Exception e) {
			Environment.loger.log(Level.ERROR, "Exception Occured! "+e);
		}

	}	
	//Convert Soap Message to string 
	public static String SOAPMessageToString(SOAPMessage message){
		String result = null;
		if (message != null) {
			ByteArrayOutputStream baos = null;
			try{
				baos = new ByteArrayOutputStream();
				message.writeTo(baos); 
				result = baos.toString();
			} 
			catch (Exception e){
				e.printStackTrace();
			} 
			finally {
				if (baos != null){
					try{
						baos.close();
					} 
					catch (IOException ioe){
						ioe.printStackTrace();
					}
				}
			}
		}
		return result;
	}
	
	//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-
	public String GetValueFromExcel(String TestCaseName){
		ExcelUtil Excel = LoadExcel("InputDatasheet.xlsx", "");
		int RowIndex = Excel.GetRowIndex(2, TestCaseName);
		int ColumnIndex = Excel.GetColIndex(1, "Result");
		if(RowIndex!=-1 && ColumnIndex!=-1)
			return Excel.GetData(RowIndex, ColumnIndex);
		else{
			Reporter.WriteLog(Level.FATAL, "Testcase name or result column doesnt match with the excel");
			return null;
		}
	}
	
	
}
