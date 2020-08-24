package com.geeks_it_naoki_hayashi.address_book.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.geeks_it_naoki_hayashi.address_book.service.UserService;
import com.geeks_it_naoki_hayashi.address_book.dto.UserRequest;
import com.geeks_it_naoki_hayashi.address_book.dto.UserUpdateRequest;
import com.geeks_it_naoki_hayashi.address_book.entity.User;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {

    //UserServiceクラスをDＩしている
	  @Autowired
    UserService userService;

    @PersistenceContext
    EntityManager entityManager;

    //ユーザー一覧取得
    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public String displayList( Model model) {
        List<User> userlist = userService.serachAll();
        model.addAttribute("userlist", userlist);
        return "user/list";
    }

    //新規登録画面に移動
    @GetMapping(value = "/user/add")
    public String displayAdd(final Model model) {
      model.addAttribute("userRequest", new UserRequest());
      return "user/add";
    }

    //新規登録画面
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String create(@Validated @ModelAttribute final UserRequest userRequest, final BindingResult result, final Model model) {
    	if (result.hasErrors()) {
    		final List<String> errorList = new ArrayList<String>();
    		for (final ObjectError error : result.getAllErrors()) {
          errorList.add(error.getDefaultMessage());
    		}
        model.addAttribute("validationError", errorList);
        return "user/add";
    	}

      userService.create(userRequest);
      return "redirect:/user/list";
    }

    //ユーザーごとに詳細画面を実装
    @GetMapping("/user/{id}")
    public String displayView(@PathVariable final Long id, final Model model) {
      final User user = userService.findById(id);
      model.addAttribute("userRequest", user);
      return "user/detail";
    }

    //詳細画面から編集画面に移動
    @GetMapping("/user/{id}/edit")
    public String displayEdit(@PathVariable final Long id, final Model model) {
        final User user = userService.findById(id);
        final UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setId(user.getId());
        userUpdateRequest.setUsername(user.getUsername());
        userUpdateRequest.setPhone(user.getPhone());
        userUpdateRequest.setAddress(user.getAddress());
        model.addAttribute("userUpdateRequest", userUpdateRequest);
        return "user/edit";
    }

    //編集画面
    @RequestMapping(value="/user/update", method=RequestMethod.POST)
    public String update(@Validated @ModelAttribute final UserUpdateRequest userUpdateRequest, final BindingResult result, final Model model) {
        if (result.hasErrors()) {
            final List<String> errorList = new ArrayList<String>();
            for(final ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
              model.addAttribute("validationError", errorList);
              return "user/edit";
            }

        userService.update(userUpdateRequest);
        return String.format("redirect:/user/%d", userUpdateRequest.getId());
    }

    //詳細画面から削除機能を実行
    @GetMapping(value="/user/{id}/delete")
    public String delete(@PathVariable final Long id) {
    	userService.deleteUser(id);
		return String.format("redirect:/user/list");

    }

    //検索機能を実行→検索結果画面へ
   @RequestMapping(value="/user/serach", method=RequestMethod.GET)
   public String displaySearch(Model model, @RequestParam String keyword) {
    List<User> userlist = new ArrayList<User>();
    if (StringUtils.isEmpty(keyword)) {
      userlist = userService.serachAll();
    }else{
      userlist = userService.findAll(keyword);
    }
    model.addAttribute("userlist", userlist);
      return "user/list";
   }

   // 削除画面一覧へ
   @GetMapping("/user/deletelist")
   public String displaydeletelist(Model model){
    List<User> userlist = userService.serachAll();
    model.addAttribute("userlist", userlist);
     return "user/deletelist";
   }
   //編集画面一覧へ
   @GetMapping("/user/editlist")
   public String displayeditlist(Model model){
    List<User> userlist = userService.serachAll();
    model.addAttribute("userlist", userlist);
     return "user/editlist";
   }
}


