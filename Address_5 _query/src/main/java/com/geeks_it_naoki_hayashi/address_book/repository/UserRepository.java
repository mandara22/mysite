package com.geeks_it_naoki_hayashi.address_book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.geeks_it_naoki_hayashi.address_book.entity.User;

//JpaRepositoryを継承したインターフェースによってデーター一覧(findAll),新規登録,更新(save),ユーザー検索(findById),削除(delete),をsql文を使わずに実装する
@Repository
public interface UserRepository extends JpaRepository <User, Long> {

	@Query("select u from User u where u.username like %:keyword% ")
	List<User> findUsers(@Param("keyword") String keyword);
}

