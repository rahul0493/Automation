import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.PropertiesConfigurationLayout;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

public class ConnectServer 
{
  public static void main(String[] arg) throws ConfigurationException, JSchException, IOException, SftpException{
	  PropertiesConfiguration config = new PropertiesConfiguration();
	  Scanner scan = new Scanner(System.in);
	  PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout(config);
	  System.out.println("Enter path for newPassword.properties file example(C:\\Users\\Rahuly\\Desktop\\conf):");
	  File file = new File(scan.next()+"/"+"newPassword.properties"); 
		 layout.load(new InputStreamReader(new FileInputStream(file)));
		 System.out.println(config.getProperty("sb.node1.directory").toString().substring(0, (config.getProperty("sb.node1.directory").toString().lastIndexOf('/'))));
	  Utility utility = new Utility();
	String resume ="Y";
	  while(resume.equalsIgnoreCase("Y")){
	  
	  System.out.println("Enter which envirnoment you want to trigger");
	  System.out.println("1> Masterback up - Property files");
	  System.out.println("2> Crontab backup & Remove");
	  System.out.println("3> Prod Env");
	  System.out.println("4> QA Env");
	  System.out.println("5> Dev Env");
	  System.out.println("6> Staging Env");
	  System.out.println("7> Sandbox Env");
	  System.out.println("8> Create Crontab ");
	  System.out.println("9> Reset Password");
	  
	  int env =scan.nextInt();
	  switch(env) {
	  case 1:
		  utility.masterBackup(env,config.getProperty("prd.node1.host").toString(),config.getProperty("prd.password").toString(),config.getProperty("prd.node1.directory").toString(),config.getProperty("prd.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.masterBackup(env,config.getProperty("prd.node2.host").toString(),config.getProperty("prd.password").toString(),config.getProperty("prd.node2.directory").toString(),config.getProperty("prd.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.masterBackup(env,config.getProperty("prd.node3.host").toString(),config.getProperty("prd.password").toString(),config.getProperty("prd.node3.directory").toString(),config.getProperty("prd.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.masterBackup(env,config.getProperty("prd.node4.host").toString(),config.getProperty("prd.password").toString(),config.getProperty("prd.node4.directory").toString(),config.getProperty("prd.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.masterBackup(env,config.getProperty("qa.node1.host").toString(),config.getProperty("qa.password").toString(),config.getProperty("qa.node1.directory").toString(),config.getProperty("qa.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.masterBackup(env,config.getProperty("qa.node2.host").toString(),config.getProperty("qa.password").toString(),config.getProperty("qa.node2.directory").toString(),config.getProperty("qa.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.masterBackup(env,config.getProperty("qa.node3.host").toString(),config.getProperty("qa.password").toString(),config.getProperty("qa.node3.directory").toString(),config.getProperty("qa.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.masterBackup(env,config.getProperty("qa.node4.host").toString(),config.getProperty("qa.password").toString(),config.getProperty("qa.node4.directory").toString(),config.getProperty("qa.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.masterBackup(env,config.getProperty("dev.node1.host").toString(),config.getProperty("dev.password").toString(),config.getProperty("dev.node1.directory").toString(),config.getProperty("dev.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.masterBackup(env,config.getProperty("stg.node1.host").toString(),config.getProperty("stg.password").toString(),config.getProperty("stg.node1.directory").toString(),config.getProperty("stg.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.masterBackup(env,config.getProperty("stg.node2.host").toString(),config.getProperty("stg.password").toString(),config.getProperty("stg.node2.directory").toString(),config.getProperty("stg.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.masterBackup(env,config.getProperty("stg.node3.host").toString(),config.getProperty("stg.password").toString(),config.getProperty("stg.node3.directory").toString(),config.getProperty("stg.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.masterBackup(env,config.getProperty("stg.node4.host").toString(),config.getProperty("stg.password").toString(),config.getProperty("stg.node4.directory").toString(),config.getProperty("stg.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.masterBackup(env,config.getProperty("sb.node1.host").toString(),config.getProperty("sb.password").toString(),config.getProperty("sb.node1.directory").toString(),config.getProperty("sb.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.masterBackup(env,config.getProperty("sb.node2.host").toString(),config.getProperty("sb.password").toString(),config.getProperty("sb.node2.directory").toString(),config.getProperty("sb.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.masterBackup(env,config.getProperty("sb.node3.host").toString(),config.getProperty("sb.password").toString(),config.getProperty("sb.node3.directory").toString(),config.getProperty("sb.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.masterBackup(env,config.getProperty("sb.node4.host").toString(),config.getProperty("sb.password").toString(),config.getProperty("sb.node4.directory").toString(),config.getProperty("sb.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  System.out.println("To continue press Y,to Exit press any other Key");
		 resume= scan.next();
		  break;
		  
	  case 2:
	 	  utility.croneTabBackup(env,config.getProperty("prd.node1.host").toString(),config.getProperty("prd.password").toString(),config.getProperty("prd.node1.directory").toString(),config.getProperty("prd.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.croneTabBackup(env,config.getProperty("prd.node2.host").toString(),config.getProperty("prd.password").toString(),config.getProperty("prd.node2.directory").toString(),config.getProperty("prd.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.croneTabBackup(env,config.getProperty("prd.node3.host").toString(),config.getProperty("prd.password").toString(),config.getProperty("prd.node3.directory").toString(),config.getProperty("prd.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.croneTabBackup(env,config.getProperty("prd.node4.host").toString(),config.getProperty("prd.password").toString(),config.getProperty("prd.node4.directory").toString(),config.getProperty("prd.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.croneTabBackup(env,config.getProperty("qa.node1.host").toString(),config.getProperty("qa.password").toString(),config.getProperty("qa.node1.directory").toString(),config.getProperty("qa.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.croneTabBackup(env,config.getProperty("qa.node2.host").toString(),config.getProperty("qa.password").toString(),config.getProperty("qa.node2.directory").toString(),config.getProperty("qa.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.croneTabBackup(env,config.getProperty("qa.node3.host").toString(),config.getProperty("qa.password").toString(),config.getProperty("qa.node3.directory").toString(),config.getProperty("qa.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.croneTabBackup(env,config.getProperty("qa.node4.host").toString(),config.getProperty("qa.password").toString(),config.getProperty("qa.node4.directory").toString(),config.getProperty("qa.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.croneTabBackup(env,config.getProperty("dev.node1.host").toString(),config.getProperty("dev.password").toString(),config.getProperty("dev.node1.directory").toString(),config.getProperty("dev.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.croneTabBackup(env,config.getProperty("stg.node1.host").toString(),config.getProperty("stg.password").toString(),config.getProperty("stg.node1.directory").toString(),config.getProperty("stg.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.croneTabBackup(env,config.getProperty("stg.node2.host").toString(),config.getProperty("stg.password").toString(),config.getProperty("stg.node2.directory").toString(),config.getProperty("stg.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.croneTabBackup(env,config.getProperty("stg.node3.host").toString(),config.getProperty("stg.password").toString(),config.getProperty("stg.node3.directory").toString(),config.getProperty("stg.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.croneTabBackup(env,config.getProperty("stg.node4.host").toString(),config.getProperty("stg.password").toString(),config.getProperty("stg.node4.directory").toString(),config.getProperty("stg.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.croneTabBackup(env,config.getProperty("sb.node1.host").toString(),config.getProperty("sb.password").toString(),config.getProperty("sb.node1.directory").toString(),config.getProperty("sb.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.croneTabBackup(env,config.getProperty("sb.node2.host").toString(),config.getProperty("sb.password").toString(),config.getProperty("sb.node2.directory").toString(),config.getProperty("sb.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.croneTabBackup(env,config.getProperty("sb.node3.host").toString(),config.getProperty("sb.password").toString(),config.getProperty("sb.node3.directory").toString(),config.getProperty("sb.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.croneTabBackup(env,config.getProperty("sb.node4.host").toString(),config.getProperty("sb.password").toString(),config.getProperty("sb.node4.directory").toString(),config.getProperty("sb.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  System.out.println("To continue press Y,to Exit press any other Key");
		  resume= scan.next();
		  break;
	 case 3:
		  utility.Process(env,config.getProperty("prd.node1.host").toString(),config.getProperty("prd.password").toString(),config.getProperty("prd.node1.directory").toString(),config.getProperty("prd.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.Process(env,config.getProperty("prd.node2.host").toString(),config.getProperty("prd.password").toString(),config.getProperty("prd.node2.directory").toString(),config.getProperty("prd.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.Process(env,config.getProperty("prd.node3.host").toString(),config.getProperty("prd.password").toString(),config.getProperty("prd.node3.directory").toString(),config.getProperty("prd.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.Process(env,config.getProperty("prd.node4.host").toString(),config.getProperty("prd.password").toString(),config.getProperty("prd.node4.directory").toString(),config.getProperty("prd.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  System.out.println("To continue press Y,to Exit press any other Key");
		  resume= scan.next();
		  break;
	 case 4:
		  //utility.Process(env,config.getProperty("qa.node1.host").toString(),config.getProperty("qa.password").toString(),config.getProperty("qa.node1.directory").toString(),config.getProperty("qa.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.Process(env,config.getProperty("qa.node2.host").toString(),config.getProperty("qa.password").toString(),config.getProperty("qa.node2.directory").toString(),config.getProperty("qa.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  //utility.Process(env,config.getProperty("qa.node3.host").toString(),config.getProperty("qa.password").toString(),config.getProperty("qa.node3.directory").toString(),config.getProperty("qa.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		 // utility.Process(env,config.getProperty("qa.node4.host").toString(),config.getProperty("qa.password").toString(),config.getProperty("qa.node4.directory").toString(),config.getProperty("qa.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  System.out.println("To continue press Y,to Exit press any other Key");
		  resume= scan.next();
		  break;
	case 5:
		 utility.Process(env,config.getProperty("dev.node1.host").toString(),config.getProperty("dev.password").toString(),config.getProperty("dev.node1.directory").toString(),config.getProperty("dev.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		 System.out.println("To continue press Y,to Exit press any other Key");
		 resume= scan.next();
		 break;
	case 6:
		  utility.Process(env,config.getProperty("stg.node1.host").toString(),config.getProperty("stg.password").toString(),config.getProperty("stg.node1.directory").toString(),config.getProperty("stg.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.Process(env,config.getProperty("stg.node2.host").toString(),config.getProperty("stg.password").toString(),config.getProperty("stg.node2.directory").toString(),config.getProperty("stg.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.Process(env,config.getProperty("stg.node3.host").toString(),config.getProperty("stg.password").toString(),config.getProperty("stg.node3.directory").toString(),config.getProperty("stg.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.Process(env,config.getProperty("stg.node4.host").toString(),config.getProperty("stg.password").toString(),config.getProperty("stg.node4.directory").toString(),config.getProperty("stg.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  System.out.println("To continue press Y,to Exit press any other Key");
		  resume= scan.next();
		  break;
	case 7:
		  utility.Process(env,config.getProperty("sb.node1.host").toString(),config.getProperty("sb.password").toString(),config.getProperty("sb.node1.directory").toString(),config.getProperty("sb.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.Process(env,config.getProperty("sb.node2.host").toString(),config.getProperty("sb.password").toString(),config.getProperty("sb.node2.directory").toString(),config.getProperty("sb.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.Process(env,config.getProperty("sb.node3.host").toString(),config.getProperty("sb.password").toString(),config.getProperty("sb.node3.directory").toString(),config.getProperty("sb.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.Process(env,config.getProperty("sb.node4.host").toString(),config.getProperty("sb.password").toString(),config.getProperty("sb.node4.directory").toString(),config.getProperty("sb.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  System.out.println("To continue press Y,to Exit press any other Key");
		  resume= scan.next();
		  break;
	case 8:
	 	  utility.reCreatecroneTab(env,config.getProperty("prd.node1.host").toString(),config.getProperty("prd.password").toString(),config.getProperty("prd.node1.directory").toString(),config.getProperty("prd.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.reCreatecroneTab(env,config.getProperty("prd.node2.host").toString(),config.getProperty("prd.password").toString(),config.getProperty("prd.node2.directory").toString(),config.getProperty("prd.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.reCreatecroneTab(env,config.getProperty("prd.node3.host").toString(),config.getProperty("prd.password").toString(),config.getProperty("prd.node3.directory").toString(),config.getProperty("prd.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.reCreatecroneTab(env,config.getProperty("prd.node4.host").toString(),config.getProperty("prd.password").toString(),config.getProperty("prd.node4.directory").toString(),config.getProperty("prd.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.reCreatecroneTab(env,config.getProperty("qa.node1.host").toString(),config.getProperty("qa.password").toString(),config.getProperty("qa.node1.directory").toString(),config.getProperty("qa.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.reCreatecroneTab(env,config.getProperty("qa.node2.host").toString(),config.getProperty("qa.password").toString(),config.getProperty("qa.node2.directory").toString(),config.getProperty("qa.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.reCreatecroneTab(env,config.getProperty("qa.node3.host").toString(),config.getProperty("qa.password").toString(),config.getProperty("qa.node3.directory").toString(),config.getProperty("qa.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.reCreatecroneTab(env,config.getProperty("qa.node4.host").toString(),config.getProperty("qa.password").toString(),config.getProperty("qa.node4.directory").toString(),config.getProperty("qa.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.reCreatecroneTab(env,config.getProperty("dev.node1.host").toString(),config.getProperty("dev.password").toString(),config.getProperty("dev.node1.directory").toString(),config.getProperty("dev.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.reCreatecroneTab(env,config.getProperty("stg.node1.host").toString(),config.getProperty("stg.password").toString(),config.getProperty("stg.node1.directory").toString(),config.getProperty("stg.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.reCreatecroneTab(env,config.getProperty("stg.node2.host").toString(),config.getProperty("stg.password").toString(),config.getProperty("stg.node2.directory").toString(),config.getProperty("stg.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.reCreatecroneTab(env,config.getProperty("stg.node3.host").toString(),config.getProperty("stg.password").toString(),config.getProperty("stg.node3.directory").toString(),config.getProperty("stg.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.reCreatecroneTab(env,config.getProperty("stg.node4.host").toString(),config.getProperty("stg.password").toString(),config.getProperty("stg.node4.directory").toString(),config.getProperty("stg.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.reCreatecroneTab(env,config.getProperty("sb.node1.host").toString(),config.getProperty("sb.password").toString(),config.getProperty("sb.node1.directory").toString(),config.getProperty("sb.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.reCreatecroneTab(env,config.getProperty("sb.node2.host").toString(),config.getProperty("sb.password").toString(),config.getProperty("sb.node2.directory").toString(),config.getProperty("sb.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.reCreatecroneTab(env,config.getProperty("sb.node3.host").toString(),config.getProperty("sb.password").toString(),config.getProperty("sb.node3.directory").toString(),config.getProperty("sb.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  utility.reCreatecroneTab(env,config.getProperty("sb.node4.host").toString(),config.getProperty("sb.password").toString(),config.getProperty("sb.node4.directory").toString(),config.getProperty("sb.tempdirectory").toString(),config.getProperty("sb.sandboxtempdirectory").toString(),config);
		  System.out.println("To continue press Y,to Exit press any other Key");
		  resume= scan.next();
		  break;
	case 9:
		  ResetPassword resetPassword = new ResetPassword();
		  resetPassword.changePassword(config.getProperty("SandBoxSI_nggsifw.ConnectionName").toString(),config.getProperty("SandBoxSI_nggsifw.Hostname").toString(),config.getProperty("SandBoxSI_nggsifw.Servicename").toString(),config.getProperty("SandBoxSI_nggsifw.Port").toString(),config.getProperty("SandBoxSI_nggsifw.Username").toString(),config.getProperty("SandBoxSI_nggsifw.Password").toString(), config.getProperty("SandBoxSI_nggsifw.NewPassword").toString());
		  System.out.println("To continue press Y,to Exit press any other Key");
		  resume= scan.next();
		  break;
	default:
		System.out.println("parameter is not correct");
		
	}
	  }
	 
  }
  }