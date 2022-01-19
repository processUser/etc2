window.onload = () => {
    const agreeform = document.querySelector('#agreeform');

    agreeform.submit.addEventListener('click', (e)=>{
        if(!agreeform.termsOfUse.checked || !agreeform.personalInformation.checked || !agreeform.age.checked || !agreeform.making.checked){
            agreeform.termsOfUse.parentNode.style.color = 'red';
            agreeform.personalInformation.parentNode.style.color = 'red';
            agreeform.age.parentNode.style.color = 'red';
            agreeform.making.parentNode.style.color = 'red';
            e.preventDefault()
        }else{
            //db 값 넣기 작업.
            location.href='/join.html'
        }
    })

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

    agreeform.marketing.addEventListener('click', () =>{
        if(!agreeform.marketing.checked || !agreeform.termsOfUse.checked || !agreeform.personalInformation.checked || !agreeform.age.checked || !agreeform.making.checked){
            agreeform.all.checked = false
        } else{
            agreeform.all.checked = true
        }
    });
}