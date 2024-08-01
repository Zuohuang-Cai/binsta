document.querySelectorAll('.heart').forEach(item => {
    item.addEventListener('click', event => {
        let likesFiled = event.target.parentElement.parentElement.children[2]
        const Bid = item.parentElement.parentElement.children[0].id
        if (likesFiled.querySelector("svg").style.color === "red") {
            fetch(`api/blog/like?blogId=${Bid}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                    'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content')
                }
            })
                .then(data => {
                    if (data.status === 200) {
                        likesFiled.querySelector("svg").style.color = "black"
                        // count of likes -1
                        likesFiled.querySelector("span").innerText = parseInt(likesFiled.querySelector("span").innerText) - 1
                        Toastify({
                            text: "Success Unliked",
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
                            text: "fail Unliked",
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
        } else if (likesFiled.querySelector("svg").style.color === "black") {
            fetch(`api/blog/like?blogId=${Bid}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content')
                }
            })
                .then(data => {
                    if (data.status === 200) {
                        likesFiled.querySelector("svg").style.color = "red"
                        // count of likes +1
                        likesFiled.querySelector("span").innerText = parseInt(likesFiled.querySelector("span").innerText) + 1
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
        }
    })
})

fetch('api/current-user-likes')
    .then(response => response.json())
    .then(response => {
        response.data.forEach(like => {
            let likesFiled = document.getElementById(like.blogId).parentElement.children[2]
            likesFiled.querySelector("svg").style.color = "red"
        })
    })