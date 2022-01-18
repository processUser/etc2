window.onload = () =>{
    // 회원가입 JSON 생성
    let formElem = document.querySelector('#formWarp');
    let submitElem = formElem.querySelector('#submit');
    submitElem.addEventListener('click', (e) => {
        let login = new Object();
        login.email = formElem.email.value;
        login.upw = formElem.upw.value;
        login.nm = formElem.nm.value;
        login.gender = formElem.gender.value;
        login.birthdaymm = formElem.birthdaymm.value;
        login.birthdaydd = formElem.birthdaydd.value;
        login.joinpath = formElem.joinpath.value;
        
        let jsonLogin = JSON.stringify(login);
        console.log(jsonLogin);
        insLogin(jsonLogin)
        e.preventDefault;
    })

    // 회원가입 실행
    function insLogin(jsonLogin){
        let url = "http://localhost:8100/join"    
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
    function goLogin(aa) {
        let joinUser = aa
        if(joinUser.result){
            location.href ="/login.html"
        }
        console.log(aa);
    }
}