<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>View Request</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    </head>
    <body>
        <main class="container">
                    <ul class="nav justify-content-center">
                        <li class="nav-item">
                            <c:if test="${isManager == true}">
                                <a class="nav-link" href="manager-home">Homepage</a>
                            </c:if>
                            <c:if test="${isManager == false}">
                                <a class="nav-link" href="employee-home">Homepage</a>
                            </c:if>
                          </li>
                        <li class="nav-item">
                          <a class="nav-link" href="edit-user">Edit User Info</a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="logout">Log Out</a>
                        </li>
                      </ul>
            <h1>View Request</h1>
            <c:if test="${isManager == true && request.status == 'PENDING'}">
                <a class="btn btn-primary" role="button" href="approve?id=${request.requestID}">approve</a>
                <a class="btn btn-danger" role="button" href="deny?id=${request.requestID}">deny</a>
            </c:if>
            <c:if test="${request.status == 'NEW'}">
                <a class="btn btn-primary" href="submit?id=${request.requestID}">submit</a>
            </c:if>
            <table>
                <tbody>
                    <tr>
                        <td>Title:</td>
                        <td><c:out value="${request.title}" /></td>
                    </tr>
                    <tr>
                        <td>Description:</td>
                        <td><c:out value="${request.description}" /></td>
                    </tr>
                    <tr>
                        <td>Comments:</td>
                        <td><c:out value="${request.requestComments}" /></td>
                    </tr>
                    <tr>
                        <td>Date Created:</td>
                        <td><c:out value="${request.dateSubmitted}" /></td>
                    </tr>
                    <tr>
                        <td>Status:</td>
                        <td><c:out value="${request.status}" /></td>
                    </tr>
                    <tr>
                        <td>Created By:</td>
                        <td><c:out value="${request.user.firstName} ${request.user.lastName}" /></td>
                    </tr>
                </tbody>
            </table>
            <h1 class="mt-4">Items</h1>
            <c:if test="${request.status == 'NEW' && isManager == false}">
                <form action="add-item?id=${request.requestID}" method="get">
                    <input type="hidden" name="id" value="${request.requestID}">
                    <button class="btn btn-primary" type="submit">Add an item</button>
                </form>
            </c:if>
            <table class="table">
                <thead>
                    <tr>
                        <th>Type</th>
                        <th>Description</th>
                        <th>Amount</th>
                        <th>Comments</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${items}">
                        <tr>
                            <td><c:out value="${item.itemType}" /></td>
                            <td><c:out value="${item.description}" /></td>
                            <td><c:out value="${item.amount}"/></td>
                            <td><c:out value="${item.itemComments}"/></td>
                            <c:if test="${request.status == 'NEW' && isManager == false}">
                                <td><a href="edit-item?id=${item.requestItemID}">edit</a></td>
                                <td><a href="delete-item?id=${item.requestItemID}">delete</a></td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </main>
    </body>
</html>