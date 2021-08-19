package com.app.control.api.models.dto;

import com.app.control.api.models.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

	private String zipCode;
	private String andress;
	private int numberHouse;
	private String complement;
	private String district;
	private String city;
	private String uf;

	private long contact;

	public static AddressDTO convertToDto(Address address) {

		return new AddressDTO(address.getZipCode(), address.getAndress(), address.getNumberHouse(),
				address.getComplement(), address.getDistrict(), address.getCity(), address.getUf(),
				address.getContact().getId());
	}
}
