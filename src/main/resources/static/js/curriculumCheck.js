
let sendIndex = 0;

function selectStatus(curriculumId, statusId, event){
	event.preventDefault();
	
	console.log(curriculumId);
	console.log(event.target);
	
	event.target.parentElement.setAttribute('hidden', true);
	const statusSelect = event.target.parentElement.nextElementSibling;
	statusSelect.removeAttribute('hidden');
	statusSelect.children[statusId - 1].setAttribute("selected", true);

	if(statusSelect.name == '') {
		statusSelect.name = `cCheckList[${sendIndex}].status`;
		document.getElementById(`id#${curriculumId}`).name = `cCheckList[${sendIndex}].curriculumId`;
		document.getElementById(`score#${curriculumId}`).name = `cCheckList[${sendIndex}].score`;
		sendIndex++;
	}
}

function inputScore(curriculumId, event){
	event.preventDefault();
	
	event.target.parentElement.setAttribute(`hidden`, true);
	const scoreInput = event.target.parentElement.nextElementSibling;
	scoreInput.removeAttribute(`hidden`);
	
	if(scoreInput.name == '') {
		scoreInput.name = `cCheckList[${sendIndex}].score`;
		document.getElementById(`id#${curriculumId}`).name = `cCheckList[${sendIndex}].curriculumId`;
		document.getElementById(`status#${curriculumId}`).name = `cCheckList[${sendIndex}].status`;
		sendIndex++;
	}
}