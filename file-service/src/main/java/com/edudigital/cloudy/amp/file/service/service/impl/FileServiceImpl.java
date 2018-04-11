package com.edudigital.cloudy.amp.file.service.service.impl;

import org.springframework.stereotype.Service;

import com.edudigital.cloudy.amp.file.service.entity.po.Picture;
import com.edudigital.cloudy.amp.file.service.service.BaseService;
import com.edudigital.cloudy.amp.file.service.service.IFileService;

@Service
public class FileServiceImpl extends BaseService implements IFileService {

	@Override
	public Picture savePicture(Picture picture) {
		mongoTemplate.save(picture, "amp");
		return picture;
	}

	@Override
	public Picture getStream(String id) {
		Picture picture = mongoTemplate.findById(id, Picture.class, "amp");
		return picture;
	}


}
