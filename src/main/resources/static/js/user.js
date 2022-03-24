let index = {
	init: function() {
		$("#btn-save").on("click", () => { //function(){}대신 사용한 이유는 this를 바인딩 하기 위해서 사용
			this.save();
		});
		$("#btn-update").on("click", () => { //function(){}대신 사용한 이유는 this를 바인딩 하기 위해서 사용
			this.update();
		});
	},

	save: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()

		};
		//console.log(data)

		//ajax통신을 이용해 3개의 데이터를 jason으로 변경하여 insert요청을 할거임
		// ajax 호출시 default가 비동기 요청임

		$.ajax({
			// 회원가입 수행을 요청(100초라 가정해도 시간낭비x)			
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), //body타입
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			if(resp.status === 500)
			{
				alert("회원가입에 실패하였습니다.");
			}else{
				alert("회원가입이 완료되었습니다.");
			//console.log(resp);
			location.href = "/";				
			}		
		}).fail(function(error) {
			alert(JSON.stringify(error));

		});
	},
	
	update: function() {
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()

		};
		//console.log(data)

		//ajax통신을 이용해 3개의 데이터를 jason으로 변경하여 insert요청을 할거임
		// ajax 호출시 default가 비동기 요청임

		$.ajax({
			// 회원가입 수행을 요청(100초라 가정해도 시간낭비x)			
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data), //body타입
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("회원수정이 완료되었습니다.");
			//console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));

		});
	}
	
}

index.init();