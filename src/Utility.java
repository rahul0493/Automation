import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfigurationLayout;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.UserInfo;
 


public class Utility {
	public static String paswString;
	public  void Process(int env, String host,String hostPassword, String inputDirectory,String tempDirectory, String sandBoxTempDirectory, PropertiesConfiguration propertyFile) throws  ConfigurationException, JSchException, IOException, SftpException{ 
		JSch jsch=new JSch();  
	        paswString = hostPassword;
	      MyUserInfo myUserInfo = new MyUserInfo();
	      myUserInfo.setNewPassword(hostPassword);
	      // enter username and ipaddress for machine you need to connect
	      String user=host.substring(0, host.indexOf('@'));
	      host=host.substring(host.indexOf('@')+1);
	       Session session=jsch.getSession(user, host, 22);
	      // username and password will be given via UserInfo interface.
	      UserInfo ui=new MyUserInfo();
	      session.setUserInfo(ui);
	      session.connect();
	      System.out.println("###################connected to "+host+" ##################################");
	 
	 ChannelSftp channel = new ChannelSftp();
	 channel = (ChannelSftp) session.openChannel("sftp");
	 channel.connect();
	  //rename the file and create a backup
	 String existingfile = "jdbc_customer.properties.in";
	 String backUpFile = existingfile.concat(new SimpleDateFormat("'_'yyyyMMdd").format(new Date()));
	 String FileDirectory =inputDirectory.substring(0,(inputDirectory.toString().lastIndexOf('/'))+1);
	 JSCHcl commond = new JSCHcl();
	 BufferedInputStream bis;
	 byte[] buffer = new byte[1024];
	 try {
		
		
			/*MakeBackupFile*/
			
			 String command ="cp "+inputDirectory+" "+FileDirectory+backUpFile;
			  commond.sendCommand(command,session);
			// Change to output directory
			channel.cd(FileDirectory);
			File file1 = new File(tempDirectory);
			bis = new BufferedInputStream(channel.get(file1.getName()));
			File newFile = new File(tempDirectory);
			// Download file
			OutputStream os = new FileOutputStream(newFile);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			int readCount;
			while ((readCount = bis.read(buffer)) > 0) {
			bos.write(buffer, 0, readCount);
			}
			bis.close();
			bos.close();
			System.out.println("File downloaded successfully - "+ file1.getAbsolutePath());
            JSCHcl replace = new JSCHcl();
            replace.replace(tempDirectory, tempDirectory+1);

	 PropertiesConfiguration config = new PropertiesConfiguration();
	 
	 PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
	 File file = new File(tempDirectory+1); 
	 layout.load(new InputStreamReader(new FileInputStream(file)));
	 if(env==7){
		System.out.println("++++++++++++++new Password+++++++++++++++");
		System.out.println("siaPool.password"+propertyFile.getProperty("siaPool.password"));
		System.out.println("WorkdayDB2Pool.password"+propertyFile.getProperty("WorkdayDB2Pool.password"));
		System.out.println("frameworkManagerPool.password"+propertyFile.getProperty("frameworkManagerPool.password"));
		System.out.println("+++++++++++++++++++++++++++++");
		if(propertyFile.getProperty("siaPool.password")!=null && config.getProperty("siaPool.password")!=null)		{	
			config.setProperty("siaPool.password",propertyFile.getProperty("siaPool.password"));
	 }else {
			System.out.println("password not changed siaPool.password");
		}
			 if(propertyFile.getProperty("WorkdayDB2Pool.password")!=null && config.getProperty("WorkdayDB2Pool.password")!=null) {
			 config.setProperty("WorkdayDB2Pool.password",propertyFile.getProperty("WorkdayDB2Pool.password"));
	 }else {
			System.out.println("password not changed WorkdayDB2Pool.password");
		}
			 if(propertyFile.getProperty("frameworkManagerPool.password")!=null &&  config.getProperty("frameworkManagerPool.password")!=null)
			 { config.setProperty("frameworkManagerPool.password",propertyFile.getProperty("frameworkManagerPool.password"));
	}else {
		System.out.println("password not changed frameworkManagerPool.password");
	}
			 layout.save(new FileWriter(tempDirectory+1, false));
			 JSCHcl jscHcl = new JSCHcl();
			 jscHcl.replace2(tempDirectory+1, tempDirectory);
			 FileInputStream fis = null;
				try {
					// Change to output directory
					 channel.cd(FileDirectory);
					// Upload file
					File sandfile = new File(tempDirectory);
					fis = new FileInputStream(sandfile);
					channel.put(fis, existingfile);
					fis.close();
					System.out.println("File uploaded successfully - "+ file.getAbsolutePath());

				} catch (Exception e) {
					e.printStackTrace();
				}
			
	}else if(env==5){
		System.out.println("++++++++++++++new Password+++++++++++++++");
		
		System.out.println("siaPool.password"+propertyFile.getProperty("siaPool.password"));
		System.out.println("NGGTPOPool.password"+propertyFile.getProperty("NGGTPOPool.password"));
		System.out.println("frameworkManagerPool.password"+propertyFile.getProperty("frameworkManagerPool.password"));
		System.out.println("CSMSPool.password"+propertyFile.getProperty("CSMSPool.password"));
		System.out.println("+++++++++++++++++++++++++++++");
		if(propertyFile.getProperty("siaPool.password")!=null && config.getProperty("siaPool.password")!=null) {
			config.setProperty("siaPool.password",propertyFile.getProperty("siaPool.password"));
		}else {
			System.out.println("password not changed siaPool.password");
		}
		if(propertyFile.getProperty("frameworkManagerPool.password")!=null && config.getProperty("frameworkManagerPool.password")!=null) {
			 config.setProperty("frameworkManagerPool.password",propertyFile.getProperty("frameworkManagerPool.password"));
	}else {
		System.out.println("password not changed frameworkManagerPool.password");
	}
			 if(propertyFile.getProperty("NGGTPOPool.password")!=null && config.getProperty("NGGTPOPool.password")!=null) {
			 config.setProperty("NGGTPOPool.password",propertyFile.getProperty("NGGTPOPool.password"));
			 }else {
					System.out.println("password not changed NGGTPOPool.password");
				}
			 if(propertyFile.getProperty("CSMSPool.password")!=null && config.getProperty("CSMSPool.password")!=null) {
			 config.setProperty("CSMSPool.password",propertyFile.getProperty("CSMSPool.password"));
			 }else {
					System.out.println("password not changed for CSMSPool.password ");
				}
			 layout.save(new FileWriter(tempDirectory+1, false));
			 JSCHcl jscHcl = new JSCHcl();
			 jscHcl.replace2(tempDirectory+1, tempDirectory);
			 FileInputStream fis = null;
				try {
					// Change to output directory
					 channel.cd(FileDirectory);
					// Upload file
					File sandfile = new File(tempDirectory);
					fis = new FileInputStream(sandfile);
					channel.put(fis, existingfile);
					fis.close();
					System.out.println("File uploaded successfully - "+ file.getAbsolutePath());

				} catch (Exception e) {
					e.printStackTrace();
				}
			 // channel.put(tempDirectory,inputDirectory);
	}else if(env==6){
		System.out.println("++++++++++++++new Password+++++++++++++++");
		System.out.println("siaPool.password"+propertyFile.getProperty("siaPool.password"));
		System.out.println("frameworkManagerPool.password"+propertyFile.getProperty("frameworkManagerPool.password"));
		System.out.println("CSMSPool.password"+propertyFile.getProperty("CSMSPool.password"));
		System.out.println("+++++++++++++++++++++++++++++");
				if(propertyFile.getProperty("siaPool.password")!=null && config.getProperty("siaPool.password")!=null)
			    {config.setProperty("siaPool.password",propertyFile.getProperty("siaPool.password"));
	}else {
		System.out.println("password not changed siaPool.password");
	}
			   if(propertyFile.getProperty("CSMSPool.password")!=null  && config.getProperty("CSMSPool.password")!=null)
			   {  config.setProperty("CSMSPool.password",propertyFile.getProperty("CSMSPool.password"));
	 }else {
			System.out.println("password not changed CSMSPool.password");
		}
			   if(propertyFile.getProperty("frameworkManagerPool.password")!=null && config.getProperty("frameworkManagerPool.password")!=null)
			   {  config.setProperty("frameworkManagerPool.password",propertyFile.getProperty("frameworkManagerPool.password"));
	 }else {
			System.out.println("password not changed frameworkManagerPool.password");
		}
			   layout.save(new FileWriter(tempDirectory+1, false));
			  //System.out.println("config.getEncoding()4"+config.getEncoding());
			  JSCHcl jscHcl = new JSCHcl();
				 jscHcl.replace2(tempDirectory+1, tempDirectory);
				 FileInputStream fis = null;
					try {
						// Change to output directory
						 channel.cd(FileDirectory);
						// Upload file
						File sandfile = new File(tempDirectory);
						fis = new FileInputStream(sandfile);
						channel.put(fis, existingfile);
						fis.close();
						System.out.println("File uploaded successfully - "+ file.getAbsolutePath());

					} catch (Exception e) {
						e.printStackTrace();
					}
				 //channel.put(tempDirectory,inputDirectory);
			  
	}else if(env==4){
		System.out.println("++++++++++++++new Password+++++++++++++++");
		System.out.println("siaPool.password"+propertyFile.getProperty("siaPool.password"));
		System.out.println("db2pool.password"+propertyFile.getProperty("db2pool.password"));
		System.out.println("frameworkManagerPool.password"+propertyFile.getProperty("frameworkManagerPool.password"));
		System.out.println("CSMSPool.password"+propertyFile.getProperty("CSMSPool.password"));
		System.out.println("concurDB2Pool.password"+propertyFile.getProperty("concurDB2Pool.password"));
		System.out.println("WorkdayDB2Pool.password"+propertyFile.getProperty("WorkdayDB2Pool.password"));
		System.out.println("mssqlWIMS.password"+propertyFile.getProperty("qa.mssqlWIMS.password"));
		System.out.println("mssqlCOMED.password"+propertyFile.getProperty("qa.mssqlCOMED.password"));
		System.out.println("+++++++++++++++++++++++++++++");
		/* Set<String> newSet = layout.getKeys();
		 for (String string : newSet) {
			System.out.println(string);
		}*/
//		System.out.println(layout.getKeys());
		if(propertyFile.getProperty("siaPool.password")!=null  && config.getProperty("siaPool.password")!=null) {
			config.setProperty("siaPool.password",propertyFile.getProperty("siaPool.password"));
		}else {
			System.out.println("password not changed siaPool.password");
		}
			if(propertyFile.getProperty("frameworkManagerPool.password")!=null  && config.getProperty("frameworkManagerPool.password")!=null)
			{config.setProperty("frameworkManagerPool.password",propertyFile.getProperty("frameworkManagerPool.password"));
	}else {
		System.out.println("password not changed frameworkManagerPool.password");
	}
			 if(propertyFile.getProperty("db2pool.password")!=null  && config.getProperty("db2pool.password")!=null)
			 {config.setProperty("db2pool.password",propertyFile.getProperty("db2pool.password"));
	 }else {
			System.out.println("password not changed fdb2pool.password");
		}
			 if(propertyFile.getProperty("concurDB2Pool.password")!=null   && config.getProperty("concurDB2Pool.password")!=null)
			 { config.setProperty("concurDB2Pool.password",propertyFile.getProperty("concurDB2Pool.password"));
	}else {
		System.out.println("password not changed concurDB2Pool.password");
	}
			 if(propertyFile.getProperty("WorkdayDB2Pool.password")!=null  && config.getProperty("WorkdayDB2Pool.password")!=null)
			 { config.setProperty("WorkdayDB2Pool.password",propertyFile.getProperty("WorkdayDB2Pool.password"));
}else {
	System.out.println("password not changed WorkdayDB2Pool.password");
}
			 if(propertyFile.getProperty("qa.mssqlWIMS.password")!=null && config.getProperty("mssqlWIMS.password")!=null) {
				 config.setProperty("mssqlWIMS.password",propertyFile.getProperty("qa.mssqlWIMS.password"));
				 }else {
						System.out.println("password not changed mssqlWIMS.password");
					}
			
			 if(propertyFile.getProperty("CSMSPool.password")!=null && config.getProperty("CSMSPool.password")!=null) {
			 config.setProperty("CSMSPool.password",propertyFile.getProperty("CSMSPool.password"));
			 }
			 else {
				 System.out.println("password not changed CSMSPool.password");
			 }
			 if(propertyFile.getProperty("qa.mssqlCOMED.password")!=null && config.getProperty("mssqlCOMED.password")!=null)
			 { config.setProperty("mssqlCOMED.password",propertyFile.getProperty("qa.mssqlCOMED.password"));
	 }
	 else {
		 System.out.println("password not changed CSMSPool.password");
	 }
			 layout.save(new FileWriter(tempDirectory+1, false));
			 JSCHcl jscHcl = new JSCHcl();
			 jscHcl.replace2(tempDirectory+1, tempDirectory);
			 FileInputStream fis = null;
				try {
					// Change to output directory
					 channel.cd(FileDirectory);
					// Upload file
					File sandfile = new File(tempDirectory);
					fis = new FileInputStream(sandfile);
					channel.put(fis, existingfile);
					fis.close();
					System.out.println("File uploaded successfully - "+ file.getAbsolutePath());

				} catch (Exception e) {
					e.printStackTrace();
				}
			 //channel.put(tempDirectory,inputDirectory);
	}else if(env==3){
		System.out.println("++++++++++++++new Password+++++++++++++++");
		System.out.println("siaPool.password"+propertyFile.getProperty("siaPool.password"));
		System.out.println("db2pool.password"+propertyFile.getProperty("db2pool.password"));
		System.out.println("frameworkManagerPool.password"+propertyFile.getProperty("frameworkManagerPool.password"));
		System.out.println("CSMSPool.password"+propertyFile.getProperty("CSMSPool.password"));
		System.out.println("concurDB2Pool.password"+propertyFile.getProperty("concurDB2Pool.password"));
		System.out.println("WorkdayDB2Pool.password"+propertyFile.getProperty("WorkdayDB2Pool.password"));
		System.out.println("mssqlWIMS.password"+propertyFile.getProperty("prod.mssqlWIMS.password"));
		System.out.println("mssqlCOMED.password"+propertyFile.getProperty("prod.mssqlCOMED.password"));
		System.out.println("+++++++++++++++++++++++++++++");
		if(propertyFile.getProperty("siaPool.password")!=null)
			config.setProperty("siaPool.password",propertyFile.getProperty("siaPool.password"));
		if(propertyFile.getProperty("frameworkManagerPool.password")!=null && config.getProperty("frameworkManagerPool.password")!=null)
			{config.setProperty("frameworkManagerPool.password",propertyFile.getProperty("frameworkManagerPool.password"));
	}else {
		System.out.println("password not changed frameworkManagerPool.password");
	}
			 if(propertyFile.getProperty("db2pool.password")!=null && config.getProperty("db2pool.password")!=null)
			 { config.setProperty("db2pool.password",propertyFile.getProperty("db2pool.password"));
			 
			 }else {
				 System.out.println("password not changed db2pool.password");
			 }
			 if(propertyFile.getProperty("concurDB2Pool.password")!=null && config.getProperty("concurDB2Pool.password")!=null)
			 { config.setProperty("concurDB2Pool.password",propertyFile.getProperty("concurDB2Pool.password"));
			 }else {
				 System.out.println("password not changed concurDB2Pool.password");
			 }
			 if(propertyFile.getProperty("WorkdayDB2Pool.password")!=null && config.getProperty("WorkdayDB2Pool.password")!=null)
			 { config.setProperty("WorkdayDB2Pool.password",propertyFile.getProperty("WorkdayDB2Pool.password"));
	 }else {
		 System.out.println("password not WorkdayDB2Pool.password");
	 }
			 if(propertyFile.getProperty("prod.mssqlWIMS.password")!=null && config.getProperty("mssqlWIMS.password")!=null)
			 { config.setProperty("mssqlWIMS.password",propertyFile.getProperty("prod.mssqlWIMS.password"));
	 }else {
		 System.out.println("password not changed mssqlWIMS.password");
	 }
			 if(propertyFile.getProperty("CSMSPool.password")!=null && config.getProperty("CSMSPool.password")!=null) {
			 config.setProperty("CSMSPool.password",propertyFile.getProperty("CSMSPool.password"));
	 }else {
		 System.out.println("password not changed CSMSPool.password");
	 }
			 if(propertyFile.getProperty("prod.mssqlCOMED.password")!=null && config.getProperty("mssqlCOMED.password")!=null)
			 { config.setProperty("mssqlCOMED.password",propertyFile.getProperty("prod.mssqlCOMED.password"));
}else {
	 System.out.println("password not changed mssqlCOMED.password");
}
			 layout.save(new FileWriter(tempDirectory+1, false));
			 JSCHcl jscHcl = new JSCHcl();
			 jscHcl.replace2(tempDirectory+1, tempDirectory);
			 FileInputStream fis = null;
				try {
					// Change to output directory
					 channel.cd(FileDirectory);
					// Upload file
					File sandfile = new File(tempDirectory);
					fis = new FileInputStream(sandfile);
					channel.put(fis, existingfile);
					fis.close();
					System.out.println("File uploaded successfully - "+ file.getAbsolutePath());

				} catch (Exception e) {
					e.printStackTrace();
				}
			 //channel.put(tempDirectory,inputDirectory);
			 
	}else{
		System.out.println("wrong parameter");
		System.exit(-1);
	}
	 } catch (Exception e) {
			e.printStackTrace();
		}finally{
		File file = new File(tempDirectory);
		File file2 = new File(tempDirectory+1);
		file.deleteOnExit();
		file2.deleteOnExit();
		}
	 
	
		 try {
			//sandox config
			 String sandBoxInputFile = "sandbox.cfg";
			 String sandBoxbackupFile = sandBoxInputFile.concat(new SimpleDateFormat("'_'yyyyMMdd").format(new Date()));
			//channel.put( new ByteArrayInputStream( "".getBytes() ), FileDirectory+sandBoxbackupFile);
			/*channel.get(FileDirectory+sandBoxInputFile,sandBoxTempDirectory);
			channel.put(sandBoxTempDirectory,FileDirectory+sandBoxbackupFile);*/
			
			 /*MakeBackupFile*/
			 
			   String sandboxCommand ="cp "+FileDirectory+sandBoxInputFile+" "+FileDirectory+sandBoxbackupFile;
			  commond.sendCommand(sandboxCommand,session);
			
				// Change to output directory
				//String cdDir = fileName.substring(0, fileName.lastIndexOf("/") + 1);
				channel.cd(FileDirectory);

				File sanBoxfile = new File(FileDirectory+sandBoxInputFile);
				bis = new BufferedInputStream(channel.get(sanBoxfile.getName()));

				File newFile = new File(sandBoxTempDirectory);
				
				// Download file
				OutputStream os = new FileOutputStream(newFile);
				BufferedOutputStream bos = new BufferedOutputStream(os);
				int readCount;
				while ((readCount = bis.read(buffer)) > 0) {
					
					bos.write(buffer, 0, readCount);
				}
				bis.close();
				bos.close();
				System.out.println("File downloaded successfully - "+ FileDirectory+sandBoxInputFile);
	            JSCHcl jscHcl = new JSCHcl();
	            jscHcl.replace(sandBoxTempDirectory,sandBoxTempDirectory+1);
			
		 PropertiesConfiguration sandboxConfig = new PropertiesConfiguration();
		 PropertiesConfigurationLayout sandBoxLayout = new PropertiesConfigurationLayout(sandboxConfig);
		 
		File sandBoxDirectory = new File(sandBoxTempDirectory+1); 
		System.out.println("++++++++++++++new Password+++++++++++++++");
		System.out.println("ORA_PASS"+propertyFile.getProperty("ORA_PASS"));
		System.out.println("DB_PASS"+propertyFile.getProperty("DB_PASS"));
		System.out.println("YANTRA_DB_PASS"+propertyFile.getProperty("YANTRA_DB_PASS"));
		System.out.println("+++++++++++++++++++++++++++++");
		 sandBoxLayout.load(new InputStreamReader(new FileInputStream(sandBoxDirectory)));
		 if(propertyFile.getProperty("ORA_PASS")!=null)
		 sandboxConfig.setProperty("ORA_PASS", propertyFile.getProperty("ORA_PASS"));
		 if(propertyFile.getProperty("DB_PASS")!=null)
		 sandboxConfig.setProperty("DB_PASS", propertyFile.getProperty("DB_PASS"));
		 if(propertyFile.getProperty("YANTRA_DB_PASS")!=null)
		sandboxConfig.setProperty("YANTRA_DB_PASS", propertyFile.getProperty("YANTRA_DB_PASS"));

		 sandBoxLayout.save(new FileWriter(sandBoxTempDirectory+1, false));
		 JSCHcl replace2 = new JSCHcl();
		 replace2.replace2(sandBoxTempDirectory+1, sandBoxTempDirectory);
		 //channel.put(sandBoxTempDirectory,FileDirectory+sandBoxInputFile);
		 FileInputStream fis = null;
			try {
				// Change to output directory
				 channel.cd(FileDirectory);

				// Upload file
				File sandfile = new File(sandBoxTempDirectory);
				fis = new FileInputStream(sandfile);
				channel.put(fis, sandBoxInputFile);
				fis.close();
				System.out.println("File uploaded successfully - "+ sandBoxTempDirectory);

			} catch (Exception e) {
				e.printStackTrace();
			}
		 } catch (Exception e) {
				e.printStackTrace();
			}finally{
              File file = new File(sandBoxTempDirectory);
              File file2 = new File(sandBoxTempDirectory+1);
              file.deleteOnExit();
              file2.deleteOnExit();
			}

	
	 if(channel.isClosed()){
	          System.out.println("exit-status: "+channel.getExitStatus());
	         // break;
	        }
       try{Thread.sleep(1000);}catch(Exception ee){}
	     // }
	      //channel.disconnect();
	      session.disconnect();
	      
	  }
	 
	  public static class MyUserInfo implements UserInfo{
	    public String getPassword(){ return passwd; }
	    public boolean promptYesNo(String str){
	        str = "Yes";
	        return true;}
	    public String newPassword;
		public void setNewPassword(String newPassword) {
			this.newPassword = newPassword;
		}
		String passwd;
	    public String getPassphrase(){ return null; }
	    public boolean promptPassphrase(String message){ return true; }
	    public boolean promptPassword(String message){
	        passwd=paswString; // enter the password for the machine you want to connect.
	        return true;
	    }
		@Override
		public void showMessage(String arg0) {
			// TODO Auto-generated method stub
			
		}
	    
	  
	    }

	public void masterBackup(int env, String host, String password,
			String maindirectory, String tempJdbcDirectory, String tempsandBoxDirectory,
			PropertiesConfiguration config) throws JSchException {
		 try {   
		JSch jsch=new JSch();  
	       paswString = password;
	      MyUserInfo myUserInfo = new MyUserInfo();
	      myUserInfo.setNewPassword(password);
	      // enter username and ipaddress for machine you need to connect
	      String user=host.substring(0, host.indexOf('@'));
	      host=host.substring(host.indexOf('@')+1);
	       Session session=jsch.getSession(user, host, 22);
	      // username and password will be given via UserInfo interface.
	      UserInfo ui=new MyUserInfo();
	      session.setUserInfo(ui);
	      session.connect();
	      System.out.println("###################connected to "+host+" ##################################");
	 
	 ChannelSftp channel = new ChannelSftp();
	 channel = (ChannelSftp) session.openChannel("sftp");
	 channel.connect();
	  //rename the file and create a backup
	 String jdbcFile = "jdbc_customer.properties.in";
	 String jdbcbackUpFile = jdbcFile.concat(new SimpleDateFormat("'_'yyyyMMdd").format(new Date()));
	 String sandBoxInputFile = "sandbox.cfg";
	 String sandBoxbackupFile = sandBoxInputFile.concat(new SimpleDateFormat("'_'yyyyMMdd").format(new Date()));
	 String FileDirectory =maindirectory.substring(0,(maindirectory.toString().lastIndexOf('/'))+1);
	 String BackupDirectory="/opt/sterling/3M_stuff/passwordchange_masterbckup/propertie_files/";
	 String croneTabBackupFile="crontab -l > /opt/sterling/3M_stuff/passwordchange_masterbckup/master_cron_backup/Automated_cron_master_backup_file.txt";
	
	 JSCHcl commond = new JSCHcl();
	
			/*MakeBackupFile*/
			  String jdbcBackup ="cp "+maindirectory+" "+BackupDirectory+jdbcbackUpFile;
			  String output1 =commond.sendCommand(jdbcBackup,session);
			  /*MakeSandBoxBackupFile*/
			  System.out.println("jdbc Master Backup:"+output1);
			  String sandboxCommand ="cp "+FileDirectory+sandBoxInputFile+" "+BackupDirectory+sandBoxbackupFile;
			  String output2=commond.sendCommand(sandboxCommand,session);
			  System.out.println(" Sandbox Master Backup:"+output2);
			  /*MakecroneTabBackupFile*/
			 String output3= commond.sendCommand(croneTabBackupFile,session);
			  System.out.println(" CroneTab Master Backup:"+output3);
			  if(channel.isClosed()){
			         System.out.println("exit-status: "+channel.getExitStatus());
			        // break;
			       }
			  try{Thread.sleep(1000);}catch(Exception ee){}
			    // }
			     //channel.disconnect();
			     session.disconnect();
				
	 }catch(Exception e){
		 e.printStackTrace();
	 }
	}

	public void croneTabBackup(int env, String host, String password,
			String maindirectory, String tempJdbcDirectory, String tempsandBoxDirectory,
			PropertiesConfiguration config) throws JSchException {
		 try {  
		JSch jsch=new JSch();  
	        paswString = password;
	      MyUserInfo myUserInfo = new MyUserInfo();
	      myUserInfo.setNewPassword(password);
	      // enter username and ipaddress for machine you need to connect
	      String user=host.substring(0, host.indexOf('@'));
	      host=host.substring(host.indexOf('@')+1);
	       Session session=jsch.getSession(user, host, 22);
	      // username and password will be given via UserInfo interface.
	      UserInfo ui=new MyUserInfo();
	      session.setUserInfo(ui);
	      session.connect();
	      System.out.println("###################connected to "+host+" ##################################");
	 
	 ChannelSftp channel = new ChannelSftp();
	 channel = (ChannelSftp) session.openChannel("sftp");
	 channel.connect();
	 JSCHcl commond = new JSCHcl();
	
		     String croneTabBackupDirectory= "crontab -l > /opt/sterling/3M_stuff/passwordchange_masterbckup/cron_backups/";
			 String croneTabBackupFile ="crontab_backup_".concat(new SimpleDateFormat("'_'yyyyMMdd").format(new Date()))+".txt";
			 String Command = croneTabBackupDirectory+ croneTabBackupFile;
			 String removeCroneTabcommand="crontab -r";
			 /*MakecroneTabBackupFile*/
		    String output =commond.sendCommand(Command,session);
		    System.out.println("Output::"+output);
		    String removeCroneTab =commond.sendCommand(removeCroneTabcommand,session);
			System.out.println("Output::"+removeCroneTab);
			 if(channel.isClosed()){
		         System.out.println("exit-status: "+channel.getExitStatus());
		        // break;
		       }
		  try{Thread.sleep(1000);}catch(Exception ee){}
		    // }
		     //channel.disconnect();
		     session.disconnect();
	 }catch(Exception e){
		 e.printStackTrace();
	 }
	
	}
	public void reCreatecroneTab(int env, String host, String password,
			
			String maindirectory, String tempJdbcDirectory, String tempsandBoxDirectory,
			PropertiesConfiguration config) throws JSchException {
		 try {  
		JSch jsch=new JSch();  
	        paswString = password;
	      MyUserInfo myUserInfo = new MyUserInfo();
	      myUserInfo.setNewPassword(password);
	      // enter username and ipaddress for machine you need to connect
	      String user=host.substring(0, host.indexOf('@'));
	      host=host.substring(host.indexOf('@')+1);
	       Session session=jsch.getSession(user, host, 22);
	      // username and password will be given via UserInfo interface.
	      UserInfo ui=new MyUserInfo();
	      session.setUserInfo(ui);
	      session.connect();
	      System.out.println("###################connected to "+host+" ##################################");
	 
	 ChannelSftp channel = new ChannelSftp();
	 channel = (ChannelSftp) session.openChannel("sftp");
	 channel.connect();
	 JSCHcl commond = new JSCHcl();
	
		     String croneTabBackupDirectory= "crontab /opt/sterling/3M_stuff/passwordchange_masterbckup/cron_backups/";
			 String croneTabBackupFile ="crontab_backup_".concat(new SimpleDateFormat("'_'yyyyMMdd").format(new Date()))+".txt";
			 String Command = croneTabBackupDirectory+ croneTabBackupFile;
			 
			 /*MakecroneTabBackupFile*/
		    String output =commond.sendCommand(Command,session);
		    System.out.println("Output::"+output);
		    
		    if(channel.isClosed()){
		         System.out.println("exit-status: "+channel.getExitStatus());
		        // break;
		       }
		  try{Thread.sleep(1000);}catch(Exception ee){}
		    // }
		     //channel.disconnect();
		     session.disconnect();
	 }catch(Exception e){
		 e.printStackTrace();
	 }
	 
	}
	  
}
