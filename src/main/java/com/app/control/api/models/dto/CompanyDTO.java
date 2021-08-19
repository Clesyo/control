package com.app.control.api.models.dto;

import com.app.control.api.models.Company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {

	private long id;
	private String token;
	private String name;
	private String email;
	private String identity;
	private String telephone;
	private String cellphone;
	private String zipCode;
	private String andress;
	private int numberHouse;
	private String complement;
	private String district;
	private String city;
	private String uf;

	private long user;

	public Company convertToEntity(CompanyDTO dto) {
		return Company.builder().name(this.name).email(this.email).identity(this.identity).telephone(this.telephone)
				.cellphone(this.cellphone).andress(this.andress).zipCode(this.zipCode).numberHouse(this.numberHouse)
				.complement(this.complement).district(this.district).city(this.city).uf(this.uf).build();
	}

	public static CompanyDTO convertToDto(Company c) {
		return new CompanyDTO(c.getId(),c.getToken(), c.getName(), c.getEmail(), c.getIdentity(), c.getTelephone(),
				c.getCellphone(), c.getZipCode(), c.getAndress(), c.getNumberHouse(), c.getComplement(),
				c.getDistrict(), c.getCity(), c.getUf(), c.getUser().getId());
	}
}
