package ast;
import compiler.Failure;

/** An abstract base class for arithmetic binary expressions.
 */
public abstract class ArithBinExpr extends BinExpr {

    /** Default constructor.
     */
    public ArithBinExpr(Expr left, Expr right) {
        super(left, right);
    }
    

    public Type typeOf(Context ctxt, TypeEnv locals) 
       throws Failure {
        //find the type of theleft operand 
       Type leftType = left.typeOf(ctxt, locals); 
       //find the type of the right operand 
       Type rightType = right.typeOf(ctxt, locals); 
       Type leftTypeReturn = left.typeOf(ctxt, locals);
       type = leftTypeReturn;

      
       //check if left type and right type are both int/double
       if ((rightType.equals(Type.DOUBLE) || rightType.equals(Type.INT)) 
           && (leftType.equals(Type.DOUBLE) || leftType.equals(Type.INT))) {
          
           if (rightType.equals(Type.INT) && leftType.equals(Type.INT)) {
              return Type.INT; 
           } else {
           if (rightType.equals(Type.INT) && leftType.equals(Type.DOUBLE)) {
               right = new IntToDouble(right); 
               type = right.typeOf(ctxt, locals); 
           } else if (rightType.equals(Type.DOUBLE) && leftType.equals(Type.INT)) {
               left = new IntToDouble(left); 
               type = left.typeOf(ctxt, locals);
           }
           
           return Type.DOUBLE;
              }
        } else {
          ctxt.report (new Failure("ArithBinArgsNumeric")); 
        }
         
        return leftType; 
     }    
}
