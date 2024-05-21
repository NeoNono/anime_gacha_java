package server.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouletteController {
    @GetMapping(path = {"", "/"})
   public ResponseEntity<String> getMethod(){
        String welcomeMessage = """
                <p>
                Welcome to GachaGirls! <br>
                Here is the API map: <br>
                POST /players <br>
                    Create a new player <br>
                    <br>
                etc...<br>
                </p>
                """;

        return ResponseEntity.ok(welcomeMessage); // ResponseEntity.ok("\n" +
//                "Choose an option:\n" +
//                "1- check the collection\n" +
//                "2- pull for character\n" +
//                "3- open the store *\n" +
//                "4- upgrade your character from the collection\n" +
//                "5- fight a character\n" +
//                "6 - sell the character");
    }
}
