@echo off
setlocal
set CLASSPATH=D:\java\antlr4-4.5.3\tool\target\antlr4-4.5.4-SNAPSHOT.jar
java org.antlr.v4.Tool -Dlanguage=JavaScript arithmetic.g4
endlocal