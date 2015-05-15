package ast;

/** Abstract syntax for print statements.
 */
public class Print extends Stmt {

    /** The value that should be printed out.
     */
    private Expr exp;

    /** Default constructor.
     */
    public Print(Expr exp) {
        this.exp = exp;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, "Print");
        exp.indent(out, n+1);
    }
}
