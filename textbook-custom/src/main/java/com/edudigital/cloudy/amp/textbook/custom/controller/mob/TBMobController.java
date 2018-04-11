package com.edudigital.cloudy.amp.textbook.custom.controller.mob;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edudigital.cloudy.amp.file.base.entity.dto.FileDTO;
import com.edudigital.cloudy.amp.msg.base.constant.enumeration.MsgStatus;
import com.edudigital.cloudy.amp.msg.base.entity.MsgDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.ao.UserAO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.EbkDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.GroupDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.QuestionDTO;
import com.edudigital.cloudy.amp.textbook.custom.controller.BaseController;
import com.edudigital.cloudy.amp.textbook.custom.entity.bo.MsgBO;
import com.edudigital.cloudy.amp.textbook.custom.service.IFileService;
import com.edudigital.cloudy.amp.textbook.custom.service.IMsgService;
import com.edudigital.cloudy.amp.textbook.custom.service.ITextBookService;
import com.edudigital.cloudy.amp.textbook.custom.service.IUserService;
import com.edudigital.cloudy.amp.textbook.custom.util.EntityUtils;
import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;
import com.edudigital.cloudy.amp.util.base.util.BaseEntityUtils;
import com.edudigital.cloudy.amp.util.base.util.constant.enumeration.ResCode;
import com.edudigital.cloudy.amp.util.base.util.entity.dto.ResDTO;
import com.edudigital.cloudy.amp.util.base.util.res.ResUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin(origins = "*")
@Api("E-Book Mobile API Docs")
@SuppressWarnings("static-access")
@RestController
@RequestMapping(value = "/mob/tb/")
public class TBMobController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 705646355305349184L;

	@Autowired
	private ITextBookService iTextBookService;

	@Autowired
	private IMsgService iMsgService;

	@Autowired
	private IUserService iUserService;

	@Autowired
	private IFileService iFileService;

	@ApiOperation(value = "查询图书购买数", notes = "统计接口")
	@RequestMapping(value = "data/getDataDownloadsNum", method = { RequestMethod.GET })
	public void getDataDownloadsNum(HttpServletRequest req, HttpServletResponse res) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		iTextBookService.getDataDownloadsNum();
	}

	@ApiOperation(value = "添加图书", notes = "通用接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "userDTO", value = "用户对象", required = true, dataType = "UserDTO") })
	@RequestMapping(value = "add", method = { RequestMethod.POST })
	public void add(HttpServletRequest req, HttpServletResponse res, @ApiIgnore EbkDTO ebkDTO,
			@ApiIgnore UserDTO userDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		iTextBookService.add("");
	}

	@ApiOperation(value = "获取图书列表", notes = "移动端接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "用户id", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "groupId", value = "组号", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "type", value = "用户类型", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "majorId", value = "专业编号", required = false, dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "book", value = "书名", required = false, dataType = "String", paramType = "query") })
	@RequestMapping(value = "listBooks", method = { RequestMethod.GET })
	public void listBooks(HttpServletRequest req, HttpServletResponse res, @ApiIgnore GroupDTO groupDTO,
			@ApiIgnore UserDTO userDTO, @ApiIgnore EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		Map<String, Object> map = new HashMap<>();
		try {
			map.putAll(EntityUtils.instanceNotNull2ObjMap(groupDTO));
			map.putAll(EntityUtils.instanceNotNull2ObjMap(userDTO));
			map.putAll(EntityUtils.instanceNotNull2ObjMap(ebkDTO));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			logger.info(e.getMessage());
		}
		ResDTO resDTO = iTextBookService.listBooks(map, 0, 0);
		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
	}

	@ApiOperation(value = "获取图书内容接口", notes = "通用接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bookId", value = "图书Id", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "typeId", value = "图书类型", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "contextId", value = "章节Id", required = false, dataType = "int", paramType = "query") })
	@RequestMapping(value = "listEbkData", method = { RequestMethod.GET })
	public void listEbkData(HttpServletRequest req, HttpServletResponse res, @ApiIgnore EbkDTO ebkDTO,
			@ApiIgnore QuestionDTO questionDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		ResDTO resDTO = null;
		Map<String, Object> map = new HashMap<>();
		try {
			map.putAll(EntityUtils.instanceNotNull2ObjMap(ebkDTO));
			map.putAll(EntityUtils.instanceNotNull2ObjMap(questionDTO));
			resDTO = iTextBookService.listEbkData(map);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			logger.info(e.getMessage());
		}

		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
	}

	@ApiOperation(value = "获取图书内容列表", notes = "通用接口")
	@ApiImplicitParam(name = "bookId", value = "图书id", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "listContents", method = { RequestMethod.GET })
	public void listContents(HttpServletRequest req, HttpServletResponse res, @ApiIgnore EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		ResDTO resDTO = null;
		try {
			resDTO = iTextBookService.listContents(EntityUtils.instance2ObjMap(ebkDTO));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			logger.info(e.getMessage());
		}
		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
	}

	@ApiOperation(value = "获取压缩内容接口", notes = "通用接口")
	@ApiImplicitParam(name = "filePath", value = "图书路径", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "getSource", method = { RequestMethod.GET })
	public void getEbkCompressed(HttpServletRequest req, HttpServletResponse res, @ApiIgnore String filePath) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		if (null == filePath) {
			ResDTO resDTO = new ResDTO();
			EntityUtils.pkg(resDTO, ResCode.FAILURE_NOT_MATCHES);
			ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
		}
		FileDTO fileDTO = iFileService.getSource(filePath);
		ResUtils.toFile(res, fileDTO.getBuffer(), fileDTO.getFileName());
	}

	@RequestMapping(value = "mob/getEbkCompressed", method = { RequestMethod.GET })
	public void getData(HttpServletRequest req, HttpServletResponse res, @ApiIgnore EbkDTO ebkDTO) {
		iTextBookService.getData("");
	}

	@ApiOperation(value = "获取系别专业", notes = "移动端接口")
	@ApiImplicitParam(name = "type", value = "查询类型,1三级专业，2二级专业", dataType = "int", paramType = "query")
	@RequestMapping(value = "listMajors", method = { RequestMethod.GET })
	public void listMajors(HttpServletRequest req, HttpServletResponse res, Integer type) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		if (type != 1 && type != 2) {
			ResDTO resDTO = new ResDTO();
			EntityUtils.pkg(resDTO, ResCode.FAILURE_NOT_MATCHES);
			ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
		}
		ResDTO resDTO = iTextBookService.listMajors(type);
		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));

	}

	@ApiOperation(value = "获取短信验证码", notes = "移动端接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "msgBO", value = "获取短信", required = true, dataType = "MsgBO") })
	@RequestMapping(value = "sendMsg", method = { RequestMethod.POST })
	public void sendMsg(HttpServletRequest req, HttpServletResponse res, @RequestBody MsgBO msgBO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		MsgDTO msgDTO = null;
		ResCode resCode = ResCode.FAILURE;
		ResDTO resDTO = new ResDTO();
		JSONArray jsonArray = new JSONArray();

		if (msgBO != null && StringUtils.isNotBlank(msgBO.getMsgID()) && StringUtils.isNotBlank(msgBO.getPhones())
				&& StringUtils.isNotBlank(msgBO.getMsgMethod())) {
			msgDTO = msgBO.toDTO();
			msgDTO = iMsgService.sendMsg(msgDTO);

			if (null != msgDTO && MsgStatus.SUCCESS.getStatusCode() == msgDTO.getMsgStatus().getStatusCode()) {
				resCode = ResCode.SUCCESS;
			}

			BaseEntityUtils.pkg(resDTO, resCode);
			jsonArray.add(msgDTO);
			resDTO.setData(jsonArray.toString());
		} else {
			resCode = ResCode.FAILURE_NOT_MATCHES;
			BaseEntityUtils.pkg(resDTO, resCode);
		}

		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
	}

	@ApiOperation(value = "注册账号", notes = "移动端接口")
	@ApiImplicitParam(name = "userAO", value = "用户", required = true, dataType = "UserAO")
	@RequestMapping(value = "signup", method = { RequestMethod.POST })
	public void signUp(HttpServletRequest req, HttpServletResponse res, @RequestBody UserAO userAO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		ResDTO resDTO = new ResDTO();
		if (StringUtils.isBlank(userAO.getPassword()) || StringUtils.isBlank(userAO.getAccount())
				|| StringUtils.isBlank(userAO.getMsgID()) || StringUtils.isBlank(userAO.getCode())) {
			EntityUtils.pkg(resDTO, ResCode.FAILURE_NOT_MATCHES);
		} else {
			boolean boo = iMsgService.verifyMsg(userAO.getMsgID(), userAO.getCode());
			UserDTO userDTO = new UserDTO();
			try {
				EntityUtils.copy(userAO, userDTO);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
					| InvocationTargetException e) {
				logger.info(e.getMessage());
			}
			if (boo) {
				resDTO = iUserService.signUp(userDTO);
				if (resDTO.getCode() == 40000) {
					EntityUtils.pkg(resDTO, ResCode.TB_FAILURE_EXISTENCE);
				}
			} else {
				EntityUtils.pkg(resDTO, ResCode.TB_ERROR_MSGCODE);
			}
		}
		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
	}

	@ApiOperation(value = "登陆账号", notes = "移动端接口")
	@ApiImplicitParam(name = "userDTO", value = "用户", required = true, dataType = "UserDTO")
	@RequestMapping(value = "login", method = { RequestMethod.PUT })
	public void logIn(HttpServletRequest req, HttpServletResponse res, @RequestBody UserDTO userDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		// 获取当前ip
		String remoteIp = req.getRemoteAddr();
		ResDTO resDTO = iUserService.logIn(userDTO, remoteIp);
		if (resDTO.getCode() == 40001) {
			EntityUtils.pkg(resDTO, ResCode.TB_FAILURE_INEXISTENCE);
		}
		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
	}

	@ApiOperation(value = "找回密码", notes = "移动端接口")
	@ApiImplicitParam(name = "userAO", value = "用户", required = true, dataType = "UserAO")
	@RequestMapping(value = "updateUser", method = { RequestMethod.PUT })
	public void updateUser(HttpServletRequest req, HttpServletResponse res, @RequestBody UserAO userAO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		ResDTO resDTO = new ResDTO();
		if (StringUtils.isBlank(userAO.getPassword()) || StringUtils.isBlank(userAO.getAccount())
				|| StringUtils.isBlank(userAO.getMsgID()) || StringUtils.isBlank(userAO.getCode())) {
			EntityUtils.pkg(resDTO, ResCode.FAILURE_NOT_MATCHES);
		} else {
			boolean boo = iMsgService.verifyMsg(userAO.getMsgID(), userAO.getCode());
			UserDTO userDTO = new UserDTO();
			try {
				EntityUtils.copy(userAO, userDTO);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
					| InvocationTargetException e) {
				logger.info(e.getMessage());
			}
			if (boo) {
				resDTO = iUserService.updateUser(userDTO);
				if (resDTO.getCode() == 40000) {
					EntityUtils.pkg(resDTO, ResCode.TB_FAILURE_NOTEXISTENCE);
				}
			} else {
				EntityUtils.pkg(resDTO, ResCode.TB_ERROR_MSGCODE);
			}
		}
		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
	}

	@ApiOperation(value = "检查登陆状态", notes = "移动端接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "tokenKey", value = "用户token", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "name", value = "名字", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "checkLoginStatus", method = { RequestMethod.GET })
	public void checkLoginStatus(HttpServletRequest req, HttpServletResponse res, @ApiIgnore UserDTO userDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
			iUserService.checkLoginStatus(EntityUtils.instance2ObjMap(userDTO));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			logger.info(e.getMessage());
		}
	}

	@ApiOperation(value = "获取图书封面", notes = "移动端接口")
	@ApiImplicitParam(name = "cover", value = "图书封面", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "getCover", method = { RequestMethod.GET })
	public void getCover(HttpServletRequest req, HttpServletResponse res, @ApiIgnore String cover) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		if (null == cover) {
			ResDTO resDTO = new ResDTO();
			EntityUtils.pkg(resDTO, ResCode.FAILURE_NOT_MATCHES);
			ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
			return;
		}
		String coverPath = iFileService.getPicture(cover);
		this.toImg(res, coverPath);
	}

	/**
	 * 图片处理
	 * 
	 * @param response
	 * @param str
	 * @return
	 */
	public static int toImg(HttpServletResponse response, String str) {
		if (response == null || str == null) {
			return 0;
		}
		OutputStream os = null;
		try {
			byte[] decodeBase64 = Base64.decodeBase64(str);
			os = response.getOutputStream();
			os.write(decodeBase64);
			os.flush();
			os.close();
			return 1;
		} catch (IOException e) {
			return 2;
		}
	}

}
