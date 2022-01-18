
    fetch('http://localhost:8090/goodslist',{
        'method': 'post',
        'headers': {'Content-Type': 'application/json'},
        'body': JSON.stringify({
            categorypk: 0
        })
    }).then(res =>{ 
        return res.json(); 
    } ).then(data => {
        console.log(data)
        goodsList(data)
    }).catch(e => {
        console.log(e);
    });

    //http://placehold.it/150x150 - 이미지 대체

    function goodsList(data) {
        let jsonlist = data.list;
        
        const listwarpElem = document.getElementById('listwarp');
        jsonlist.forEach((items) => {  // 전체 수 만큼 반복.
            console.log('items : ' + items)
            const articleElem = document.createElement('article');
            const imgElem = document.createElement('img');
            const divElem = document.createElement('div');
            const spanElem1 = document.createElement('span');
            const spanElem2 = document.createElement('span');
            const pElem1 = document.createElement('p');
            const pElem2 = document.createElement('p');
            
            imgElem.src = 'http://placehold.it/268x320'
            imgElem.alt = "사진"

            pElem1.innerText = items.gnm;
            pElem1.dataset.gnum = items.gnum;
            pElem2.innerText = items.price;

            spanElem1.innerText = items.reviewvo.avgscore;
            spanElem2.innerText = '리뷰 ' + items.reviewvo.countscore;
            spanElem2.style.marginLeft = '15px'

            listwarpElem.append(articleElem);
            articleElem.append(imgElem)
            articleElem.append(pElem1)
            articleElem.append(pElem2)
            articleElem.append(divElem)
            divElem.append(spanElem1);
            divElem.append(spanElem2);
        });
        console.log(data.list);
        /*
        let jsonlist = data.list[1];
        for(var val in jsonlist) {
            console.log(jsonlist[val]);
        }
        */
    }
