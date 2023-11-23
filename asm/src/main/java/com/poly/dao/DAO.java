 package com.poly.dao;

import java.util.List;

public interface DAO<T, K> {
	T insert(T entity);
	T update(T entity);
	T findById(K id);
	void delete(K[] ids);
	List<T> findAllWithPagination(int offset, int skip);
	int count();
}
