package springdata.producttask1.services;

import springdata.producttask1.CategoryCount;
import springdata.producttask1.dto.ProductDto;

import java.util.List;

public interface ProductService {
    void insertProduct(ProductDto productDto);
    List<ProductDto> getProducts(Integer priceMin , Integer priceMax);

    List<CategoryCount> getCategoryCounts();


}
