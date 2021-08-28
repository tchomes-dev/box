#define LODD 1
#define STOD 2
#define ADDD 3
#define SUBD 4
#define JPOS 5
#define JZER 6
#define JUMP 7
#define LOCO 8
#define LODL 9
#define STOL 10
#define ADDL 11
#define SUBL 12
#define JNEG 13
#define JNZE 14
#define CALL 15
#define PSHI 16
#define POPI 17
#define PUSH 18
#define POP 19
#define RETN 20
#define SWAP 21
#define INSP 22
#define DESP 23
#define HALT 24
#define INTEG 25
#define JUNK 26
#define LABEL 27
#define LOC 28
#define STR 29
#define MULT 30
#define RSHIFT 31
#define DIV 32

%%

[Ll][Oo][Dd][Dd] return(LODD);
[Ss][Tt][Oo][Dd] return(STOD);
[Aa][Dd][Dd][Dd] return(ADDD);
:
:
[Hh][Aa][Ll][Tt] return(HALT);
[Mm][Uu][Ll][Tt] return(MULT);
[Rr][Ss][Hh][Ii][Ff][Tt] return(RSHIFT);
[Dd][Ii][Vv] return(DIV);

\".+\" return(STR);

-?[0-9][0-9]* return(INTEG);

[A-Za-z][0-9A-Za-z]*: return(LABEL);

\.LOC return(LOC);

;.*\n ;

" " |
"\t" |
"\r" |
"\n" ;

[^ \t\r\n]* return(JUNK);