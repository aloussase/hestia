let wantsReload = localStorage.getItem('reload');

if (wantsReload === '' || wantsReload === undefined || wantsReload == null) {
    wantsReload = true;
} else {
    wantsReload = JSON.parse(wantsReload)
}

const reloadButton = document.getElementById("reload")

reloadButton?.addEventListener("click", () => {
    localStorage.setItem("reload", JSON.stringify(!wantsReload))
    window.location.reload();
})


if (reloadButton) {
    reloadButton.checked = wantsReload
}

if (wantsReload) {
    setTimeout(
        () => window.location.reload(),
        10000
    );
}

