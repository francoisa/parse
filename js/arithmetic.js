/*global arithmeticListener */
/*global antlr4 */
load("jvm-npm.js");
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
    for (var i = 0; i < tree.children.length; ++i) {
		var child = tree.children[i];
		var name = parser.ruleNames[child.ruleIndex];
		var value = child.getText();
		print(name + " = " + value);
	}
}

var exp = "a = 4 + 5";
parse(exp);