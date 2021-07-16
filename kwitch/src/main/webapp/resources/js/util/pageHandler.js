const back = function() {
	if(document.referrer && document.referrer.indexOf(location.host) != -1)
		history.back();
	else
		location.href="/";
}
