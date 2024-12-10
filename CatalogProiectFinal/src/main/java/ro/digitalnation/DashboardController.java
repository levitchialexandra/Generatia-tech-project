package ro.digitalnation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DashboardController {

    @GetMapping("/admin/dashboard")
    public String showAdminDashboard() {
        return "adminpage";  
    }

    @GetMapping("/profesor/dashboard/{userId}")
    public String showProfessorDashboard(@PathVariable Long userId) {
        return "profesor"; 
    }

    @GetMapping("/student/dashboard/{userId}")
    public String showStudentDashboard(@PathVariable Long userId) {
        return "student";  
    }
}
