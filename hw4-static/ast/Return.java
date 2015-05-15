package ast;
import compiler.Failure;

/** Abstract syntax for return statements.
 */
public class Return extends Stmt {

    /** The value that should be returned (or else null).
     */
    private Expr exp;

    /** Default constructor.
     */
    public Return(Expr exp) {
        this.exp = exp;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "Return");
        if (exp==null) {
            out.indent(n, "[no return value]");
        } else {
            exp.indent(out, n+1);
        }
    }

    public TypeEnv check (Context ctxt, TypeEnv locals) 
       throws Failure {
       //check to see if expression is null first so program won't seg fault
       if (exp == null) {
          if (ctxt.retType.isNumeric()) {
             ctxt.report (new Failure ("ReturnValueRequired")); 
          } else if (ctxt.retType.equals(Type.BOOLEAN)){
             ctxt.report (new Failure ("ReturnValueRequired"));
          }              
          
       } else if (exp != null) {
           Type expType = exp.typeOf(ctxt, locals);
           if (ctxt.retType == null) { 
             ctxt.report (new Failure ("ReturnVoidRequired"));
          } else if (!ctxt.retType.equals(expType)) {
             ctxt.report(new Failure("ReturnType")); 
          } else if (ctxt.retType.equals(Type.DOUBLE) && expType.equals(Type.INT)) {
             exp = new IntToDouble(exp); 
          } else if (ctxt.retType.equals(Type.INT) && expType.equals(Type.DOUBLE)) {
             exp = new DoubleToInt(exp); 
          }
       }
        return locals; 
      }
}
