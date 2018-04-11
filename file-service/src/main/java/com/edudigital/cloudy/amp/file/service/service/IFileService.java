package com.edudigital.cloudy.amp.file.service.service;

import com.edudigital.cloudy.amp.file.service.entity.po.Picture;

public interface IFileService {

	Picture savePicture(Picture picture);
	
	Picture getStream(String id);
}
