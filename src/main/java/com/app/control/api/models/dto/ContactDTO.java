package com.app.control.api.models.dto;

import java.util.ArrayList;
import java.util.List;

import com.app.control.api.models.Contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {

	private String name;
	private String email;
	private String cell;

	private List<AddressDTO> addresses;

	public static ContactDTO convertToDto(Contact contact) {

		List<AddressDTO> addressDTOs = new ArrayList<AddressDTO>();

		contact.getAddresses().stream().map(address -> addressDTOs.add(AddressDTO.convertToDto(address)));

		return new ContactDTO(contact.getName(), contact.getEmail(), contact.getCell(),addressDTOs);
	}
}
