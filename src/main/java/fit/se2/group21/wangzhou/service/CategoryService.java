package fit.se2.group21.wangzhou.service;

import java.util.List;

/**
 * Category service interface
 */
public interface CategoryService {

    Object getCategoryById(Integer categoryId);

    List<Object> getAllCategories();

    Object createCategory(Object request);

    Object updateCategory(Integer categoryId, Object request);

    void deleteCategory(Integer categoryId);
}

