package javaMailUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import entidades.SmtpPop3Server;
import exeptions.ConfigUtilityException;
 
/**
 * A utility class that reads/saves SMTP & POP3 settings from/to  property file.
 *
 */
public class ConfigUtility {
    
   String systemPropertiesServerFileName = System.getProperty("user.dir") + "/src/resources/server.properties";
     
    public void setServerProperties(SmtpPop3Server server) throws ConfigUtilityException {
	
	
        try (OutputStream output = new FileOutputStream(systemPropertiesServerFileName)) {

            Properties prop = new Properties();

            // set the properties value
            prop.setProperty("mail.smtp.host", server.getSmptHost());
            prop.setProperty("mail.smtp.port", server.getSmptPort());
            prop.setProperty("mail.user", server.getUserName());
            prop.setProperty("mail.password", server.getPassword());
            prop.setProperty("mail.smtp.auth", "true"); 
            prop.setProperty("mail.smtp.starttls.enable", "true"); 
            prop.setProperty("mail.smtp.ssl.trust", server.getSmptHost());
            prop.setProperty("mail.pop3.host", server.getPop3Host());
            prop.setProperty("mail.pop3.port", server.getPop3Port());
            prop.setProperty("mail.user", server.getUserName());
            prop.setProperty("mail.password", server.getPassword());
            prop.setProperty("mail.pop3.ssl.enable", "true");
            prop.setProperty("mail.pop3.ssl.trust", "*");

            // save properties to project root folder
            prop.store(output, null);


        } catch (Exception e) {
            throw new ConfigUtilityException("No se pudo establecer las propiedades de los servidores ");
        }
         
    } 
    
    
    public Properties loadServerProperties() throws ConfigUtilityException {
	
	try (InputStream input = new FileInputStream(systemPropertiesServerFileName)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // return properties file
            return prop;

        } catch (Exception ex) {
            throw new ConfigUtilityException("No se pudo cargar las propiedades de los servidores ");
        }

    }
        
    

}
