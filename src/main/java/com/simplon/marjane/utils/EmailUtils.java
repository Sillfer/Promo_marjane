package com.simplon.marjane.utils;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalTime;
import java.util.Date;
import java.util.Random;

public abstract class EmailUtils {


        /**
         * Utility method to send simple HTML email
         * @param session Session
         * @param toEmail String
         * @param subject String
         * @param body String
         */
        public static void sendEmail(Session session, String toEmail, String fromEmail, String subject, String body){
            LocalTime sentTime = null;
            try
            {
                MimeMessage msg = new MimeMessage(session);
                //set message headers
                msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
                msg.addHeader("format", "flowed");
                msg.addHeader("Content-Transfer-Encoding", "8bit");

                msg.setFrom(new InternetAddress(fromEmail, "Marjane Email Service"));

                msg.setReplyTo(InternetAddress.parse(toEmail, false));

                msg.setSubject(subject, "UTF-8");

//            msg.setText(body, "UTF-8");
                msg.setContent(body, "text/html");

                msg.setSentDate(new Date());


                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
                System.out.println("Sending email...");
                Transport.send(msg);

                System.out.println("Email was sent successfully!");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
