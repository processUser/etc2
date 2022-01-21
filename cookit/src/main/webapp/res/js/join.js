

window.onload = () => {
    let formElem = document.querySelector('#formWarp');
    let labelWrapElem = formElem.querySelectorAll('.labelWrap');
    //console.log();

    const cookieValue = document.cookie.split('; ').find(row => row.startsWith('agree')).split('=')[1];
    console.log(JSON.parse(decodeURI(cookieValue)));

    // 이메일 체크
    formElem.email.addEventListener('blur', () => {
        //console.log(reg.email.test(formElem.email.value))
        if(!reg.email.test(formElem.email.value)){ // 정규식과 일치 안한다.
            // formElem.email.focus();
            //console.log(document.querySelector('.err'))
            if(!labelWrapElem[0].querySelector('.err')){
                const divElem = document.createElement('div');
                formElem.email.classList.add('errBorder');
                divElem.innerHTML = 'email을 입력해주세요.<br>영문, 숫자, 특수문자( _ ) 사용 가능합니다.';
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

    //생일 월 선택시 -> 일 필수 입력
    formElem.birthdaymm.addEventListener('blur', () => {
        if(formElem.birthdaydd.value === ''){
            if(!labelWrapElem[4].querySelector('.info')){
                const divElem = document.createElement('div');
                formElem.birthdaydd.classList.add('errBorder');
                divElem.innerHTML = '"월" 선택시 "일"은 필수입니다.';
                divElem.classList.add('info');
                divElem.style.color = 'red'
                labelWrapElem[4].append(divElem)
            }
        } else{
            const divElem = labelWrapElem[4].querySelector('.info');
            formElem.birthdaydd.classList.remove('errBorder');
            if(divElem)
                divElem.remove();
        }
        console.log(reg.dd.test(formElem.birthdaydd.value))
    });

    //생일 일 확인 
    formElem.birthdaydd.addEventListener('blur', () => {
        if(!reg.dd.test(formElem.birthdaydd.value)){
            if(!labelWrapElem[4].querySelector('.err')){
                const divElem = document.createElement('div');
                formElem.birthdaydd.classList.add('errBorder');
                divElem.innerHTML = '숫자 2자리 (ex. 01) ';
                divElem.classList.add('err');
                divElem.style.color = 'red'
                labelWrapElem[4].append(divElem)
            }
        } else{
            const divElem1 = labelWrapElem[4].querySelector('.err');
            const divElem2 = labelWrapElem[4].querySelector('.info');
            formElem.birthdaydd.classList.remove('errBorder');
            if(divElem2 || divElem1){
                divElem1.remove();
                divElem2.remove();
            }
        }
        console.log(reg.dd.test(formElem.birthdaydd.value))
    });

    // 취소하기
    const backElem = document.querySelector('#back');
    backElem.addEventListener('click', ()=>{
        if(confirm('정보는 저장되지 않습니다.')) {
            location.href = '/main';
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
            login.pw = formElem.upw.value;
            login.nm = formElem.nm.value;
            login.gender = formElem.gender.value === '' ? '03':formElem.gender.value; // 선택 안함
            login.birthdaymm = formElem.birthdaymm.value === '' ? '00':formElem.birthdaymm.value; // 기본값 00 
            login.birthdaydd = formElem.birthdaydd.value === '' ? '00':formElem.birthdaydd.value; // 기본값 00 
            login.joinpath = formElem.joinpath.value;
            login.agree = JSON.parse(decodeURI(cookieValue));
            
            let jsonLogin = JSON.stringify(login);
            console.log(jsonLogin);
            insLogin(jsonLogin)
        }
    })
}