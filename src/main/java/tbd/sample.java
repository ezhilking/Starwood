package tbd;

import java.io.File;
import java.io.IOException;

public class sample {
public static void main(String[] args) throws IOException {
//	File file = new File("C:\\Automation\\Software\\Eclipse\\eclipseWthAllplugins\\eclipse.exe");
//	Process p = Runtime.getRuntime().exec(file.getAbsolutePath());
	String a="9142034455EXT255";
	
	System.out.println(a.substring(0, a.indexOf("EXT")));
	System.out.println(a.substring(a.indexOf("EXT")+"EXT".length(),a.length()));
//	System.out.println(a.replaceAll("^\\d", ""));
//	System.out.println(a.replaceAll("\\d", ""));
//	System.out.println(a.replaceAll("[a-zA-Z]", ""));
//	System.out.println(a.split("\\d")[0]);
}
}
