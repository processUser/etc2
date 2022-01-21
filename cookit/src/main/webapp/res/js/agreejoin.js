window.onload = () => {
    const agreeform = document.querySelector('#agreeform');

    // submit 버튼 이벤트
    agreeform.submit.addEventListener('click', (e)=>{
        if(!agreeform.termsOfUse.checked || !agreeform.userInformation.checked || !agreeform.age.checked || !agreeform.notRealpage.checked){
            agreeform.termsOfUse.parentNode.style.color = 'red';
            agreeform.userInformation.parentNode.style.color = 'red';
            agreeform.age.parentNode.style.color = 'red';
            agreeform.notRealpage.parentNode.style.color = 'red';
            e.preventDefault()
        }else{
            //db 값 넣기 작업.
            let param = {
                'termsOfUse': agreeform.termsOfUse.value,
                userInformation: agreeform.userInformation.value,
                marketing: agreeform.marketing.value,
                age: agreeform.age.value,
                notRealpage: agreeform.notRealpage.value
            }
            ;
            agreeCookie('agree',JSON.stringify(param), '/', 1); // 1일짜리 쿠키
            location.href='/join';
        }
    })

    // 전체 동의 이벤트
    agreeform.all.addEventListener('click', () => {
        const checkbox = document.querySelectorAll('input[type="checkbox"]')
        if(agreeform.all.checked){
            checkbox.forEach((item) =>{
                item.checked = true
            })
        } else(
            checkbox.forEach((item) =>{
                item.checked = false
            })
        )
    });

    //선택 항목 동의 이벤트
    agreeform.marketing.addEventListener('click', () =>{
        if(!agreeform.marketing.checked || !agreeform.termsOfUse.checked || !agreeform.userInformation.checked || !agreeform.age.checked || !agreeform.notRealpage.checked){
            agreeform.all.checked = false
        } else{
            agreeform.all.checked = true
        }
    });

    //동의 버튼 클릭시 쿠키생성.
    function agreeCookie(cKey, cName, cPath, day) {
        var date = new Date();
        date.setTime(date.getTime() + (day * 24 * 60 * 60 * 1000)); 
        cookies = cKey +'='+ encodeURI(cName) +'; path='+ cPath +'; expires='+ date;
        document.cookie = cookies;
    }
}