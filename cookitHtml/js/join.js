const reg = {
    email: /^[0-9a-zA-Z]([_]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/
}

window.onload = () => {
    let formElem = document.querySelector('#formWarp');
    let labelWrapElem = formElem.querySelectorAll('.labelWrap');
    console.log(labelWrapElem)
    formElem.email.addEventListener('blur', () => {
        console.log(reg.email.test(formElem.email.value))
        if(!reg.email.test(formElem.email.value)){
            // formElem.email.focus();
            const divElem = document.createElement('div');
            formElem.email.classList.add('errBorder');
            divElem.innerText = '영문 숫자 특수문자( _ )만 입력가능합니다.';
            divElem.classList.add('err');
            divElem.style.color = 'red'
            labelWrapElem[0].append(divElem)

        } else{
            const divElem = document.querySelector('.err');
            formElem.email.classList.remove('errBorder');
            divElem.remove();
        }
    })
}