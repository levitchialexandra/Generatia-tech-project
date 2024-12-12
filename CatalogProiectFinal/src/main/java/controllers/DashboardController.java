package controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import basic.AppUser;
import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {
	@Autowired
    private HttpSession session;
    @GetMapping("/admin/dashboard")
    public String showAdminDashboard() {
    	 AppUser sessionUser = (AppUser) session.getAttribute("sessionUser");
    	 if (sessionUser == null || !"admin".equals(sessionUser.getRole())) {
             return "redirect:/login"; 
         }
        return "adminpage";  
    }

    @GetMapping("/profesor/dashboard/{userId}")
    public String showProfessorDashboard(@PathVariable Long userId) {
    	 AppUser sessionUser = (AppUser) session.getAttribute("sessionUser");
         if (sessionUser == null || !"profesor".equals(sessionUser.getRole()) || !sessionUser.getProfessor().getId().equals(userId)) {
             return "redirect:/login"; 
         }
        return "redirect:/profesorpage"; 
    }

   /* @GetMapping("/student/dashboard/{userId}")
    public String showStudentDashboard(@PathVariable Long userId) {
    	AppUser sessionUser = (AppUser) session.getAttribute("sessionUser");
        if (sessionUser == null || !"student".equals(sessionUser.getRole()) || !sessionUser.getStudent().getId().equals(userId)) {
            return "redirect:/login"; 
        }
        return "student";  
    }*/
    
   
}
