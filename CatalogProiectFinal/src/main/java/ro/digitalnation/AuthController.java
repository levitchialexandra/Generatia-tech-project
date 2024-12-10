package ro.digitalnation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import basic.AppUserRepository;

@Controller
public class AuthController {

	AppUserRepository appUserRep;
    // Pagina de login
    @GetMapping("/login")
    public String loginPage() {
    System.out.println(appUserRep.count());	
        return "login";  
    }
  
    
}
