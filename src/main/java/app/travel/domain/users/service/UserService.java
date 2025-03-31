package app.travel.domain.users.service;

import app.travel.advice.exception.templates.ErrorHolderException;
import app.travel.common.constant.Error;
import app.travel.model.roles.RoleEntity;
import app.travel.model.users.UserEntity;
import app.travel.model.users.UserMapper;
import app.travel.shared.identity.UserDetailsImpl;
import app.travel.shared.service.roles.IRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j(topic = "USER-SERVICE")
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService, UserDetailsService {

    UserMapper userMapper;

    IRoleService roleService;

    @Override
    public UserEntity getUserByUsername(String username) {

        log.info("get user by username {}", username);

        return userMapper.findByUsername(username).orElseThrow(
                () -> {

                    log.error("get user by username {} failed", username);

                    return new ErrorHolderException(Error.RESOURCE_NOT_FOUND);
                }
        );
    }

    @Override
    public UserEntity getUserByUsernameOrEmail(String usernameOrEmail) {

        log.info("get user by username or email {}", usernameOrEmail);

        return userMapper.findByUsernameOrEmail(usernameOrEmail).orElseThrow(
                () -> {

                    log.error("get user by username or email {} failed", usernameOrEmail);

                    return new ErrorHolderException(Error.RESOURCE_NOT_FOUND);
                }
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("load user by username {}", username);

        UserEntity userEntity = userMapper.findByUsernameOrEmail(username)
                        .orElseThrow(
                                () -> {

                                    log.error("load user by username or email {} failed", username);

                                    return new UsernameNotFoundException(Error.BAD_CREDENTIALS.getMessage());
                                }
                        );

        RoleEntity roleEntity = roleService.getRoleById(userEntity.getRoleId());

        return UserDetailsImpl.builder()
                .user(userEntity)
                .role(roleEntity)
                .build();
    }

    @Override
    public UserEntity getUserById(UUID id) {

        log.info("get user by id {}", id);

        return userMapper.findById(id).orElseThrow(
                () -> {

                    log.error("get user by id {} failed", id);

                    return new ErrorHolderException(Error.RESOURCE_NOT_FOUND);
                }
        );
    }
}
