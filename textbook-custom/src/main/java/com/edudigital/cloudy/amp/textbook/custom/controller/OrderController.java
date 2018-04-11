package com.edudigital.cloudy.amp.textbook.custom.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.edudigital.cloudy.amp.order.base.entity.dto.OrderDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.EbkDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.PreOrderDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.UserBookDTO;
import com.edudigital.cloudy.amp.textbook.custom.service.IOrderService;
import com.edudigital.cloudy.amp.textbook.custom.service.ITextBookService;
import com.edudigital.cloudy.amp.textbook.custom.service.IUserService;
import com.edudigital.cloudy.amp.textbook.custom.util.EntityUtils;
import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/****
 * 
 * @author yangS
 *
 */
@Api(value = "Order API Docs")
@RestController
@RequestMapping(value = "/order/")
public class OrderController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7935270095904732787L;

	@Autowired
	private IOrderService iOrderService;

	@Autowired
	private IUserService iUserService;

	@Autowired
	private ITextBookService iTextBookService;

	public static final String IP_ADDRESS = "127.0.0.1";

	@ApiOperation(value = "创建预支付订单", notes = "移动端移动支付接口")
	@ApiImplicitParam(name = "preOrderDTO", value = "预支付订单信息", dataType = "PreOrderDTO")
	@RequestMapping(value = "precreate", method = RequestMethod.POST)
	public JSONObject precreate(HttpServletRequest req, HttpServletResponse res, @RequestBody PreOrderDTO preOrderDTO) {

		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		// 获取当前客户端IP
		String hostIP = req.getRemoteAddr();
		// 返回结果
		JSONObject jes = new JSONObject();
		// 获取支付项目
		String payProject = preOrderDTO.getProject();
		// 获取支付方式
		String payStyle = preOrderDTO.getPayStyle();
		// 判断关键字
		String payKey = payProject + payStyle;
		// 正则表达式规则
		String regEx = "^(192|172|10|0:0:0).*";
		// 编译正则表达式
		Pattern pattern = Pattern.compile(regEx);
		// 匹配客户端IP
		Matcher matcher = pattern.matcher(hostIP);
		// 图书信息
		EbkDTO ebkDTO = new EbkDTO();
		// 用户信息
		UserDTO userDTO = new UserDTO();

		userDTO.setAdCode(preOrderDTO.getAdCode());
		userDTO.setName(preOrderDTO.getUserName());
		userDTO.setType(2);
		userDTO.setId(preOrderDTO.getUserId());

		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		iUserService.addUser(userDTO);

		int bkCode = 0;
		try {
			bkCode = new Integer(preOrderDTO.getProductId());
		} catch (Exception e) {
			logger.error(e.getMessage());
			jes.put("msg", "产品Code不正确");
			return jes;
		}
		ebkDTO.setBookId(bkCode);

		UserBookDTO userBookDTO = new UserBookDTO();
		userBookDTO.setEbkDTO(ebkDTO);
		userBookDTO.setUserDTO(userDTO);
		// 查询用户关系
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		ebkDTO = iTextBookService.getUserBook(userBookDTO);

		if (ebkDTO instanceof EbkDTO) {

			jes.put("msg", "产品已经购买");
			return jes;
		}
		// 特殊IP的处理
		if (IP_ADDRESS.contains(hostIP)) {

			preOrderDTO.setK_Spbill_Create_Ip("202.168.1.160");

		} else if (true == matcher.matches()) {

			preOrderDTO.setK_Spbill_Create_Ip("202.168.1.160");

		} else {

			preOrderDTO.setK_Spbill_Create_Ip(hostIP);

		}

		// 不同的支付情景特殊处理部分
		switch (payKey) {

		case "BKpm_wx":

			break;

		case "BKpm_ali":

			break;

		case "APPpm_wx":

			break;

		case "APPpm_ali":

			break;

		case "BKpm_cp":

			break;

		case "APPpm_cp":

			break;

		default:

			break;
		}
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		// 创建预支付订单
		jes = iOrderService.precreate(preOrderDTO);
		return jes;

	}

	@ApiOperation(value = "查询订单", notes = "移动端移动支付接口")
	@ApiImplicitParam(name = "orderDTO", value = "订单信息", required = true, dataType = "OrderDTO")
	@RequestMapping(value = "mob/check", method = { RequestMethod.GET })
	public void check(HttpServletRequest req, HttpServletResponse res, OrderDTO orderDTO) {

		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		iOrderService.check(orderDTO);
	}

	@ApiOperation(value = "查询订单", notes = "移动端移动支付接口-web端")
	@ApiImplicitParam(name = "orderDTO", value = "订单", dataType = "OrderDTO")
	@RequestMapping(value = "listOrderBy", method = { RequestMethod.GET })
	public List<OrderDTO> listOrderBy(HttpServletRequest req, HttpServletResponse res, OrderDTO orderDTO) {

		List<OrderDTO> dtoList = null;

		try {
			dtoList = iOrderService.listOrderBy(EntityUtils.instanceNotNull2ObjMap(orderDTO));
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {

			e.printStackTrace();
		}
		return dtoList;
	}

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {

		return "newFile";
	}

	@RequestMapping(value = "v1/uploadSize", method = RequestMethod.POST)
	@ResponseBody
	public Long uploadSize(HttpServletRequest request) {
		// String name = request.getParameter("name");
		// Long size = Long.parseLong(request.getParameter("size"));
		/*
		 * try { JedisShardInfo jedisShardInfo = new JedisShardInfo("192.168.0.12");
		 * Jedis jedis = new Jedis(jedisShardInfo); String str = jedis.get(name);
		 * UploadedFileInfo uploadedFileInfo = null; // 如果首次上传，已上传大小为 0 if
		 * (StringUtils.isEmpty(str)) { uploadedFileInfo = new UploadedFileInfo();
		 * jedis.set(name, JSON.toJSONString(uploadedFileInfo)); jedis.close(); return
		 * 0L; } uploadedFileInfo = JSON.parseObject(str, UploadedFileInfo.class);
		 * jedis.close(); // 已经全部上传 if (size <= uploadedFileInfo.getSize()) {
		 * System.out.println("上传完成"); } //
		 * UploadUtil.getFileInfo(uploadedFileInfo.getFileId()); return
		 * uploadedFileInfo.getSize(); } catch (Exception e) { e.printStackTrace();
		 * return 0L; }
		 */
		return 0L;
	}

	@RequestMapping(value = "v1/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public int upload(HttpServletRequest request) {
		// CommonsMultipartResolver resolver = new
		// CommonsMultipartResolver(request.getSession().getServletContext());
		// MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)
		// request;
		// Iterator<String> t = multiRequest.getFileNames();
		// MultipartFile fileDetail = multiRequest.getFile(t.next());
		/*
		 * String name = request.getParameter("name"); JedisShardInfo jedisShardInfo =
		 * new JedisShardInfo("192.168.0.12"); Jedis jedis = new Jedis(jedisShardInfo);
		 * UploadedFileInfo uploadedFileInfo = JSON.parseObject(jedis.get(name),
		 * UploadedFileInfo.class); uploadedFileInfo.setFileName(name); String path =
		 * "d:/testUpload"; File file = new File(path); if (!file.exists() &&
		 * !file.isDirectory()) { boolean b = file.mkdirs(); } RandomAccessFile
		 * randomFile = null; try { randomFile = new RandomAccessFile(path + "/" + name,
		 * "rw"); randomFile.seek(randomFile.length());
		 * randomFile.write(fileDetail.getBytes()); uploadedFileInfo.setLocalPath(path +
		 * "/" + name); uploadedFileInfo.setSize(randomFile.length()); } catch
		 * (IOException e) { e.printStackTrace(); } finally { try { randomFile.close();
		 * } catch (IOException e) { e.printStackTrace(); } } // 将上传文件信息保存到redis
		 * jedis.set(name, JSON.toJSONString(uploadedFileInfo)); jedis.close();
		 */
		return 0;
	}
}
