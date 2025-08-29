package com.example.fruithub.service;

import com.example.fruithub.dto.ProductDto;
import com.example.fruithub.entity.Category;
import com.example.fruithub.entity.Product;
import com.example.fruithub.entity.Status;
import com.example.fruithub.entity.Unit;
import com.example.fruithub.repository.CategoryRepository;
import com.example.fruithub.repository.ProductRepository;
import com.example.fruithub.repository.StatusRepository;
import com.example.fruithub.repository.UnitRepository;
import com.example.fruithub.util.ExcelReader;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ExcelReader excelReader;
    private final ProductRepository productRepository;
    private final StatusRepository statusRepository;
    private final CategoryRepository categoryRepository;
    private final UnitRepository unitRepository;

    @PostConstruct
    public void init(){
        importProductsFromExcel();
    }

    public void importProductsFromExcel(){
        String filePath = "C:\\Users\\mrjer\\OneDrive\\Desktop\\products.xlsx";

        List <Product> productsFromExcel = excelReader.readProductsFromExcel(filePath);

        Unit unit = unitRepository.findByCode("kg");
        Status status = statusRepository.findByName("Active");
        Category category = categoryRepository.findByName("Fresh Fruits");

        List<Product> productsToSave = new ArrayList<>();

        for(Product product : productsFromExcel){
            if(productRepository.existsByName(product.getName())){
                continue;
            }
            product.setStatus(status);
            product.setUnit(unit);
            product.setCategory(category);

            productsToSave.add(product);
        }
        productRepository.saveAll(productsToSave);
    }

    public List<ProductDto> getALlProducts(String search, String sort, int page, int offset) {
        Sort.Direction direction = sort.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, offset, Sort.by(direction, "name"));

        Page<Product> products;
        if (search != null && !search.isEmpty()) {
            products = productRepository.findByNameContainingIgnoreCase(search, pageable);
        } else {
            products = productRepository.findAll(pageable);
        }

        return products.map(this::convertToDto).getContent();
    }

    private ProductDto convertToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .imageURL("/images/" + product.getName().toLowerCase().replace(" ", "_") + ".png")
                .name(product.getName())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .unit(product.getUnit().getCode())
                .build();
    }
}
