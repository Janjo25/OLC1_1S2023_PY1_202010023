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

/* espacios en blanco */
Espacio=\s

/* identificadores */
Identificador=[a-zA-Z][a-zA-Z_$0-9]*

/* símbolos */
Flecha=\-\s*\>

/* comentarios */
ComentarioBloque=\<\![^>]*\!\>
ComentarioLínea=\/\/.*

%%

<YYINITIAL> {

  /* palabras reservada */
  "CONJ"                         { System.out.println("CONJ: " + yytext()); return new Symbol(sym.CONJ, yyline, yycolumn, yytext()); }

  /* signos y símbolos */
  "%%"                           { System.out.println("Separador: " + yytext()); return new Symbol(sym.SEPARATOR, yyline, yycolumn, yytext()); }
  "*"                            { System.out.println("Asterisco: " + yytext()); return new Symbol(sym.ASTERISK, yyline, yycolumn, yytext()); }
  "+"                            { System.out.println("Más: " + yytext()); return new Symbol(sym.PLUS, yyline, yycolumn, yytext()); }
  ","                            { System.out.println("Coma: " + yytext()); return new Symbol(sym.COMMA, yyline, yycolumn, yytext()); }
  "."                            { System.out.println("Concatenación: " + yytext()); return new Symbol(sym.CONCATENATION, yyline, yycolumn, yytext()); }
  ":"                            { System.out.println("Dos puntos: " + yytext()); return new Symbol(sym.COLON, yyline, yycolumn, yytext()); }
  ";"                            { System.out.println("Punto y coma: " + yytext()); return new Symbol(sym.SEMICOLON, yyline, yycolumn, yytext()); }
  "?"                            { System.out.println("Interrogación: " + yytext()); return new Symbol(sym.QUESTION_MARK, yyline, yycolumn, yytext()); }
  "\\'"                          {  }
  "\\\""                         {  }
  "\\n"                          {  }
  "{"                            { System.out.println("Llave izquierda: " + yytext()); return new Symbol(sym.LEFT_CURLY_BRACKET, yyline, yycolumn, yytext()); }
  "|"                            { System.out.println("Pleca: " + yytext()); return new Symbol(sym.ALTERNATION, yyline, yycolumn, yytext()); }
  "}"                            { System.out.println("Llave derecha: " + yytext()); return new Symbol(sym.RIGHT_CURLY_BRACKET, yyline, yycolumn, yytext()); }
  "~"                            { System.out.println("Virgulilla: " + yytext()); return new Symbol(sym.TILDE, yyline, yycolumn, yytext()); }

  /* expresiones regulares */
  {Cadena}                       { System.out.println("Cadena: " + yytext()); return new Symbol(sym.STRING, yyline, yycolumn, yytext()); }
  {ComentarioBloque}             {  }
  {ComentarioLínea}              {  }
  {Dígito}                       { System.out.println("Dígito: " + yytext()); return new Symbol(sym.DIGIT, yyline, yycolumn, yytext()); }
  {Espacio}                      {  }
  {Flecha}                       { System.out.println("Flecha: " + yytext()); return new Symbol(sym.ARROW, yyline, yycolumn, yytext()); }
  {Letra}                        { System.out.println("Letra: " + yytext()); return new Symbol(sym.LETTER, yyline, yycolumn, yytext()); }
  {Identificador}                { System.out.println("Identificador: " + yytext()); return new Symbol(sym.IDENTIFIER, yyline, yycolumn, yytext()); }
  {Símbolo}                      { System.out.println("Símbolo: " + yytext()); return new Symbol(sym.SYMBOL, yyline, yycolumn, yytext()); }

  /* errores */
  .                              { System.out.println("Error: " + yytext()); }

}
