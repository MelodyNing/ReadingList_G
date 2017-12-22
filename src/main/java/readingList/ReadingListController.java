package readingList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // 组件扫描会自动将其注册为Spring应用程序上下文的一个Bean
@RequestMapping("/") // @RequestMapping一个用来处理请求地址映射的注解，可用于类或方法上
public class ReadingListController {
	private ReadingListRepository readingListRepository;

	@Autowired // @Autowired可以对成员变量、方法和构造函数进行标注，来完成自动装配的工作
	public ReadingListController(ReadingListRepository readingListRepository) {
		this.readingListRepository = readingListRepository;
	}

	@GetMapping("index")
	public String list(){
		return "readingList";
	}
	
	@RequestMapping(value = "/{reader}", method = RequestMethod.GET) 
	public String readerBooks(@PathVariable("reader") String reader, Model model) {
		// @PathVariable 可以将 URL 中占位符参数绑定到控制器处理方法的入参中：URL 中的 {xxx}
		// 占位符可以通过@PathVariable("xxx") 绑定到操作方法的入参中。
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
