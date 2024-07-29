document.querySelectorAll('.heart').forEach(item => {
    item.addEventListener('click', event => {
        const id = item.parentElement.parentElement.children[0].id

        fetch(`api/blog/like?blogId=${id}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content')
            }
        })
            .then(data => {
                if (data.status == 200) {
                    Toastify({
                        text: "Success Liked",
                        duration: 3000,
                        close: true,
                        gravity: "bottom",
                        position: "right",
                        style: {
                            background: "green",
                        },
                    }).showToast();
                } else {
                    Toastify({
                        text: "fail Liked",
                        duration: 3000,
                        close: true,
                        gravity: "bottom",
                        position: "right",
                        style: {
                            background: "red",
                        },
                    }).showToast();
                }
            })
    })
})