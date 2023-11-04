const btn = document.getElementById('startButton');
const maxMoves = 3;
let moveCount = 0;

btn.addEventListener('mouseover', function (event) {
    if (moveCount++ >= maxMoves || event.shiftKey) {
        btn.style.position = 'static';
        return;
    }

    if (moveCount === 1) {
        btn.style.position = 'absolute';
    }
    const maxX = window.innerWidth - btn.clientWidth;
    const maxY = window.innerHeight - btn.clientHeight;

    const randomX = Math.random() * maxX;
    const randomY = Math.random() * maxY;

    btn.style.left = `${randomX}px`;
    btn.style.top = `${randomY}px`;
});
