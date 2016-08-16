// Generated from arithmetic.g4 by ANTLR 4.5.3
// jshint ignore: start
var antlr4 = require('antlr4/index');

// This class defines a complete listener for a parse tree produced by arithmeticParser.
function arithmeticListener() {
	antlr4.tree.ParseTreeListener.call(this);
	return this;
}

arithmeticListener.prototype = Object.create(antlr4.tree.ParseTreeListener.prototype);
arithmeticListener.prototype.constructor = arithmeticListener;

// Enter a parse tree produced by arithmeticParser#equation.
arithmeticListener.prototype.enterEquation = function(ctx) {
};

// Exit a parse tree produced by arithmeticParser#equation.
arithmeticListener.prototype.exitEquation = function(ctx) {
};


// Enter a parse tree produced by arithmeticParser#expression.
arithmeticListener.prototype.enterExpression = function(ctx) {
};

// Exit a parse tree produced by arithmeticParser#expression.
arithmeticListener.prototype.exitExpression = function(ctx) {
};


// Enter a parse tree produced by arithmeticParser#multiplyingExpression.
arithmeticListener.prototype.enterMultiplyingExpression = function(ctx) {
};

// Exit a parse tree produced by arithmeticParser#multiplyingExpression.
arithmeticListener.prototype.exitMultiplyingExpression = function(ctx) {
};


// Enter a parse tree produced by arithmeticParser#powExpression.
arithmeticListener.prototype.enterPowExpression = function(ctx) {
};

// Exit a parse tree produced by arithmeticParser#powExpression.
arithmeticListener.prototype.exitPowExpression = function(ctx) {
};


// Enter a parse tree produced by arithmeticParser#atom.
arithmeticListener.prototype.enterAtom = function(ctx) {
};

// Exit a parse tree produced by arithmeticParser#atom.
arithmeticListener.prototype.exitAtom = function(ctx) {
};


// Enter a parse tree produced by arithmeticParser#scientific.
arithmeticListener.prototype.enterScientific = function(ctx) {
};

// Exit a parse tree produced by arithmeticParser#scientific.
arithmeticListener.prototype.exitScientific = function(ctx) {
};


// Enter a parse tree produced by arithmeticParser#relop.
arithmeticListener.prototype.enterRelop = function(ctx) {
};

// Exit a parse tree produced by arithmeticParser#relop.
arithmeticListener.prototype.exitRelop = function(ctx) {
};


// Enter a parse tree produced by arithmeticParser#number.
arithmeticListener.prototype.enterNumber = function(ctx) {
};

// Exit a parse tree produced by arithmeticParser#number.
arithmeticListener.prototype.exitNumber = function(ctx) {
};


// Enter a parse tree produced by arithmeticParser#variable.
arithmeticListener.prototype.enterVariable = function(ctx) {
};

// Exit a parse tree produced by arithmeticParser#variable.
arithmeticListener.prototype.exitVariable = function(ctx) {
};



exports.arithmeticListener = arithmeticListener;