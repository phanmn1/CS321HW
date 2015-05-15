package ast;

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

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "Assign");
        out.indent(n+1, "\"" + lhs + "\"");
        rhs.indent(out, n+1);
    }
}
