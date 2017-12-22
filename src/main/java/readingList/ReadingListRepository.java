package readingList;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository是一个繁星接口，有两个参数：仓库操作的领域对象类型，及其ID属性的类型
//18个执行持久化操作方法，在运行时自动实现
public interface ReadingListRepository extends JpaRepository<Book,Long>{
	List<Book> findByReader(String reader);//根据读者的用户名来查找阅读列表
}
