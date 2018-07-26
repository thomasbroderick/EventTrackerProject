window.addEventListener('load', function(e) {
	console.log('document loaded');
	getRounds();
	console.log("listener loaded");
	document.newRoundForm.roundSubmit.addEventListener("click",
			function(event) {
				event.preventDefault();
				postRound();
			});
	

	
function displayRounds(rounds) {
	var roundTable = document.getElementById("roundTable");
	roundTable.textContent = "";
	var table = document.createElement("table");
	var thead = document.createElement("thead");
	var headerRow = document.createElement("tr");
	var headerOpp = document.createElement("th");
	headerOpp.textContent = "Opponent Name";
	var headerRank = document.createElement("th");
	headerRank.textContent = "Rank";
	var headerDate = document.createElement("th");
	headerDate.textContent = "Date";
	var headerPoints = document.createElement("th");
	headerPoints.textContent = "Points Scored";
	var headerResult = document.createElement("th");
	headerResult.textContent = "Result";

	var tbody = document.createElement("tbody");
	rounds.forEach(function(element) {
		var tr = document.createElement("tr");
		var tdOpponent = document.createElement("td");
		tdOpponent.textContent = element.opponentName;
		tr.appendChild(tdOpponent);
		var tdRank = document.createElement("td");
		tdRank.textContent = element.opponentRank;
		tr.appendChild(tdRank);
		var tdDate = document.createElement("td");
		tdDate.textContent = element.date.toString().substring(0, 10);
		tr.appendChild(tdDate);
		var tdPoints = document.createElement("td");
		tdPoints.textContent = element.pointsScored;
		tr.appendChild(tdPoints);
		var tdResult = document.createElement("td");
		tdResult.textContent = element.result;
		tr.appendChild(tdResult);
		tr.addEventListener("click", function(e) {
			displayRoundDetails(element);
		})
		tbody.appendChild(tr);
	});

	headerRow.appendChild(headerOpp);
	headerRow.appendChild(headerRank);
	headerRow.appendChild(headerDate);
	headerRow.appendChild(headerPoints);
	headerRow.appendChild(headerResult);
	thead.appendChild(headerRow);

	table.appendChild(thead);
	table.appendChild(tbody);
	roundTable.appendChild(table);

}

function getRounds() {
	var xhr = new XMLHttpRequest();

	xhr.open('GET', "api/rounds", true);

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status < 400) {
			var data = JSON.parse(xhr.responseText);
			console.log(data);
			displayRounds(data);
			var analysisButton = document.getElementById("dataAnalysis");
			analysisButton.addEventListener("click", function(event) {
				event.preventDefault();
				analyzeData(data);
			});

		}

		if (xhr.readyState === 4 && xhr.status >= 400) {
			console.error(xhr.status + ': ' + xhr.responseText);
			var dataDiv = document.getElementById('roundTable');
			dataDiv.textContent = 'Rounds not loaded';

		}
	};

	xhr.send(null);

}

function postRound() {
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'api/rounds', true);

	xhr.setRequestHeader("Content-type", "application/json"); // Specify JSON
																// request body

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status == 200 || xhr.status == 201) { // Ok or Created
				var data = JSON.parse(xhr.responseText);
				console.log(data);
				getRounds();
			} else {
				console.log("POST request failed.");
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}
	};
	var nff = document.getElementsByName("newRoundForm")[0];
	var round = {
		opponentName : nff.opponentName.value,
		opponentRank : nff.opponentRank.value,
		date : nff.date.value,
		pointsScored : nff.pointsScored.value,
		result : nff.result.value
	};
	console.log(round);

	var roundObjectJson = JSON.stringify(round); // Convert JS object to JSON
													// string

	xhr.send(roundObjectJson);
	nff.reset();
}

function displayRoundDetails(round) {
	console.log(round);
	var detailsDiv = document.getElementById("roundDetails");
	var h2 = document.createElement("h2");
	h2.textContent = "Round Details";
	detailsDiv.appendChild(h2);
	var updateForm = document.createElement("form");
	updateForm.name = "updateForm";
	var labels = ["Opponent Name", "Opponent Rank", "Date", "Points Scored", "Result"];
	var counter = 0;
	for (element in round) {
		if (element !== "id") {
			console.log(round[element]);
			var label = document.createElement("label");
			label.for = element;
			label.textContent = labels[counter] + "\t";
			counter++;
			var input = document.createElement("input");
			input.id = element;
			input.value = round[element];
			label.appendChild(input);
			updateForm.appendChild(label);
			var br = document.createElement("br");
			updateForm.appendChild(br);
			
		}
		else {
			var input = document.createElement("input");
			input.id = element;
			input.value = round[element];
			input.type = "hidden";
			updateForm.appendChild(input);
		}
		}
	var inputUpdate = document.createElement("input");
	inputUpdate.type = "submit";
	inputUpdate.name = "updateSubmit";
	inputUpdate.value = "Update"
	updateForm.appendChild(inputUpdate);
	
	var inputDelete = document.createElement("input");
	inputDelete.type = "submit";
	inputDelete.name = "deleteSubmit";
	inputDelete.value = "Delete"
	updateForm.appendChild(inputDelete);
	
	detailsDiv.appendChild(updateForm);
	
	document.updateForm.updateSubmit.addEventListener("click", function(event) {
		event.preventDefault();
		updateRound();
		var detailsDiv = document.getElementById("roundDetails");
		detailsDiv.textContent = "";

});
	document.updateForm.deleteSubmit.addEventListener("click", function(event) {
		event.preventDefault();
		deleteRound();
		var detailsDiv = document.getElementById("roundDetails");
		detailsDiv.textContent = "";

});
	
	}

function updateRound() {
	var nff = document.getElementsByName("updateForm")[0];
	var round = {
		id: nff.id.value, 
		opponentName : nff.opponentName.value,
		opponentRank : nff.opponentRank.value,
		date : nff.date.value,
		pointsScored : nff.pointsScored.value,
		result : nff.result.value
	};
	console.log(round);
	
	var xhr = new XMLHttpRequest();
	xhr.open('PATCH', `api/rounds/${round.id}`, true);

	xhr.setRequestHeader("Content-type", "application/json"); // Specify JSON
																// request body

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status == 200 || xhr.status == 201) { // Ok or Created
				var data = JSON.parse(xhr.responseText);
				console.log(data);
				getRounds();
			} else {
				console.log("PATCH request failed.");
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}
	};
	

	var roundObjectJson = JSON.stringify(round); // Convert JS object to JSON
													// string

	xhr.send(roundObjectJson);
}


function deleteRound() {
	var nff = document.getElementsByName("updateForm")[0];
	var round = {
		id: nff.id.value, 
	};
	console.log(round);
	
	var xhr = new XMLHttpRequest();
	xhr.open('DELETE', `api/rounds/${round.id}`, true);

	xhr.setRequestHeader("Content-type", "application/json"); // Specify JSON
																// request body

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status == 200 || xhr.status == 201) { // Ok or Created
				
				getRounds();
			} else {
				console.log("PATCH request failed.");
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}
	};
	xhr.send(null);
}

function analyzeData(rounds) {
	var totalRounds = 0;
	var totalPoints = 0;
	var totalWins = 0;
	
	rounds.forEach(function(round) {
		totalRounds++;
		totalPoints += round.pointsScored;
		if (round.result === "Win") {
			totalWins++;
		}
		
	})
	console.log(totalRounds);
	console.log(totalPoints);
	console.log(totalWins);
	
	var analysisTable = document.getElementById("analysis");
	analysisTable.textContent = "";
	var table = document.createElement("table");
	var thead = document.createElement("thead");
	var headerRow = document.createElement("tr");
	var headerTotal = document.createElement("th");
	headerTotal.textContent = "Total Rounds";
	var headerAvgPoints = document.createElement("th");
	headerAvgPoints.textContent = "Average Points Scored";
	var headerWinPct = document.createElement("th");
	headerWinPct.textContent = "Win Percentage";
	
	var tbody = document.createElement("tbody");
	var tr = document.createElement("tr");
	var tdRounds = document.createElement("td");
	tdRounds.textContent = totalRounds;
	var tr = document.createElement("tr");
	var tdPoints = document.createElement("td");
	var avgPoints = totalPoints / totalRounds;
	tdPoints.textContent = avgPoints.toFixed(2);
	var tr = document.createElement("tr");
	var tdWinPct = document.createElement("td");
	var avgWin = ((totalWins / totalRounds) * 100);
	tdWinPct.textContent = avgWin.toFixed(2) + "%";

	tr.appendChild(tdRounds);
	tr.appendChild(tdPoints);
	tr.appendChild(tdWinPct);
	
	tbody.appendChild(tr);
	
	headerRow.appendChild(headerTotal);
	headerRow.appendChild(headerAvgPoints);
	headerRow.appendChild(headerWinPct);
	thead.appendChild(headerRow);

	table.appendChild(thead);
	table.appendChild(tbody);
	analysisTable.appendChild(table);
}

});