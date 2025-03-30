package app.travel.model.users;

import org.apache.ibatis.annotations.*;

import java.util.Optional;
import java.util.UUID;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users u " +
            "WHERE u.id = #{id}"
    )
    @Results(id = "UserEntityFieldsMap", value = {
            @Result(column = "role_id", property = "roleId"),
            @Result(column = "is_enabled", property = "isEnabled"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt")
    })
    Optional<UserEntity> findById(@Param("id") UUID id);


    @Select("SELECT * FROM users u " +
            "WHERE u.username = #{username}"
    )
    @ResultMap("UserEntityFieldsMap")
    Optional<UserEntity> findByUsername(@Param("username") String username);


    @Select("SELECT * FROM users u " +
            "WHERE u.username = #{usernameOrEmail} " +
            "OR u.email = #{usernameOrEmail}"
    )
    @ResultMap("UserEntityFieldsMap")
    Optional<UserEntity> findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);

}
