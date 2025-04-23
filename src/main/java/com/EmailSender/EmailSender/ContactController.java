package com.EmailSender.EmailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/contact")
public class ContactController {

    private final EmailService emailService;

    @Autowired
    public ContactController(EmailService emailService) {
        this.emailService = emailService;
    }


    @GetMapping("/contact")
    public String showForm(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "contact-form"; // Thymeleaf template
    }

    // Handle form submission
    @PostMapping("/submit")  // Added specific path
    public ResponseEntity<String> submitForm(@RequestBody ContactForm contactForm) {
        try {
            String emailBody = String.format(
                    "Name: %s\nEmail: %s\nPhone: %s\nQuery: %s",
                    contactForm.getName(),
                    contactForm.getEmail(),
                    contactForm.getPhone(),  // Make sure this matches your ContactForm field
                    contactForm.getQuery()
            );

            emailService.sendEmail(
                    "your-email@gmail.com",
                    "New Contact Form Submission",
                    emailBody
            );

            return ResponseEntity.ok("Message sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send message");
        }
    }




}
