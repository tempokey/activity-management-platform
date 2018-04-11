package com.edudigital.cloudy.amp.textbook.service.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.edudigital.cloudy.amp.textbook.service.util.ResUtils;
import com.edudigital.cloudy.entity.UpLoadFileInfo;
import com.edudigital.cloudy.util.UploadUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/common/")
public class CommonController extends BaseController {
	private UploadUtils uploadUtils = new UploadUtils();

	@RequestMapping(value = "uploads", method = { RequestMethod.POST, RequestMethod.GET })
	public void uploads(HttpServletRequest req, HttpServletResponse res, MultipartFile file,
			UpLoadFileInfo upLoadFileInfo) {
		logger.info("uploading..." + upLoadFileInfo.getFileMd5());
		Map<String, String> map = new HashMap<>();

		synchronized (this) {
			uploadUtils.uploads(map, upLoadFileInfo, filePath, file);
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.putAll(map);

		ResUtils.toJson(res, jsonObject);
	}

	@RequestMapping(value = "checkFile", method = { RequestMethod.POST, RequestMethod.GET })
	public void checkFile(HttpServletRequest req, HttpServletResponse res, UpLoadFileInfo upLoadFileInfo) {
		logger.info("checkFile..." + upLoadFileInfo.getFileMd5());

		Map<String, String> map = new HashMap<>();

		synchronized (this) {
			uploadUtils.checkFile(map, upLoadFileInfo, filePath, null, null);
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.putAll(map);

		ResUtils.toJson(res, jsonObject);
	}

	@RequestMapping(value = "checkChunk", method = { RequestMethod.POST, RequestMethod.GET })
	public void checkChunk(HttpServletRequest req, HttpServletResponse res, UpLoadFileInfo upLoadFileInfo) {
		logger.info("checkChunk..." + upLoadFileInfo.getFileMd5());
		logger.info("Chunk:" + upLoadFileInfo.getChunk());
		logger.info("Size:" + upLoadFileInfo.getChunkSize());

		Map<String, String> map = new HashMap<>();

		synchronized (this) {
			uploadUtils.checkChunk(map, upLoadFileInfo, filePath, null, null);
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.putAll(map);

		ResUtils.toJson(res, jsonObject);
	}

	@RequestMapping(value = "mergeChunk", method = { RequestMethod.POST, RequestMethod.GET })
	public void mergeChunk(HttpServletRequest req, HttpServletResponse res, UpLoadFileInfo upLoadFileInfo) {
		logger.info("mergeChunk...");

		Map<String, String> map = new HashMap<>();

		synchronized (this) {
			uploadUtils.mergeChunk(map, upLoadFileInfo, filePath, null, null, null);
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.putAll(map);

		ResUtils.toJson(res, jsonObject);
	}

}
