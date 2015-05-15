package ast;
import compiler.Failure;

public class Assign extends StmtExpr {

    /** The variable that we are assigning to.
     */
    private String lhs;

    /** The expression whose value will be saved.
     */
    private Expr rhs;

    /** Default constructor.
     */
    public Assign(String lhs, Expr rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    /** This attribute should be filled in during static analysis to
     *  record the type of the right hand side of this assignment.
     *  This information will be useful for the purposes of code
     *  generation.  The type attribute is set to null when an Assign
     *  node is first created to indicate that the type has yet to be
     *  determined.
     */
    protected Type type = null;

    public Type typeOf (Context ctxt, TypeEnv locals) 
    throws Failure { 
       //find type of left operand having name and type 
       //find type of right hand side        
      
      Type actual = rhs.typeOf(ctxt, locals); 
      type = ctxt.findVar(lhs, locals).getType(); 

      if (type.equals(Type.INT) && actual.equals(Type.DOUBLE)) {
         rhs = new DoubleToInt(rhs); 
      } else if (type.equals(Type.DOUBLE) && actual.equals(Type.INT)) {
         rhs = new IntToDouble(rhs);
      } else if (!type.equals(actual)) {
         ctxt.report(new Failure("InitVarEntryType")); 
      } 
        return type; 
       
    } 
   

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, (type==null) ? "Assign" : ("Assign " + type));
        out.indent(n+1, "\"" + lhs + "\"");
        rhs.indent(out, n+1);
    }
}
