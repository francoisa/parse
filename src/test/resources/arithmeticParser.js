// Generated from arithmetic.g4 by ANTLR 4.5.3
// jshint ignore: start
var antlr4 = require('antlr4/index');
var arithmeticListener = require('./arithmeticListener').arithmeticListener;
var grammarFileName = "arithmetic.g4";

var serializedATN = ["\u0003\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd",
    "\u0003\u0011W\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t",
    "\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t\u0007\u0004",
    "\b\t\b\u0004\t\t\t\u0004\n\t\n\u0003\u0002\u0003\u0002\u0003\u0002\u0003",
    "\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0007\u0003\u001c\n\u0003",
    "\f\u0003\u000e\u0003\u001f\u000b\u0003\u0003\u0004\u0003\u0004\u0003",
    "\u0004\u0007\u0004$\n\u0004\f\u0004\u000e\u0004\'\u000b\u0004\u0003",
    "\u0005\u0003\u0005\u0003\u0005\u0005\u0005,\n\u0005\u0003\u0006\u0003",
    "\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0005\u00064",
    "\n\u0006\u0003\u0007\u0003\u0007\u0003\u0007\u0005\u00079\n\u0007\u0003",
    "\b\u0003\b\u0003\t\u0005\t>\n\t\u0003\t\u0006\tA\n\t\r\t\u000e\tB\u0003",
    "\t\u0003\t\u0006\tG\n\t\r\t\u000e\tH\u0005\tK\n\t\u0003\n\u0005\nN\n",
    "\n\u0003\n\u0003\n\u0007\nR\n\n\f\n\u000e\nU\u000b\n\u0003\n\u0002\u0002",
    "\u000b\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0002\u0006\u0003\u0002",
    "\u0005\u0006\u0003\u0002\u0007\b\u0003\u0002\t\u000b\u0003\u0002\u000f",
    "\u0010Y\u0002\u0014\u0003\u0002\u0002\u0002\u0004\u0018\u0003\u0002",
    "\u0002\u0002\u0006 \u0003\u0002\u0002\u0002\b(\u0003\u0002\u0002\u0002",
    "\n3\u0003\u0002\u0002\u0002\f5\u0003\u0002\u0002\u0002\u000e:\u0003",
    "\u0002\u0002\u0002\u0010=\u0003\u0002\u0002\u0002\u0012M\u0003\u0002",
    "\u0002\u0002\u0014\u0015\u0005\u0004\u0003\u0002\u0015\u0016\u0005\u000e",
    "\b\u0002\u0016\u0017\u0005\u0004\u0003\u0002\u0017\u0003\u0003\u0002",
    "\u0002\u0002\u0018\u001d\u0005\u0006\u0004\u0002\u0019\u001a\t\u0002",
    "\u0002\u0002\u001a\u001c\u0005\u0006\u0004\u0002\u001b\u0019\u0003\u0002",
    "\u0002\u0002\u001c\u001f\u0003\u0002\u0002\u0002\u001d\u001b\u0003\u0002",
    "\u0002\u0002\u001d\u001e\u0003\u0002\u0002\u0002\u001e\u0005\u0003\u0002",
    "\u0002\u0002\u001f\u001d\u0003\u0002\u0002\u0002 %\u0005\b\u0005\u0002",
    "!\"\t\u0003\u0002\u0002\"$\u0005\b\u0005\u0002#!\u0003\u0002\u0002\u0002",
    "$\'\u0003\u0002\u0002\u0002%#\u0003\u0002\u0002\u0002%&\u0003\u0002",
    "\u0002\u0002&\u0007\u0003\u0002\u0002\u0002\'%\u0003\u0002\u0002\u0002",
    "(+\u0005\n\u0006\u0002)*\u0007\u000e\u0002\u0002*,\u0005\u0004\u0003",
    "\u0002+)\u0003\u0002\u0002\u0002+,\u0003\u0002\u0002\u0002,\t\u0003",
    "\u0002\u0002\u0002-4\u0005\f\u0007\u0002.4\u0005\u0012\n\u0002/0\u0007",
    "\u0003\u0002\u000201\u0005\u0004\u0003\u000212\u0007\u0004\u0002\u0002",
    "24\u0003\u0002\u0002\u00023-\u0003\u0002\u0002\u00023.\u0003\u0002\u0002",
    "\u00023/\u0003\u0002\u0002\u00024\u000b\u0003\u0002\u0002\u000258\u0005",
    "\u0010\t\u000267\u0007\r\u0002\u000279\u0005\u0010\t\u000286\u0003\u0002",
    "\u0002\u000289\u0003\u0002\u0002\u00029\r\u0003\u0002\u0002\u0002:;",
    "\t\u0004\u0002\u0002;\u000f\u0003\u0002\u0002\u0002<>\u0007\u0006\u0002",
    "\u0002=<\u0003\u0002\u0002\u0002=>\u0003\u0002\u0002\u0002>@\u0003\u0002",
    "\u0002\u0002?A\u0007\u0010\u0002\u0002@?\u0003\u0002\u0002\u0002AB\u0003",
    "\u0002\u0002\u0002B@\u0003\u0002\u0002\u0002BC\u0003\u0002\u0002\u0002",
    "CJ\u0003\u0002\u0002\u0002DF\u0007\f\u0002\u0002EG\u0007\u0010\u0002",
    "\u0002FE\u0003\u0002\u0002\u0002GH\u0003\u0002\u0002\u0002HF\u0003\u0002",
    "\u0002\u0002HI\u0003\u0002\u0002\u0002IK\u0003\u0002\u0002\u0002JD\u0003",
    "\u0002\u0002\u0002JK\u0003\u0002\u0002\u0002K\u0011\u0003\u0002\u0002",
    "\u0002LN\u0007\u0006\u0002\u0002ML\u0003\u0002\u0002\u0002MN\u0003\u0002",
    "\u0002\u0002NO\u0003\u0002\u0002\u0002OS\u0007\u000f\u0002\u0002PR\t",
    "\u0005\u0002\u0002QP\u0003\u0002\u0002\u0002RU\u0003\u0002\u0002\u0002",
    "SQ\u0003\u0002\u0002\u0002ST\u0003\u0002\u0002\u0002T\u0013\u0003\u0002",
    "\u0002\u0002US\u0003\u0002\u0002\u0002\r\u001d%+38=BHJMS"].join("");


var atn = new antlr4.atn.ATNDeserializer().deserialize(serializedATN);

var decisionsToDFA = atn.decisionToState.map( function(ds, index) { return new antlr4.dfa.DFA(ds, index); });

var sharedContextCache = new antlr4.PredictionContextCache();

var literalNames = [ null, "'('", "')'", "'+'", "'-'", "'*'", "'/'", "'>'", 
                     "'<'", "'='", "'.'", null, "'^'" ];

var symbolicNames = [ null, "LPAREN", "RPAREN", "PLUS", "MINUS", "TIMES", 
                      "DIV", "GT", "LT", "EQ", "POINT", "E", "POW", "LETTER", 
                      "DIGIT", "WS" ];

var ruleNames =  [ "equation", "expression", "multiplyingExpression", "powExpression", 
                   "atom", "scientific", "relop", "number", "variable" ];

function arithmeticParser (input) {
	antlr4.Parser.call(this, input);
    this._interp = new antlr4.atn.ParserATNSimulator(this, atn, decisionsToDFA, sharedContextCache);
    this.ruleNames = ruleNames;
    this.literalNames = literalNames;
    this.symbolicNames = symbolicNames;
    return this;
}

arithmeticParser.prototype = Object.create(antlr4.Parser.prototype);
arithmeticParser.prototype.constructor = arithmeticParser;

Object.defineProperty(arithmeticParser.prototype, "atn", {
	get : function() {
		return atn;
	}
});

arithmeticParser.EOF = antlr4.Token.EOF;
arithmeticParser.LPAREN = 1;
arithmeticParser.RPAREN = 2;
arithmeticParser.PLUS = 3;
arithmeticParser.MINUS = 4;
arithmeticParser.TIMES = 5;
arithmeticParser.DIV = 6;
arithmeticParser.GT = 7;
arithmeticParser.LT = 8;
arithmeticParser.EQ = 9;
arithmeticParser.POINT = 10;
arithmeticParser.E = 11;
arithmeticParser.POW = 12;
arithmeticParser.LETTER = 13;
arithmeticParser.DIGIT = 14;
arithmeticParser.WS = 15;

arithmeticParser.RULE_equation = 0;
arithmeticParser.RULE_expression = 1;
arithmeticParser.RULE_multiplyingExpression = 2;
arithmeticParser.RULE_powExpression = 3;
arithmeticParser.RULE_atom = 4;
arithmeticParser.RULE_scientific = 5;
arithmeticParser.RULE_relop = 6;
arithmeticParser.RULE_number = 7;
arithmeticParser.RULE_variable = 8;

function EquationContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = arithmeticParser.RULE_equation;
    return this;
}

EquationContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
EquationContext.prototype.constructor = EquationContext;

EquationContext.prototype.expression = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(ExpressionContext);
    } else {
        return this.getTypedRuleContext(ExpressionContext,i);
    }
};

EquationContext.prototype.relop = function() {
    return this.getTypedRuleContext(RelopContext,0);
};

EquationContext.prototype.enterRule = function(listener) {
    if(listener instanceof arithmeticListener ) {
        listener.enterEquation(this);
	}
};

EquationContext.prototype.exitRule = function(listener) {
    if(listener instanceof arithmeticListener ) {
        listener.exitEquation(this);
	}
};




arithmeticParser.EquationContext = EquationContext;

arithmeticParser.prototype.equation = function() {

    var localctx = new EquationContext(this, this._ctx, this.state);
    this.enterRule(localctx, 0, arithmeticParser.RULE_equation);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 18;
        this.expression();
        this.state = 19;
        this.relop();
        this.state = 20;
        this.expression();
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function ExpressionContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = arithmeticParser.RULE_expression;
    return this;
}

ExpressionContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
ExpressionContext.prototype.constructor = ExpressionContext;

ExpressionContext.prototype.multiplyingExpression = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(MultiplyingExpressionContext);
    } else {
        return this.getTypedRuleContext(MultiplyingExpressionContext,i);
    }
};

ExpressionContext.prototype.PLUS = function(i) {
	if(i===undefined) {
		i = null;
	}
    if(i===null) {
        return this.getTokens(arithmeticParser.PLUS);
    } else {
        return this.getToken(arithmeticParser.PLUS, i);
    }
};


ExpressionContext.prototype.MINUS = function(i) {
	if(i===undefined) {
		i = null;
	}
    if(i===null) {
        return this.getTokens(arithmeticParser.MINUS);
    } else {
        return this.getToken(arithmeticParser.MINUS, i);
    }
};


ExpressionContext.prototype.enterRule = function(listener) {
    if(listener instanceof arithmeticListener ) {
        listener.enterExpression(this);
	}
};

ExpressionContext.prototype.exitRule = function(listener) {
    if(listener instanceof arithmeticListener ) {
        listener.exitExpression(this);
	}
};




arithmeticParser.ExpressionContext = ExpressionContext;

arithmeticParser.prototype.expression = function() {

    var localctx = new ExpressionContext(this, this._ctx, this.state);
    this.enterRule(localctx, 2, arithmeticParser.RULE_expression);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 22;
        this.multiplyingExpression();
        this.state = 27;
        this._errHandler.sync(this);
        var _alt = this._interp.adaptivePredict(this._input,0,this._ctx)
        while(_alt!=2 && _alt!=antlr4.atn.ATN.INVALID_ALT_NUMBER) {
            if(_alt===1) {
                this.state = 23;
                _la = this._input.LA(1);
                if(!(_la===arithmeticParser.PLUS || _la===arithmeticParser.MINUS)) {
                this._errHandler.recoverInline(this);
                }
                else {
                    this.consume();
                }
                this.state = 24;
                this.multiplyingExpression(); 
            }
            this.state = 29;
            this._errHandler.sync(this);
            _alt = this._interp.adaptivePredict(this._input,0,this._ctx);
        }

    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function MultiplyingExpressionContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = arithmeticParser.RULE_multiplyingExpression;
    return this;
}

MultiplyingExpressionContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
MultiplyingExpressionContext.prototype.constructor = MultiplyingExpressionContext;

MultiplyingExpressionContext.prototype.powExpression = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(PowExpressionContext);
    } else {
        return this.getTypedRuleContext(PowExpressionContext,i);
    }
};

MultiplyingExpressionContext.prototype.TIMES = function(i) {
	if(i===undefined) {
		i = null;
	}
    if(i===null) {
        return this.getTokens(arithmeticParser.TIMES);
    } else {
        return this.getToken(arithmeticParser.TIMES, i);
    }
};


MultiplyingExpressionContext.prototype.DIV = function(i) {
	if(i===undefined) {
		i = null;
	}
    if(i===null) {
        return this.getTokens(arithmeticParser.DIV);
    } else {
        return this.getToken(arithmeticParser.DIV, i);
    }
};


MultiplyingExpressionContext.prototype.enterRule = function(listener) {
    if(listener instanceof arithmeticListener ) {
        listener.enterMultiplyingExpression(this);
	}
};

MultiplyingExpressionContext.prototype.exitRule = function(listener) {
    if(listener instanceof arithmeticListener ) {
        listener.exitMultiplyingExpression(this);
	}
};




arithmeticParser.MultiplyingExpressionContext = MultiplyingExpressionContext;

arithmeticParser.prototype.multiplyingExpression = function() {

    var localctx = new MultiplyingExpressionContext(this, this._ctx, this.state);
    this.enterRule(localctx, 4, arithmeticParser.RULE_multiplyingExpression);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 30;
        this.powExpression();
        this.state = 35;
        this._errHandler.sync(this);
        var _alt = this._interp.adaptivePredict(this._input,1,this._ctx)
        while(_alt!=2 && _alt!=antlr4.atn.ATN.INVALID_ALT_NUMBER) {
            if(_alt===1) {
                this.state = 31;
                _la = this._input.LA(1);
                if(!(_la===arithmeticParser.TIMES || _la===arithmeticParser.DIV)) {
                this._errHandler.recoverInline(this);
                }
                else {
                    this.consume();
                }
                this.state = 32;
                this.powExpression(); 
            }
            this.state = 37;
            this._errHandler.sync(this);
            _alt = this._interp.adaptivePredict(this._input,1,this._ctx);
        }

    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function PowExpressionContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = arithmeticParser.RULE_powExpression;
    return this;
}

PowExpressionContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
PowExpressionContext.prototype.constructor = PowExpressionContext;

PowExpressionContext.prototype.atom = function() {
    return this.getTypedRuleContext(AtomContext,0);
};

PowExpressionContext.prototype.POW = function() {
    return this.getToken(arithmeticParser.POW, 0);
};

PowExpressionContext.prototype.expression = function() {
    return this.getTypedRuleContext(ExpressionContext,0);
};

PowExpressionContext.prototype.enterRule = function(listener) {
    if(listener instanceof arithmeticListener ) {
        listener.enterPowExpression(this);
	}
};

PowExpressionContext.prototype.exitRule = function(listener) {
    if(listener instanceof arithmeticListener ) {
        listener.exitPowExpression(this);
	}
};




arithmeticParser.PowExpressionContext = PowExpressionContext;

arithmeticParser.prototype.powExpression = function() {

    var localctx = new PowExpressionContext(this, this._ctx, this.state);
    this.enterRule(localctx, 6, arithmeticParser.RULE_powExpression);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 38;
        this.atom();
        this.state = 41;
        _la = this._input.LA(1);
        if(_la===arithmeticParser.POW) {
            this.state = 39;
            this.match(arithmeticParser.POW);
            this.state = 40;
            this.expression();
        }

    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function AtomContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = arithmeticParser.RULE_atom;
    return this;
}

AtomContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
AtomContext.prototype.constructor = AtomContext;

AtomContext.prototype.scientific = function() {
    return this.getTypedRuleContext(ScientificContext,0);
};

AtomContext.prototype.variable = function() {
    return this.getTypedRuleContext(VariableContext,0);
};

AtomContext.prototype.LPAREN = function() {
    return this.getToken(arithmeticParser.LPAREN, 0);
};

AtomContext.prototype.expression = function() {
    return this.getTypedRuleContext(ExpressionContext,0);
};

AtomContext.prototype.RPAREN = function() {
    return this.getToken(arithmeticParser.RPAREN, 0);
};

AtomContext.prototype.enterRule = function(listener) {
    if(listener instanceof arithmeticListener ) {
        listener.enterAtom(this);
	}
};

AtomContext.prototype.exitRule = function(listener) {
    if(listener instanceof arithmeticListener ) {
        listener.exitAtom(this);
	}
};




arithmeticParser.AtomContext = AtomContext;

arithmeticParser.prototype.atom = function() {

    var localctx = new AtomContext(this, this._ctx, this.state);
    this.enterRule(localctx, 8, arithmeticParser.RULE_atom);
    try {
        this.state = 49;
        this._errHandler.sync(this);
        var la_ = this._interp.adaptivePredict(this._input,3,this._ctx);
        switch(la_) {
        case 1:
            this.enterOuterAlt(localctx, 1);
            this.state = 43;
            this.scientific();
            break;

        case 2:
            this.enterOuterAlt(localctx, 2);
            this.state = 44;
            this.variable();
            break;

        case 3:
            this.enterOuterAlt(localctx, 3);
            this.state = 45;
            this.match(arithmeticParser.LPAREN);
            this.state = 46;
            this.expression();
            this.state = 47;
            this.match(arithmeticParser.RPAREN);
            break;

        }
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function ScientificContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = arithmeticParser.RULE_scientific;
    return this;
}

ScientificContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
ScientificContext.prototype.constructor = ScientificContext;

ScientificContext.prototype.number = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(NumberContext);
    } else {
        return this.getTypedRuleContext(NumberContext,i);
    }
};

ScientificContext.prototype.E = function() {
    return this.getToken(arithmeticParser.E, 0);
};

ScientificContext.prototype.enterRule = function(listener) {
    if(listener instanceof arithmeticListener ) {
        listener.enterScientific(this);
	}
};

ScientificContext.prototype.exitRule = function(listener) {
    if(listener instanceof arithmeticListener ) {
        listener.exitScientific(this);
	}
};




arithmeticParser.ScientificContext = ScientificContext;

arithmeticParser.prototype.scientific = function() {

    var localctx = new ScientificContext(this, this._ctx, this.state);
    this.enterRule(localctx, 10, arithmeticParser.RULE_scientific);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 51;
        this.number();
        this.state = 54;
        _la = this._input.LA(1);
        if(_la===arithmeticParser.E) {
            this.state = 52;
            this.match(arithmeticParser.E);
            this.state = 53;
            this.number();
        }

    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function RelopContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = arithmeticParser.RULE_relop;
    return this;
}

RelopContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
RelopContext.prototype.constructor = RelopContext;

RelopContext.prototype.EQ = function() {
    return this.getToken(arithmeticParser.EQ, 0);
};

RelopContext.prototype.GT = function() {
    return this.getToken(arithmeticParser.GT, 0);
};

RelopContext.prototype.LT = function() {
    return this.getToken(arithmeticParser.LT, 0);
};

RelopContext.prototype.enterRule = function(listener) {
    if(listener instanceof arithmeticListener ) {
        listener.enterRelop(this);
	}
};

RelopContext.prototype.exitRule = function(listener) {
    if(listener instanceof arithmeticListener ) {
        listener.exitRelop(this);
	}
};




arithmeticParser.RelopContext = RelopContext;

arithmeticParser.prototype.relop = function() {

    var localctx = new RelopContext(this, this._ctx, this.state);
    this.enterRule(localctx, 12, arithmeticParser.RULE_relop);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 56;
        _la = this._input.LA(1);
        if(!((((_la) & ~0x1f) == 0 && ((1 << _la) & ((1 << arithmeticParser.GT) | (1 << arithmeticParser.LT) | (1 << arithmeticParser.EQ))) !== 0))) {
        this._errHandler.recoverInline(this);
        }
        else {
            this.consume();
        }
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function NumberContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = arithmeticParser.RULE_number;
    return this;
}

NumberContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
NumberContext.prototype.constructor = NumberContext;

NumberContext.prototype.MINUS = function() {
    return this.getToken(arithmeticParser.MINUS, 0);
};

NumberContext.prototype.DIGIT = function(i) {
	if(i===undefined) {
		i = null;
	}
    if(i===null) {
        return this.getTokens(arithmeticParser.DIGIT);
    } else {
        return this.getToken(arithmeticParser.DIGIT, i);
    }
};


NumberContext.prototype.POINT = function() {
    return this.getToken(arithmeticParser.POINT, 0);
};

NumberContext.prototype.enterRule = function(listener) {
    if(listener instanceof arithmeticListener ) {
        listener.enterNumber(this);
	}
};

NumberContext.prototype.exitRule = function(listener) {
    if(listener instanceof arithmeticListener ) {
        listener.exitNumber(this);
	}
};




arithmeticParser.NumberContext = NumberContext;

arithmeticParser.prototype.number = function() {

    var localctx = new NumberContext(this, this._ctx, this.state);
    this.enterRule(localctx, 14, arithmeticParser.RULE_number);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 59;
        _la = this._input.LA(1);
        if(_la===arithmeticParser.MINUS) {
            this.state = 58;
            this.match(arithmeticParser.MINUS);
        }

        this.state = 62; 
        this._errHandler.sync(this);
        _la = this._input.LA(1);
        do {
            this.state = 61;
            this.match(arithmeticParser.DIGIT);
            this.state = 64; 
            this._errHandler.sync(this);
            _la = this._input.LA(1);
        } while(_la===arithmeticParser.DIGIT);
        this.state = 72;
        _la = this._input.LA(1);
        if(_la===arithmeticParser.POINT) {
            this.state = 66;
            this.match(arithmeticParser.POINT);
            this.state = 68; 
            this._errHandler.sync(this);
            _la = this._input.LA(1);
            do {
                this.state = 67;
                this.match(arithmeticParser.DIGIT);
                this.state = 70; 
                this._errHandler.sync(this);
                _la = this._input.LA(1);
            } while(_la===arithmeticParser.DIGIT);
        }

    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function VariableContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = arithmeticParser.RULE_variable;
    return this;
}

VariableContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
VariableContext.prototype.constructor = VariableContext;

VariableContext.prototype.LETTER = function(i) {
	if(i===undefined) {
		i = null;
	}
    if(i===null) {
        return this.getTokens(arithmeticParser.LETTER);
    } else {
        return this.getToken(arithmeticParser.LETTER, i);
    }
};


VariableContext.prototype.MINUS = function() {
    return this.getToken(arithmeticParser.MINUS, 0);
};

VariableContext.prototype.DIGIT = function(i) {
	if(i===undefined) {
		i = null;
	}
    if(i===null) {
        return this.getTokens(arithmeticParser.DIGIT);
    } else {
        return this.getToken(arithmeticParser.DIGIT, i);
    }
};


VariableContext.prototype.enterRule = function(listener) {
    if(listener instanceof arithmeticListener ) {
        listener.enterVariable(this);
	}
};

VariableContext.prototype.exitRule = function(listener) {
    if(listener instanceof arithmeticListener ) {
        listener.exitVariable(this);
	}
};




arithmeticParser.VariableContext = VariableContext;

arithmeticParser.prototype.variable = function() {

    var localctx = new VariableContext(this, this._ctx, this.state);
    this.enterRule(localctx, 16, arithmeticParser.RULE_variable);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 75;
        _la = this._input.LA(1);
        if(_la===arithmeticParser.MINUS) {
            this.state = 74;
            this.match(arithmeticParser.MINUS);
        }

        this.state = 77;
        this.match(arithmeticParser.LETTER);
        this.state = 81;
        this._errHandler.sync(this);
        _la = this._input.LA(1);
        while(_la===arithmeticParser.LETTER || _la===arithmeticParser.DIGIT) {
            this.state = 78;
            _la = this._input.LA(1);
            if(!(_la===arithmeticParser.LETTER || _la===arithmeticParser.DIGIT)) {
            this._errHandler.recoverInline(this);
            }
            else {
                this.consume();
            }
            this.state = 83;
            this._errHandler.sync(this);
            _la = this._input.LA(1);
        }
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};


exports.arithmeticParser = arithmeticParser;
