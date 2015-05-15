package ast;
import compiler.Failure;

/** An abstract base class for relational binary expressions.
 */
public abstract class RelBinExpr extends BinExpr {

    /** Default constructor.
     */
    public RelBinExpr(Expr left, Expr right) {
        super(left, right);
    }
     
    public Type typeOf(Context ctxt, TypeEnv locals) 
       throws Failure {
        //find the type of theleft operand 
       Type leftType = left.typeOf(ctxt, locals); 
       //find the type of the right operand 
       Type rightType = right.typeOf(ctxt, locals); 
      
       //check if left type and right type are both int/double
                        
      if (leftType.equals(Type.INT) && rightType.equals(Type.DOUBLE)) {
         left = new IntToDouble(left); 
          } 

      if (leftType.equals(Type.DOUBLE) && rightType.equals(Type.INT)) {
         right = new IntToDouble(right); 
      }
      Type leftTypeReturn = left.typeOf(ctxt, locals);
      type = leftTypeReturn;

      return Type.BOOLEAN;
    } 

}
