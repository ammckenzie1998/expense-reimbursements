<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Item</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
</head>
<body>
    <div class="container">
        <ul class="nav justify-content-center">
            <li class="nav-item">
                <a class="nav-link" href="employee-home">Homepage</a>
              </li>
            <li class="nav-item">
              <a class="nav-link" href="edit-user">Edit User Info</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="logout">Log Out</a>
            </li>
          </ul>
        <h1>Edit Item</h1>
        <form class="form" action="edit-item" method="post">
            <input type="hidden" name="id" value="${item.requestItemID}">
            <div class="form-group">
                <label for="type">Type</label>
                <input class="form-control" type=text id="type" name="type" value="${item.itemType}" required/>
            </div>
            <div class="form-group">
                <label for="desc">Description</label>
                <input class="form-control" type=text id="desc" name="desc" value="${item.description}"/>
            </div>
            <div class="form-group">
                <label for="amt">Amount</label>
                <input class="form-control" type="number" id="amt" name="amt" value="${item.amount}"/>
            </div>
            <div class="form-group">
                <label for="comments">Comments</label>
                <input class="form-control" type="text" id="comments" name="comments" value="${item.itemComments}" required/>
            </div>
            <div class="form-group">
                <button class="btn btn-primary" type="submit">Submit</button>
            </div>
        </form>
    </div>
</body>
</html>