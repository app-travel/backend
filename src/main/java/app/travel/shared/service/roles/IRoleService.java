package app.travel.shared.service.roles;

import app.travel.model.roles.RoleEntity;

import java.util.UUID;

public interface IRoleService {

    RoleEntity getRoleById(UUID id);

}
