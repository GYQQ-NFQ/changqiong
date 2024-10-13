package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.result.PageResult;

public interface CategoryService {
    /**分类分页查询
     *
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**根据id删除分类
     *
     * @param id
     * @return
     */
    void delete(Long id);

    /**启用禁用分类
     *
     * @param status
     * @param id
     * @return
     */
    void startOrStop(Integer status, Long id);

    /**新增分类
     *
     * @param categoryDTO
     * @return
     */
    void save(CategoryDTO categoryDTO);

    /**编辑分类信息
     *
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);

    /**根据类型查询分类
     *
     * @param type
     * @return
     */
    void list(long type);
}
