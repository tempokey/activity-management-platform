package com.edudigital.cloudy.amp.file.service.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edudigital.cloudy.amp.file.base.constant.enumeration.FileStatus;
import com.edudigital.cloudy.amp.file.base.entity.dto.FileDTO;
import com.edudigital.cloudy.amp.file.service.entity.po.Picture;
import com.edudigital.cloudy.amp.file.service.service.IFileService;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.EbkDTO;

@RestController
public class FileController extends BaseController {

	@Autowired
	private IFileService iFileService;

	@RequestMapping(value = "/savePicture", method = RequestMethod.PUT)
	public EbkDTO saveCoverPath(@RequestBody EbkDTO ebkDTO) {
		Picture picture = new Picture();
		String base64Stream = ebkDTO.getCover();
		picture.setBase64Stream(base64Stream);
		picture.setStream(base64Stream.substring(base64Stream.indexOf(",") + 1));
		Picture pic = iFileService.savePicture(picture);
		ebkDTO.setCover(pic.getId());
		return ebkDTO;
	}

	@RequestMapping(value = "/getPicture", method = RequestMethod.GET)
	public String saveCoverPath(@RequestParam("cover") String cover) {
		Picture picture = iFileService.getStream(cover);
		String stream = picture.getStream();
		return stream;
	}

	/**
	 * 获取压缩内容接口
	 * 
	 * @param res
	 * @param filePath
	 */
	@RequestMapping(value = "getSource", method = { RequestMethod.GET })
	public FileDTO getSource(@RequestParam("filePath") String filePath) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		String uri = BASE_PATH + filePath;
		File file = new File(uri);
		FileDTO fileDTO = new FileDTO();
		try {

			if (!file.exists()) {
				logger.info(uri + " is not existed!");
				fileDTO.setCode(FileStatus.UNDEFINED.getIndex());
				fileDTO.setMsg(FileStatus.UNDEFINED.getType());
				return fileDTO;
			}
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(uri));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			fileDTO.setBuffer(buffer);
			fileDTO.setFileName(file.getName());
			fileDTO.setCode(FileStatus.SUCCESS.getIndex());
			fileDTO.setMsg(FileStatus.SUCCESS.getType());
			return fileDTO;
		} catch (Exception e) {
			logger.error(e.getMessage());
			fileDTO.setCode(FileStatus.ERROR.getIndex());
			fileDTO.setMsg(FileStatus.ERROR.getType());
			return fileDTO;
		}
	}

}
