package com.edudigital.cloudy.amp.textbook.service.service;

import java.util.List;

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
import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;

/**
 * 
 * @author Tempo
 * @date 2017年7月26日 下午5:00:46
 *
 */
public interface IEbkService {
	/**
	 * 
	 * @return
	 */
	public List<EbkDTO> getEbkSaledNums(UserDTO user);

	/**
	 * 
	 * @param user
	 * @return
	 */
	public int addEbk(EbkDTO ebkDTO);

	/**
	 * 
	 * @param groupDTO
	 * @param userDTO
	 * @param ebkDTO
	 * @param start
	 * @param size
	 * @return
	 */
	public List<EbkDTO> listBooks(GroupDTO groupDTO, UserDTO userDTO, EbkDTO ebkDTO, Integer start, Integer size);

	/**
	 * 
	 * @param userDTO
	 * @param ebkDTO
	 * @return
	 */
	public EbkDTO getUserBook(UserDTO userDTO, EbkDTO ebkDTO);

	/**
	 * 
	 * @param userDTO
	 * @param ebkDTO
	 * @return
	 */
	public int addUserBook(UserDTO userDTO, EbkDTO ebkDTO);

	/**
	 * 
	 * @param ebkDTO
	 * @return
	 */
	public List<EbkDataDTO> listEbkData(EbkDTO ebkDTO);

	/**
	 * 
	 * @param ebkDTO
	 * @return
	 */
	public List<TestDTO> listTest(EbkDTO ebkDTO);

	/**
	 * 
	 * @param ebkDTO
	 * @return
	 */
	public List<ChapterAO> listContents(EbkDTO ebkDTO);

	/**
	 * 
	 * @param ebkDTO
	 * @return
	 */
	public List<ChapterAO> listContents2(EbkDTO ebkDTO);

	/**
	 * 
	 * @return
	 */
	public List<TypeDTO> listTypes();

	/**
	 * 
	 * @param ebkDTO
	 * @return
	 */
	public List<ContextDTO> listContexts(EbkDTO ebkDTO);

	/**
	 * 
	 * @param contextPO
	 * @return
	 */
	public int addContext(ContextDTO contextDTO);

	/**
	 * 
	 * @param askPOs
	 * @return
	 */
	public int addAsks(List<AskDTO> askDTOs);

	/**
	 * 
	 * @param askPO
	 * @return
	 */
	public List<AskDTO> listAsks(AskDTO askDTO);

	/**
	 * 
	 * @param questionPO
	 * @return
	 */
	public int addQuestion(QuestionDTO questionDTO);

	/**
	 * 
	 * @param questionPO
	 * @return
	 */
	public List<QuestionDTO> listQuestions(QuestionDTO questionDTO);

	/**
	 * 
	 * @param ebkDTO
	 * @return
	 */
	public int addBkNum(EbkDTO ebkDTO);

	/**
	 * 
	 * @param ebkDTO
	 * @return
	 */
	public int updateBk(EbkDTO ebkDTO);

	/**
	 * 
	 * @param textDTO
	 * @return
	 */
	public List<TextDTO> listTexts(TextDTO textDTO);

	/**
	 * 
	 * @param textDTO
	 * @return
	 */
	public int addText(TextDTO textDTO, int bookCode);

	/**
	 * 
	 * @param discussDTO
	 * @return
	 */
	public List<DiscussDTO> listDiscuss(DiscussDTO discussDTO);

	/**
	 * 
	 * @param discussDTOs
	 * @return
	 */
	public int addDiscuss(List<DiscussDTO> discussDTOs);

	/**
	 * 
	 * @param courseDTO
	 * @return
	 */
	public List<MajorDTO> listCourses(MajorDTO courseDTO);

	/**
	 * 
	 * @return
	 */
	public List<PeriodDTO> listPeriod();

	/**
	 * 
	 * @param ebkDataDTO
	 * @return
	 */
	public List<EbkDataDTO> listData(EbkDTO ebkDTO);

	/**
	 * 
	 * @param chapterContextAOs
	 * @return
	 */
	public int delteContext(List<ChapterContextAO> chapterContextAOs);

	/**
	 * 
	 * @param contextDTO
	 * @param start
	 * @return
	 */
	public List<ChapterContextAO> listChapterContexts(ContextDTO contextDTO, Integer start, Integer size);

	/**
	 * 
	 * @param userDTO
	 * @param ebkDTO
	 * @return
	 */
	public int countBooksByAuthor(UserDTO userDTO, EbkDTO ebkDTO);

	/**
	 * 
	 * @param contextDTO
	 * @return
	 */
	public int countChapterContexts(ContextDTO contextDTO);

	/**
	 * 查询书籍
	 * 
	 * @param bookMajorDTO
	 * @return
	 */
	public List<EbkDTO> likeListBooks(BookMajorDTO bookMajorDTO);

	/**
	 * 查询专业
	 * 
	 * @param type
	 * @return
	 */
	public List<MajorDTO> listMajors(int type);

	/**
	 * 
	 * @param ebkPO
	 * @return
	 */
	public List<EbkAO> listWorkBooks(GroupDTO groupDTO, UserDTO userDTO, EbkDTO ebkDTO);

	/**
	 * 移动端习题查询
	 * 
	 * @param ebkDTO
	 * @param questionDTO
	 * @return
	 */
	public List<QuestionAO> listEbkDataQuestion(EbkDTO ebkDTO, QuestionDTO questionDTO);
}
