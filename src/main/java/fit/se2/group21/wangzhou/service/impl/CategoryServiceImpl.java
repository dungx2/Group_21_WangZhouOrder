package fit.se2.group21.wangzhou.service.impl;

import fit.se2.group21.wangzhou.repository.CategoryRepository;
import fit.se2.group21.wangzhou.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Category service implementation
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public Object getCategoryById(Integer categoryId) {
        // TODO: Implement get category by ID
        return new Object();
    }

    @Override
    public List<Object> getAllCategories() {
        // TODO: Implement get all categories
        return List.of();
    }

    @Override
    public Object createCategory(Object request) {
        // TODO: Implement create category
        return new Object();
    }

    @Override
    public Object updateCategory(Integer categoryId, Object request) {
        // TODO: Implement update category
        return new Object();
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        // TODO: Implement delete category
    }
}

