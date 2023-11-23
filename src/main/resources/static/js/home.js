const url = "http://localhost:8080/api/chartData";

window.addEventListener("DOMContentLoaded", (event) => {
    let chartData = null;
    getChartData()
    .then((data) => {
        chartData = Array.from(data).map(el => ({
            y: el.sum,
            label: el.desc
        }));
        var chart = new CanvasJS.Chart("chart", {
            backgroundColor: "#346eeb",
            exportEnabled: true,
            animationEnabled: true,
            animationDuration: 500,
            title: {
                text: "Расходы за последний месяц",
                fontFamily: "Segoe UI",
                fontColor: "white",
                fontWeight: "bold"
            },
            data: [{
                type: "pie",
                startAngle: 240,
                indexLabel: "{label} - {y}",
                yValueFormatString: "##0.00\"руб.\"",
                indexLabelFontFamily: "Segoe UI",
                indexLabelFontColor: "white",
                dataPoints: chartData
            }]
        });
        chart.render();    
    });
});



async function getChartData() {
    const response = await fetch(url);
    const data = await response.json();
    return data;
}