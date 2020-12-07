package edu.miu.cs.auctionproject.controller;

import edu.miu.cs.auctionproject.domain.Role;
import edu.miu.cs.auctionproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(value = {"/role"})
public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping(value = {"/add"})
    public void addNewRole(@RequestBody Role role) {
        System.out.println(role.toString());
        roleService.saveRole(role);
    }

    @GetMapping(value = {"/get/{id}"})
    public Optional<Role> getRolebyId(@PathVariable(value = "id") Long id) {
        return roleService.findRoleById(id);
    }

    @DeleteMapping(value = {"/delete/{id}"})
    public void deleteRoleById(@PathVariable(value = "id") Long id) {
        roleService.deleteRole(id);
    }

    @GetMapping(value = {"/getall"})
    public List<Role> getAllRoles() {
        return roleService.findAllRoles();
    }

    @GetMapping(value = {"edit/{roleId}"})
    public String editRole(@PathVariable long roleId, Model model) {
        Optional<Role> role = roleService.findRoleById(roleId);
        if (role.isPresent()) {
            model.addAttribute("role", role.get());
            return "/editroleform";
        }
        return "/getall";
    }

    @PostMapping(value = {"/edit"})
    public String updateRole(@Validated @ModelAttribute("role") Role role,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/editroleform";
        }
        roleService.saveRole(role);
        return "redirect:/getall";
    }
}
