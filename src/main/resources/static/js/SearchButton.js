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
                container.className = 'd-flex border border-top-0';
                container.appendChild(img);
                container.appendChild(text);
                usersField.appendChild(container);
            })
        })
}
