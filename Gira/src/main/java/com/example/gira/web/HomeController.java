package com.example.gira.web;

import com.example.gira.model.dto.TaskDTO;
import com.example.gira.service.TaskService;
import com.example.gira.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    private UserService userService;
    private TaskService taskService;

    @Autowired
    public HomeController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String index() {
        if (this.userService.isLogged()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {

        if (!this.userService.isLogged()) {
            return "redirect:/";
        }

        List<TaskDTO> tasks = this.taskService.getAllTasks();

        model.addAttribute("tasks", tasks);

        return "home";
    }

    @GetMapping("/task/progress/{id}")
    public String progressTask(@PathVariable Long id) {
        if (!this.userService.isLogged()) {
            return "redirect:/";
        }
        this.taskService.progress(id);
        return "redirect:/home";
    }
}
