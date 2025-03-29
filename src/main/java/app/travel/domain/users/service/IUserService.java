package app.travel.domain.users.service;

import app.travel.model.users.UserEntity;

public interface IUserService {

    UserEntity getUserByUsername(String username);

    UserEntity getUserByUsernameOrEmail(String usernameOrEmail);

}
