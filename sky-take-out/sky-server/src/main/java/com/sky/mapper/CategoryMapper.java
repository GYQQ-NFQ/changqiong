// @author Tjzlo
// @version 2024/10/13 13:37

package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    /**分类分页查询
     *
     * @param categoryPageQueryDTO
     * @return
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**删除分类
     *
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void delete(Long id);

    /**动态修改数据
     *
     * @param category
     */
    void update(Category category);

    /**新增分类
     *
     * @param category
     */
    @Insert("INSERT INTO category (id, type, name, sort, status, create_time, update_time, create_user, update_user) " +
            "values" +
            "(#{id},#{type},#{name},#{sort},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void insert(Category category);
}
