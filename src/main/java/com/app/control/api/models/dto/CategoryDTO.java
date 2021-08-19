package com.app.control.api.models.dto;

import com.app.control.api.models.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
	
	private long id;
	private String name;
	private long parent;
	private long company;
	
	public static CategoryDTO convertToDto(Category c) {
		return new CategoryDTO(c.getId(), c.getName(), c.getParent(), c.getCompany().getId());
	}

}
