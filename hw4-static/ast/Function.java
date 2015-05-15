package ast;
import compiler.Failure;

/** Abstract syntax for function definitions.
 */
public class Function extends Defn {

    /** The return type of this function (or null for
     *  a procedure/void function).
     */
    private Type retType;

    /** The name of this function.
     */
    private String name;

    /** The formal parameters for this function.
     */
    private Formal[] formals;

    /** The body of this function.
     */
    private Stmt body;

    /** Default constructor.
     */
    public Function(Type retType, String name, Formal[] formals, Stmt body) {
        this.retType = retType;
        this.name = name;
        this.formals = formals;
        this.body = body;
    }

    /** Print an indented description of this definition.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "Function");
        out.indent(n+1, (retType==null) ? "void" : retType.toString());
        out.indent(n+1, "\"" + name + "\"");
        for (int i=0; i<formals.length; i++) {
            formals[i].indent(out, n+1);
        }
        body.indent(out, n+1);
    }

    /** Extend the environments in the given context with entries from
     *  this definition.
     */
    void extendGlobalEnv(Context ctxt)
      throws Failure {
        // Check that there is no previously defined function with
        // the same name:
        if (FunctionEnv.find(name, ctxt.functions)!=null) {
            ctxt.report(new Failure("FunctionNamesUnique"));
        }
  
        // Extend the function environment with a new entry for this
        // definition:
        ctxt.functions = new FunctionEnv(name, this, ctxt.functions);
    }

    /** Check that this is a valid function definition.
     */
    void check(Context ctxt)
      throws Failure {
        // Check for duplicate names in the formal parameter list:
        if (Formal.containsRepeats(formals)) {
            ctxt.report(new Failure("FormalsUnique"));
        }
  
        // Build an environment for this function's local variables:
        TypeEnv locals = TypeEnv.empty;
        for (int i=0; i<formals.length; i++) {
            locals = formals[i].extend(locals);
        }
  
        // Type check the body of this function:
        ctxt.retType = this.retType;
        body.check(ctxt, locals);
    }
    //get formal length of function calls
    public int formalLength() {
       return formals.length; 
    }
  
   void checkargs(Context ctxt, TypeEnv locals, Expr[] exprs)
        throws Failure {

           if(exprs.length != formals.length)
                  throw new Failure("CallNumberOfArgs");


           for(int i=0; i < formals.length; i++) {
                  Type formalType = formals[i].getType();
                  Type argType = exprs[i].typeOf(ctxt, locals);

                if(formalType.equals(Type.INT) && argType.equals(Type.DOUBLE)) {
                        exprs[i] = new DoubleToInt(exprs[i]);
                      }

             else if(formalType.equals(Type.DOUBLE) && argType.equals(Type.INT)) {
                                exprs[i] = new IntToDouble(exprs[i]);
                        }

                        else if(!formalType.equals(argType)) {
                                ctxt.report(new Failure("FormalTypeMismatch"));
                        }
                }


        }


    public Type getRetType() {
        return retType;
    } 
}
