package com.edudigital.cloudy.amp.textbook.service.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.edudigital.cloudy.amp.textbook.base.entity.ao.ChapterAO;
import com.edudigital.cloudy.amp.textbook.base.entity.ao.ChapterContextAO;
import com.edudigital.cloudy.amp.textbook.base.entity.ao.EbkAO;
import com.edudigital.cloudy.amp.textbook.base.entity.ao.QuestionAO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.BookMajorDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.ContextDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.DiscussDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.EbkDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.GroupDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.MajorDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.PeriodDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.QuestionDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.TextDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.TypeDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.UserBookDTO;
import com.edudigital.cloudy.amp.textbook.service.constant.CommonContants;
import com.edudigital.cloudy.amp.textbook.service.entity.bo.Discuss;
import com.edudigital.cloudy.amp.textbook.service.service.IEbkService;
import com.edudigital.cloudy.amp.textbook.service.service.IUserService;
import com.edudigital.cloudy.amp.textbook.service.util.EntityUtils;
import com.edudigital.cloudy.amp.textbook.service.util.ResUtils;
import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;
import com.edudigital.cloudy.amp.util.base.util.constant.enumeration.ResCode;
import com.edudigital.cloudy.amp.util.base.util.entity.dto.ResDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin
@SuppressWarnings("static-access")
@Api("E-Book API Docs")
@RestController
public class TextBookController extends BaseController {

	@Autowired
	private IEbkService ebkService;

	@Autowired
	private IUserService userSerivce;

	/**
	 * 添加图书
	 * 
	 * @param req
	 * @param res
	 * @param ebkDTO
	 * @return
	 */
	@RequestMapping(value = "addBk", method = { RequestMethod.POST })
	public ResDTO addBk(HttpServletRequest req, HttpServletResponse res, @RequestBody EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		int result = ebkService.addEbk(ebkDTO);

		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);

		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.SUCCESS);
		resDTO.setData(jsonArray.toString());

		return resDTO;
	}

	/**
	 * 查询图书列表
	 * 
	 * @param str
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "listBooks", method = { RequestMethod.GET })
	public ResDTO listBooks(GroupDTO groupDTO, UserDTO userDTO, EbkDTO ebkDTO, @RequestParam("page") Integer page,
			@RequestParam("size") Integer size) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		if (null == page) {
			page = 0;
		}
		if (null == size) {
			size = 3;
		}
		int start = page * size;

		int count = 0;
		int countPage = 0;

		JSONObject jsonObject = new JSONObject();
		if (userDTO.getType() % 2 == 0) {
			List<EbkAO> ebkList = ebkService.listWorkBooks(groupDTO, userDTO, ebkDTO);
			if (size == 0 && null != ebkList) {
				count = ebkList.size();
			}
			jsonObject.put("data", ebkList);
		} else {
			List<EbkDTO> ebkList = ebkService.listBooks(groupDTO, userDTO, ebkDTO, start, size);
			count = ebkService.countBooksByAuthor(userDTO, ebkDTO);
			countPage = (count - 1) / size + 1;
			jsonObject.put("data", ebkList);
		}

		jsonObject.put("count", count);
		jsonObject.put("countPage", countPage);

		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.SUCCESS);
		resDTO.setData(jsonObject.toString());

		return resDTO;
	}

	/**
	 * 获取课程种类
	 * 
	 * @param req
	 * @param res
	 * @param kindDTO
	 * @return
	 */
	@RequestMapping(value = "listTypes", method = { RequestMethod.GET })
	public ResDTO listTypes(HttpServletRequest req, HttpServletResponse res, @ApiIgnore TypeDTO typeDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<TypeDTO> bos = ebkService.listTypes();
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(bos);
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.SUCCESS);
		resDTO.setData(jsonArray.toString());

		return resDTO;
	}

	/**
	 * 获取目录信息
	 * 
	 * @param req
	 * @param res
	 * @param str
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "listContexts", method = { RequestMethod.GET })
	public ResDTO listContexts(HttpServletRequest req, HttpServletResponse res,
			@ApiIgnore @RequestParam("str") String str, Integer page, Integer size) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		JSONObject jso = new JSONObject().fromObject(str);
		ContextDTO contextDTO = (ContextDTO) JSONObject.toBean(jso.getJSONObject("ContextDTO"), ContextDTO.class);
		if (null == page) {
			page = 0;
		}
		if (null == size) {
			size = 4;
		}
		int start = page * size;

		List<ChapterContextAO> bos = ebkService.listChapterContexts(contextDTO, start, size);
		int count = ebkService.countChapterContexts(contextDTO);
		JSONObject jsonObject = new JSONObject();

		int countPage = (count - 1) / size + 1;
		jsonObject.accumulate("countPage", countPage);
		jsonObject.accumulate("data", bos);

		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.SUCCESS);
		resDTO.setData(jsonObject.toString());

		return resDTO;
	}

	/**
	 * 添加目录
	 * 
	 * @param req
	 * @param res
	 * @param contextDTO
	 * @return
	 */
	@RequestMapping(value = "addContext", method = { RequestMethod.POST })
	public ResDTO addContext(HttpServletRequest req, HttpServletResponse res, @RequestBody ContextDTO contextDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ebkService.addContext(contextDTO) > 0 ? ResCode.SUCCESS : ResCode.FAILURE);

		String myType = req.getParameter("mode");
		if (StringUtils.isNoneBlank(myType) && "1".equals(myType.trim())) {
			EbkDTO ebkDTO = new EbkDTO();
			ebkDTO.setBookId(contextDTO.getBookId());
			List<ContextDTO> bos = ebkService.listContexts(ebkDTO);

			JSONArray jsonArray = new JSONArray();
			jsonArray.addAll(bos);

			resDTO.setData(jsonArray.toString());
		}

		return resDTO;
	}

	/**
	 * 添加习题
	 * 
	 * @param req
	 * @param res
	 * @param questionDTO
	 * @return
	 */
	@RequestMapping(value = "addQuestion", method = { RequestMethod.POST })
	public ResDTO addQuestion(HttpServletRequest req, HttpServletResponse res, @RequestBody QuestionDTO questionDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		int result = ebkService.addQuestion(questionDTO);

		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, result > 0 ? ResCode.SUCCESS : ResCode.FAILURE);

		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);

		resDTO.setData(jsonArray.toString());

		return resDTO;
	}

	/**
	 * 获取习题列表
	 * 
	 * @param req
	 * @param res
	 * @param questionDTO
	 * @return
	 */
	@RequestMapping(value = "listQuestions", method = { RequestMethod.GET })
	public ResDTO listQuestions(HttpServletRequest req, HttpServletResponse res, @ApiIgnore QuestionDTO questionDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<QuestionDTO> bos = ebkService.listQuestions(questionDTO);

		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.SUCCESS);

		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(bos);

		resDTO.setData(jsonArray.toString());

		return resDTO;
	}

	/**
	 * 更新图书
	 * 
	 * @param req
	 * @param res
	 * @param ebkDTO
	 * @return
	 */
	@RequestMapping(value = "updateBk", method = { RequestMethod.PUT })
	public ResDTO updateBk(HttpServletRequest req, HttpServletResponse res, @RequestBody EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		int result = ebkService.updateBk(ebkDTO);

		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, result > 0 ? ResCode.SUCCESS : ResCode.FAILURE);

		resDTO.setData(new Integer(result).toString());

		return resDTO;
	}

	/**
	 * 添加文本
	 * 
	 * @param req
	 * @param res
	 * @param textDTO
	 * @param ebkDTO
	 * @return
	 */
	@RequestMapping(value = "addText", method = { RequestMethod.POST })
	public ResDTO addText(HttpServletRequest req, HttpServletResponse res, @RequestBody TextDTO textDTO,
			EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		int result = ebkService.addText(textDTO, ebkDTO.getBookId());

		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, result > 0 ? ResCode.SUCCESS : ResCode.FAILURE);

		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);

		resDTO.setData(jsonArray.toString());

		return resDTO;
	}

	/**
	 * 获取文本列表
	 * 
	 * @param req
	 * @param res
	 * @param textDTO
	 * @return
	 */
	@RequestMapping(value = "listTexts", method = { RequestMethod.GET })
	public ResDTO listTexts(HttpServletRequest req, HttpServletResponse res, @ApiIgnore TextDTO textDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<TextDTO> bos = ebkService.listTexts(textDTO);

		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.SUCCESS);

		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(bos);

		resDTO.setData(jsonArray.toString());

		return resDTO;
	}

	/**
	 * 添加讨论
	 * 
	 * @param req
	 * @param res
	 * @param discuss
	 * @return
	 */
	@RequestMapping(value = "addDiscuss", method = { RequestMethod.POST })
	public ResDTO addDiscuss(HttpServletRequest req, HttpServletResponse res, @RequestBody Discuss discuss) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		int result = ebkService.addDiscuss(discuss.getDiscuss());

		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, result > 0 ? ResCode.SUCCESS : ResCode.FAILURE);

		JSONArray jsonArray = new JSONArray();
		jsonArray.add(result);

		resDTO.setData(jsonArray.toString());

		return resDTO;
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
	public ResDTO listDiscuss(HttpServletRequest req, HttpServletResponse res, @ApiIgnore DiscussDTO discussDTO) {

		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<DiscussDTO> bos = ebkService.listDiscuss(discussDTO);
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.SUCCESS);

		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(bos);

		resDTO.setData(jsonArray.toString());

		return resDTO;
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
	public ResDTO listCourses(HttpServletRequest req, HttpServletResponse res, @ApiIgnore MajorDTO courseDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<MajorDTO> bos = ebkService.listCourses(courseDTO);

		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.SUCCESS);

		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(bos);

		resDTO.setData(jsonArray.toString());

		return resDTO;
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
	public ResDTO listPeriod(HttpServletRequest req, HttpServletResponse res, @ApiIgnore PeriodDTO periodDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<PeriodDTO> bos = ebkService.listPeriod();

		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.SUCCESS);

		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(bos);

		resDTO.setData(jsonArray.toString());

		return resDTO;
	}

	/**
	 * 删除章
	 * 
	 * @param req
	 * @param res
	 * @param context
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "deleteContext", method = { RequestMethod.DELETE })
	public ResDTO deleteContext(@RequestParam("str") String str) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		// 字符串转化为json数组
		JSONArray jsonArray = JSONArray.fromObject(str);

		int result = ebkService.delteContext(jsonArray);
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, result > 0 ? ResCode.SUCCESS : ResCode.FAILURE);

		JSONArray jsonArrayTwo = new JSONArray();
		jsonArrayTwo.add(result);

		resDTO.setData(jsonArrayTwo.toString());

		return resDTO;
	}

	/**
	 * 查询图书购买数
	 * 
	 * @param req
	 * @param res
	 */
	@RequestMapping(value = "data/getDataDownloadsNum", method = { RequestMethod.GET })
	public void getDataDownloadsNum(HttpServletRequest req, HttpServletResponse res) {

		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		logger.info(CommonContants.C_STRUCTURE_PARAMETERS);

		UserDTO userDTO = new UserDTO();
		List<EbkDTO> list = null;

		userDTO = userSerivce.getUserByAccount(userDTO);

		logger.info(CommonContants.C_EXTRACT_DATA);
		list = ebkService.getEbkSaledNums(userDTO);

		logger.info(CommonContants.C_RETURN_RESULT);
		JSONArray ja = new JSONArray();
		ja.addAll(list);
		ResUtils.toJson(res, ja);
	}

	/**
	 * 添加图书
	 * 
	 * @param req
	 * @param res
	 * @param ebkDTO
	 * @param userDTO
	 */
	@RequestMapping(value = "add", method = { RequestMethod.POST })
	public void add(HttpServletRequest req, HttpServletResponse res, EbkDTO ebkDTO, UserDTO userDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		int result = ebkService.addEbk(ebkDTO);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put(CommonContants.R_RETURN_KEY, result);
		ResUtils.toJson(res, jsonObject);
	}

	/**
	 * 获取图书习题
	 * 
	 * @param req
	 * @param res
	 * @param ebkDTO
	 * @param questionDTO
	 * @return
	 */
	@RequestMapping(value = "listEbkData", method = { RequestMethod.GET })
	public ResDTO listEbkData(HttpServletRequest req, HttpServletResponse res, EbkDTO ebkDTO, QuestionDTO questionDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		JSONArray jsonArray = new JSONArray();
		List<QuestionAO> listEbkDataQuestion = ebkService.listEbkDataQuestion(ebkDTO, questionDTO);

		jsonArray.addAll(listEbkDataQuestion);
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.SUCCESS);
		resDTO.setData(jsonArray.toString());
		return resDTO;
	}

	/**
	 * 获取图书章节列表
	 * 
	 * @param req
	 * @param res
	 * @param str
	 */
	@RequestMapping(value = "listContents", method = { RequestMethod.GET, RequestMethod.POST })
	public ResDTO listContents(HttpServletRequest req, HttpServletResponse res, EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<ChapterAO> aos = new ArrayList<>();

		aos = ebkService.listContents2(ebkDTO);

		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(aos);
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.SUCCESS);
		resDTO.setData(jsonArray.toString());

		return resDTO;
	}

	@RequestMapping(value = "mob/getEbkCompressed", method = { RequestMethod.GET })
	public void getData(HttpServletRequest req, HttpServletResponse res, EbkDTO ebkDTO) {

	}

	/**
	 * 图书查询
	 * 
	 * @param req
	 * @param res
	 * @param bookMajorDTO
	 * @return
	 */
	@ApiOperation(value = "获取图书列表", notes = "移动端接口")
	@ApiImplicitParams({ @ApiImplicitParam(name = "majorNum", value = "专业编号", required = true, dataType = "String"),
			@ApiImplicitParam(name = "bookName", value = "书名", required = true, dataType = "String") })
	@RequestMapping(value = "getListBooks", method = { RequestMethod.GET })
	public String likeListBooks(HttpServletRequest req, HttpServletResponse res, String majorNum, String bookName) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		BookMajorDTO bookMajorDTO = new BookMajorDTO();
		bookMajorDTO.setMajorNum(majorNum);
		bookMajorDTO.setBookName(bookName);
		List<EbkDTO> likeListBooks = ebkService.likeListBooks(bookMajorDTO);
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(likeListBooks);
		return jsonArray.toString();
	}

	/**
	 * 专业类别
	 * 
	 * @param req
	 * @param res
	 * @param type
	 * @return
	 */
	@ApiOperation(value = "获取系别专业", notes = "移动端接口")
	@ApiImplicitParam(name = "type", value = "查询类型", dataType = "int", paramType = "query")
	@RequestMapping(value = "listMajors", method = { RequestMethod.GET })
	public ResDTO listMajors(HttpServletRequest req, HttpServletResponse res, Integer type) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		if (null == type) {
			type = 1;
		}
		List<MajorDTO> listMajors = ebkService.listMajors(type);
		JSONArray jsonArray = new JSONArray();
		jsonArray.addAll(listMajors);
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.SUCCESS);
		resDTO.setData(jsonArray.toString());
		return resDTO;
	}

	@RequestMapping(value = "getUserBook", method = { RequestMethod.POST })
	public EbkDTO getUserBook(HttpServletRequest req, HttpServletResponse res, @RequestBody UserBookDTO UserBookDTO) {

		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		EbkDTO ebkDTO = new EbkDTO();

		ebkDTO = ebkService.getUserBook(UserBookDTO.getUserDTO(), UserBookDTO.getEbkDTO());

		return ebkDTO;
	}
}
