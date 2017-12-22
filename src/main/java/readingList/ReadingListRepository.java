package readingList;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository��һ�����ǽӿڣ��������������ֿ����������������ͣ�����ID���Ե�����
//18��ִ�г־û�����������������ʱ�Զ�ʵ��
public interface ReadingListRepository extends JpaRepository<Book,Long>{
	List<Book> findByReader(String reader);//���ݶ��ߵ��û����������Ķ��б�
}
