package org.digevil.sboot3user.mapper;

import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.ibatis.annotations.*;
import org.digevil.sandbox.s.model.User;

@Schema
@Mapper
public interface UserMapper {

    /**
     * 应该避免使用 ${} 而尽量使用 #{}, 区别是 #{} 代表了参数填充会做校验而 ${} 只是字符串替换所以不安全容易导致sql注入和错误:
     * <p>
     * select * from teacher where name = #{name} == select * from teacher where name = ?
     * <p>
     * select * from teacher where name = ${name} == select * from teacher where name = 'var_value'
     * <p>
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM aio_users WHERE id = #{id}")
    User findById(@Param("id") int id);

    @Select("SELECT * FROM aio_users WHERE uuid = #{uuid}")
    User findByUuid(@Param("uuid") String uuid);

    /**
     * @param user insert 成功的话 id 会刷新成真正的插入记录的 id
     */
    @Insert("INSERT INTO aio_users(uuid, name, gender) VALUES (#{uuid}, #{name}, #{gender})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(User user);
}
