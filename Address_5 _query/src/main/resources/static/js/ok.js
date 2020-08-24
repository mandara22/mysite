function conf1(){
	let result = window.confirm("アカウントは削除されますがよろしいですか？")
	if(result){
		alert("アカウントは削除されました")
		return true;
	}else{
		alert("削除はキャンセルされました")
		return false;
	}
}

function conf2(){
	let result = window.confirm("編集内容を保存しますか？")
	if(result){
		return true;
	}else{
		return false;
	}
}

function conf3(){
	let result = window.confirm("以上の内容を登録しますか？")
	if(result){
		return true;
	}else{
		return false;
	}
}

function conf4(){
	let result = window.confirm("登録を取り消しますか？※入力内容は保存されません。")
	if(result){
		return true;
	}else{
		return false;
	}
}