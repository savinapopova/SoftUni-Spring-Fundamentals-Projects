package com.example.gira.web;

import com.example.gira.model.dto.CreateTaskDTO;
import com.example.gira.service.TaskService;
import com.example.gira.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TaskController {

    private TaskService taskService;
    private UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @ModelAttribute
    public CreateTaskDTO init() {
        return new CreateTaskDTO();
    }

    @GetMapping("/task/add")
    public String addTask() {

        if (!this.userService.isLogged()) {
            return "redirect:/";
        }

        return "add-task";
    }

    @PostMapping("/task/add")
    public String addTask(@Valid CreateTaskDTO createTaskDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (!this.userService.isLogged()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createTaskDTO", createTaskDTO);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.createTaskDTO",
                            bindingResult);
            return "redirect:/task/add";
        }
        if (this.taskService.nameExists(createTaskDTO)) {
            redirectAttributes.addFlashAttribute("createTaskDTO", createTaskDTO);
            redirectAttributes
                    .addFlashAttribute("taskExists",true);
            return "redirect:/task/add";
        }
        this.taskService.register(createTaskDTO);
        return "redirect:/home";
    }
}
