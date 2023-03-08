package main.java.analyzers.lexical;

import java_cup.runtime.*;

%%

%class LexicalAnalysis
%column
%cup
%cupdebug
%line
%public
%unicode

%{

%}

/* datos primitivos y símbolos especiales */
Dígito=[0-9]
Letra=[a-zA-Z]
Símbolo=[!-\/:-@\[-`{-~]

/* datos no primitivos */
Cadena=\"[^\"]*\"

/* comentarios */
ComentarioBloque=\<\!.*\s*.*\!\>
ComentarioLínea=\/\/.*

/* espacios en blanco */
Espacio=\s

/* identificadores */
Identificador=[a-zA-Z_$][a-zA-Z_$0-9]*

%%

<YYINITIAL> {

  /* palabras reservada */
  "CONJ"                         { System.out.println("Token: " + yytext()); return new Symbol(sym.CONJ, yyline, yycolumn, yytext()); }

  /* signos y símbolos */
  "%%"                           { System.out.println("Token: " + yytext()); return new Symbol(sym.SEPARATOR, yyline, yycolumn, yytext()); }
  "*"                            { System.out.println("Token: " + yytext()); return new Symbol(sym.ASTERISK, yyline, yycolumn, yytext()); }
  "+"                            { System.out.println("Token: " + yytext()); return new Symbol(sym.PLUS, yyline, yycolumn, yytext()); }
  "->"                           { System.out.println("Token: " + yytext()); return new Symbol(sym.ARROW, yyline, yycolumn, yytext()); }
  "."                            { System.out.println("Token: " + yytext()); return new Symbol(sym.CONCATENATION, yyline, yycolumn, yytext()); }
  ":"                            { System.out.println("Token: " + yytext()); return new Symbol(sym.COLON, yyline, yycolumn, yytext()); }
  ";"                            { System.out.println("Token: " + yytext()); return new Symbol(sym.SEMICOLON, yyline, yycolumn, yytext()); }
  "?"                            { System.out.println("Token: " + yytext()); return new Symbol(sym.QUESTION_MARK, yyline, yycolumn, yytext()); }
  "{"                            { System.out.println("Token: " + yytext()); return new Symbol(sym.LEFT_CURLY_BRACKET, yyline, yycolumn, yytext()); }
  "|"                            { System.out.println("Token: " + yytext()); return new Symbol(sym.ALTERNATION, yyline, yycolumn, yytext()); }
  "}"                            { System.out.println("Token: " + yytext()); return new Symbol(sym.RIGHT_CURLY_BRACKET, yyline, yycolumn, yytext()); }
  "~"                            { System.out.println("Token: " + yytext()); return new Symbol(sym.TILDE, yyline, yycolumn, yytext()); }

  /* expresiones regulares */
  {Cadena}                       { System.out.println("Token: " + yytext()); return new Symbol(sym.STRING, yyline, yycolumn, yytext()); }
  {ComentarioBloque}             {  }
  {ComentarioLínea}              {  }
  {Dígito}                       { System.out.println("Token: " + yytext()); return new Symbol(sym.DIGIT, yyline, yycolumn, yytext()); }
  {Espacio}                      {  }
  {Letra}                        { System.out.println("Token: " + yytext()); return new Symbol(sym.LETTER, yyline, yycolumn, yytext()); }
  {Identificador}                { System.out.println("Token: " + yytext()); return new Symbol(sym.IDENTIFIER, yyline, yycolumn, yytext()); }
  {Símbolo}                      { System.out.println("Token: " + yytext()); return new Symbol(sym.SYMBOL, yyline, yycolumn, yytext()); }

  /* errores */
  .                              { System.out.println("Error: " + yytext()); }

}
