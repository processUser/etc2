//https://regex101.com/
//https://hee-kkk.tistory.com/22
const reg = {  // 정규식
    email: /^[0-9a-zA-Z]([_]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/,
    pw: /(?=.*\d{1,12})(?=.*[!@_]{1,12})(?=.*[a-zA-Z]{1,12}).{8,12}$/gm,
    nm: /[가-힣]{2,5}/gm,
    dd: /^[0-9]{2}$/gm
}

// JWT access token 가져오기
// response 헤더에 Authorization 이곳에 담겨있다.
// 로그인 처리 시키기
function accessToken(token) {
    var tokens =token
    var base64Payload = encodeURIComponent(tokens.split('.')[1]); //value 0 -> header, 1 -> payload, 2 -> VERIFY SIGNATURE
    var payload = decodeURIComponent(escape(window.atob(base64Payload))); // 한글깨짐 해결 - https://developer.mozilla.org/en-US/docs/Glossary/Base64
    var result = JSON.parse(payload.toString())
    console.log(result);

    document.cookie = 'accessToken='+ encodeURIComponent(payload.toString()) +';path=/;';
    test(getCookie('accessToken'))
}

function test(data) {
    let a = JSON.parse(decodeURIComponent(escape(data)))
    console.log(a);

}

function getCookie(name) { //--가져올 쿠키의 이름을 파라미터 값으로 받고
    var nameOfCookie = name + "="; //--쿠키는 "쿠키=값" 형태로 가지고 있어서 뒤에 있는 값을 가져오기 위해 = 포함
    var x = 0;

    while (x <= document.cookie.length) {  //--현재 세션에 가지고 있는 쿠키의 총 길이를 가지고 반복
        var y = (x + nameOfCookie.length); //--substring으로 찾아낼 쿠키의 이름 길이 저장
        if (document.cookie.substring(x, y) == nameOfCookie) { //--잘라낸 쿠키와 쿠키의 이름이 같다면
            if ((endOfCookie = document.cookie.indexOf(";", y)) == -1) //--y의 위치로부터 ;값까지 값이 있으면
            endOfCookie = document.cookie.length; //--쿠키의 길이로 적용하고
            return unescape(document.cookie.substring(y, endOfCookie)); //--쿠키의 시작점과 끝점을 찾아서 값을 반환
        }
        x = document.cookie.indexOf(" ", x) + 1; //--다음 쿠키를 찾기 위해 시작점을 반환
        if (x == 0) //--쿠키 마지막이면
        break; //--반복문 빠져나오기
    }
    return ""; //--빈값 반환
}

//출처: https://rjs5967.tistory.com/25 [에코의 내맘대로 일상생활]


//
function sendToken() {
    //console.log("65000")
    console.log("----------sendToken---------------")
    let tokens = {
        'preUrl': document.URL, // 이전 url
    }
    let url = "http://localhost:8090/refreshtoken"
    fetch(url,{
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(tokens),
        // credentials: 'include',
    }).then((response) => {
        accessToken(response.headers.get('Authorization'))
        //console.log(response)
    }).then((data) => {
        //console.log('checktoken : '+ data);
    }).catch((e) => {
        console.log(e);
    })
    //console.log('document.cookie : ' + document.cookie) // ajax에서는 안됨.
}

setTimeout(sendToken(),5000);