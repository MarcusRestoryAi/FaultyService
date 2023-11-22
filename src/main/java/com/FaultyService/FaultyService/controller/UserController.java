package com.FaultyService.FaultyService.controller;

import com.FaultyService.FaultyService.models.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/")
    public String login(
            @RequestBody User user
            ) {
        //Fetch values from payload
        String username = user.getUsername();
        String password = user.getPassword();

        //Establish connectnio to DB
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bobby", "root", "SokrateS13");

            //String sqlQuerry = String.format("SELECT * FROM users2 WHERE username = '%s' && password = '%s';", username, password);

            //conn.prepareStatement(sqlQuerry);

            Statement stmt = conn.createStatement();
            System.out.println(username);
            System.out.println(password);

            String sqlquerry = String.format("SELECT * FROM users2 WHERE username = '%s' && password = '%s';", username, password);
            System.out.println(sqlquerry);

            ResultSet rs = stmt.executeQuery(sqlquerry);

            if (rs.next()) return "Du har loggat in";
            else           return "Inloggning misslyckad";

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
