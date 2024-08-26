document.getElementById('searchIcon').addEventListener('click', function () {
    fetchContent();
});

document.getElementById('searchText').addEventListener('keyup', function (event) {
    fetchContent();
})

function fetchContent() {
    fetch('api/search-user?username=' + document.getElementById('searchText').value)
        .then(response => response.json())
        .then(result => {
            const usersField = document.querySelector('#usersfield');
            while (usersField.firstChild) {
                usersField.removeChild(usersField.firstChild);
            }
            result.data.forEach(user => {
                let container = document.createElement('div');
                let text = document.createElement('a');
                let img = document.createElement('img');

                img.src = "data:image/jpeg;base64," + user.avatar;
                img.width = 30;
                text.textContent = user.nickname;
                text.href = `http://127.0.0.1:8080/user/profile?username=${user.username}`;
                text.classList.add('text-dark');
                text.classList.add('text-decoration-none');
                container.className = 'd-flex border border-top-0';
                container.appendChild(img);
                container.appendChild(text);
                usersField.appendChild(container);
            })
        })
}

