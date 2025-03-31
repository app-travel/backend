package app.travel.model.tokens;

import org.apache.ibatis.annotations.*;

import java.util.Optional;
import java.util.UUID;

@Mapper
public interface TokenMapper {

    @Select("SELECT * FROM tokens t " +
            "WHERE t.id = #{id}"
    )
    @Results(id = "TokenEntityResultMap", value = {
            @Result(column = "user_id", property = "userId"),
            @Result(column = "token_type", property = "tokenType"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt")
    })
    Optional<TokenEntity> findById(@Param("id") UUID id);

}
