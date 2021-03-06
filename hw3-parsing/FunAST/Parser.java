/* Generated By:JavaCC: Do not edit this line. Parser.java */
import ast.*;
public class Parser implements ParserConstants {
  public static void main(String[] args) {
     new Parser(System.in);
     try {
       Defn[] program = Top();
       new IndentOutput(System.out).indent(program);
     } catch (ParseException e) {
       System.out.println("Invalid syntax at ("
                          + token.beginColumn + ","
                          + token.beginLine + "), "
                          + token.image);
     } catch (TokenMgrError e) {
       System.out.println(e.getMessage());
     }
  }

//- Parser Functions --------------------------------------------------
  static final public Defn[] Top() throws ParseException {
                Defn[] program;
    program = Program(0);
    jj_consume_token(0);
    {if (true) return program;}
    throw new Error("Missing return statement in function");
  }

  static final public Defn[] Program(int soFar) throws ParseException {
                             Defn d; Defn[] program;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 4:
    case 14:
    case 15:
      d = Defn();
      program = Program(soFar+1);
      program[soFar] = d; {if (true) return program;}
      break;
    default:
      jj_la1[0] = jj_gen;
      {if (true) return new Defn[soFar];}
    }
    throw new Error("Missing return statement in function");
  }

  static final public Defn Defn() throws ParseException {
              Defn d,e;
    if (jj_2_1(3)) {
      d = GlobalVarDecal();
                     {if (true) return d;}
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 4:
      case 14:
      case 15:
        d = Funct();
  {if (true) return d;}
        break;
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    throw new Error("Missing return statement in function");
  }

//Function defnitions 
  static final public Defn Funct() throws ParseException {
                Type t; String name; Formal [] formals; Stmt body;
    t = retType();
    name = name();
    jj_consume_token(1);
    formals = formals(0);
    jj_consume_token(2);
    body = Block();
 {if (true) return new Function (t, name, formals, body);}
    throw new Error("Missing return statement in function");
  }

//Function call list
  static final public StmtExpr FunctCall() throws ParseException {
                         Token t; Expr [] e;
    t = jj_consume_token(IDENT);
    jj_consume_token(1);
    e = ExpArray(0);
    jj_consume_token(2);
                                  {if (true) return new Call(t.image,e);}
    throw new Error("Missing return statement in function");
  }

//Variable declarations 
  static final public Defn GlobalVarDecal() throws ParseException {
                          Type t; VarIntro [] intros; Stmt s;
    t = Type();
    intros = VarIntros(0);
    jj_consume_token(3);
                                      {if (true) return new Globals(t, intros);}
    throw new Error("Missing return statement in function");
  }

//Return types
  static final public Type retType() throws ParseException {
                  Type t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 4:
      jj_consume_token(4);
        {if (true) return null;}
      break;
    case 14:
    case 15:
      t = Type();
                                    {if (true) return t;}
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public String name() throws ParseException {
                Token t;
    t = jj_consume_token(IDENT);
           {if (true) return t.image;}
    throw new Error("Missing return statement in function");
  }

//array expr so for function calls
  static final public Expr [] ExpArray(int soFar) throws ParseException {
                                Expr s; Expr [] array;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 1:
    case INTLIT:
    case IDENT:
      s = Expr();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 5:
        jj_consume_token(5);
        break;
      default:
        jj_la1[3] = jj_gen;
        ;
      }
      array = ExpArray(soFar+1);
    array[soFar]=s; {if (true) return array;}
      break;
    default:
      jj_la1[4] = jj_gen;
     {if (true) return new Expr[soFar];}
    }
    throw new Error("Missing return statement in function");
  }

//Formal parameter array 
  static final public Formal[] formals(int soFar) throws ParseException {
                              Formal f; Formal [] list;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 14:
    case 15:
      f = formalList();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 5:
        jj_consume_token(5);
        break;
      default:
        jj_la1[5] = jj_gen;
        ;
      }
      list = formals(soFar+1);
   list[soFar]=f; {if (true) return list;}
      break;
    default:
      jj_la1[6] = jj_gen;
    {if (true) return new Formal[soFar];}
    }
    throw new Error("Missing return statement in function");
  }

//Formal argument parameters 
  static final public Formal formalList() throws ParseException {
                       Type t; Token r;
    t = Type();
    r = jj_consume_token(IDENT);
                      {if (true) return new Formal(t, r.image);}
    throw new Error("Missing return statement in function");
  }

//Statement block grammar 
  static final public Stmt Stmt() throws ParseException {
              Stmt s, t=null ; Expr n = null; Type p; String name; StmtExpr x;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 11:
      s = Block();
             {if (true) return s;}
      break;
    case 6:
      jj_consume_token(6);
      jj_consume_token(1);
      n = Assign();
      jj_consume_token(2);
      s = Stmt();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 7:
        jj_consume_token(7);
        t = Stmt();
        break;
      default:
        jj_la1[7] = jj_gen;
        ;
      }
                                                  {if (true) return new If (n,s,t);}
      break;
    case 8:
      jj_consume_token(8);
      jj_consume_token(1);
      n = Assign();
      jj_consume_token(2);
      s = Stmt();
                                            {if (true) return new While(n,s);}
      break;
    case 9:
      jj_consume_token(9);
      n = Assign();
      jj_consume_token(3);
                            {if (true) return new Print(n);}
      break;
    case 10:
      jj_consume_token(10);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 1:
      case INTLIT:
      case IDENT:
        n = Assign();
        break;
      default:
        jj_la1[8] = jj_gen;
        ;
      }
      jj_consume_token(3);
                                {if (true) return new Return(n);}
      break;
    case 3:
      jj_consume_token(3);
         {if (true) return new Empty();}
      break;
    case IDENT:
      x = StmtExpr();
      jj_consume_token(3);
                        {if (true) return new ExprStmt(x);}
      break;
    case 14:
    case 15:
      s = Locals();
      jj_consume_token(3);
                      {if (true) return s;}
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public Stmt Block() throws ParseException {
                Stmt[] stmts;
    jj_consume_token(11);
    stmts = Stmts(0);
    jj_consume_token(12);
    {if (true) return new Block(stmts);}
    throw new Error("Missing return statement in function");
  }

//intros is array of varIntros 
  static final public Stmt Locals() throws ParseException {
                   Type t; VarIntro [] intros; Stmt s;
    t = Type();
    intros = VarIntros(0);
                                  {if (true) return new Locals(t, intros);}
    throw new Error("Missing return statement in function");
  }

  static final public InitVarIntro InitVarIntro() throws ParseException {
                               Expr e; Token t;
    t = jj_consume_token(IDENT);
    e = Assign();
                          {if (true) return new InitVarIntro(t.image,e);}
    throw new Error("Missing return statement in function");
  }

  static final public VarIntro VarIntro() throws ParseException {
                       Token t; Expr e, n; VarIntro v;
    if (jj_2_2(4)) {
      t = jj_consume_token(IDENT);
      jj_consume_token(13);
      e = Expr();
                            {if (true) return new InitVarIntro(t.image,e);}
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IDENT:
        t = jj_consume_token(IDENT);
               {if (true) return new VarIntro(t.image);}
        break;
      default:
        jj_la1[10] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    throw new Error("Missing return statement in function");
  }

//array of var intros for local var decals 
  static final public VarIntro [] VarIntros(int soFar) throws ParseException {
                                    VarIntro s; VarIntro [] vars;
    if (jj_2_3(2)) {
      s = VarIntro();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 5:
        jj_consume_token(5);
        break;
      default:
        jj_la1[11] = jj_gen;
        ;
      }
      vars = VarIntros(soFar+1);
    vars[soFar] = s ; {if (true) return vars;}
    } else {
    {if (true) return new VarIntro[soFar];}
    }
    throw new Error("Missing return statement in function");
  }

//create array of statments for block 
  static final public Stmt[] Stmts(int soFar) throws ParseException {
                           Stmt s; Stmt[] stmts;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 3:
    case 6:
    case 8:
    case 9:
    case 10:
    case 11:
    case 14:
    case 15:
    case IDENT:
      s = Stmt();
      stmts = Stmts(soFar+1);
      stmts[soFar]=s; {if (true) return stmts;}
      break;
    default:
      jj_la1[12] = jj_gen;
      {if (true) return new Stmt[soFar];}
    }
    throw new Error("Missing return statement in function");
  }

//Return types of boolean or ints 
  static final public Type Type() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 14:
      jj_consume_token(14);
                {if (true) return Type.INT;}
      break;
    case 15:
      jj_consume_token(15);
                {if (true) return Type.BOOLEAN;}
      break;
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

//StmtExpr is expression used in statement 
//ExprStmt is statement that contains expression 

//Assignment in stmtExpr cast 
  static final public StmtExpr StmtExpr() throws ParseException {
                       Expr e; Token t;
    t = jj_consume_token(IDENT);
    label_1:
    while (true) {
      jj_consume_token(13);
      e = Assign();
                                           {if (true) return new Assign(t.image,e);}
      if (jj_2_4(2)) {
        ;
      } else {
        break label_1;
      }
    }
    throw new Error("Missing return statement in function");
  }

//Assignment Expr used in stmt and exprs 
  static final public Expr Assign() throws ParseException {
                Expr n; Token t;
    if (jj_2_5(2)) {
      t = jj_consume_token(IDENT);
      jj_consume_token(13);
      n = Assign();
                             {if (true) return new Assign(t.image,n);}
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 1:
      case INTLIT:
      case IDENT:
        n = Expr();
              {if (true) return n;}
        break;
      default:
        jj_la1[14] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    throw new Error("Missing return statement in function");
  }

  static final public Expr Expr() throws ParseException {
               Expr n,m;
    n = OR();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 16:
      jj_consume_token(16);
      m = Expr();
                          n= new LOr(n,m);
      break;
    default:
      jj_la1[15] = jj_gen;
      ;
    }
   {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  static final public Expr OR() throws ParseException {
              Expr n,m;
    n = AND();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 17:
      jj_consume_token(17);
      m = OR();
                          n = new LAnd(n,m);
      break;
    default:
      jj_la1[16] = jj_gen;
      ;
    }
   {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  static final public Expr AND() throws ParseException {
              Expr n,m;
    n = COMPARE();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 18:
    case 19:
    case 20:
    case 21:
    case 22:
    case 23:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 18:
        jj_consume_token(18);
        m = COMPARE();
                                    n=new Lt(n,m);
        break;
      case 19:
        jj_consume_token(19);
        m = COMPARE();
                                    n=new Lte(n,m);
        break;
      case 20:
        jj_consume_token(20);
        m = COMPARE();
                                    n=new Gt(n,m);
        break;
      case 21:
        jj_consume_token(21);
        m = COMPARE();
                                    n=new Gte(n,m);
        break;
      case 22:
        jj_consume_token(22);
        m = COMPARE();
                                    n=new Eql(n,m);
        break;
      case 23:
        jj_consume_token(23);
        m = COMPARE();
                                    n=new Neq(n,m);
        break;
      default:
        jj_la1[17] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[18] = jj_gen;
      ;
    }
   {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  static final public Expr COMPARE() throws ParseException {
                  Expr n,m;
    n = ADDSUB();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 24:
      case 25:
        ;
        break;
      default:
        jj_la1[19] = jj_gen;
        break label_2;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 24:
        jj_consume_token(24);
        m = ADDSUB();
                                  n = new Add(n,m);
        break;
      case 25:
        jj_consume_token(25);
        m = ADDSUB();
                                  n = new Sub(n,m);
        break;
      default:
        jj_la1[20] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  static final public Expr ADDSUB() throws ParseException {
                 Expr n,m;
    n = MULTDIV();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 26:
      case 27:
        ;
        break;
      default:
        jj_la1[21] = jj_gen;
        break label_3;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 26:
        jj_consume_token(26);
        m = MULTDIV();
                                   n = new Mul(n,m);
        break;
      case 27:
        jj_consume_token(27);
        m = MULTDIV();
                                 n = new Div(n,m);
        break;
      default:
        jj_la1[22] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
   {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  static final public Expr MULTDIV() throws ParseException {
                   Expr n; Token t; StmtExpr e;
    if (jj_2_6(2)) {
      //lookahead to see if identifier or function call 
         e = FunctCall();
                  {if (true) return e;}
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 1:
        jj_consume_token(1);
        n = Assign();
        jj_consume_token(2);
                         {if (true) return n;}
        break;
      case INTLIT:
      case IDENT:
        n = I();
            {if (true) return n;}
        break;
      default:
        jj_la1[23] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    throw new Error("Missing return statement in function");
  }

  static final public Expr I() throws ParseException {
            Expr e; Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTLIT:
      t = jj_consume_token(INTLIT);
                {if (true) return new IntLit(Integer.parseInt(t.image));}
      break;
    case IDENT:
      t = jj_consume_token(IDENT);
                {if (true) return new Id(t.image);}
      break;
    default:
      jj_la1[24] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  static private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  static private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  static private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  static private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  static private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  static private boolean jj_3R_18() {
    if (jj_3R_20()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_21()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3R_12() {
    if (jj_scan_token(16)) return true;
    return false;
  }

  static private boolean jj_3R_11() {
    if (jj_3R_18()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_19()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3_4() {
    if (jj_scan_token(13)) return true;
    if (jj_3R_8()) return true;
    return false;
  }

  static private boolean jj_3R_5() {
    if (jj_3R_11()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_12()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3R_15() {
    if (jj_3R_5()) return true;
    return false;
  }

  static private boolean jj_3R_8() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_5()) {
    jj_scanpos = xsp;
    if (jj_3R_15()) return true;
    }
    return false;
  }

  static private boolean jj_3_5() {
    if (jj_scan_token(IDENT)) return true;
    if (jj_scan_token(13)) return true;
    return false;
  }

  static private boolean jj_3R_4() {
    if (jj_3R_10()) return true;
    if (jj_3R_7()) return true;
    if (jj_scan_token(3)) return true;
    return false;
  }

  static private boolean jj_3R_9() {
    if (jj_scan_token(IDENT)) return true;
    if (jj_scan_token(1)) return true;
    return false;
  }

  static private boolean jj_3R_17() {
    if (jj_scan_token(15)) return true;
    return false;
  }

  static private boolean jj_3R_16() {
    if (jj_scan_token(14)) return true;
    return false;
  }

  static private boolean jj_3R_10() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_16()) {
    jj_scanpos = xsp;
    if (jj_3R_17()) return true;
    }
    return false;
  }

  static private boolean jj_3R_14() {
    return false;
  }

  static private boolean jj_3_1() {
    if (jj_3R_4()) return true;
    return false;
  }

  static private boolean jj_3R_7() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_3()) {
    jj_scanpos = xsp;
    if (jj_3R_14()) return true;
    }
    return false;
  }

  static private boolean jj_3_3() {
    if (jj_3R_6()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(5)) jj_scanpos = xsp;
    if (jj_3R_7()) return true;
    return false;
  }

  static private boolean jj_3R_13() {
    if (jj_scan_token(IDENT)) return true;
    return false;
  }

  static private boolean jj_3R_6() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_2()) {
    jj_scanpos = xsp;
    if (jj_3R_13()) return true;
    }
    return false;
  }

  static private boolean jj_3_2() {
    if (jj_scan_token(IDENT)) return true;
    if (jj_scan_token(13)) return true;
    if (jj_3R_5()) return true;
    return false;
  }

  static private boolean jj_3R_40() {
    if (jj_scan_token(IDENT)) return true;
    return false;
  }

  static private boolean jj_3R_39() {
    if (jj_scan_token(INTLIT)) return true;
    return false;
  }

  static private boolean jj_3R_38() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_39()) {
    jj_scanpos = xsp;
    if (jj_3R_40()) return true;
    }
    return false;
  }

  static private boolean jj_3R_36() {
    if (jj_scan_token(26)) return true;
    return false;
  }

  static private boolean jj_3R_31() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_36()) {
    jj_scanpos = xsp;
    if (jj_3R_37()) return true;
    }
    return false;
  }

  static private boolean jj_3R_37() {
    if (jj_scan_token(27)) return true;
    return false;
  }

  static private boolean jj_3R_35() {
    if (jj_3R_38()) return true;
    return false;
  }

  static private boolean jj_3R_34() {
    if (jj_scan_token(1)) return true;
    if (jj_3R_8()) return true;
    return false;
  }

  static private boolean jj_3R_33() {
    if (jj_scan_token(25)) return true;
    return false;
  }

  static private boolean jj_3R_23() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_32()) {
    jj_scanpos = xsp;
    if (jj_3R_33()) return true;
    }
    return false;
  }

  static private boolean jj_3R_32() {
    if (jj_scan_token(24)) return true;
    return false;
  }

  static private boolean jj_3_6() {
    if (jj_3R_9()) return true;
    return false;
  }

  static private boolean jj_3R_30() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_6()) {
    jj_scanpos = xsp;
    if (jj_3R_34()) {
    jj_scanpos = xsp;
    if (jj_3R_35()) return true;
    }
    }
    return false;
  }

  static private boolean jj_3R_29() {
    if (jj_scan_token(23)) return true;
    return false;
  }

  static private boolean jj_3R_28() {
    if (jj_scan_token(22)) return true;
    return false;
  }

  static private boolean jj_3R_27() {
    if (jj_scan_token(21)) return true;
    return false;
  }

  static private boolean jj_3R_22() {
    if (jj_3R_30()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_31()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  static private boolean jj_3R_26() {
    if (jj_scan_token(20)) return true;
    return false;
  }

  static private boolean jj_3R_25() {
    if (jj_scan_token(19)) return true;
    return false;
  }

  static private boolean jj_3R_21() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_24()) {
    jj_scanpos = xsp;
    if (jj_3R_25()) {
    jj_scanpos = xsp;
    if (jj_3R_26()) {
    jj_scanpos = xsp;
    if (jj_3R_27()) {
    jj_scanpos = xsp;
    if (jj_3R_28()) {
    jj_scanpos = xsp;
    if (jj_3R_29()) return true;
    }
    }
    }
    }
    }
    return false;
  }

  static private boolean jj_3R_24() {
    if (jj_scan_token(18)) return true;
    return false;
  }

  static private boolean jj_3R_20() {
    if (jj_3R_22()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_23()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  static private boolean jj_3R_19() {
    if (jj_scan_token(17)) return true;
    return false;
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private Token jj_scanpos, jj_lastpos;
  static private int jj_la;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[25];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0xc010,0xc010,0xc010,0x20,0x2,0x20,0xc000,0x80,0x2,0xcf48,0x0,0x20,0xcf48,0xc000,0x2,0x10000,0x20000,0xfc0000,0xfc0000,0x3000000,0x3000000,0xc000000,0xc000000,0x2,0x0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x0,0x44,0x0,0x0,0x0,0x44,0x40,0x40,0x0,0x40,0x0,0x44,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x44,0x44,};
   }
  static final private JJCalls[] jj_2_rtns = new JJCalls[6];
  static private boolean jj_rescan = false;
  static private int jj_gc = 0;

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 25; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 25; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 25; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 25; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 25; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 25; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  static final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  static private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;
  static private int[] jj_lasttokens = new int[100];
  static private int jj_endpos;

  static private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[39];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 25; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 39; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

  static private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 6; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  static private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
