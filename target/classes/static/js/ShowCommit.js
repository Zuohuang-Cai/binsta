function ShowCommit(event) {
    event.preventDefault();
    const commitField = event.target.parentElement.parentElement.parentElement.querySelector('.commitsField');
    const svgs = event.target.parentElement.querySelectorAll("svg")
    svgs.forEach(svg => {
        if (svg.classList.contains('visually-hidden')) {
            svg.classList.remove('visually-hidden')
            commitField.classList.remove('visually-hidden');
        } else {
            svg.classList.add('visually-hidden')
            commitField.classList.add('visually-hidden')
        }
    })
}