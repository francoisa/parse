/*global arithmeticListener */
/*global antlr4 */
var console = {};
console.log = print;
console.error = print;
var exports = {};

var antlr4 = require("antlr4/index.js");
var arithmeticLexer = require("arithmeticLexer.js").arithmeticLexer;
var arithmeticParser = require("arithmeticParser.js").arithmeticParser;

function parse(toParse) {
    var input = new antlr4.InputStream(toParse);
    var lexer = new arithmeticLexer(input);
    var tokens = new antlr4.CommonTokenStream(lexer);
    var parser = new arithmeticParser(tokens);
    parser.buildParseTrees = true;
    var tree = parser.equation();
    var result = [];
    for (var i = 0; i < tree.children.length; ++i) {
		var child = tree.children[i];
		var name = parser.ruleNames[child.ruleIndex];
		var value = child.getText();
		result.push({"rule":  name, "value": value});
	}
    return JSON.stringify(result);
}