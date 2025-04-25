package com.EmailSender.EmailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {
        "http://localhost:3000",
        "https://catatlystinstitute.netlify.app"
})
@RequestMapping("/api/contact")
public class ContactController {

    private final EmailService emailService;

    @Autowired
    public ContactController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitForm(@RequestBody ContactForm contactForm) {
        try {
            String emailBody = String.format(
                    "Name: %s\nEmail: %s\nPhone: %s\nQuery: %s",
                    contactForm.getName(),
                    contactForm.getEmail(),
                    contactForm.getPhone(),
                    contactForm.getQuery()
            );

            emailService.sendEmail(
                    "your-email@gmail.com", // Replace with your actual email
                    "New Contact Form Submission",
                    emailBody
            );

            return ResponseEntity.ok("Message sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Failed to send message: " + e.getMessage());
        }
    }
}