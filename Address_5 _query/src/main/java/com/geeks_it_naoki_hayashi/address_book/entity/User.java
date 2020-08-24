package com.geeks_it_naoki_hayashi.address_book.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import lombok.Data;

import javax.persistence.Id;
//テーブルおよびカラムの設定　lombokを使いgetterとsetterを自動生成している
@Entity
@Data
@Table(name="user")
public class User implements Serializable   {
	
		@Id
	    @Column(name="id")
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		@Column(name="username")
		private String username;

		@Column(name="address")
		private String address;

		@Column(name="phone")
		private String phone;

		@Column(name="update_date")
		private Date updateDate;

		@Column(name="create_date")
		private Date createDate;
		
		
	}