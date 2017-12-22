package readingList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // ���ɨ����Զ�����ע��ΪSpringӦ�ó��������ĵ�һ��Bean
@RequestMapping("/") // @RequestMappingһ���������������ַӳ���ע�⣬��������򷽷���
public class ReadingListController {
	private ReadingListRepository readingListRepository;

	@Autowired // @Autowired���ԶԳ�Ա�����������͹��캯�����б�ע��������Զ�װ��Ĺ���
	public ReadingListController(ReadingListRepository readingListRepository) {
		this.readingListRepository = readingListRepository;
	}

	@GetMapping("index")
	public String list(){
		return "readingList";
	}
	
	@RequestMapping(value = "/{reader}", method = RequestMethod.GET) 
	public String readerBooks(@PathVariable("reader") String reader, Model model) {
		// @PathVariable ���Խ� URL ��ռλ�������󶨵�������������������У�URL �е� {xxx}
		// ռλ������ͨ��@PathVariable("xxx") �󶨵���������������С�
		List<Book> readingList = readingListRepository.findByReader(reader);
		if (readingList != null) {
			model.addAttribute("books", readingList);
		}
		return "readingList";
	}

	@RequestMapping(value = "/{reader}", method = RequestMethod.POST)
	public String addToReadingList(@PathVariable("reader") String reader, Book book) {
		book.setReader(reader);
		readingListRepository.save(book);
		return "redirect:/{reader}";
	}
}
