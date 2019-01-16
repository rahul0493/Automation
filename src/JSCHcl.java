
import com.jcraft.jsch.*;

import java.io.*;
import java.util.*;


 
public class JSCHcl
{
	 public void replace(String oldFileName,String tmpFileName) {
	     /* String  = "try.dat";
	      String tmpFileName = "tmp_try.dat";

	      PropertiesConfiguration config = new PropertiesConfiguration();
	 	 System.out.println("config.getEncoding()1"+config.getEncoding());
	 	 PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
	 	 File file = new File(tempDirectory+1); 
	 	 System.out.println("config.getEncoding()2"+config.getEncoding());
	 	 layout.load(new InputStreamReader(new FileInputStream(file)));*/
	      BufferedReader br = null;
	      BufferedWriter bw = null;
	      try {
	         br = new BufferedReader(new FileReader(oldFileName));
	         bw = new BufferedWriter(new FileWriter(tmpFileName));
	         String line;
	         while ((line = br.readLine()) != null) {
	            if (line.contains("\\"))	           
	               line = line.replace("\\", "\\\\");
	            if (line.contains(","))
	            	line = line.replace(",", "\\,");
	            
	            bw.write(line+"\n");
	         }
	         
	      } catch (Exception e) {
	         return;
	      } finally {
	         try {
	            if(br != null)
	               br.close();
	         } catch (IOException e) {
	            //
	         }
	         try {
	            if(bw != null)
	               bw.close();
	         } catch (IOException e) {
	            //
	         }
	      }
	      }

	public void replace2(String oldFileName, String tmpFileName) {
		BufferedReader br = null;
	      BufferedWriter bw = null;
	      try {
	         br = new BufferedReader(new FileReader(oldFileName));
	         bw = new BufferedWriter(new FileWriter(tmpFileName));
	         String line;
	         while ((line = br.readLine()) != null) {
	            if (line.contains("\\\\"))	           
	               line = line.replace("\\\\", "\\");
	                if (line.contains("\\,"))
	            	line = line.replace("\\,", ",");
	            bw.write(line+"\n");
	         }
	         
	      } catch (Exception e) {
	         return;
	      } finally {
	         try {
	            if(br != null)
	               br.close();
	         } catch (IOException e) {
	            //
	         }
	         try {
	            if(bw != null)
	               bw.close();
	         } catch (IOException e) {
	            //
	         }
	      }
		
	}
	 public String sendCommand(String command,Session sesConnection)
	  {
	     StringBuilder outputBuffer = new StringBuilder();

	     try
	     {
	        Channel channel = sesConnection.openChannel("exec");
	        ((ChannelExec)channel).setCommand(command);
	        InputStream commandOutput = channel.getInputStream();
	        channel.connect();
	        int readByte = commandOutput.read();

	        while(readByte != 0xffffffff)
	        {
	           outputBuffer.append((char)readByte);
	           readByte = commandOutput.read();
	        }

	        channel.disconnect();
	     }
	     catch(IOException ioX)
	     {
	        System.out.println(ioX.getMessage());
	        return null;
	     }
	     catch(JSchException jschX)
	     {
	    	 System.out.println(jschX.getMessage());
	        return null;
	     }

	     return outputBuffer.toString();
	  }

}
