package com.selfstudy.sendemail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import com.selfstudy.sendemail.util.EmailUtil;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        sendSSLEmail();
//        sendTSLEmail();
    }
    
    
    public static void sendTSLEmail() {

        final String fromEmail = "tqh.testemail@gmail.com"; // requires valid gmail id
        final String password = "Q!W@E#R$T%"; // correct password for gmail id
        final String toEmail = "tranquanghuy288@gmail.com"; // can be any email id

        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
        props.put("mail.smtp.port", "587"); // TLS Port
        props.put("mail.smtp.auth", "true"); // enable authentication
        props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS
//        props.put("mail.debug", "true");

        // create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            // override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        EmailUtil.sendEmail(session, toEmail, "TLSEmail Testing Subject", "TLSEmail Testing Body");
    }
    
    public static void sendSSLEmail() {
        final String fromEmail = "tqh.testemail@gmail.com"; // requires valid gmail id
        final String password = "Q!W@E#R$T%"; // correct password for gmail id
        final String toEmail = "tranquanghuy288@gmail.com"; // can be any email id
        
        System.out.println("SSLEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port
//        props.put("mail.debug", "true");
        
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        
        Session session = Session.getDefaultInstance(props, auth);
        System.out.println("Session created");
//            EmailUtil.sendEmail(session, toEmail,"SSLEmail Testing Subject", "SSLEmail Testing Body");

//            EmailUtil.sendAttachmentEmail(session, toEmail,"SSLEmail Testing Subject with Attachment", "SSLEmail Testing Body with Attachment");
//
            EmailUtil.sendImageEmail(session, toEmail,"SSLEmail Testing Subject with Image", "SSLEmail Testing Body with Image");
    }
}
