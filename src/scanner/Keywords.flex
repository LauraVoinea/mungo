// 3.9 Keywords
<YYINITIAL> {
  // 3.9 Keyword
  "typestate"                    { return sym(Terminals.TYPESTATE); }
//  "Typestate"                    { return sym(Terminals.TYPESTATE_ANNOT); }
/*  "start"                        { return sym(Terminals.START); }*/
/*  "from"                         { return sym(Terminals.FROM); }*/
/*  "session"                      { return sym(Terminals.SESSION); }*/
/*  "where"                        { return sym(Terminals.WHERE); }*/
  "end"                          { return sym(Terminals.END); }
  "enum"                         { return sym(Terminals.ENUM); }
}
