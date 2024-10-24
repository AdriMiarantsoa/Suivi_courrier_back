package adri.suivi_courrier.controller;
import adri.suivi_courrier.data.entity.Role;
import adri.suivi_courrier.service.RoleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin

public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/insert_role")
    public ResponseEntity<String> submitRole(@RequestBody Role role) {
        try {
            roleService.saveRole(role); // Ensure this method handles Role objects correctly
            return ResponseEntity.ok("Role submitted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @DeleteMapping("/deleteRole/{id_role}")
    public ResponseEntity<Void> deleteRole(@PathVariable String id_role) {
        roleService.deleteRole(id_role);
        return ResponseEntity.noContent().build();  
    }

}
