package com.opensource;

public class Principal {
  
  public static void main(String[] args) {

    ClientMail client = new ClientMail();
    String host = "pop.gmail.com";
    
    ClientMail.properties(host);
    
    try {
      ClientMail.connectStore();
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    
    try {
      client.check();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
