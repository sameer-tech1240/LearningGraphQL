package com.boot.graphql.service;

import com.boot.graphql.entity.User;
import com.boot.graphql.repo.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Log4j2
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${pdf.report.path}")
    private String pdfPath;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(int userId, String userName, String email) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        if (userName != null && !userName.isEmpty()) {
            user.setUserName(userName);
        }
        if (email != null && !email.isEmpty()) {
            user.setEmail(email);
        }

        return userRepository.save(user);
    }


    @Override
    public User getUserById(int userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id : " + userId));

    }

    @Override
    public List<User> getAllUsers() {
        List<User> all = userRepository.findAll();
        if (all.isEmpty()) {
            log.warn("No users found");
            throw new RuntimeException("No users found");
        }
        log.info("fetch all users : {}", all);
        return all;
    }

    @Override
    public boolean existByUserName(String userName) {
        if (userName == null || userName.isEmpty()) {
            return false;
        }
        return userRepository.existsByUserName(userName);

    }

    @Override
    public List<User> findByUserNameContaining(String userName) {
        if (userName == null || userName.isEmpty()) {
            return userRepository.findAll();
        }
        return userRepository.findByUserNameContaining(userName);
    }

    @Override
    public String deleteUserById(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User with ID " + id + " deleted successfully.";
        }

        return "User with ID " + id + " not found.";
    }

    @Override
    public List<User> getAllUserInDescendingOrder() {
        return userRepository.findAllByOrderByUserIdDesc();
    }

    @Override
    public String allUserPDFGenerator() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new RuntimeException("No users found to generate report.");
        }
        try {
            PdfWriter writer = new PdfWriter(pdfPath);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            document.add(new Paragraph("User Report"));
            document.add(new Paragraph("Total Users: " + users.size()));
            document.add(new Paragraph("\n"));

            for (User user : users) {
                document.add(new Paragraph("User ID: " + user.getUserId()));
                document.add(new Paragraph("User Name: " + user.getUserName()));
                document.add(new Paragraph("Email: " + user.getEmail()));
                document.add(new Paragraph("Phone: " + user.getPhone()));
                document.add(new Paragraph("Password: " + maskPasswordForGeneratePDF(user.getPassword())));
                document.add(new Paragraph("\n"));

            }

            document.close();
            pdfDocument.close();

            return "PDF generated successfully at " + pdfPath;
        } catch (IOException e) {
            log.error("Error generating PDF: {}", e.getMessage());
            throw new RuntimeException("Error generating PDF", e);
        }
    }


    private String maskPasswordForGeneratePDF(String password) {
        if (password == null || password.length() <= 4) {
            return "****";
        }
        return password.substring(0, password.length() - 4) + "****";

    }


}
