package com.edudigital.cloudy.amp.textbook.custom.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.edudigital.cloudy.amp.file.base.entity.dto.FileDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.EbkDTO;
import com.edudigital.cloudy.amp.textbook.custom.service.fallback.FileServiceFallBack;

@FeignClient(name = "file-service",fallback = FileServiceFallBack.class)
public interface IFileService {

	@RequestMapping(value = "/file-service/savePicture", method = RequestMethod.PUT)
	public EbkDTO savePicture(@RequestBody EbkDTO ebkDTO);
	@RequestMapping(value = "/file-service/getPicture", method = RequestMethod.GET)
	public String getPicture(@RequestParam("cover") String cover);
	
	@RequestMapping(value = "/file-service/getSource", method = { RequestMethod.GET })
	public FileDTO getSource(@RequestParam("filePath") String filePath);


}
