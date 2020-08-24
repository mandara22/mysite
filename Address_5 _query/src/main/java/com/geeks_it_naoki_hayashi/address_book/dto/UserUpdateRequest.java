package com.geeks_it_naoki_hayashi.address_book.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

//ユーザー更新時に入力内容とは別に該当ユーザーのidが必要になるためUserRequestを継承したクラスを作成
@Data
public class UserUpdateRequest extends UserRequest implements Serializable {
	@NotNull
	private Long id;
}