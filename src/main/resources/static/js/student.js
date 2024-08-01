window.addEventListener('DOMContentLoaded', () => {
	const startList = [];
	document.querySelectorAll('.startMonth').forEach(e => startList.push(e.textContent));
	
	const courseList = document.getElementsByClassName('li_course');
	for(elem of courseList){
		const startMonth = elem.firstElementChild.textContent;
		if(startMonth == startList[0]) {
			const newLi = `<li>
							<div class="start_month">
								${startMonth.substring(2,4)}年${startMonth.substring(5,7)}月
							</div>
						</li>`;
			elem.insertAdjacentHTML('beforebegin', newLi);
			startList.shift();
		}
	}
})