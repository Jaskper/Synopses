import java.io.*;
import java.util.*;


public class Parser {

	public static String getVerbInfo(String inputVerb) throws IOException{
		String wordsPath = System.getProperty("user.dir")+"\\Words";
		Runtime rt = Runtime.getRuntime();
	    Process commandProcess = rt.exec("cmd.exe /c cd " + wordsPath + " & words.exe " + inputVerb);
		InputStream cmdStream = commandProcess.getInputStream();
		return convertStreamToString(cmdStream);
	}
	
	public static String convertStreamToString(java.io.InputStream is){
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("//A");
		return s.hasNext() ? s.next() : "";
	}
	
	public static String[] parseVerb(String inputVerb) throws IOException{
		String verbInfo = getVerbInfo(inputVerb);
		String[] parsedInfo = verbInfo.split("\\s+");
		return parsedInfo;
	}
	
	public static boolean isVerb(String[] inputInfo){
		if(!inputInfo[1].equals("V")){
			return false;
		}else{
			return true;
		}
	}
	

}
