package com.edudigital.cloudy.amp.file.service.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.edudigital.cloudy.amp.util.base.util.res.ResUtils;
import com.edudigital.cloudy.entity.UpLoadFileInfo;
import com.edudigital.cloudy.util.UploadUtils;

import net.sf.json.JSONObject;

@RestController
public class CommonController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private UploadUtils uploadUtils = new UploadUtils();

	@RequestMapping(value = "/info")
	public String info(HttpServletRequest rep, HttpServletResponse res) {
		logger.info("info...");
		return "ing...";
	}

	@RequestMapping(value = "/health")
	public String health(HttpServletRequest rep, HttpServletResponse res) {
		logger.info("health...");
		return "health...";
	}

	/******
	 * 上传文件
	 * 
	 * @param request
	 * @param response
	 * @param file
	 * @param upLoadFileInfo
	 * @throws IOException
	 */
	@RequestMapping(value = "/uploads", method = { RequestMethod.POST, RequestMethod.GET })
	public void uploads(HttpServletRequest request, HttpServletResponse response, MultipartFile file,
			UpLoadFileInfo upLoadFileInfo) throws IOException {
		logger.info("uploading..." + upLoadFileInfo.getFileMd5());
		Map<String, String> map = new HashMap<>();
		String filePath = this.uploadFilePath;
		File dir = new File(filePath + upLoadFileInfo.getFileMd5());

		if (!dir.exists()) {
			dir.mkdirs();
		}
		map.put("fileMd5", upLoadFileInfo.getFileMd5());
		synchronized (this) {
			uploadUtils.uploads(map, upLoadFileInfo, filePath, file);

			String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1,
					file.getOriginalFilename().length());
			if (extension.equals("doc") || extension.equals("docx")) {
				map.put("type", "1");
			} else if (extension.equals("pdf")) {
				map.put("type", "2");
			} else if (extension.equals("flv") || extension.equals("mp4") || extension.equals("MP4")
					|| extension.equals("rmvb")) {
				map.put("type", "3");
			} else if (extension.equals("jpg")) {
				map.put("type", "4");
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.putAll(map);

		ResUtils.toJson(response, jsonObject);
	}

	/****
	 * 校验文件
	 * 
	 * @param req
	 * @param res
	 * @param upLoadFileInfo
	 */
	@RequestMapping(value = "checkFile", method = { RequestMethod.POST, RequestMethod.GET })
	public void checkFile(HttpServletRequest req, HttpServletResponse res, UpLoadFileInfo upLoadFileInfo) {
		logger.info("checkFile..." + upLoadFileInfo.getFileMd5());

		Map<String, String> map = new HashMap<>();

		synchronized (this) {
			uploadUtils.checkFile(map, upLoadFileInfo, this.uploadFilePath, null, null);
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.putAll(map);

		ResUtils.toJson(res, jsonObject);
	}

	/*****
	 * 校验分片
	 * 
	 * @param req
	 * @param res
	 * @param upLoadFileInfo
	 */
	@RequestMapping(value = "checkChunk", method = { RequestMethod.POST, RequestMethod.GET })
	public void checkChunk(HttpServletRequest req, HttpServletResponse res, UpLoadFileInfo upLoadFileInfo) {
		logger.info("checkChunk..." + upLoadFileInfo.getFileMd5());
		logger.info("Chunk:" + upLoadFileInfo.getChunk());
		logger.info("Size:" + upLoadFileInfo.getChunkSize());

		Map<String, String> map = new HashMap<>();

		synchronized (this) {
			uploadUtils.checkChunk(map, upLoadFileInfo, this.uploadFilePath, null, null);
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.putAll(map);

		ResUtils.toJson(res, jsonObject);
	}

	/*****
	 * 合并分片
	 * 
	 * @param req
	 * @param res
	 * @param upLoadFileInfo
	 */
	@RequestMapping(value = "mergeChunk", method = { RequestMethod.POST, RequestMethod.GET })
	public void mergeChunk(HttpServletRequest req, HttpServletResponse res, UpLoadFileInfo upLoadFileInfo) {
		logger.info("mergeChunk...");

		Map<String, String> map = new HashMap<>();

		synchronized (this) {
			uploadUtils.mergeChunk(map, upLoadFileInfo, this.uploadFilePath, null, null, null);
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.putAll(map);

		ResUtils.toJson(res, jsonObject);
	}
}
