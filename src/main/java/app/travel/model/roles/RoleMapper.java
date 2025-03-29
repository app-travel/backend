package app.travel.model.roles;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;
import java.util.UUID;

@Mapper
public interface RoleMapper {

    @Select("SELECT * FROM roles r WHERE r.id = #{id}")
    Optional<RoleEntity> findById(@Param("id") UUID id);

}
