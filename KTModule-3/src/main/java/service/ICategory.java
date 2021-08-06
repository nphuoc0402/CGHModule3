package service;

import model.Category;
import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ICategory {
    public List<Category> SelectAllCategory();
    public Category SelectCategoryById(int id);
    public int findId(String name);
}
