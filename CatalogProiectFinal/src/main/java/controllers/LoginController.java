package controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import basic.AppUser;
import jakarta.servlet.http.HttpSession;
import repository.AppUserRepository;

@Controller
public class LoginController {
	@Autowired
    private HttpSession session;
	@Autowired
	AppUserRepository appUserRepo;
	
    @GetMapping("/login")
    public String loginPage() {
    
        return "login";  
    }
    
    @PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, Model model) {
        
    	try {
            AppUser appUser = loginUser(username, password); 
            session.setAttribute("sessionUser", appUser);
            String redirectUrl = getUserRoleAndRedirect(appUser);

            return "redirect:/" + redirectUrl; 

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "login"; 
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
       session.invalidate();
       
        return "redirect:/login";
    }
    
    public AppUser loginUser(String username, String password) {
        AppUser appUser = appUserRepo.findByUsername(username).orElse(null);

        if (appUser==null) {
            throw new RuntimeException("Utilizatorul nu a fost găsit");
        }

       
        if (!appUser.checkPassword(password)) {
            throw new RuntimeException("Parolă incorectă");
        }

        return appUser; 
    }
    public String getUserRoleAndRedirect(AppUser appUser) {
        switch (appUser.getRole()) {
            case "admin":
                return "admin/dashboard";
            case "student":
                return "student/dashboard/" + appUser.getStudent().getId();
            case "profesor":
                return "profesor/dashboard/" + appUser.getProfessor().getId();
            default:
                throw new RuntimeException("Rol necunoscut");
        }
    }
   

    
}
