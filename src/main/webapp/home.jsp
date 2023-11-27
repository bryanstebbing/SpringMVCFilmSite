<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Film Site</title>
</head>
<body>

	<h1>Welcome to Bryan's Film Site</h1>

	<h3>By ID</h3>
	<form action="GetFilmByID.do" method="GET">
		ID: <input type="text" name="filmId" /> <input type="submit"
			value="filmId" />
	</form>
	<br>
	<h3>By Keyword</h3>
	<form action="GetFilmByKeyword.do" method="GET">
		Keyword: <input type="text" name="keyword" /> <input type="submit"
			value="keyword" />
	</form>

</body>
</html>