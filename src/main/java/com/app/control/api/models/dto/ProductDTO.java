package com.app.control.api.models.dto;

import java.util.List;

import com.app.control.api.models.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private long id;
	private String name;
	private Double price;
	private String description;
	private boolean menu;
	private String type;
	private long category;
	private String image;
	private long company;

	public Product convertToEntity(ProductDTO dto) {
		return Product.builder().name(this.name).price(price).description(description).menuOp(menu).type(type).image(image).build();
	}

	public static ProductDTO convertToDto(Product p) {
		return new ProductDTO(p.getId(), p.getName(), p.getPrice(), p.getDescription(),
				p.isMenuOp(), p.getType(), p.getCategory().getId(), p.getImage(), p.getCompany().getId());
	}

}
