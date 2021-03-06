package com.huizhi.dass.manager.service;

import java.util.List;

import com.huizhi.dass.model.Admin;
import com.huizhi.dass.model.PageInfo;
import com.huizhi.dass.model.Criteria;

public interface AdminService {
    /**
     * 根据条件查询记录总数
     */
    int countByCriteria(Criteria criteria);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    Long add(Admin record);

    /**
     * 根据条件查询记录集
     */
    List<Admin> findListByCriteria(Criteria criteria);
    
    /**
     * 根据条件查询记录集
     */
    PageInfo<Admin> findListByCriteria(Criteria criteria, int pageNo, int pageSize);

    /**
     * 根据主键查询记录
     */
    Admin findById(Long id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByIdSelective(Admin record);
    
    /**
     * 更加登录名查询
     * @param username
     * @return
     */
    Admin findByUsername(String username);

}