package springdata.producttask1.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import springdata.producttask1.CategoryCount;
import springdata.producttask1.dto.ProductDto;
import springdata.producttask1.model.Product;
import springdata.producttask1.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    @Override
    public void insertProduct(ProductDto productDto) {
        if(productRepository.existsByName(productDto.getName())){
            throw new RuntimeException("Product name already exists");
        }
        Product product = new Product();
        BeanUtils.copyProperties(productDto,product);
        productRepository.save(product);

    }

    @Override
    public List<ProductDto> getProducts(Integer priceMin , Integer priceMax) {
        List<Product> productList;
        if(priceMin==null && priceMax == null){
            productList = productRepository.findAll();
        }else {
            productList = productRepository.findAllByPriceLessThanEqualAndPriceGreaterThanEqual(priceMin,priceMax);
        }
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : productList) {
            ProductDto productDto = ProductDto.builder()
                    .name(product.getName())
                    .categoryEnum(product.getCategoryEnum())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .build();
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    @Override
    public List<CategoryCount> getCategoryCounts() {
        return productRepository.findCategoryCounts();
    }



}
