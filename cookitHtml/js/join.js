//https://regex101.com/
//https://hee-kkk.tistory.com/22
const reg = {
    email: /^[0-9a-zA-Z]([_]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/,
    pw: /(?=.*\d{1,12})(?=.*[!@_]{1,12})(?=.*[a-zA-Z]{1,12}).{8,12}$/gm,
    nm: /[가-힣]{2,5}/gm
}

window.onload = () => {
    let formElem = document.querySelector('#formWarp');
    let labelWrapElem = formElem.querySelectorAll('.labelWrap');
    //console.log(labelWrapElem)

    // 이메일 체크
    formElem.email.addEventListener('blur', () => {
        //console.log(reg.email.test(formElem.email.value))
        if(!reg.email.test(formElem.email.value)){ // 정규식과 일치 안한다.
            // formElem.email.focus();
            //console.log(document.querySelector('.err'))
            if(!labelWrapElem[0].querySelector('.err')){
                const divElem = document.createElement('div');
                formElem.email.classList.add('errBorder');
                divElem.innerText = '영문, 숫자, 특수문자( _ ) 사용';
                divElem.classList.add('err');
                divElem.style.color = 'red'
                labelWrapElem[0].append(divElem)
            }
        } else{
            const divElem = document.querySelector('.err');
            formElem.email.classList.remove('errBorder');
            if(divElem)
                divElem.remove();
        }
    });

    // 비밀번호
    formElem.upw.addEventListener('blur', () => {
        if(!reg.pw.test(formElem.upw.value)){ // 일치하면 false
            if(!labelWrapElem[1].querySelector('.err')){
                const divElem = document.createElement('div');
                formElem.upw.classList.add('errBorder');
                divElem.innerHTML = '영문, 숫자, 특수문자(!, @, _) 각 1회 이상 사용<br> 8자리 이상 12자리이하';
                divElem.classList.add('err');
                divElem.style.color = 'red'
                labelWrapElem[1].append(divElem)
            }
        } else{
            const divElem = labelWrapElem[1].querySelector('.err');
            formElem.upw.classList.remove('errBorder');
            if(divElem)
                divElem.remove();
        }
        console.log(reg.pw.test(formElem.upw.value))
    });

    formElem.upw.addEventListener('keydown', () => {
        if(formElem.checkpw.value === ''){
            if(!labelWrapElem[2].querySelector('.err')){
                const divElem = document.createElement('div');
                formElem.checkpw.classList.add('errBorder');
                divElem.innerHTML = '비밀번호를 확인하세요.';
                divElem.classList.add('err');
                divElem.style.color = 'red'
                labelWrapElem[2].append(divElem)
            }
        }
    });

    // 비밀번호 확인
    formElem.checkpw.addEventListener('blur', () => {
        if(formElem.checkpw.value !== formElem.upw.value){
            if(!labelWrapElem[2].querySelector('.err')){
                const divElem = document.createElement('div');
                formElem.checkpw.classList.add('errBorder');
                divElem.innerHTML = '비밀번호를 확인하세요.';
                divElem.classList.add('err');
                divElem.style.color = 'red'
                labelWrapElem[2].append(divElem)
            }
        } else{
            const divElem = labelWrapElem[2].querySelector('.err');
            formElem.checkpw.classList.remove('errBorder');
            if(divElem)
                divElem.remove();
        }
    });

    //이름 확인
    formElem.nm.addEventListener('blur', () => {
        console.log(formElem.nm.value)
        if(formElem.nm.value === '' || !reg.nm.test(formElem.nm.value)){
            if(!labelWrapElem[3].querySelector('.err')){
                const divElem = document.createElement('div');
                formElem.nm.classList.add('errBorder');
                divElem.innerHTML = '한글 2자리 이상 5자리 이하';
                divElem.classList.add('err');
                divElem.style.color = 'red'
                labelWrapElem[3].append(divElem)
            }
        } else{
            const divElem = labelWrapElem[3].querySelector('.err');
            formElem.nm.classList.remove('errBorder');
            if(divElem)
                divElem.remove();
        }
    });
    // 취소하기
    const backElem = document.querySelector('#back');
    backElem.addEventListener('click', ()=>{
        if(confirm('정보는 저장되지 않습니다.')) {
            location.href = '/main.html';
        }
    })
    console.log(backElem)
    // 회원가입 JSON 생성
    const submitElem = document.querySelector('#submit');
    submitElem.addEventListener('click', ()=>{
        if(!document.querySelectorAll('.err').length) {
            console.log('click')
            let login = new Object();
            login.email = formElem.email.value;
            login.upw = formElem.upw.value;
            login.nm = formElem.nm.value;
            login.gender = formElem.gender.value; // 선택 안함
            login.birthdaymm = formElem.birthdaymm.value === '' ? '00':formElem.birthdaymm.value; // 기본값 00 
            login.birthdaydd = formElem.birthdaydd.value === '' ? '00':formElem.birthdaydd.value; // 기본값 00 
            login.joinpath = formElem.joinpath.value;
            
            let jsonLogin = JSON.stringify(login);
            console.log(jsonLogin);
            insLogin(jsonLogin)
        }
    })
}