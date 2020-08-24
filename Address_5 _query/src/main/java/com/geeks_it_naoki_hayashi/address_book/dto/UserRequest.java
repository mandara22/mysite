package com.geeks_it_naoki_hayashi.address_book.dto;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest implements Serializable {
//新規登録でバリデーションによって入力される要素を設定　エラー時にメッセージを表示
  @NotEmpty(message = "名前が空欄です")
  @Size(max = 100, message = "名前が長すぎます")
  private String username;
  @NotEmpty(message = "住所が空欄です")
  private String address;
  @Pattern(regexp = "^\\d{2,4}-\\d{2,4}-\\d{4}$", message = "電話番号に入力できない値が含まれています")
  private String phone;
}