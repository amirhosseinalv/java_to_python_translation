/**
 * Entry point of the OFP compiler. It handles reading the input source file, 
 * initializes the ANTLR lexer and parser, generates the parse tree, and 
 * coordinates the tree traversal using a ParseTreeWalker to trigger listeners.
 */

package ofp;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import generated.ofpLexer;
import generated.ofpParser;

public class Main  {

    public static void main(String[] args)  {
        String testProgram = args.length > 0 ? args[0] : "test.ofp";
        
        if ( !testProgram.endsWith(".ofp") ) {
            System.out.println("\nPrograms most end with suffix .ofp! Found "+testProgram);
            System.exit(-1);
        }
        System.out.println("Reading test program from: "+testProgram);
        
        System.out.println("\nParsing started");
        ofpParser parser = null;
        ofpParser.ProgramContext root = null;
        try {
            CharStream inputStream = CharStreams.fromFileName(testProgram);
            ofpLexer lexer = new ofpLexer( inputStream );		
            parser = new ofpParser(new BufferedTokenStream(lexer));	
            root = parser.program();
        } catch (IOException e) {
            System.out.println("\nCould not read the program: " + e.getMessage());
            System.exit(-1);
        }
        // Without a parse tree there is nothing to analyse (unreadable file)
        if (root == null) {
            System.out.println("\nParsing failed, no syntax tree was produced!");
            System.exit(-1);
        }
        System.out.println("\nParsing completed");

        //Print listener
        ParseTreeWalker walker = new ParseTreeWalker();
        // Uncomment to print every parse tree node type (debug aid, very verbose)
        // PrintListener listener = new PrintListener();
        // walker.walk(listener, root);
        // System.out.println("\nPrint listener completed");

        //Symbol table listener
        SymbolTableListener symbolTableListener = new SymbolTableListener();
        walker.walk(symbolTableListener, root);
        System.out.println("\nSymbol table listener completed");
        System.out.println("\nSymbol table listener error count: " + symbolTableListener.getErrorCount());
        OFPScope globalScope = symbolTableListener.getScopes().get(root);
        System.out.println("\nSymbol Table:");
        globalScope.printSymbolTable(0);
        // globalScope.printTree(0);   // the older, raw print of each scope's map

        //Check ref Listener
        CheckRefListener checkRefListener = new CheckRefListener(symbolTableListener.getScopes());
        walker.walk(checkRefListener, root);
        System.out.println("\nCheck reference listener completed");
        System.out.println("Check reference error count: " + checkRefListener.getErrorCount());

        //Type check visitor
        System.out.println("\nType check visitor started");
        TypeCheckVisitor tc = new TypeCheckVisitor(symbolTableListener.getScopes());
        tc.visit(root);
        System.out.println("\nType check visitor completed");
        System.out.println("\nType check visitor error count: " + tc.getErrorCount());

        // Total over all three analysis phases. Zero errors => the program is valid.
        int totalErrors = symbolTableListener.getErrorCount()
                + checkRefListener.getErrorCount()
                + tc.getErrorCount();
        System.out.println("\n=====================================");
        if (totalErrors == 0) {
            System.out.println("Semantic analysis completed: no errors found");
        } else {
            System.out.println("Semantic analysis completed: " + totalErrors + " error(s) found");
        }
        System.out.println("=====================================");

        // Python code generation (A3) - only valid programs are translated
        if (totalErrors > 0) {
            System.out.println("\nNo Python file generated: fix the errors above first");
            System.exit(1);
        }
        PythonGenerator generator = new PythonGenerator(symbolTableListener.getScopes());
        String pythonCode = generator.visit(root);

        // X.ofp -> out/X.py (a separate folder, so the reference .py files are never overwritten)
        String fileName = Paths.get(testProgram).getFileName().toString();
        Path outputFile = Paths.get("out", fileName.replace(".ofp", ".py"));
        try {
            Files.createDirectories(outputFile.getParent());
            Files.writeString(outputFile, pythonCode);
        } catch (IOException e) {
            System.out.println("\nCould not write the Python file: " + e.getMessage());
            System.exit(-1);
        }
        System.out.println("\nPython code written to: " + outputFile);
    }
}
