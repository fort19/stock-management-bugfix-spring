package jp.co.rakus.stockmanagement.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.rakus.stockmanagement.domain.Book;
import jp.co.rakus.stockmanagement.repository.BookRepository;

/**
 * 書籍関連サービスクラス.
 * @author igamasayuki
 *
 */
@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;
	
	public List<Book> findAll(){
		return bookRepository.findAll();
	}
	
	public Book findOne(Integer id) {
		return bookRepository.findOne(id);
	}
	
	/**新たに書籍を追加するメソッド.
	 * @param book 追加する書籍情報
	 * @param id　新たに生成した書籍id
	 */
	public Book insert(Book book){
		return bookRepository.save(book);
	}
	
	/**
	 * 新規idを作成
	 * @return
	 */
	public Integer createId(){
		Integer currentMaxId = bookRepository.getMaxId();
		if(currentMaxId == null){
			currentMaxId = 1;
			return currentMaxId;
		}
		return currentMaxId + 1;
	}
	
	public Book update(Book book){
		return bookRepository.update(book);
	}
	
	/**
	 * String型で与えられた日付をDBに挿入するためにDate型に変換する.
	 * 
	 * @param currentDate　画面から与えられた日付
	 * @return　変換された日付
	 */
	public Date convertStringDate(String before){
		Date after = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try{
			after = sdf.parse(before);
		}catch(Exception e){
			System.out.println("日付の形式が異なります。例外キャッチ");
			return null;
		}
		return after;
	}
	
//	public void delete(Integer id){
//		bookRepository.delete(id);
//	}
	
	
}
