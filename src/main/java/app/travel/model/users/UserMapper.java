package app.travel.model.users;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.Optional;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users u " +
            "WHERE u.username = #{username}"
    )
    Optional<UserEntity> findByUsername(@Param("username") String username);

    @Select("SELECT * FROM users u " +
            "WHERE u.username = #{usernameOrEmail} " +
            "OR u.email = #{usernameOrEmail}"
    )
    Optional<UserEntity> findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);

}
