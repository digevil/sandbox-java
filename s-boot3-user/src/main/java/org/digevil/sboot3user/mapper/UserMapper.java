package org.digevil.sboot3user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.digevil.sandbox.s.model.User;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM aio_users WHERE id = #{id}")
    User findById(@Param("id") int id);
}
