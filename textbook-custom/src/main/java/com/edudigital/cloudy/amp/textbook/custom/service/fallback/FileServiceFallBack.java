package com.edudigital.cloudy.amp.textbook.custom.service.fallback;

import org.springframework.stereotype.Component;

import com.edudigital.cloudy.amp.file.base.entity.dto.FileDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.EbkDTO;
import com.edudigital.cloudy.amp.textbook.custom.service.IFileService;
@Component
public class FileServiceFallBack implements IFileService{

	@Override
	public EbkDTO savePicture(EbkDTO ebkDTO) {
		return new EbkDTO();
	}

	@Override
	public String getPicture(String cover) {
		return "";
	}

	@Override
	public FileDTO getSource(String filePath) {
		return new FileDTO();
	}

}
