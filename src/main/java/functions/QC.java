/* Purpose		:QC related reusable methods
 * Developed By	:Ezhilarasan.S
 * Modified By	:
 * Modified Date:
 * Reviewed By	:
 * Reviewed Date:
 */
package functions;

import org.apache.log4j.Level;

import com.jacob.com.LibraryLoader;

import atu.alm.wrapper.ALMServiceWrapper;
import atu.alm.wrapper.ITestCase;
import atu.alm.wrapper.enums.StatusAs;
import atu.alm.wrapper.exceptions.ALMServiceException;

public class QC {
	static ALMServiceWrapper wrapper;

	public QC(String Username, String Password, String Domain, String Project) {
		try{
			System.setProperty("jacob.dll.path", Environment.LibraryPath+"\\jacob-1.18-x86.dll");
			LibraryLoader.loadJacobLibrary();
			wrapper = new ALMServiceWrapper("http://10.132.170.53:8080/qcbin");
			wrapper.connect(Username,Password,Environment.Tower,Project);
		}catch(ALMServiceException e){
			Environment.loger.log(Level.ERROR, "Connection not established with QC!");
		}catch(Exception e){
			Environment.loger.log(Level.ERROR, "Exception occurred",e);
		}
	}

	public static ITestCase UpdateResult(String testSetFolderPath, String testSetName, int testSetID, String tcName, StatusAs as){
		try{
			ITestCase IC = wrapper.updateResult(testSetFolderPath, testSetName, testSetID, tcName, as);
			Environment.loger.log(Level.INFO, "TESTCASE:'"+testSetID+"' Updated with status as ");
			return IC;
		}catch (ALMServiceException e) {
			Environment.loger.log(Level.ERROR, "Exception occurred",e);
		}catch(Exception e){
			throw e;
		}
		return null;
	}

	public void UpdateResultAndAttachFile(String testSetFolderPath, String testSetName, int testSetID, String tcName, StatusAs as,String ScreenshotPath,String AttachmentDescription){
		try{
			ITestCase IC = UpdateResult(testSetFolderPath, testSetName, testSetID, tcName, as);
			wrapper.newAttachment(ScreenshotPath, AttachmentDescription, IC);
		}catch (ALMServiceException e) {
			Environment.loger.log(Level.ERROR, "Exception occurred",e);
		}catch(Exception e){
			throw e;
		}
	}
	
//	public void CreateRunAndAddStep(){
//	wrapper.createNewRun(it, "Run 1",StatusAs.PASSED);//Create Run will create Run in the TC. its not required here. And for this ITestCase return type is mandatory from UpdateResult. So once the UpdateResult called then we have to use the return type if that inthis method.
//	wrapper.addStep(loginRun, "Automation Sample step2", StatusAs.FAILED, "AddStepDescription", "Expected111", "Actual222");
//	}

}
