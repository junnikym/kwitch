const back = function() {
	if(document.referrer && document.referrer.indexOf(location.host) != -1)
		history.back();
	else
		location.href="/";
}

function loadScript(url) {
	const script = document.createElement('script');
	script.src = url;
	script.type = "text/javascript";
	script.async = false;
	document.head.appendChild(script);
}
