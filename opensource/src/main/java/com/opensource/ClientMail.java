package com.opensource;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

public class ClientMail {

  private static Store store;
  private static Properties properties;
  
  public static void properties(String host) {
    properties = new Properties();

    properties.put("mail.pop3s.host", host);
    properties.put("mail.pop3s.port", "995");
    properties.put("mail.pop3s.starttls.enable", "true");

  }
  
  public static void connectStore() throws Exception {
    
    Session emailSession = Session.getInstance(properties, new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("youremail@mail.com", "yourpassword");
      }
    });
    // emailSession.setDebug(true);

    store = emailSession.getStore("pop3s");

    store.connect();
  }

  public void check() throws Exception {
      Folder emailFolder = store.getFolder("INBOX");
      emailFolder.open(Folder.READ_ONLY);

      Message[] messages = emailFolder.getMessages();

      for (int i = 0, n = messages.length; i < n; i++) {
        Message message = messages[i];
        System.out.println("---------------------------------");
        System.out.println("Email Number " + (i + 1));
        System.out.println("Subject: " + message.getSubject());
        System.out.println("From: " + message.getFrom()[0]);
        System.out.println("Text: " + message.getContent().toString());
      }

      emailFolder.close(false);
      store.close();
  }
}
