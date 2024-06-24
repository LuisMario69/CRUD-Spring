package com.adso.project1.controller;

import com.adso.project1.model.Registro;
import com.adso.project1.service.ServiceRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registro")
public class UserController {

    @Autowired
    private ServiceRegistro serviceRegistro;

    @GetMapping
    public String listRegistros(Model model) {
        model.addAttribute("registros", serviceRegistro.getAllRegistro());
        return "pages/list";
    }

    @GetMapping("/new")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registro", new Registro());
        return "pages/register";
    }

    @PostMapping
    public String registerUser(@ModelAttribute Registro registro, Model model) {
        try {
            System.out.println("Registro recibido: " + registro);
            serviceRegistro.saveRegister(registro);
            System.out.println("Registro guardado con éxito");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error"; // Redirigir a la página de error
        }
        return "redirect:/registro";
    }

    @GetMapping("/{id}")
    public String viewRegistro(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("registro", serviceRegistro.getRegistroById(id).orElseThrow(() -> new IllegalArgumentException("Invalid registro Id:" + id)));
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return "pages/view";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        model.addAttribute("registro", serviceRegistro.getRegistroById(id).orElseThrow(() -> new IllegalArgumentException("Invalid registro Id:" + id)));
        return "pages/edit";
    }

    @PostMapping("/{id}")
    public String updateRegistro(@PathVariable Long id, @ModelAttribute Registro registro, Model model) {
        try {
            registro.setId(id);
            serviceRegistro.updateRegistro(registro);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return "redirect:/registro";
    }


    @GetMapping("/{id}/delete")
    public String deleteRegistro(@PathVariable Long id, Model model) {
        try {
            serviceRegistro.deleteRegistro(id);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return "redirect:/registro";
    }
}

