package com.zwd.boot.common.base;

import java.util.List;

/**
 * 基本CURD
 * @author 随风逐梦
 * @create 2020-07-20 21:40
 */
public interface AbstractService<T,PK> {
    T insert(T entity);

    default void insertList(List<T> entities) {

    }

    boolean removeByPrimaryKey(PK primaryKey);

    boolean updateSelective(T entity);

    T getByPrimaryKey(PK primaryKey);

    default List<T> listAll() {
        return null;
    }
}
