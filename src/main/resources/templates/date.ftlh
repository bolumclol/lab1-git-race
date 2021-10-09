<#-- @ftlvariable name="currentDate" type="java.lang.String" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Date</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          crossorigin="anonymous">
</head>
<body>
<div class="px-4 py-5 my-5 text-center">
    <h1 class="display-5 fw-bold">Today is ...</h1>
    <div class="d-flex justify-content-center">
        <div class="card" style="width: 24rem; background-color:lightblue;">
            <div class="card-body">
                <h1 class="card-title">${currentDate}</h1>
            </div>
        </div>
    </div>
    <p class="lead mb-4"></p>
    <a class="btn btn-primary" href="/" role="button">Go back</a>
</div>
</body>
</html>