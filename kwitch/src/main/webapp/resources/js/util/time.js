function timeDifference(current, previous) {

	const msPerMinute = 60 * 1000;
	const msPerHour = msPerMinute * 60;
	const msPerDay = msPerHour * 24;
	const msPerMonth = msPerDay * 30;
	const msPerYear = msPerDay * 365;

	const elapsed = current - previous;

	if (elapsed < msPerMinute) {
		return Math.round(elapsed/1000) + ' seconds ago';
	}

	else if (elapsed < msPerHour) {
		return Math.round(elapsed/msPerMinute) + ' minutes ago';
	}

	else if (elapsed < msPerDay ) {
		return Math.round(elapsed/msPerHour ) + ' hours ago';
	}

	const options = { weekday: 'short', year: '2-digit', month: 'short', day: 'numeric' };
	
	return previous.toLocaleDateString('en-US', options) + " / " + previous.toLocaleTimeString('en-US');
}