<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Manager Homepage</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    </head>
    <body>
        <main class="container">
            <ul class="nav justify-content-center">
                <li class="nav-item">
                    <a class="nav-link" href="manager-home">Homepage</a>
                  </li>
                <li class="nav-item">
                  <a class="nav-link" href="edit-user">Edit User Info</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="logout">Log Out</a>
                </li>
              </ul>
            <h1>Welcome, ${user.firstName} ${user.lastName}</h1>
            <div class="row">
                <div class="col">
                    <h4></h4>
                    <table class="table">
                        <thead>
                           <th>Title</th>
                           <th>Description</th>
                           <th>Status</th>
                           <th>User</th>
                           <th></th>
                        </thead>
                        <tbody>
                            <c:forEach var="request" items="${requests}">
                                <c:if test="${request.status != 'NEW'}">
                                    <tr>
                                        <td><c:out value="${request.title}" /></td>
                                        <td><c:out value="${request.description}" /></td>
                                        <td><c:out value="${request.status}" /></td>
                                        <td><c:out value="${request.user.firstName} ${request.user.lastName}" /></td>
                                        <td><form action="view-request?id=${request.requestID}" method="get">
                                                <input type="hidden" name="id" value="${request.requestID}">
                                                <button class="btn btn-primary" type="submit">View</button>
                                            </form></td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col">
                    <h4>All Employees</h4>
                    <table class="table">
                        <thead>
                            <th>Employee</th>
                        </thead>
                        <tbody>
                            <c:forEach var="emp" items="${employees}">
                                <tr>
                                    <td><a href="manager-home?id=${emp.userID}"><c:out value="${emp.firstName} ${emp.lastName}" /></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
    </body>
</html>