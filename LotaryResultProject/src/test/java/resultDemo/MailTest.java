package resultDemo;

import java.io.IOException;

import org.testng.annotations.Test;

public class MailTest {
  @Test
  public void f() throws InterruptedException, IOException {
	 MailSent s= new MailSent();
	 s.result();
	 s.mail();
  }
}
