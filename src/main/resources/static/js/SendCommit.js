function createCommit(event) {
    event.preventDefault();

    const formDiv = event.target.closest('.comment-form');
    const blogId = formDiv.querySelector('input[name="blogId"]').value;

    const description = formDiv.querySelector('input[name="description"]').value;

    fetch(`/blog/commit?blogId=${blogId}&description=${description}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content')
        }
    })
        .then(response => {
            if (response.url.includes('login')) {
                window.location.href = '/user/login?authentication';
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
                throw new Error('Network response was not ok.');
            }
        })
        .catch((error) => {
            console.error('Error:', error);
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
        });
}