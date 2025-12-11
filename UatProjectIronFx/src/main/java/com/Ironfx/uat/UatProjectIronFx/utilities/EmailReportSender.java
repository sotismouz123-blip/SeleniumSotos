package com.Ironfx.uat.UatProjectIronFx.utilities;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.activation.*;

import java.util.Properties;

public class EmailReportSender {

    public static void sendReport(String recipientEmail, String reportPath) {
        // Στοιχεία SMTP server (π.χ. Gmail)
        final String username = "nickchigg@gmail.com"; // το email σου
        final String password = "nkcdqkfatzrpcxji";     // app password (όχι το κανονικό password)

        // SMTP configuration
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");


        // Optional but recommended:
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.user", "nickchigg@gmail.com");

        // Δημιουργία session με Jakarta Authenticator
        Session session = Session.getInstance(props,
            new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

        try {
            // Δημιουργία μηνύματος
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(recipientEmail)
            );
            message.setSubject("Automation Test Report");

            // Σώμα email
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Καλησπέρα,\n\nΣας επισυνάπτω το automation test report.\n\nΦιλικά,\nQA Automation");

            // Επισύναψη report
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(reportPath);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName("TestReport.html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);

            // Αποστολή
            Transport.send(message);

            System.out.println("✅ Report email sent successfully to " + recipientEmail);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("❌ Failed to send report email: " + e.getMessage());
        }
    }
}
