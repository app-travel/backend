package app.travel.domain.users.service;

import app.travel.model.users.UserEntity;

import java.util.UUID;

public interface IUserService {

    UserEntity getUserByUsername(String username);

    UserEntity getUserByUsernameOrEmail(String usernameOrEmail);

    UserEntity getUserById(UUID id);

}
