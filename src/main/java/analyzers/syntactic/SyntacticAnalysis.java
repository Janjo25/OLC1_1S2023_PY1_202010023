
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package main.java.analyzers.syntactic;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class SyntacticAnalysis extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  @Deprecated
  public SyntacticAnalysis() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public SyntacticAnalysis(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public SyntacticAnalysis(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\031\000\002\002\004\000\002\006\005\000\002\003" +
    "\007\000\002\013\011\000\002\013\010\000\002\005\003" +
    "\000\002\005\003\000\002\014\005\000\002\004\005\000" +
    "\002\004\003\000\002\007\003\000\002\007\003\000\002" +
    "\007\003\000\002\011\007\000\002\011\006\000\002\010" +
    "\005\000\002\010\004\000\002\010\005\000\002\010\004" +
    "\000\002\010\004\000\002\010\003\000\002\002\003\000" +
    "\002\002\005\000\002\012\007\000\002\012\006" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\103\000\004\015\005\001\002\000\004\002\105\001" +
    "\002\000\004\012\007\001\002\000\004\021\104\001\002" +
    "\000\004\007\077\001\002\000\006\012\011\014\012\001" +
    "\002\000\004\007\057\001\002\000\004\005\054\001\002" +
    "\000\006\014\014\023\015\001\002\000\004\005\030\001" +
    "\002\000\004\023\016\001\002\000\004\014\020\001\002" +
    "\000\006\014\024\021\uffff\001\002\000\004\007\021\001" +
    "\002\000\004\024\022\001\002\000\004\022\023\001\002" +
    "\000\006\014\uffe9\021\uffe9\001\002\000\004\007\025\001" +
    "\002\000\004\024\026\001\002\000\004\022\027\001\002" +
    "\000\006\014\uffea\021\uffea\001\002\000\020\004\037\006" +
    "\035\011\036\015\031\017\033\020\041\024\034\001\002" +
    "\000\004\014\052\001\002\000\022\004\uffed\006\uffed\011" +
    "\uffed\015\uffed\017\uffed\020\uffed\022\uffed\024\uffed\001\002" +
    "\000\020\004\037\006\035\011\036\015\031\017\033\020" +
    "\041\024\034\001\002\000\022\004\uffec\006\uffec\011\uffec" +
    "\015\uffec\017\uffec\020\uffec\022\uffec\024\uffec\001\002\000" +
    "\020\004\037\006\035\011\036\015\031\017\033\020\041" +
    "\024\034\001\002\000\020\004\037\006\035\011\036\015" +
    "\031\017\033\020\041\024\034\001\002\000\020\004\037" +
    "\006\035\011\036\015\031\017\033\020\041\024\034\001" +
    "\002\000\004\022\043\001\002\000\020\004\037\006\035" +
    "\011\036\015\031\017\033\020\041\024\034\001\002\000" +
    "\022\004\uffee\006\uffee\011\uffee\015\uffee\017\uffee\020\uffee" +
    "\022\uffee\024\uffee\001\002\000\006\014\ufff4\023\ufff4\001" +
    "\002\000\020\004\037\006\035\011\036\015\031\017\033" +
    "\020\041\024\034\001\002\000\022\004\ufff2\006\ufff2\011" +
    "\ufff2\015\ufff2\017\ufff2\020\ufff2\022\ufff2\024\ufff2\001\002" +
    "\000\020\004\037\006\035\011\036\015\031\017\033\020" +
    "\041\024\034\001\002\000\022\004\ufff0\006\ufff0\011\ufff0" +
    "\015\ufff0\017\ufff0\020\ufff0\022\ufff0\024\ufff0\001\002\000" +
    "\022\004\ufff1\006\ufff1\011\ufff1\015\ufff1\017\ufff1\020\ufff1" +
    "\022\ufff1\024\ufff1\001\002\000\022\004\uffef\006\uffef\011" +
    "\uffef\015\uffef\017\uffef\020\uffef\022\uffef\024\uffef\001\002" +
    "\000\004\021\053\001\002\000\022\004\uffeb\006\uffeb\011" +
    "\uffeb\015\uffeb\017\uffeb\020\uffeb\022\uffeb\024\uffeb\001\002" +
    "\000\020\004\037\006\035\011\036\015\031\017\033\020" +
    "\041\024\034\001\002\000\004\022\056\001\002\000\006" +
    "\014\ufff3\023\ufff3\001\002\000\004\014\060\001\002\000" +
    "\004\005\061\001\002\000\010\013\070\016\065\025\066" +
    "\001\002\000\004\022\ufffc\001\002\000\010\010\072\022" +
    "\ufff8\026\073\001\002\000\004\022\ufffb\001\002\000\010" +
    "\010\ufff6\022\ufff6\026\ufff6\001\002\000\010\010\ufff5\022" +
    "\ufff5\026\ufff5\001\002\000\004\022\071\001\002\000\010" +
    "\010\ufff7\022\ufff7\026\ufff7\001\002\000\006\012\ufffe\014" +
    "\ufffe\001\002\000\010\013\070\016\065\025\066\001\002" +
    "\000\010\013\070\016\065\025\066\001\002\000\004\022" +
    "\ufffa\001\002\000\006\010\072\022\ufff8\001\002\000\004" +
    "\022\ufff9\001\002\000\004\014\100\001\002\000\004\005" +
    "\101\001\002\000\010\013\070\016\065\025\066\001\002" +
    "\000\004\022\103\001\002\000\006\012\ufffd\014\ufffd\001" +
    "\002\000\004\002\000\001\002\000\004\002\001\001\002" +
    "" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\103\000\004\006\003\001\001\000\002\001\001\000" +
    "\006\003\005\013\007\001\001\000\002\001\001\000\002" +
    "\001\001\000\004\011\012\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\004\012\016\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\006\002\031\010\037\001\001" +
    "\000\002\001\001\000\002\001\001\000\006\002\031\010" +
    "\050\001\001\000\002\001\001\000\006\002\031\010\047" +
    "\001\001\000\006\002\031\010\045\001\001\000\006\002" +
    "\031\010\043\001\001\000\002\001\001\000\006\002\031" +
    "\010\041\001\001\000\002\001\001\000\002\001\001\000" +
    "\006\002\031\010\044\001\001\000\002\001\001\000\006" +
    "\002\031\010\046\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\006\002\031\010\054\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\012" +
    "\004\063\005\066\007\062\014\061\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\006\004\075\007\074\001\001\000\004" +
    "\007\073\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\012" +
    "\004\063\005\101\007\062\014\061\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$SyntacticAnalysis$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$SyntacticAnalysis$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$SyntacticAnalysis$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$SyntacticAnalysis$actions {
  private final SyntacticAnalysis parser;

  /** Constructor */
  CUP$SyntacticAnalysis$actions(SyntacticAnalysis parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$SyntacticAnalysis$do_action_part00000000(
    int                        CUP$SyntacticAnalysis$act_num,
    java_cup.runtime.lr_parser CUP$SyntacticAnalysis$parser,
    java.util.Stack            CUP$SyntacticAnalysis$stack,
    int                        CUP$SyntacticAnalysis$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$SyntacticAnalysis$result;

      /* select the action based on the action number */
      switch (CUP$SyntacticAnalysis$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= program EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.elementAt(CUP$SyntacticAnalysis$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.elementAt(CUP$SyntacticAnalysis$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$SyntacticAnalysis$stack.elementAt(CUP$SyntacticAnalysis$top-1)).value;
		RESULT = start_val;
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.elementAt(CUP$SyntacticAnalysis$top-1)), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$SyntacticAnalysis$parser.done_parsing();
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // program ::= LEFT_CURLY_BRACKET code_block RIGHT_CURLY_BRACKET 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("program",4, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.elementAt(CUP$SyntacticAnalysis$top-2)), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // code_block ::= sets regular_expression_input SEPARATOR SEPARATOR regular_expression_output 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("code_block",1, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.elementAt(CUP$SyntacticAnalysis$top-4)), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // sets ::= sets CONJ COLON IDENTIFIER ARROW notation SEMICOLON 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("sets",9, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.elementAt(CUP$SyntacticAnalysis$top-6)), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // sets ::= CONJ COLON IDENTIFIER ARROW notation SEMICOLON 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("sets",9, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.elementAt(CUP$SyntacticAnalysis$top-5)), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // notation ::= tilde_set 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("notation",3, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // notation ::= comma_set 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("notation",3, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // tilde_set ::= range TILDE range 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("tilde_set",10, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.elementAt(CUP$SyntacticAnalysis$top-2)), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // comma_set ::= range COMMA comma_set 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("comma_set",2, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.elementAt(CUP$SyntacticAnalysis$top-2)), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // comma_set ::= range 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("comma_set",2, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // range ::= DIGIT 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("range",5, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // range ::= LETTER 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("range",5, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // range ::= SYMBOL 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("range",5, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // regular_expression_input ::= regular_expression_input IDENTIFIER ARROW regular_expression SEMICOLON 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("regular_expression_input",7, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.elementAt(CUP$SyntacticAnalysis$top-4)), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // regular_expression_input ::= IDENTIFIER ARROW regular_expression SEMICOLON 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("regular_expression_input",7, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.elementAt(CUP$SyntacticAnalysis$top-3)), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // regular_expression ::= ALTERNATION regular_expression regular_expression 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("regular_expression",6, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.elementAt(CUP$SyntacticAnalysis$top-2)), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // regular_expression ::= ASTERISK regular_expression 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("regular_expression",6, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.elementAt(CUP$SyntacticAnalysis$top-1)), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // regular_expression ::= CONCATENATION regular_expression regular_expression 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("regular_expression",6, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.elementAt(CUP$SyntacticAnalysis$top-2)), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // regular_expression ::= PLUS regular_expression 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("regular_expression",6, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.elementAt(CUP$SyntacticAnalysis$top-1)), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // regular_expression ::= QUESTION_MARK regular_expression 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("regular_expression",6, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.elementAt(CUP$SyntacticAnalysis$top-1)), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // regular_expression ::= characters 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("regular_expression",6, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // characters ::= STRING 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("characters",0, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // characters ::= LEFT_CURLY_BRACKET IDENTIFIER RIGHT_CURLY_BRACKET 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("characters",0, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.elementAt(CUP$SyntacticAnalysis$top-2)), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // regular_expression_output ::= regular_expression_output IDENTIFIER COLON STRING SEMICOLON 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("regular_expression_output",8, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.elementAt(CUP$SyntacticAnalysis$top-4)), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // regular_expression_output ::= IDENTIFIER COLON STRING SEMICOLON 
            {
              Object RESULT =null;
		  
              CUP$SyntacticAnalysis$result = parser.getSymbolFactory().newSymbol("regular_expression_output",8, ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.elementAt(CUP$SyntacticAnalysis$top-3)), ((java_cup.runtime.Symbol)CUP$SyntacticAnalysis$stack.peek()), RESULT);
            }
          return CUP$SyntacticAnalysis$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$SyntacticAnalysis$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$SyntacticAnalysis$do_action(
    int                        CUP$SyntacticAnalysis$act_num,
    java_cup.runtime.lr_parser CUP$SyntacticAnalysis$parser,
    java.util.Stack            CUP$SyntacticAnalysis$stack,
    int                        CUP$SyntacticAnalysis$top)
    throws java.lang.Exception
    {
              return CUP$SyntacticAnalysis$do_action_part00000000(
                               CUP$SyntacticAnalysis$act_num,
                               CUP$SyntacticAnalysis$parser,
                               CUP$SyntacticAnalysis$stack,
                               CUP$SyntacticAnalysis$top);
    }
}

}
