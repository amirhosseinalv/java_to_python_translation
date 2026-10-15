// Generated from /Users/amirhossein/University/4DT902-Compiler/Assignment_2/ofp.g4 by ANTLR 4.13.1

    package generated;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ofpParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ofpVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ofpParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(ofpParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link ofpParser#main}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMain(ofpParser.MainContext ctx);
	/**
	 * Visit a parse tree produced by {@link ofpParser#funcDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDecl(ofpParser.FuncDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link ofpParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(ofpParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valueParam}
	 * labeled alternative in {@link ofpParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueParam(ofpParser.ValueParamContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayParam}
	 * labeled alternative in {@link ofpParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayParam(ofpParser.ArrayParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link ofpParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(ofpParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ofpParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(ofpParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ofpParser#retType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRetType(ofpParser.RetTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ofpParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(ofpParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclStmt(ofpParser.DeclStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStmt(ofpParser.AssignStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayAssignStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAssignStmt(ofpParser.ArrayAssignStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStmt(ofpParser.PrintStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallStmt(ofpParser.CallStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(ofpParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(ofpParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link ofpParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(ofpParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDecl}
	 * labeled alternative in {@link ofpParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(ofpParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayDecl}
	 * labeled alternative in {@link ofpParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayDecl(ofpParser.ArrayDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link ofpParser#funcCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCall(ofpParser.FuncCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code charExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharExpr(ofpParser.CharExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntExpr(ofpParser.IntExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addSubExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpr(ofpParser.AddSubExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(ofpParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringExpr(ofpParser.StringExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code indexExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexExpr(ofpParser.IndexExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lengthExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLengthExpr(ofpParser.LengthExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code floatExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatExpr(ofpParser.FloatExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code negExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegExpr(ofpParser.NegExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayLiteralExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLiteralExpr(ofpParser.ArrayLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExpr(ofpParser.CallExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExpr(ofpParser.BoolExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulDivExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivExpr(ofpParser.MulDivExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newArrayExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewArrayExpr(ofpParser.NewArrayExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExpr(ofpParser.IdExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compareExpr}
	 * labeled alternative in {@link ofpParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareExpr(ofpParser.CompareExprContext ctx);
}