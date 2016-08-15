 var RequireHelper = Java.type("net.jmf.app.RequireHelper");

var hello = function(path) {
 	var contents = RequireHelper.getContents(path);
	return contents;
}