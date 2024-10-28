package adri.suivi_courrier.service;

import adri.suivi_courrier.data.entity.Role;
import adri.suivi_courrier.data.repository.RoleRepository;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RoleRepository roleRepository;

    public void saveRole(Role role) {
        String nextId = jdbcTemplate.queryForObject("SELECT generate_id('ROLE',5, 'seqRole')", String.class);

        role.setId_role(nextId);

        String sql = "INSERT INTO role (id_role, nom_role) VALUES (?, ?)";

        jdbcTemplate.update(sql, role.getId_role(), role.getNom_role());

    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Transactional
    public void deleteRole(String id_role) {
        roleRepository.deleteRoleById(id_role);
    }

    @Transactional
    public void updateRole(Role r) {
        roleRepository.updateRole(r.getId_role(),r.getNom_role());
    }
}
