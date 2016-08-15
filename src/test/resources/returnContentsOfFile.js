var contents = function(fileName) {
	var fs = require("fs").fs;
	var data = fs.readFileSync(fileName, "utf8");
	return data;
}
