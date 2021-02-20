<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>CRUD</title>
    <link rel="stylesheet" href="style/style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="content">
    <div class="row">
        <div class="col-lg">
            <H4>Name: </H4>
            <input type="text" id="name" class="input2" value=" ">
            <br>
            <H4>Last Name: </H4>
            <input type="text" id="last_name" class="input2" value=" ">
            <br>
            <H4>Faculty: </H4>
            <input type="text" id="faculty" class="input2" value=" ">
            <br>
            <H4>Subject: </H4>
            <input type="text" id="subject" class="input2" value=" ">
            <br>
            <button onclick="addJson()" id="addButton">Add</button>
            <br>
            <button onclick="load()" id="updateButton">Show/Update</button>
        </div></div>
</div>
<div class="listContent">
    <p>
    <div id="text"></div>
    </p>

    <ul id="list">
        <li></li>
    </ul>
</div>
<script>

    function addJson() {
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'Main');
        var json = '';

        xhr.onreadystatechange = function () {
            if (xhr.readyState != 4) return;
            if (xhr.status != 200) {
                // обработать ошибку
                alert(xhr.status + ': ' + xhr.statusText);
            }
            ;
        }
        json = {
            'name': document.getElementById("name").value,
            'last_name': document.getElementById("last_name").value,
            'faculty': document.getElementById("faculty").value,
            'subject': document.getElementById("subject").value
        };

        xhr.send("add = "+JSON.stringify(json));
    }

    function load() {

        var xhr = new XMLHttpRequest();

        xhr.open('GET', 'Main', true);

        xhr.onreadystatechange = function () {
            if (xhr.readyState != 4) return;
            if (xhr.status != 200) {
                // обработать ошибку
                alert(xhr.status + ': ' + xhr.statusText);
            } else {
                try {
                    var yarn = JSON.parse(xhr.responseText);
                } catch (e) {
                    alert("Некорректный ответ " + e.message);
                }
                show(yarn)
            }
            ;
        }

        xhr.send();

    }

    function show(teacher) {
        var li = list;
        while (li.firstChild) {
            li.removeChild(li.firstChild);
        }
        var i = 0;
        teacher.forEach(function (teacher) {
            i++;
            li = list.appendChild(document.createElement('li'));
            /*li.innerHTML = i + '.    Color: ' + yarn.yarnColor + '      Material: ' + yarn.material + '   Width: ' + yarn.width
                + '     Price: ' + yarn.price + '$';
             */
            let name = li.appendChild(document.createElement('input')).id ="name" + teacher.id ;
            let last_name = li.appendChild(document.createElement('input')).id = "last_name" + teacher.id;
            let faculty = li.appendChild(document.createElement('input')).id = "faculty" + teacher.id;
            let subject = li.appendChild(document.createElement('input')).id = "subject" + teacher.id ;
            document.getElementById("name" + teacher.id).value = teacher.name;
            document.getElementById("last_name" + teacher.id).value = teacher.last_name;
            document.getElementById("faculty" + teacher.id).value = teacher.faculty;
            document.getElementById("subject" + teacher.id ).value = teacher.subject;
            let update = li.appendChild(document.createElement('input'));
            update.type = "button";
            update.style.color="#e52165";
            update.style.width ="100px";
            update.style.height ="50px";
            update.onclick = function () {
                updateT(teacher.id);
            };
            update.value = "UPDATE";
            let button = li.appendChild(document.createElement('input'));
            button.type = "button";
            update.style.color="#e52165";
            button.style.width ="100px";
            button.style.height ="50px";
            button.onclick = function () {
                deleteT(teacher.id)
            };
            button.value = "DELETE";
        });
    }


    function deleteT(id) {
        var xhr = new XMLHttpRequest();
        xhr.open('POST','Main');
        xhr.onreadystatechange = function () {
            if (xhr.readyState != 4) return;
            if (xhr.status != 200) {
                // обработать ошибку
                alert(xhr.status + ': ' + xhr.statusText);
            }
            ;
        }
        xhr.send("delete = "+id);
    }

    function updateT(id) {
        var xhr = new XMLHttpRequest();
        xhr.open('POST','Main');
        //alert(id);
        xhr.onreadystatechange = function () {
            if (xhr.readyState != 4) return;
            if (xhr.status != 200) {
                // обработать ошибку
                alert(xhr.status + ': ' + xhr.statusText);
            }
            ;
        }
        let json = {
            'id': id,
            'name': document.getElementById("name" + id).value,
            'last_name': document.getElementById("last_name" + id).value,
            'faculty': document.getElementById("faculty" + id).value,
            'subject': document.getElementById("subject" + id).value
        };
        xhr.send("update = " +JSON.stringify(json));
    }
</script>
</body>
</html>