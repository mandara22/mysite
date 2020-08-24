package com.geeks_it_naoki_hayashi.address_book.service;

import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import com.geeks_it_naoki_hayashi.address_book.repository.UserRepository;
import com.geeks_it_naoki_hayashi.address_book.dto.UserRequest;
import com.geeks_it_naoki_hayashi.address_book.dto.UserUpdateRequest;
import com.geeks_it_naoki_hayashi.address_book.entity.User;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserService {
	//UserRepositoryインターフェースをDIしている
	@Autowired
	UserRepository userRepository;
	
	//ユーザー一覧メソッド生成
	public List<User> serachAll(){
		 return userRepository.findAll();
		 
	}
	//ユーザ登録メソッド生成
	public void create(final UserRequest userRequest) {
		final Date now = new Date();
		
		final User user = new User();
		user.setUsername(userRequest.getUsername());
		user.setAddress(userRequest.getAddress());
		user.setPhone(userRequest.getPhone());
		user.setCreateDate(now);
		user.setUpdateDate(now);
		userRepository.save(user);
	}
	
	//ユーザー詳細用メソッド生成
	public User findById(final Long id) {
	        return userRepository.findById(id).get();
	    }
	//ユーザー更新メソッド生成
	public void update(final UserUpdateRequest userUpdateRequest) {
        final User user = findById(userUpdateRequest.getId());
        user.setAddress(userUpdateRequest.getAddress());
        user.setUsername(userUpdateRequest.getUsername());
        user.setPhone(userUpdateRequest.getPhone());
        user.setUpdateDate(new Date());
        userRepository.save(user);
    }
	//ユーザー削除用メソッド生成
	public void  deleteUser(final Long id){
		final User user =findById(id);
		userRepository.delete(user);
	}
	//ユーザーあいまい検索
	public List<User> findAll(@Param("keyword") String keyword){
		return userRepository.findUsers(keyword);
	}
}
