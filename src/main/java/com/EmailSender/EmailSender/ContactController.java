package com.EmailSender.EmailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
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
    @PostMapping("/contact")
    public String submitForm(@ModelAttribute("contactForm") ContactForm contactForm) {
        // Build email body
        String emailBody = String.format(
                "Name: %s\nEmail: %s\nPhone: %s\nQuery: %s",
                contactForm.getName(),
                contactForm.getEmail(),
                contactForm.getNumber(),
                contactForm.getQuery()
        );

        // Send email
        emailService.sendEmail(
                "your-email@gmail.com", // Your email
                "New Contact Form Submission",
                emailBody
        );

        return "redirect:/contact/success";
    }

    // Success page
    @GetMapping("/contact/success")
    public String successPage() {
        return "success";
    }


}
