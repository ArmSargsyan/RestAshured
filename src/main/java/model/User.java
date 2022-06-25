package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@Data
public class User {

    private int id;
    private String name;
    private String email;
    private String gender;
    private String status;


    public static String getRandomUser() {
       final String x = String.valueOf(System.currentTimeMillis());
       final String name = x;
       final String email = x + "@gmail.com";
//       final String gender = "male";
//       final String status = "active";
//
//        return new User(name, email, gender, status);
        return "{\n" +
                "        \"name\": \""+name+"\",\n" +
                "        \"email\": \""+email+"\",\n" +
                "        \"gender\": \"female\",\n" +
                "        \"status\": \"inactive\"\n" +
                "    }";
    }
}
