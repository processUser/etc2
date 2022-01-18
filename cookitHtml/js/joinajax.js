// 회원가입 실행
function insLogin(jsonLogin){
    console.log('insLogin')
    let url = "http://localhost:8090/join"    
    fetch(url,{
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: jsonLogin,
    }).then((res) => {
        return res.json();
    }).then((data) => {
        console.log(data);
        goLogin(data);
    }).catch((e) => {
        console.log(e);
    })
}

// 성공시 로그인 페이지 이동
function goLogin(data) {
    if(data.result){
        location.href ="/login.html"
    }
    console.log(data);
}
