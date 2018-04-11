package com.edudigital.cloudy.amp.textbook.service.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.edudigital.cloudy.amp.textbook.base.entity.ao.ChapterAO;
import com.edudigital.cloudy.amp.textbook.base.entity.ao.ChapterContextAO;
import com.edudigital.cloudy.amp.textbook.base.entity.ao.EbkAO;
import com.edudigital.cloudy.amp.textbook.base.entity.ao.QuestionAO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.AskDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.BookMajorDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.ContextDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.DiscussDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.EbkDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.EbkDataDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.GroupDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.MajorDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.PeriodDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.QuestionDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.TestDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.TextDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.TypeDTO;
import com.edudigital.cloudy.amp.textbook.service.dao.IEbkDAO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.AskPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.ContextPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.DiscussPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.EbkDataPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.EbkPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.GroupPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.MajorPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.PeriodPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.QuestionPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.TestPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.TextPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.UserBkPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.UserPO;
import com.edudigital.cloudy.amp.textbook.service.mapper.EbkMapper;
import com.edudigital.cloudy.amp.textbook.service.util.EntityUtils;
import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;

@Component
public class EbkDAO implements IEbkDAO {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private EbkMapper ebkMapper;

	@Override
	public List<EbkDTO> getEbkSaledNums(UserDTO user) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<EbkDTO> bolist = null;
		UserPO userDO = new UserPO();

		try {
			EntityUtils.copy(user, userDO);
			List<EbkPO> doList = ebkMapper.getEbkSaledNums(userDO);
			bolist = EntityUtils.exchange(doList, EbkDTO.class.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
			return bolist;
		}

		return bolist;
	}

	@Transactional(readOnly = false)
	@Override
	public int addEbk(EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		EbkPO ebkPO = new EbkPO();

		BeanUtils.copyProperties(ebkDTO, ebkPO);

		int result = ebkMapper.addEbk(ebkPO);
		return result > 0 ? ebkPO.getBookId() : result;
	}

	@Override
	public List<EbkDTO> listBooks(GroupDTO groupDTO, UserDTO userDTO, EbkDTO ebkDTO, Integer start, Integer size) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<EbkDTO> dtoList = null;
		GroupPO groupDO = new GroupPO();
		UserPO userDO = new UserPO();
		EbkPO ebkPO = new EbkPO();
		BeanUtils.copyProperties(groupDTO, groupDO);
		BeanUtils.copyProperties(userDTO, userDO);
		BeanUtils.copyProperties(ebkDTO, ebkPO);
		List<EbkPO> doList = ebkMapper.listBooks(groupDO, userDO, ebkPO, start, size);

		try {
			dtoList = EntityUtils.exchange(doList, EbkDTO.class.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
		}

		return dtoList;
	}

	@Override
	public EbkDTO getUserBook(UserDTO userDTO, EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		UserPO userDO = new UserPO();
		EbkPO ebkDO = new EbkPO();
		EbkDTO ebkDTO2 = new EbkDTO();

		try {
			EntityUtils.copy(userDTO, userDO);
			BeanUtils.copyProperties(ebkDTO, ebkDO);

			ebkDO = ebkMapper.getUserBook(userDO, ebkDO);
			BeanUtils.copyProperties(ebkDO, ebkDTO2);
		} catch (Exception e) {
			return null;
		}

		return ebkDTO2;
	}

	@Transactional(readOnly = false)
	@Override
	public int addUserBook(UserDTO userDTO, EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		UserBkPO userBkDO = new UserBkPO();
		userBkDO.setBookId(ebkDTO.getBookId());
		userBkDO.setAdCode(userDTO.getAdCode());
		userBkDO.setUserId(userDTO.getId());

		return ebkMapper.addUserBook(userBkDO);
	}

	@Override
	public List<EbkDataDTO> listEbkData(EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		EbkPO ebkDO = new EbkPO();
		List<EbkDataDTO> dtos = null;

		BeanUtils.copyProperties(ebkDTO, ebkDO);

		List<EbkDataPO> dos = ebkMapper.listEbkData(ebkDO);

		try {
			dtos = EntityUtils.exchange(dos, EbkDataDTO.class.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
		}

		return dtos;
	}

	@Override
	public List<ChapterAO> listContents(EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		EbkPO ebkDO = new EbkPO();

		BeanUtils.copyProperties(ebkDTO, ebkDO);

		return ebkMapper.listContents(ebkDO);
	}

	@Override
	public List<TestDTO> listTest(EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<TestDTO> dtos = null;
		EbkPO ebkDO = new EbkPO();

		BeanUtils.copyProperties(ebkDTO, ebkDO);

		List<TestPO> dos = ebkMapper.listTest(ebkDO);

		try {
			dtos = EntityUtils.exchange(dos, TestDTO.class.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
		}

		return dtos;
	}

	@Override
	public List<EbkDTO> listBooksByAuthor(UserDTO userDTO, EbkDTO ebkDTO, Integer start, Integer size) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<EbkDTO> dtos = null;
		UserPO userPO = new UserPO();
		EbkPO ebkPO = new EbkPO();

		try {
			BeanUtils.copyProperties(ebkDTO, ebkPO);
		} catch (Exception e) {

		}

		try {
			EntityUtils.copy(userDTO, userPO);

			List<EbkPO> pos = ebkMapper.listBooksByAuthor(userPO, ebkPO, start, size);

			dtos = EntityUtils.exchange(pos, EbkDTO.class.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
		}

		return dtos;
	}

	@Override
	public List<TypeDTO> listTypes() {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<TypeDTO> dtos = null;

		List<TypeDTO> pos = ebkMapper.listTypes();

		try {
			dtos = EntityUtils.exchange(pos, TypeDTO.class.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
		}

		return dtos;
	}

	@Override
	public List<ContextDTO> listContexts(EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<ContextDTO> dtos = null;
		EbkPO ebkPO = new EbkPO();

		BeanUtils.copyProperties(ebkDTO, ebkPO);

		List<ContextPO> pos = ebkMapper.listContexts(ebkPO);

		try {
			dtos = EntityUtils.exchange(pos, ContextDTO.class.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
		}

		return dtos;
	}

	public int addContext(ContextDTO contextDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		ContextPO contextPO = new ContextPO();

		BeanUtils.copyProperties(contextDTO, contextPO);

		return ebkMapper.addContext(contextPO);
	}

	@Override
	public int addAsks(List<AskDTO> askDTOs) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<AskPO> askPOs = null;

		try {
			askPOs = EntityUtils.exchange(askDTOs, AskPO.class.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
			return -1;
		}

		QuestionPO questionPO = new QuestionPO();
		questionPO.setAsks(askPOs);

		return ebkMapper.addAsks(questionPO);
	}

	@Override
	public List<AskDTO> listAsks(AskDTO askDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<AskDTO> askDTOs = null;
		AskPO askPO = new AskPO();

		BeanUtils.copyProperties(askDTO, askPO);

		List<AskPO> askPOs = ebkMapper.listAsks(askPO);

		try {
			askDTOs = EntityUtils.exchange(askPOs, AskDTO.class.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
		}

		return askDTOs;
	}

	@Transactional(readOnly = false)
	@Override
	public int addQuestion(QuestionDTO questionDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		QuestionPO questionPO = new QuestionPO();

		try {
			EntityUtils.copy(questionDTO, questionPO);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
			return -1;
		}

		int result = ebkMapper.addQuestion(questionPO);

		if (result > 0 && questionPO.getAsks() != null && !questionPO.getAsks().isEmpty()) {

			result = ebkMapper.addAsks(questionPO);
		}

		return result;
	}

	@Override
	public List<QuestionDTO> listQuestions(QuestionDTO questionDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<QuestionDTO> dtos = null;
		QuestionPO questionPO = new QuestionPO();

		BeanUtils.copyProperties(questionDTO, questionPO);

		List<QuestionPO> pos = ebkMapper.listQuestions(questionPO);
		try {
			dtos = EntityUtils.exchange(pos, QuestionDTO.class.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
		}

		return dtos;
	}

	@Override
	public int addBkNum(EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		EbkPO ebkPO = new EbkPO();
		BeanUtils.copyProperties(ebkDTO, ebkPO);

		return ebkMapper.addBkNum(ebkPO);
	}

	@Override
	public int updateBk(EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		EbkPO ebkPO = new EbkPO();

		BeanUtils.copyProperties(ebkDTO, ebkPO);

		return ebkMapper.updateBk(ebkPO);
	}

	@Override
	public List<TextDTO> listTexts(TextDTO textDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<TextDTO> textDTOs = null;
		TextPO textPO = new TextPO();
		// 数据类型转换复制
		BeanUtils.copyProperties(textDTO, textPO);
		// 查询数据库得到已存在文本的内容
		List<TextPO> listTexts = ebkMapper.listTexts(textPO);
		try {
			textDTOs = EntityUtils.exchange(listTexts, TextDTO.class.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
		}

		return textDTOs;
	}

	@Override
	public int addText(TextDTO textDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		TextPO textPO = new TextPO();
		// 数据类型转换复制
		BeanUtils.copyProperties(textDTO, textPO);

		return ebkMapper.addText(textPO);
	}

	@Override
	public List<DiscussDTO> listDiscuss(DiscussDTO discussDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<DiscussDTO> discussDTOs = null;
		DiscussPO discussPO = new DiscussPO();
		// 数据类型转换复制
		BeanUtils.copyProperties(discussDTO, discussPO);
		// 查询数据库得到已存在讨论题目
		List<DiscussPO> listDiscuss = ebkMapper.listDiscuss(discussPO);
		try {
			discussDTOs = EntityUtils.exchange(listDiscuss, DiscussDTO.class.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
		}
		return discussDTOs;
	}

	@Override
	public int addDiscuss(List<DiscussDTO> discussDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<DiscussPO> discussPOs = null;

		try {
			discussPOs = EntityUtils.exchange(discussDTO, DiscussPO.class.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
			return -1;
		}

		return ebkMapper.addDiscuss(discussPOs);
	}

	@Override
	public List<MajorDTO> listCourses(MajorDTO courseDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<MajorDTO> dtos = null;
		MajorPO coursePO = new MajorPO();

		BeanUtils.copyProperties(courseDTO, coursePO);

		List<MajorPO> pos = ebkMapper.listCourses(coursePO);

		try {
			dtos = EntityUtils.exchange(pos, MajorDTO.class.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
		}

		return dtos;
	}

	@Override
	public List<PeriodDTO> listPeriod() {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<PeriodDTO> dtos = null;

		List<PeriodPO> pos = ebkMapper.listPeriod();

		try {
			dtos = EntityUtils.exchange(pos, PeriodDTO.class.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
		}

		return dtos;
	}

	@Override
	public int upLen(int bookCode) {
		return ebkMapper.upLen(bookCode);
	}

	@Override
	public List<ChapterAO> listContents2(EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		EbkPO ebkDO = new EbkPO();

		BeanUtils.copyProperties(ebkDTO, ebkDO);

		return ebkMapper.listContents2(ebkDO);
	}

	@Override
	public List<EbkDataDTO> listData(EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		List<EbkDataDTO> dtos = null;
		EbkPO ebkPO = new EbkPO();

		BeanUtils.copyProperties(ebkDTO, ebkPO);
		List<EbkDataPO> pos = ebkMapper.listData(ebkPO);

		try {
			dtos = EntityUtils.exchange(pos, EbkDataDTO.class.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
		}
		return dtos;
	}

	@Override
	public int deleteContext(List<ChapterContextAO> chapterContextAOs) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		return ebkMapper.deleteContext(chapterContextAOs);
	}

	@Override
	public List<ChapterContextAO> listChapterContexts(ContextDTO contextDTO, Integer start, Integer size) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		ContextPO contextPO = new ContextPO();
		BeanUtils.copyProperties(contextDTO, contextPO);

		List<ChapterContextAO> pos = ebkMapper.listChapterContexts(contextPO, start, size);

		return pos;
	}

	@Override
	public int countBooksByAuthor(UserDTO userDTO, EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		int count = 0;
		UserPO userPO = new UserPO();
		EbkPO ebkPO = new EbkPO();

		try {
			BeanUtils.copyProperties(ebkDTO, ebkPO);
		} catch (Exception e) {

		}
		try {
			EntityUtils.copy(userDTO, userPO);
			count = ebkMapper.countBooksByAuthor(userPO, ebkPO);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
		}

		return count;
	}

	@Override
	public int countChapterContexts(ContextDTO contextDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		ContextPO contextPO = new ContextPO();
		try {
			BeanUtils.copyProperties(contextDTO, contextPO);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return ebkMapper.countChapterContexts(contextPO);
	}

	@Override
	public List<EbkDTO> likeListBooks(BookMajorDTO bookMajorDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		List<EbkDTO> dtos = null;

		List<EbkPO> pos = ebkMapper.likeListBooks(bookMajorDTO);
		try {
			dtos = EntityUtils.exchange(pos, EbkDTO.class.getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
		}
		return dtos;
	}

	@Override
	public List<MajorDTO> listMajors(int type) {

		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		List<MajorDTO> dtos = null;
		List<MajorPO> pos = ebkMapper.listMajors(type);
		try {
			dtos = EntityUtils.exchange(pos, MajorDTO.class.getName());
			for (int i = 0; i < dtos.size(); i++) {
				dtos.get(i).setCollegeDTOs(EntityUtils.exchange(pos.get(i).getCollegePOs(), MajorDTO.class.getName()));
				for (int j = 0; j < pos.get(i).getCollegePOs().size(); j++) {
					dtos.get(i).getCollegeDTOs().get(j).setMajorDTOs(EntityUtils
							.exchange(pos.get(i).getCollegePOs().get(j).getMajorPOs(), MajorDTO.class.getName()));
				}
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
				| InvocationTargetException e) {
			logger.error(e.getMessage());
		}
		return dtos;
	}

	@Override
	public List<EbkAO> listWorkBooks(GroupDTO groupDTO, UserDTO userDTO, EbkDTO ebkDTO) {

		return ebkMapper.listWorkBooks(groupDTO, userDTO, ebkDTO);
	}

	@Override
	public List<QuestionAO> listEbkDataQuestion(EbkDTO ebkDTO, QuestionDTO questionDTO) {

		return ebkMapper.listEbkDataQuestion(ebkDTO, questionDTO);
	}

}
