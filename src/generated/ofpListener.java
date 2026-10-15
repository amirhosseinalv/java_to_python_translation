// Generated from /Users/amirhossein/University/4DT902-Compiler/Assignment_2/ofp.g4 by ANTLR 4.13.1

    package generated;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ofpParser}.
 */
public interface ofpListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ofpParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(ofpParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ofpParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(ofpParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link ofpParser#main}.
	 * @param ctx the parse tree
	 */
	void enterMain(ofpParser.MainContext ctx);
	/**
	 * Exit a parse tree produced by {@link ofpParser#main}.
	 * @param ctx the parse tree
	 */
	void exitMain(ofpParser.MainContext ctx);
	/**
	 * Enter a parse tree produced by {@link ofpParser#funcDecl}.
	 * @param ctx the parse tree
	 */
	void enterFuncDecl(ofpParser.FuncDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ofpParser#funcDecl}.
	 * @param ctx the parse tree
	 */
	void exitFuncDecl(ofpParser.FuncDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ofpParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(ofpParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ofpParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(ofpParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valueParam}
	 * labeled alternative in {@link ofpParser#param}.
	 * @param ctx the parse tree
	 */
	void enterValueParam(ofpParser.ValueParamContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valueParam}
	 * labeled alternative in {@link ofpParser#param}.
	 * @param ctx the parse tree
	 */
	void exitValueParam(ofpParser.ValueParamContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayParam}
	 * labeled alternative in {@link ofpParser#param}.
	 * @param ctx the parse tree
	 */
	void enterArrayParam(ofpParser.ArrayParamContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayParam}
	 * labeled alternative in {@link ofpParser#param}.
	 * @param ctx the parse tree
	 */
	void exitArrayParam(ofpParser.ArrayParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link ofpParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(ofpParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ofpParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(ofpParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ofpParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(ofpParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ofpParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(ofpParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ofpParser#retType}.
	 * @param ctx the parse tree
	 */
	void enterRetType(ofpParser.RetTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ofpParser#retType}.
	 * @param ctx the parse tree
	 */
	void exitRetType(ofpParser.RetTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ofpParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(ofpParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ofpParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(ofpParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterDeclStmt(ofpParser.DeclStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitDeclStmt(ofpParser.DeclStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterAssignStmt(ofpParser.AssignStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitAssignStmt(ofpParser.AssignStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayAssignStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterArrayAssignStmt(ofpParser.ArrayAssignStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayAssignStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitArrayAssignStmt(ofpParser.ArrayAssignStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterPrintStmt(ofpParser.PrintStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitPrintStmt(ofpParser.PrintStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterCallStmt(ofpParser.CallStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitCallStmt(ofpParser.CallStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(ofpParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(ofpParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(ofpParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(ofpParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(ofpParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(ofpParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDecl}
	 * labeled alternative in {@link ofpParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(ofpParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDecl}
	 * labeled alternative in {@link ofpParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(ofpParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayDecl}
	 * labeled alternative in {@link ofpParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterArrayDecl(ofpParser.ArrayDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayDecl}
	 * labeled alternative in {@link ofpParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitArrayDecl(ofpParser.ArrayDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ofpParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void enterFuncCall(ofpParser.FuncCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link ofpParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void exitFuncCall(ofpParser.FuncCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code charExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCharExpr(ofpParser.CharExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code charExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCharExpr(ofpParser.CharExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIntExpr(ofpParser.IntExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIntExpr(ofpParser.IntExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addSubExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpr(ofpParser.AddSubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addSubExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpr(ofpParser.AddSubExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(ofpParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(ofpParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterStringExpr(ofpParser.StringExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitStringExpr(ofpParser.StringExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code indexExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIndexExpr(ofpParser.IndexExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code indexExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIndexExpr(ofpParser.IndexExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lengthExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLengthExpr(ofpParser.LengthExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lengthExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLengthExpr(ofpParser.LengthExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code floatExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFloatExpr(ofpParser.FloatExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code floatExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFloatExpr(ofpParser.FloatExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code negExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNegExpr(ofpParser.NegExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code negExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNegExpr(ofpParser.NegExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayLiteralExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterArrayLiteralExpr(ofpParser.ArrayLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayLiteralExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitArrayLiteralExpr(ofpParser.ArrayLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCallExpr(ofpParser.CallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCallExpr(ofpParser.CallExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpr(ofpParser.BoolExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpr(ofpParser.BoolExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulDivExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDivExpr(ofpParser.MulDivExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulDivExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDivExpr(ofpParser.MulDivExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newArrayExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNewArrayExpr(ofpParser.NewArrayExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newArrayExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNewArrayExpr(ofpParser.NewArrayExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdExpr(ofpParser.IdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdExpr(ofpParser.IdExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compareExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCompareExpr(ofpParser.CompareExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compareExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCompareExpr(ofpParser.CompareExprContext ctx);
}