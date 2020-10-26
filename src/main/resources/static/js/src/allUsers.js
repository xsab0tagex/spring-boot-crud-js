async function deleteUser(id) {
    await fetch('/user/' + id, {
        method: 'DELETE',
    })
        .then(res => res.text()) // or res.json()
        .then(res => console.log(res))
        refresh();
}

async function adduser(user) {
    await fetch('/user/' , {
        method: 'POST',
    })
        .then(res => res.text()) // or res.json()
        .then(res => console.log(res))
    refresh();
}

jQuery(function ($) {
    refresh();
})

function refresh() {
    document.getElementById("usrs").innerText = "";
    fetch('/users')
        .then(response => response.json())
        .then(users => {
            users.forEach(
                user => {
                    var rl = "";
                    user.authorities.forEach(
                        aut => {
                            rl += aut.name + " ,";
                        }
                    )
                    rl = rl.substring(0,rl.length-2);
                    let uid = user.id;
                    let ln = $('<tr><td>' + uid + '</td>' +
                        '<td>' + user.firstName + '</td>' +
                        '<td>' + user.lastName + '</td>' +
                        '<td>' + user.username + '</td>' +
                        '<td>' + rl + '</td>' +
                        '<td><button id="deleteButton" class="btn btn-info" type="button" onClick="deleteUser(\'' + uid + '\')" >Delete</button></td>' +
                        '<td><button type="button" class="btn btn-info" data-toggle="modal" data-target=".bd-example-modal-lg2" href="/editUser/' + uid  +
                        '">Edit user</button></td>'
                    );
                    $('#usrs').append(ln);
                })
        })
}