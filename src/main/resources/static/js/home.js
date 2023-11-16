const url = "http://localhost:8080/api/test";

window.addEventListener("DOMContentLoaded", (event) => {
    logTest();
});

async function logTest() {
    const response = await fetch(url);
    const data = await response.json();
    console.log(data);
}