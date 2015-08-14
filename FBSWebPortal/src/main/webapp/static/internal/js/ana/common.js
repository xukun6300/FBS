function checkLength(a, b, d) {
	if (a.length >= b && a.length <= d) {
		return true;
	}
	return false;
}

function trim(a) {
	return a.replace(/^\s+|\s+$/g, "");
}

function ltrim(a) {
	return a.replace(/^\s+/, "");
}

function rtrim(a) {
	return a.replace(/\s+$/, "");
}

function checkFormat(a){
	for (var b = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~", d, c = 0; c < a.length; c++) {
		d = a.charAt(c);
		d = b.indexOf(d);
		if (d < 0) {
			return false;
		}
	}
	return true;
}