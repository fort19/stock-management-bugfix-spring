package jp.co.rakus.stockmanagement.web;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.stockmanagement.domain.Member;
import jp.co.rakus.stockmanagement.service.MemberService;

/**
 * メンバー関連処理を行うコントローラー.
 * 
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/member")
@Transactional
public class MemberController {

	@Autowired
	private MemberService memberService;

	/**
	 * フォームを初期化します.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public MemberForm setUpForm() {
		return new MemberForm();
	}

	/**
	 * メンバー情報登録画面を表示します.
	 * 
	 * @return メンバー情報登録画面
	 */
	@RequestMapping(value = "form")
	public String form() {
		return "/member/form";
	}

	/**
	 * メンバー情報を登録します.
	 * 
	 * @param form
	 *            フォーム
	 * @param result
	 *            リザルト
	 * @param model
	 *            モデル
	 * @return ログイン画面
	 */
	@RequestMapping(value = "create")
	public String create(@Validated MemberForm form, BindingResult result, Model model) {

		// 確認用パスワードの入力チェック -> エラーの場合はresultに追加
		if (!(form.getPassword().equals(form.getConfirmPassword()))) {
			result.rejectValue("confirmPassword", null, "確認用パスワードが違います。");
		}

		// 入力されたメールアドレスが既に登録されたものかチェック -> エラーの場合はresultに追加
		Member member = new Member();
		member = memberService.findByMailAddress(form.getMailAddress());
		if (!(member == null)) {
			if (member.getMailAddress().equals(form.getMailAddress())) {
				result.rejectValue("mailAddress", null, "入力されたメールアドレスは既に使用されています。");
			}
		} else {

			// 入力値チェック
			if (result.hasErrors()) {
				return "/member/form";
			}
		}
			member = new Member();
			BeanUtils.copyProperties(form, member);
			memberService.save(member);
			return "redirect:/";
	}

}
