package com.edudigital.cloudy.amp.textbook.custom.controller.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.edudigital.cloudy.amp.textbook.base.entity.dto.ContextDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.DiscussDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.EbkDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.GroupDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.MajorDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.PeriodDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.QuestionDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.TextDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.TypeDTO;
import com.edudigital.cloudy.amp.textbook.custom.controller.BaseController;
import com.edudigital.cloudy.amp.textbook.custom.service.IFileService;
import com.edudigital.cloudy.amp.textbook.custom.service.ITextBookService;
import com.edudigital.cloudy.amp.textbook.custom.service.IUserService;
import com.edudigital.cloudy.amp.textbook.custom.util.EntityUtils;
import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;
import com.edudigital.cloudy.amp.util.base.util.entity.dto.ResDTO;
import com.edudigital.cloudy.amp.util.base.util.res.ResUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin
@Api("E-Book API Docs")
@RestController
@SuppressWarnings("static-access")
@RequestMapping(value = "/web/ebk/")
public class TBWebController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = -142280003343130858L;

	@Autowired
	private ITextBookService iTextBookService;

	@Autowired
	private IFileService iFileService;

	@Autowired
	private IUserService iUserService;

	@RequestMapping(value = "uploadFile", method = { RequestMethod.POST })
	public void uploadFile(MultipartHttpServletRequest req, HttpServletResponse res, MultipartFile file) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		File distFile = new File("F:\\test\\" + file.getOriginalFilename());

		try {
			distFile.createNewFile();
			file.transferTo(distFile);

		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

	}

	@ApiOperation(value = "添加图书", notes = "网页接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "bookCode", value = "书籍编码", required = true, dataType = "int") })
	@RequestMapping(value = "addBk", method = { RequestMethod.POST })
	public void addBk(HttpServletRequest req, HttpServletResponse res, @RequestBody EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		ebkDTO = iFileService.savePicture(ebkDTO);
		ResDTO resDTO = iTextBookService.addBk(ebkDTO);
		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
	}

	@ApiOperation(value = "获取图书列表", notes = "网页接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "bookCode", value = "书籍编码", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "groupCode", value = "组号", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "userCode", value = "用户编码", dataType = "int", paramType = "query") })
	@RequestMapping(value = "listBooks", method = { RequestMethod.GET })
	public void listBooks(HttpServletRequest req, HttpServletResponse res, @ApiIgnore GroupDTO groupDTO,
			@ApiIgnore UserDTO userDTO, @ApiIgnore EbkDTO ebkDTO, Integer page, Integer size) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		if (null == page) {
			page = 0;
		}
		if (null == size) {
			size = 3;
		}
		Map<String, Object> map = new HashMap<>();
		try {
			map.putAll(EntityUtils.instanceNotNull2ObjMap(groupDTO));
			map.putAll(EntityUtils.instanceNotNull2ObjMap(userDTO));
			map.putAll(EntityUtils.instanceNotNull2ObjMap(ebkDTO));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		ResDTO resDTO = iTextBookService.listBooks(map, page, size);
		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
	}

	@ApiOperation(value = "获取课程种类", notes = "网页接口")
	@ApiImplicitParam(name = "typeId", value = "图书种类", dataType = "int", paramType = "query")
	@RequestMapping(value = "listTypes", method = { RequestMethod.GET })
	public void listKinds(HttpServletRequest req, HttpServletResponse res, @ApiIgnore TypeDTO typeDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		ResDTO resDTO = iTextBookService.listTypes(typeDTO);
		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
	}

	@ApiOperation(value = "获取目录信息", notes = "网页接口")
	@ApiImplicitParam(name = "bookCode", value = "书籍编码", dataType = "int", paramType = "query")
	@RequestMapping(value = "listContexts", method = { RequestMethod.GET })
	public void listContexts(HttpServletRequest req, HttpServletResponse res, @ApiIgnore ContextDTO contextDTO,
			Integer page, Integer size) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate("ContextDTO", contextDTO);
		ResDTO resDTO = iTextBookService.listContexts(jsonObject.toString(), page, size);
		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
	}

	@ApiOperation(value = "添加目录", notes = "网页接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "contextCode", value = "目录编码", required = true, dataType = "int") })
	@RequestMapping(value = "addContext", method = { RequestMethod.POST })
	public void addContext(HttpServletRequest req, HttpServletResponse res, @RequestBody ContextDTO contextDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		ResDTO resDTO = iTextBookService.addContext(contextDTO);
		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));

	}

	@ResponseBody
	@ApiOperation(value = "添加习题", notes = "网页接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "questionCode", value = "习题编码", required = true, dataType = "int") })
	@RequestMapping(value = "addQuestion", method = { RequestMethod.POST })
	public void addQuestion(HttpServletRequest req, HttpServletResponse res, @RequestBody QuestionDTO questionDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		ResDTO resDTO = iTextBookService.addQuestion(questionDTO);
		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));

	}

	@ApiOperation(value = "获取习题列表", notes = "网页接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "questionCode", value = "习题编码", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "contextCode", value = "目录编码", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "periodCode", value = "课时编码", dataType = "int", paramType = "query") })
	@RequestMapping(value = "listQuestions", method = { RequestMethod.GET })
	public void listQuestions(HttpServletRequest req, HttpServletResponse res, @ApiIgnore QuestionDTO questionDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		ResDTO resDTO = iTextBookService.listQuestions(questionDTO);
		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
	}

	@ApiOperation(value = "更新图书", notes = "网页接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "bookCode", value = "书籍编码", required = true, dataType = "int") })
	@RequestMapping(value = "updateBk", method = { RequestMethod.PUT })
	public String updateBk(HttpServletRequest req, HttpServletResponse res, @RequestBody EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		ebkDTO = iFileService.savePicture(ebkDTO);
		String str = iTextBookService.savePicture(ebkDTO);
		return str;

	}

	@ResponseBody
	@ApiOperation(value = "添加文本", notes = "网页接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "bookCode", value = "书籍编码", required = true, dataType = "int"),
			@ApiImplicitParam(name = "txtCode", value = "文本编码", required = true, dataType = "int") })
	@RequestMapping(value = "addText", method = { RequestMethod.POST })
	public void addText(HttpServletRequest req, HttpServletResponse res, @RequestBody TextDTO textDTO, EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		ResDTO resDTO = iTextBookService.addText(textDTO, ebkDTO);
		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
	}

	@ResponseBody
	@ApiOperation(value = "获取文本列表", notes = "网页接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "contextCode", value = "目录编码", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "periodCode", value = "目录编码", dataType = "int", paramType = "query") })
	@RequestMapping(value = "listTexts", method = { RequestMethod.GET })
	public void listTexts(HttpServletRequest req, HttpServletResponse res, @ApiIgnore TextDTO textDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		ResDTO resDTO = iTextBookService.listTexts(textDTO);
		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));

	}

	@ResponseBody
	@ApiOperation(value = "添加讨论", notes = "网页接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "discuss", value = "讨论", required = true, dataType = "List") })
	@RequestMapping(value = "addDiscuss", method = { RequestMethod.POST })
	public void addDiscuss(HttpServletRequest req, HttpServletResponse res, @RequestBody DiscussDTO discuss) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		ResDTO resDTO = iTextBookService.addDiscuss(discuss);
		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
	}

	/**
	 * 习题-讨论
	 * 
	 * @param req
	 * @param res
	 * @param discussDTO
	 */
	@ResponseBody
	@ApiOperation(value = "获取讨论列表", notes = "网页接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "contextCode", value = "目录编码", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "periodCode", value = "课时编码", dataType = "int", paramType = "query") })
	@RequestMapping(value = "listDiscuss", method = { RequestMethod.GET })
	public void listDiscuss(HttpServletRequest req, HttpServletResponse res, @ApiIgnore DiscussDTO discussDTO) {

		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		ResDTO resDTO = iTextBookService.listDiscuss(discussDTO);
		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
	}

	/**
	 * 课程
	 * 
	 * @param req
	 * @param res
	 * @param courseDTO
	 */
	@ApiOperation(value = "获取课程列表", notes = "网页接口")
	@ApiImplicitParam(name = "courseCode", value = "课程编码", dataType = "int", paramType = "query")
	@RequestMapping(value = "listCourses", method = { RequestMethod.GET })
	public void listCourses(HttpServletRequest req, HttpServletResponse res, @ApiIgnore MajorDTO courseDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		ResDTO resDTO = iTextBookService.listCourses(courseDTO);
		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
	}

	/**
	 * 课前、课中、课后
	 * 
	 * @param req
	 * @param res
	 */
	@ApiOperation(value = "获取课时列表", notes = "网页接口")
	@ApiImplicitParam(name = "periodCode", value = "课时编码", dataType = "int", paramType = "query")
	@RequestMapping(value = "listPeriod", method = { RequestMethod.GET })
	public void listPeriod(HttpServletRequest req, HttpServletResponse res, @ApiIgnore PeriodDTO periodDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		ResDTO resDTO = iTextBookService.listPeriod(periodDTO);
		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
	}

	/**
	 * 删除章
	 * 
	 * @param req
	 * @param res
	 * @param context
	 */
	@ApiOperation(value = "删除章节", notes = "网页接口")
	@RequestMapping(value = "deleteContext", method = { RequestMethod.DELETE })
	public void deleteContext(HttpServletRequest req, HttpServletResponse res) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		// 接收传入参数转化为字符串
		String str = ResUtils.stream2Str(req);
		ResDTO resDTO = iTextBookService.deleteContext(str);
		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
	}

	// @RequestMapping(value = "getUserBook", method = { RequestMethod.POST })
	// public void getUserBook(HttpServletRequest req, HttpServletResponse res,
	// UserDTO userDTO, EbkDTO ebkDTO) {
	// logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
	// ResDTO resDTO = iTextBookService.listMajors();
	// ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
	//
	// }
	@ApiOperation(value = "登陆账号", notes = "移动端接口")
	@ApiImplicitParam(name = "userDTO", value = "用户", required = true, dataType = "UserDTO")
	@RequestMapping(value = "login", method = { RequestMethod.PUT })
	public void logIn(HttpServletRequest req, HttpServletResponse res, @RequestBody UserDTO userDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		// 获取当前ip
		String remoteIp = req.getRemoteAddr();
		ResDTO resDTO = iUserService.logIn(userDTO, remoteIp);
		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
	}

	@RequestMapping(value = "logout", method = RequestMethod.PUT)
	public void logOut(HttpServletRequest req, HttpServletResponse res, UserDTO userDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		ResDTO resDTO = iUserService.logout(userDTO);
		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
	}

	@ApiOperation(value = "获取系别专业", notes = "移动端接口")
	@ApiImplicitParam(name = "type", value = "查询类型,1三级专业，2二级专业", dataType = "String", paramType = "query")
	@RequestMapping(value = "listMajors", method = { RequestMethod.GET })
	public void listMajors(HttpServletRequest req, HttpServletResponse res, String type) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		ResDTO resDTO = iTextBookService.listMajors(Integer.valueOf(type));
		ResUtils.toJson(res, new JSONObject().fromObject(resDTO));
	}

}
