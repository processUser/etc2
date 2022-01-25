<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
</head>
<body>
    <div>
        <form action="/result" method="post">
            <label>
                연도 :
                <select name="year">
                    <c:forEach var="yy" begin="2000" end="2020" step="1">
                        <option value="${yy}">${yy}</option>
                    </c:forEach>
                </select>
            </label>
            <label>
                월 :
                <select name="month">
                    <c:forEach var="mm" begin="1" end="12" step="1">
                        <option value="${mm}">${mm}</option>
                    </c:forEach>
                </select>
            </label>
            <label>
                지역 :
                <select name="out_code">
                    <c:forEach var="item" items="${requestScope.location}">
                        <option value="${item.out_code}">${item.nm}</option>
                    </c:forEach>
                </select>
            </label>
            <input type="submit" value="검색">
        </form>
    </div>
    <div>
        <table>
            <tr>
                <th>법정동</th>
                <th>지번</th>
                <th>아파트명</th>
                <th>거래금액</th>
                <th>건축년도</th>
                <th>계약년도</th>
                <th>계약월</th>
                <th>계약일</th>
                <th>전용면적</th>
                <th>층</th>
            </tr>
            <c:forEach var="item2" items="${requestScope.apart}">
                <tr>
                    <td>${item2.dong}</td>
                    <td>${item2.jibun}</td>
                    <td>${item2.apartmentname}</td>
                    <td>${item2.dealamount}</td>
                    <td>${item2.buildyear}</td>
                    <td>${item2.dealyear}</td>
                    <td>${item2.dealmonth}</td>
                    <td>${item2.dealday}</td>
                    <td>${item2.areaforexclusiveuse}</td>
                    <td>${item2.floor}</td>
                </tr>
            </c:forEach>
        </table>


    </div>
</body>
</html>
