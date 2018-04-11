package com.edudigital.cloudy.amp.textbook.base.entity.dto;

import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;

public class UserBookDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserDTO userDTO;

	private EbkDTO ebkDTO;

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public EbkDTO getEbkDTO() {
		return ebkDTO;
	}

	public void setEbkDTO(EbkDTO ebkDTO) {
		this.ebkDTO = ebkDTO;
	}
}