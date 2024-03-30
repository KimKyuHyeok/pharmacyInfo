<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.pharmacyinfo.entity.PharmacyResponse" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Pharmacy</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
</head>
<body>
    <div>
        <form action="/loading" method="get">
            <button class="btn btn-warning" type="submit">데이터 가져오기</button>
        </form>
        <button class="btn btn-primary">약국 리스트 조회하기</button>
    </div>

    <table class="table table-bordered" style="font-size: 10px">
        <tr>
            <th>이름</th>
            <th>주소</th>
            <th>Tel.</th>
            <th>월요일</th>
            <th>화요일</th>
            <th>수요일</th>
            <th>목요일</th>
            <th>금요일</th>
            <th>토요일</th>
            <th>일요일</th>
            <th>공휴일</th>
        </tr>
        <% int itemPage = 20; %>
        <% int currentPage = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1; %>
        <% int startIndex = (currentPage - 1) * itemPage; %>
        <% int endIndex = currentPage * itemPage - 1; %>
        <% int total = (int) request.getAttribute("totalCnt"); %>

        <% request.setAttribute("startIndex", startIndex); %>
        <% request.setAttribute("endIndex", endIndex); %>

        <c:forEach var="pharmacy" begin="${startIndex}" end="${endIndex}" items="${list}">
            <tr>
                <td>${pharmacy.name}</td>
                <td>${pharmacy.address}</td>
                <td>${pharmacy.telNumber}</td>
                <td>${pharmacy.mon}</td>
                <td>${pharmacy.tue}</td>
                <td>${pharmacy.wed}</td>
                <td>${pharmacy.thu}</td>
                <td>${pharmacy.fri}</td>
                <td>${pharmacy.sat}</td>
                <td>${pharmacy.sun}</td>
                <td>${pharmacy.hol}</td>
            </tr>
        </c:forEach>
    </table>
    <% int totalPages = (int) Math.ceil((double) total / itemPage); %>
    <div>
        <%-- 이전 페이지 링크 --%>
        <% if (currentPage > 1) { %>
        <a href="?page=<%= currentPage - 1 %>">이전</a>
        <% } %>

        <%-- 페이지 번호 링크 --%>
        <% for (int i = currentPage - 2; i <= currentPage + 2; i++) { %>
            <% if (i <= 0) { continue; } %>
            <% if (i + 3 >= totalPages) { break; } %>
        <a href="?page=<%= i %>"><%= i %></a>
        <% } %>

        <%-- 다음 페이지 링크 --%>
        <% if (currentPage < totalPages) { %>
        <a href="?page=<%= currentPage + 1 %>">다음</a>
        <% } %>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>