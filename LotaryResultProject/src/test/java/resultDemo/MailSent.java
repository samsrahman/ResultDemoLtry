package resultDemo;


import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;


public class MailSent {

public void mail() throws InterruptedException, IOException {
	

    final String username = "qasamsur@gmail.com";
    final String password = "9932442432Qa@";

    Properties props = new Properties();

	
	  props.put("mail.smtp.auth", true); 
	  props.put("mail.smtp.starttls.enable",
	  true); 
	  props.put("mail.smtp.host", "smtp.gmail.com");
	  props.put("mail.smtp.port", "465");
	  props.put("mail.smtp.EnableSSL.enable","true");
	 

    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("qasamsur@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("samsrahman@gmail.com"));
        message.setSubject("Testing Subject");
        message.setText("PFA");

        MimeBodyPart messageBodyPart = new MimeBodyPart();

        Multipart multipart = new MimeMultipart();
        
        String file ="C:\\Users\\justf\\Desktop\\Samsur_Automation\\1.jpg";
        String fileName = "Result";
        DataSource source = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        System.out.println("Sending");

        Transport.send(message);

        System.out.println("Done");

    } catch (MessagingException e) {
        e.printStackTrace();
    }
  }
public  void result() throws InterruptedException, IOException {
	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Driver\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.get("https://lotterysambadresult.in/");
	driver.findElement(By.xpath("//span[text()='08:00 PM']")).click();
	Thread.sleep(2000);
	WebElement img=driver.findElement(By.xpath("//span[@class='ws20']/img"));
	WebElement refresh=driver.findElement(By.xpath("//span[text()='Refresh Page']"));
	
	
	
	
	for(int i=0; i<=25; i++) {
		/*
		 * String js =
		 * "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';";
		 * ((JavascriptExecutor) driver).executeScript(js, refresh); refresh.click();
		 */
		//refresh.click();
		Thread.sleep(10000);
		String js1 = "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';";
		((JavascriptExecutor) driver).executeScript(js1, img);
		
		if(img.isDisplayed()==true) {
			img.click();
			String pic=img.getAttribute("src");
			driver.get(pic);
			Thread.sleep(10000);
			 File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		        
		        //Copy the file to a location and use try catch block to handle exception
		        try {
		            FileUtils.copyFile(screenshot, new File("C:\\Users\\justf\\Desktop\\Samsur_Automation\\1.jpg"));
		        } catch (IOException e) {
		            System.out.println(e.getMessage());
		        }
		        
		      break;

		}
		else {
			Thread.sleep(60000);
		}
	}
	driver.quit();
}
}
