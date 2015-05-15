package ast;

/** An abstract base class for binary expressions (i.e.,
 *  expressions that have a left and a right operand).
 */
public abstract class BinExpr extends Expr {

    /** The left subexpression.
     */
    protected Expr left;

    /** The right subexpression.
     */
    protected Expr right;

    /** Default constructor.
     */
    public BinExpr(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public void indent(IndentOutput out, int n) {
        out.indent(n, label());
        left.indent(out, n+1);
        right.indent(out, n+1);
    }

    /** Return a string that provides a simple description of this
     *  particular type of operator node.
     */
    abstract String label();
}
