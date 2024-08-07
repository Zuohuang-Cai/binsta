const codes = document.querySelectorAll(".codes")
const renderer = new marked.Renderer();
renderer.blockquote = (quote) => {
    return `<blockquote>${quote}</blockquote>`;
};

codes.forEach((code) => {
    code.innerHTML = marked.parse(code.textContent.replace(/^[\u200B\u200C\u200D\u200E\u200F\uFEFF]/,""), {renderer: renderer});
    hljs.highlightBlock(code);
    code.classList.remove("visually-hidden")
});