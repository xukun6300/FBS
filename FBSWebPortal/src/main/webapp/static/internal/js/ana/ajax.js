/*function sendRequest(a){
	$.ajax({
		type:'POST',
		url:'../authentication/authenticate.action?'+a,
		dataType:'text'
	})
}
*/


var g_xmlhttp;
try {
	g_xmlhttp = new XMLHttpRequest;
} catch (e$$3) {
	try {
		g_xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e$$4) {
		try {
			g_xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (e$$5) {
		}
	}
}
g_xmlhttp || displayMessage("Cannot obtain XMLHttpRequest object!");
function sendRequest(a) {
	g_xmlhttp.open("POST", "../authentication/authenticate.action", false);
	g_xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	g_xmlhttp.send(a);
	return a = g_xmlhttp.responseText;
}