package uz.pcmartket.apppcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pcmartket.apppcmarket.entity.Category;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.CategoryDto;
import uz.pcmartket.apppcmarket.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    public ApiResponse addCategory(CategoryDto categoryDto) {

        Category parent = null;
        if (categoryDto.getParentId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentId());
            if (!optionalCategory.isPresent()) return new ApiResponse("Parent category not found", false);
            parent = optionalCategory.get();
        }

        boolean exists = categoryRepository.existsByNameAndParentCategoryId(categoryDto.getName(), categoryDto.getParentId());
        if (exists) return new ApiResponse("Such a category exists", false);

        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setParentCategory(parent);
        category = categoryRepository.save(category);

        return new ApiResponse("Category seccessfully saved", true, category);
    }

    public ApiResponse getCategory(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.map(category -> new ApiResponse("Category found", true, category)).orElseGet(() -> new ApiResponse("Category not found", false));
    }

    public List<Category> getCategorys() {
        return categoryRepository.findAll();
    }

    public ApiResponse editCategory(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) return new ApiResponse("Category not found", false);

        Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getId());
        if (!optionalParentCategory.isPresent()) return new ApiResponse("Parent category not found", false);

        boolean exists = categoryRepository.existsByNameAndParentCategoryIdAndIdNot(categoryDto.getName(), categoryDto.getParentId(), id);
        if (exists) return new ApiResponse("Such a category exists", false);

        Category category = optionalCategory.get();
        category.setParentCategory(optionalParentCategory.get());
        category.setName(categoryDto.getName());
        category = categoryRepository.save(category);

        return new ApiResponse("Category seccessfully edited", true, category);
    }

    public ApiResponse deleteCategory(Integer id) {
        try {
            categoryRepository.deleteById(id);
            return new ApiResponse("Category seccessfully deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Category not found", false);
        }
    }
}
