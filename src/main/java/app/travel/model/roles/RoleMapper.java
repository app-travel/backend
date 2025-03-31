package app.travel.model.roles;

import org.apache.ibatis.annotations.*;

import java.util.Optional;
import java.util.UUID;

@Mapper
public interface RoleMapper {

    @Select("SELECT * FROM roles r " +
            "WHERE r.id = #{id}"
    )
    @Results(id = "RoleEntityResultMap", value = {
            @Result(column = "role_name", property = "roleName"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt")
    })
    Optional<RoleEntity> findById(@Param("id") UUID id);

}
