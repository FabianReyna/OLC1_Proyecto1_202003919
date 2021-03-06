package analisis;

import java_cup.runtime.Symbol;
import static proyecto1_olc.Proyecto1_OLC.errores;



%%

%{
    String cadena="";
    String letter="";
    String comentario="";
    boolean banderaComentario=false;
%}

%cup
%class scanner
%public
%line
%char
%column
%full
%state CADENA
%state LETRA
%state ID
%state MULTICOMENTARIOS
%state VERIFICACION

//%debug

//simbolos y caracteres especiales
DOSPUNTOS = ":"
GUIONC = "~"
PUNTOCOMA = ";"
LLAVE1 = "{"
LLAVE2 = "}"
FLECHA = "->"
SEPARADOR = "%%"
COMA = ","
CONCAT1 = "."
OR1 = "|"
KLEENE1 = "*"
POSITIVE1 = "+"
OPTIONAL1 = "?"
CARACTER = [\!\#\$\%\&\'\(\)\-\.\/\:\;\>\@\[\\\]\^\_\`]

//comentarios y espacios en blanco
SPACE = [\ \r\t\f\t]
ENTER = [\ \n]

//reservada
CONJ1 = "CONJ"

//expresiones regulares
ENTERO = [0-9]+

COM1 = \/\/.*
COM2 = \<

%%

<YYINITIAL> {CONJ1}      { return new Symbol(sym.CONJ1, yyline, yycolumn,yytext());}
<YYINITIAL> {DOSPUNTOS}      { return new Symbol(sym.DOSPUNTOS, yyline, yycolumn,yytext());}
<YYINITIAL> {PUNTOCOMA}      { return new Symbol(sym.PUNTOCOMA, yyline, yycolumn,yytext());}
<YYINITIAL> {GUIONC}      { return new Symbol(sym.GUIONC, yyline, yycolumn,yytext());}
<YYINITIAL> {LLAVE1}      { return new Symbol(sym.LLAVE1, yyline, yycolumn,yytext());}
<YYINITIAL> {LLAVE2}      { return new Symbol(sym.LLAVE2, yyline, yycolumn,yytext());}
<YYINITIAL> {FLECHA}      { return new Symbol(sym.FLECHA, yyline, yycolumn,yytext());}
<YYINITIAL> {SEPARADOR}      { return new Symbol(sym.SEPARADOR, yyline, yycolumn,yytext());}
<YYINITIAL> {COMA}      { return new Symbol(sym.COMA, yyline, yycolumn,yytext());}
<YYINITIAL> [\"]        { yybegin(CADENA); cadena="_"; }
<YYINITIAL> {CONCAT1}      { return new Symbol(sym.CONCAT1, yyline, yycolumn,yytext());}
<YYINITIAL> {OR1}      { return new Symbol(sym.OR1, yyline, yycolumn,yytext());}
<YYINITIAL> {KLEENE1}      { return new Symbol(sym.KLEENE1, yyline, yycolumn,yytext());}
<YYINITIAL> {POSITIVE1}      { return new Symbol(sym.POSITIVE1, yyline, yycolumn,yytext());}
<YYINITIAL> {OPTIONAL1}      { return new Symbol(sym.OPTIONAL1, yyline, yycolumn,yytext());}
<YYINITIAL> {OPTIONAL1}      { return new Symbol(sym.OPTIONAL1, yyline, yycolumn,yytext());}
<YYINITIAL> {COM2}        {yybegin(VERIFICACION); comentario=yytext();}
<YYINITIAL> {CARACTER}      { return new Symbol(sym.CARACTER, yyline, yycolumn,yytext());}

<YYINITIAL> [A-Za-z????]        { yybegin(LETRA); letter=yytext(); }
<YYINITIAL> {ENTERO} { return new Symbol(sym.ENTERO, yyline, yycolumn,yytext());}

<YYINITIAL> {SPACE} {  }
<YYINITIAL> {ENTER} { }
<YYINITIAL> {COM1}        {}


<YYINITIAL> . {
        errores.NewError("Lexico", "El caracter '"+yytext()+"' no pertenece al lenguaje",yyline+1,yycolumn+1);
        System.out.println("El caracter '"+yytext()+"' no pertenece al lenguaje");
}

<CADENA>{
        [\"] {String tmp=cadena; cadena=""; yybegin(YYINITIAL); return new Symbol(sym.CADENA, yychar,yyline,tmp);}
        [\n] {cadena="";
                errores.NewError("Lexico", "Se esperaba cierre de cadena",yyline+1,yycolumn+1);
                System.out.println("Se esperaba cierre de cadena");
                yybegin(YYINITIAL);
             }
        (\\\")|(\\\")|(\\n)|[^\"] { cadena+=yytext();}
}

<LETRA>{
        [_0-9A-Za-z????] {
                letter+=yytext();
                yybegin(ID); 
                }
                
        [^_0-9A-Za-z????] {String tmp=letter;
                letter="";
                yybegin(YYINITIAL);
                yypushback(1);
                return new Symbol(sym.LETRA, yychar,yyline,tmp);}
}

<ID>{
        [_0-9A-Za-z????] { letter+=yytext();}
        [^_0-9A-Za-z????] {String tmp=letter;
                letter="";
                yybegin(YYINITIAL);
                yypushback(1);
                return new Symbol(sym.ID, yychar,yyline,tmp);}
}
<VERIFICACION>{
        [^\!] {
                String tmp=comentario;
                comentario="";
                yybegin(YYINITIAL);
                yypushback(1);
                return new Symbol(sym.CARACTER, yychar,yyline,tmp);}
        [\!] {
                yybegin(MULTICOMENTARIOS);
                comentario="";
                }
}
<MULTICOMENTARIOS>{
    \! {banderaComentario=true;}
    \> {
            if(banderaComentario){
                yybegin(YYINITIAL);
            }else{
                errores.NewError("Lexico", "Comentario Invalido",yyline+1,yycolumn+1);
            }
        }
    [^\!\>] {}
    
}

