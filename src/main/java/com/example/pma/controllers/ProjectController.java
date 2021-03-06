package com.example.pma.controllers;

import com.example.pma.dao.EmployeeRepository;
import com.example.pma.dao.ProjectRepository;
import com.example.pma.entities.Employee;
import com.example.pma.entities.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.PreUpdate;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectRepository proRepo;
    @Autowired
    EmployeeRepository empRepo;

    @GetMapping
    public String displayProjects(Model model) {
        List<Project> projects =  proRepo.findAll();
        model.addAttribute("projects", projects);
        return "projects/list-projects";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model) {
        Project project = new Project();
        List<Employee> employees = empRepo.findAll();
        model.addAttribute("allEmployees", employees);
        model.addAttribute("project", project);
        return "projects/new-project";
    }

    @PostMapping("/save")
    public String createProject(Project project, Model model) {
        proRepo.save(project);
        // use a redirect to prevent duplicate submissions
        return "redirect:/projects";
    }

}
