package com.Strong.Tshirt_Web.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Strong.Tshirt_Web.Entity.Categories;
import com.Strong.Tshirt_Web.Service.CategoryService;

@RequestMapping("manage")
@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/AddCategory")
    public String AddCategories() {
        return "AddCategory";
    }

    @PostMapping("/AddCategory")
    public String AddCategory(@ModelAttribute Categories category) {
        categoryService.SaveCategory(category);
        return "redirect:/manage/ShowCategories";
    }

    @GetMapping("/DeleteCategory")
    public String DeleteByIdCategory(@RequestParam("category_id") int categoryId) {
        categoryService.DeleteById(categoryId);
        return "redirect:/manage/ShowCategories";
    }

    @PostMapping("/UpdateCategory")
    public String UpdateProduct(Categories updatedCategory,
            @RequestParam("category_id") int categoryId) {
        Categories existingCategory = categoryService.getCategoryById(categoryId);
        if (existingCategory != null) {
            existingCategory.setName(updatedCategory.getName());
            existingCategory.setDescription(updatedCategory.getDescription());
            categoryService.UpdateCategory(existingCategory);
        }
        return "redirect:/manage/ShowCategories";
    }

    // Retrive Data From Category by Pickuped The Category Id
    @GetMapping("/ModifyCategory")
    public ModelAndView ModifyCategory(@RequestParam("category_id") int category_id) {
        Categories categoryData = categoryService.getCategoryById(category_id);
        return new ModelAndView("ModifyCategory", "category_data", categoryData);
    }

    // Showing Categories in Show Category
    @GetMapping("/ShowCategories")
    public ModelAndView ShowCategories() {
        List<Categories> categories = categoryService.getAllCategories();
        return new ModelAndView("ShowCategories", "AllCategories", categories);
    }
}
