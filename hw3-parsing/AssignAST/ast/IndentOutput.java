package ast;

/** Represents an output phase for producing textual output of
 *  abstract syntax trees using indentation.
 */
public class IndentOutput {

    private java.io.PrintStream out;

    /** Default constructor.
     */
    public IndentOutput(java.io.PrintStream out) {
        this.out = out;
    }

    /** Print a given String message indented some number of
     *  spaces (currently two times the given nesting level, n).
     */
    public void indent(int n, String msg) {
        for (int i=0; i<n; i++) {
            out.print("  ");
        }
        out.println(msg);
    }

    /** Output an indented description of the abstract syntax
     *  tree for the given expression.
     */
    public void indent(Expr e) {
        e.indent(this, 0);
    }
}
