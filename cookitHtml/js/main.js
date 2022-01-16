// 슬라이더 이미지 삽입
let ul = document.querySelector('.slideWrap').childNodes.item(1)
let li = ul.children
for(let i = 0; i < li.length; i++){
    // console.log(li[i].childNodes[1])
    li[i].style.backgroundImage = 'url(/img/main/slide/s'+(i+1)+'.jpg)'
}
// 추천 메뉴 넓이
let section3 = document.querySelectorAll('.section3 article ul')
let section3liW = section3[1].children.item(1)
console.log(section3)
for(let i = 0; i < section3.length; i++){
    console.log(((section3[i].children.item(0)).offsetWidth))
    section3[i].style.width = (section3[i].children.length * (section3[i].children.item(0)).offsetWidth)+'px'
}

