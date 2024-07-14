package com.example.contact.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.contact.Service.EmailService;

@RestController
@RequestMapping("/Contact")
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class ContactController {

    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

 @Autowired
 private EmailService emailService;
 @PostMapping("/Send")
 public String sendEmail(@RequestBody ContactForm contactForm) {
	 
	 try {
         emailService.sendSimpleMessage(
             "sivcloud.12@gmail.com", 
             contactForm.getSubject(),
             "From: " + contactForm.getName() + "\nEmail: " + contactForm.getEmail() + "\n\n" + contactForm.getMessage()
         );
         return "Email sent successfully";
     } catch (Exception e) {
         logger.error("Error while sending email", e);
         return "Error: " + e.getMessage();
     }
 }
 @GetMapping("/get")
 public String hello() {
	 return "Hello";
 }
 public static class ContactForm {
     private String name;
     private String email;
     private String subject;
     private String message;

     // Getters and setters
     public String getName() { return name; }
     public void setName(String name) { this.name = name; }
     public String getEmail() { return email; }
     public void setEmail(String email) { this.email = email; }
     public String getSubject() { return subject; }
     public void setSubject(String subject) { this.subject = subject; }
     public String getMessage() { return message; }
     public void setMessage(String message) { this.message = message; }
 }
}

