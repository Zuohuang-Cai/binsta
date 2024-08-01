async function createCommit(event) {
    event.preventDefault();

    const formDiv = event.target.closest('.comment-form');
    const blogId = formDiv.querySelector('input[name="blogId"]').value;
    const description = formDiv.querySelector('input[name="description"]').value;

    try {

        event.target.parentElement.parentElement.querySelector('.commitsField').prepend(await createCommitElement(description));

        const response = await fetch(`api/blog/commit?blogId=${blogId}&description=${description}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content')
            }
        });

        if (response.redirected) {
            window.location.href = response.url;
        } else if (response.ok) {
            Toastify({
                text: "Comment successfully posted!",
                duration: 3000,
                close: true,
                gravity: "bottom",
                position: "right",
                style: {
                    background: "green",
                },
            }).showToast();
            formDiv.querySelector('input[name="description"]').value = '';
        } else {
            const errorText = await response.text();
            throw new Error(`Network response was not ok. Status: ${response.status}. Response: ${errorText}`);
        }
    } catch (error) {
        Toastify({
            text: "Failed to post comment.",
            duration: 3000,
            close: true,
            gravity: "bottom",
            position: "right",
            style: {
                background: "red",
            },
        }).showToast();
    }
}


async function createCommitElement(des) {

    let commit = await fetch(`/api/current-user`)

    if (commit.redirected) {
        window.location.href = commit.url;
    }

    commit = await commit.json()

    const container = document.createElement('div');
    container.className = 'border-bottom';

    const header = document.createElement('div');
    header.className = 'd-flex border-top';

    const img = document.querySelector('#avatar').cloneNode(true);
    img.style.height = '20px';
    img.className = 'm-2';

    const nickname = document.createElement('span');
    nickname.className = 'm-2 fw-bolder';
    nickname.textContent = commit.nickname;

    const date = document.createElement('span');
    date.className = 'm-2';
    date.textContent = getCurrentDate();

    const description = document.createElement('p');
    description.className = 'mx-5';
    description.textContent = des

    header.appendChild(img);
    header.appendChild(nickname);
    header.appendChild(date);

    container.appendChild(header);
    container.appendChild(description);
    return container;
}

function getCurrentDate() {
    let now = new Date();

    let year = now.getFullYear();
    let month = String(now.getMonth() + 1).padStart(2, '0'); // 月份从0开始，需要加1
    let day = String(now.getDate()).padStart(2, '0');
    let hours = String(now.getHours()).padStart(2, '0');
    let minutes = String(now.getMinutes()).padStart(2, '0');

    return `${year}-${month}-${day} ${hours}:${minutes}`;
}

function binaryToBase64(binary) {
    return btoa(
        binary
            .split('')
            .map((char) => String.fromCharCode(char.charCodeAt(0) & 0xff))
            .join('')
    );
}