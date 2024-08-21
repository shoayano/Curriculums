
let newIndex = 0;
let newOrderNum = 0;
let unitTable = null;
let deleteIndex = 0;
let deleteIdList = null;

window.addEventListener("DOMContentLoaded", () => {
	/*
	const orderNumLast = document.querySelector('.curriculum_form div:last-of-type .orderNum');
	*/
	const orders = document.getElementsByClassName('orderNum');
	const orderNumLast = orders[orders.length - 1];
	if(orderNumLast != null) newOrderNum = +(orderNumLast.value) + 1;
	
	const editList = document.querySelectorAll('td input[type="text"], td input[type="number"]');
	editList.forEach((input) => input.addEventListener('change', (e) => {
									if(e.target.name != '') return;
									addCList(e.target.id);
	}));
	
	unitTable = document.getElementById('unit_table');
	deleteIdList = document.getElementById('deleteIdList');
	
	const sortTable = document.getElementById('curriculum_tbody');
	let ordersList = [];
	Sortable.create(sortTable, {
		onStart: function(){
			const prevOrders = document.getElementsByClassName('orderNum');
			for(elem of prevOrders){
				ordersList.push(elem.value);
			}
		},
		onEnd: function(){
			const newOrders = document.getElementsByClassName('orderNum');
			for(elem of newOrders){
				const order = ordersList.shift();
				if(elem.value != order){
					elem.value = order;
					if(elem.id != '') addCList(elem.id);
				}
			}
		}
	});
});


function addCList(targetId){
	const indexPlace = targetId.indexOf("#") + 1;
	const index = targetId.substring(indexPlace);
	
	const idInput = document.getElementById(`id#${index}`);
	
	if(idInput.name == '') {
		idInput.setAttribute('name', `cEditList[${newIndex}].curriculumId`);
		document.getElementById(`orderNum#${index}`).setAttribute('name', `cEditList[${newIndex}].orderNum`);
		document.getElementById(`name#${index}`).setAttribute('name', `cEditList[${newIndex}].name`);
		document.getElementById(`textId#${index}`).setAttribute('name', `cEditList[${newIndex}].textbookId`);
		document.getElementById(`textName#${index}`).setAttribute('name', `cEditList[${newIndex}].textbookName`);
		document.getElementById(`page#${index}`).setAttribute('name', `cEditList[${newIndex}].page`);
		
		newIndex++;
	}
	
}


function newCurriculum(){	
	const tbody = document.getElementById('curriculum_tbody');
	const newTr = `<tr class="tr_sortable">
					<td class="mark">&#xFE19</td>
					<td>
						<input type="hidden" name="cEditList[${newIndex}].orderNum" value="${newOrderNum}" class="orderNum">
						<input type="text" name="cEditList[${newIndex}].name">
					</td>
					<td>
						<input type="text" name="cEditList[${newIndex}].textbookName">
					</td>
					<td>
						<input type="number" name="cEditList[${newIndex}].page">
					</td>
					<td>
					</td>
					<td>
					</td>
					<td>
						<div class="nav_button delete_button">
							<a class="button_item" href="#" th:id="|delete#*{curriculumId}|" onclick="onDelete(event)">x</a>
						</div>
						<input type="hidden" name="cEditList[${newIndex}].deleteFlg" value="false">
					</td>
				</tr>`;
	
	tbody.insertAdjacentHTML('beforeend', newTr);
	
	newIndex++;
	newOrderNum++;
}


function selectText(event){
	const textbookId = event.target.value;
	const textbookName = document.getElementById('select#' + textbookId).textContent;
	const unit_tbody = document.getElementById('unit_tbody');
	if(unit_tbody) unit_tbody.remove();
	
	let request = new XMLHttpRequest();
	request.onreadystatechange = function(){
		if(request.readyState == 4){
			let unitList = JSON.parse(request.responseText);
			let tBody = `<tbody id="unit_tbody">`;
			
			unitList.forEach(unit => {
				tBody += `<tr id="unit#${unit.unitId}" onclick="addFromText(${textbookId}, '${textbookName}', this.id)" class="tr_unit">
							<td class="left_td">
								<div id="name#${unit.unitId}">${unit.name}</div>
							</td>
							<td>
								<data id="page#${unit.unitId}" value="${unit.page}">p. ${unit.page}~</data>
							</td>
						</tr>`;
			});
			
			tBody += '</tbody>';
			unitTable.insertAdjacentHTML('beforeend', tBody);
		}
	}
	
	request.open('GET', 'http://localhost:8080/unit/json?textbookId=' + textbookId, true);
	request.send(null);
}


function addFromText(textbookId, textbookName, trId){
	const unitId = trId.substring(trId.indexOf('#') + 1);
	const unitName = document.getElementById(`name#${unitId}`).textContent;
	const unitPage = document.getElementById(`page#${unitId}`).value;
	
	const tbody = document.getElementById('curriculum_tbody');
	const newTr = `<tr class="tr_sortable">
					<td class="mark">&#xFE19</td>
					<td>
						<input type="hidden" name="cEditList[${newIndex}].orderNum" value="${newOrderNum}" class="orderNum">
						<input type="hidden" name="cEditList[${newIndex}].name" value="${unitName}">${unitName}
					</td>
					<td>
						<input type="hidden" name="cEditList[${newIndex}].textbookId" value="${textbookId}">
						<input type="hidden" name="cEditList[${newIndex}].textbookName" value="${textbookName}">${textbookName}
					</td>
					<td>
						<input type="hidden" name="cEditList[${newIndex}].page" value="${unitPage}">p. ${unitPage}~
					</td>
					<td>
					</td>
					<td>
					</td>
					<td>
						<div class="nav_button delete_button">
							<a class="button_item" href="#" th:id="|delete#*{curriculumId}|" onclick="onDelete(event)">x</a>
						</div>
						<input type="hidden" name="cEditList[${newIndex}].deleteFlg" value="false">
					</td>
				</tr>`;

	tbody.insertAdjacentHTML('beforeend', newTr);

	newIndex++;
	newOrderNum++;
}


function onDelete(event){
	event.preventDefault();
	
	const targetId = event.target.id;
	if(targetId == '') {
		event.target.parentElement.parentElement.parentElement.setAttribute('hidden', true);
		event.target.parentElement.nextElementSibling.value = true;
		return;
	}
	const deleteId = targetId.substring(targetId.indexOf('#') + 1);
	
	const deleteTr = document.getElementById(`tr#${deleteId}`);
	deleteTr.setAttribute('hidden', true);

	const newInput = `<input type="hidden" name="deleteIdList[${deleteIndex}]" value="${deleteId}">`
	deleteIdList.insertAdjacentHTML('beforeend', newInput);
	deleteIndex++;
}