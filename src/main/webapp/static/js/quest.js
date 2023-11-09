function setBackgroundImage() {
    var container = document.querySelector('.quest-container');
    var title = container.getAttribute('data-title');

    var lastValidBackground = sessionStorage.getItem('lastValidBackground');

    var imagePath = 'static/images/' + title + '.jpg';
    var image = new Image();
    image.onload = function () {
        sessionStorage.setItem('lastValidBackground', 'url(' + imagePath + ')');
        container.style.backgroundImage = 'url(' + imagePath + ')';
    };
    image.onerror = function () {
        if (lastValidBackground) {
            container.style.backgroundImage = lastValidBackground;
        }
    };
    image.src = imagePath;
}

window.onload = setBackgroundImage;
