
package com.csc340.assignment2demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author unclu
 */
@RestController
public class ApiPrototypeController {
        
    @GetMapping("/cat")
    public Object GetCatFact(){
        try{
            String url = "https://meowfacts.herokuapp.com/?count=1";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();
            
            String jSonQuote = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonQuote);
            
            System.out.println("Cat fact: " + root.get("data").get(0));
            
            return root;
        }
        
        catch (JsonProcessingException ex) {
            Logger.getLogger(ApiPrototypeController.class.getName()).log(Level.SEVERE, null, ex);
            return "error in /cat";
        }
    }
}
