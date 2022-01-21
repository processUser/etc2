window.onload = () =>{

    let formElem = document.querySelector('#formWarp');
    let submitElem = formElem.querySelector('#submit');
    // form 엔터 서브밋 막기
    formElem.addEventListener('submit', (e) => { 
        e.preventDefault;
    });
    // 버튼 클릭시 json 객채 생성
    submitElem.addEventListener('click', (e) => { 
        if(formElem.email.value && formElem.upw.value){
            let login = new Object();
            login.email = formElem.email.value;
            login.pw = formElem.upw.value;
            login.preUrl = document.referrer; // 이전 페이지 url

            let jsonLogin = JSON.stringify(login);
            insLogin(jsonLogin)
            e.preventDefault;
        }
    });

    // 홈페이지 로그인 처리 
    function insLogin(jsonLogin){
        let url = "http://localhost:8090/login"    
        fetch(url,{
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: jsonLogin,
        }).then((response) => {
            console.log(response.headers.get('Authorization'))
            // // refresh token 가저오기
            // // ajax 는 document.cookie 가 안됨..
            // console.log('res - join - response.cookie : ' + response.headers.get('setcookie')) 
            accessToken(response.headers.get('Authorization'))
            // refreshToken()
            return response.json();
        }).then((data) => {
            //console.log(data);
            goLogin(data);
        }).catch((e) => {
            console.log(e);
        })
        

    }

    // 로그인 성공시 페이지 이동
    function goLogin(data) { //수정필!!
        let joinUser = data.result;

        if(joinUser){
            //agreeCookie('ref', data.rjwt, '/', 14)
            //location.href ="/main.html"
            console.log(document.cookie);
            sendToken(1);
        }else{
            alert(data.msg);
            formElem.email.focus();
        }
    }

    // JWT access token 가져오기
    // response 헤더에 Authorization 이곳에 담겨있다.
    function accessToken(token) {
        var tokens =token 
        var base64Payload = encodeURIComponent(tokens.split('.')[1]); //value 0 -> header, 1 -> payload, 2 -> VERIFY SIGNATURE 
        var payload = decodeURIComponent(escape(window.atob(base64Payload))); // 한글깨짐 해결 - https://developer.mozilla.org/en-US/docs/Glossary/Base64
        var result = JSON.parse(payload.toString()) 
        console.log(payload.toString());

        agreeCookie('accessToken',JSON.parse(payload.toString()), '/')
       // document.cookie = 'accessToken='+ payload.toString() +';path=/;expires='+ date;
    }

    //쿠키생성.
    function agreeCookie(cKey, cName, cPath, day) {
        // var date = new Date();
        // date.setTime(date.getTime() + (day * 24 * 60 * 60 * 1000));
        document.cookie = cKey +'='+ cName + ';path='+ cPath + ';'
            // + 'expires='+ date;
    }

    function get(json) {
        let cookies = document.cookie.split(';')
        const item = json;

        console.log('1 : '+document.cookie)
        console.log('2 : '+ cookies[1]);
        console.log('2 : '+ cookies[1].split('=')[1]);
        console.log('3 : '+ item.email);
    }

    function useToken(data) {
        //console.log("65000")
        //console.log("tokens"+data.substr(6))
        let url = "http://localhost:8090/checktoken"    
        fetch(url,{
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                "token" : data.substr(6)
            }),
        }).then((response) => {  
            //console.log(response)  
        }).then((data) => {
            //console.log('checktoken : '+ data);
        }).catch((e) => {
            console.log(e);
        })
        //console.log('document.cookie : ' + document.cookie) // ajax에서는 안됨.
    }

    function sendToken(tokens) {
        //console.log("65000")
        console.log("----------sendToken---------------")
        let url = "http://localhost:8090/refreshtoken"    
        fetch(url,{
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                "token" : tokens
            }),
            // credentials: 'include',
        }).then((response) => {  
            //console.log(response)  
        }).then((data) => {
            //console.log('checktoken : '+ data);
        }).catch((e) => {
            console.log(e);
        })
        //console.log('document.cookie : ' + document.cookie) // ajax에서는 안됨.
    }

}