const BASE_URL = "http://localhost:8080/api/user";

let tagChart, difficultyChart;

async function analyze() {

    const handle = document.getElementById("handle").value;

    if (!handle) return alert("Enter handle");

    document.getElementById("loader").classList.remove("hidden");
    document.getElementById("result").classList.add("hidden");

    try {

        await fetch(`${BASE_URL}/${handle}/refresh`);

        const [predRes, tagRes, diffRes] = await Promise.all([
            fetch(`${BASE_URL}/${handle}/prediction`),
            fetch(`${BASE_URL}/${handle}/tags`),
            fetch(`${BASE_URL}/${handle}/difficulty`)
        ]);

        const prediction = await predRes.json();
        const tags = await tagRes.json();
        const difficulty = await diffRes.json();

        // Rating
        document.getElementById("current").innerText = prediction.currentRating;
        document.getElementById("predicted").innerText = prediction.predictedRating;

        // Suggestions
        const sugList = document.getElementById("suggestions");
        sugList.innerHTML = "";
        tags.suggestions.forEach(s => {
            let li = document.createElement("li");
            li.innerText = s;
            sugList.appendChild(li);
        });

        // Tag Chart
        const tagLabels = Object.keys(tags.accuracy);
        const tagData = Object.values(tags.accuracy).map(v => v * 100);

        if (tagChart) tagChart.destroy();

        tagChart = new Chart(document.getElementById("tagChart"), {
            type: 'bar',
            data: {
                labels: tagLabels,
                datasets: [{
                    label: 'Accuracy %',
                    data: tagData
                }]
            }
        });

        // Difficulty Chart
        const diffLabels = Object.keys(difficulty);
        const diffData = Object.values(difficulty).map(v => v * 100);

        if (difficultyChart) difficultyChart.destroy();

        difficultyChart = new Chart(document.getElementById("difficultyChart"), {
            type: 'line',
            data: {
                labels: diffLabels,
                datasets: [{
                    label: 'Success %',
                    data: diffData
                }]
            }
        });

        document.getElementById("loader").classList.add("hidden");
        document.getElementById("result").classList.remove("hidden");

    } catch (err) {
        alert("Error fetching data");
        console.error(err);
    }
}