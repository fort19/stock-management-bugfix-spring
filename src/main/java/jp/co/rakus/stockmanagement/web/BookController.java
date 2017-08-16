package jp.co.rakus.stockmanagement.web;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import jp.co.rakus.stockmanagement.domain.Book;
import jp.co.rakus.stockmanagement.service.BookService;

/**
 * 書籍関連処理を行うコントローラー.
 * 
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/book")
@Transactional
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private ServletContext application;

	/**
	 * フォームを初期化します.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public BookForm setUpForm() {
		return new BookForm();
	}

	@ModelAttribute
	public RegisterBookForm setUpRegisterBookForm() {
		return new RegisterBookForm();
	}

	/**
	 * 書籍リスト情報を取得し書籍リスト画面を表示します.
	 * 
	 * @param model
	 *            モデル
	 * @return 書籍リスト表示画面
	 */
	@RequestMapping(value = "list")
	public String list(Model model) {
		List<Book> bookList = bookService.findAll();
		model.addAttribute("bookList", bookList);
		return "book/list";
	}

	/**
	 * 書籍詳細情報を取得し書籍詳細画面を表示します.
	 * 
	 * @param id
	 *            書籍ID
	 * @param model
	 *            モデル
	 * @return 書籍詳細画面
	 */
	@RequestMapping(value = "show/{bookId}")
	public String show(@PathVariable("bookId") Integer id, Model model) {
		Book book = bookService.findOne(id);
		model.addAttribute("book", book);
		return "book/show";
	}

	/**
	 * 書籍更新を行います.
	 * 
	 * @param form
	 *            フォーム
	 * @param result
	 *            リザルト情報
	 * @param model
	 *            モデル
	 * @return 書籍リスト画面
	 */
	@RequestMapping(value = "update")
	public String update(@Validated BookForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return show(form.getId(), model);
		}
		Book book = bookService.findOne(form.getId());
		book.setStock(form.getStock());
		bookService.update(book);
		return list(model);
	}

	/**
	 * 書籍追加画面に遷移するメソッド.
	 * 
	 * @return
	 */
	@RequestMapping(value = "toAddbookJsp")
	public String toAddBookJsp() {
		return "book/addbook";
	}

	/**
	 * 書籍を追加するメソッド.
	 * 
	 * @param model
	 * @return 書籍一覧画面に遷移するlistメソッド
	 */
	@RequestMapping(value = "insert")
	public String insertBook(@Validated RegisterBookForm form, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return toAddBookJsp();
		}

		// 入力された情報を持つformをコピー
		Book book = new Book();
		BeanUtils.copyProperties(form, book);

		// 新規Idを作成してセット
		book.setId(bookService.createId());

		// String型の日付をDate型に変換
		Date date = bookService.convertStringDate(form.getSaledate());
		book.setSaledate(date);

		/** 画像を保存 する */
		MultipartFile imageFile = form.getImageFile();
		if (imageFile.isEmpty()) {
			model.addAttribute("imageError", "画像は必須です。");
			return toAddBookJsp();
		}
		// ファイルの名前を取得
		String fileName = imageFile.getOriginalFilename();
		// ファイルを保存
		try {
			//保存場所
			String destPath = application.getRealPath("/img/" + fileName);
			//ファイル場所のpath情報が入ったオブジェクトを作る
			File destFile = new File(destPath);
			//画像を指定の場所(destFile)に移動させる
			imageFile.transferTo(destFile);
		} catch (IllegalStateException e) {
			return toAddBookJsp();
		} catch (Exception e) {
			return toAddBookJsp();
		}

		book.setImage(fileName);

		book = bookService.insert(book);

		return "redirect:list";

	}

}
