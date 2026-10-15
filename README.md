# OFP Compiler — Assignment 2 (symbol table and semantic analysis)

Course 4DV507 / 4DT902, Linnaeus University.

**Authors:** Amirhossein Alavi and Filippo Draghetti (submitted as a pair).

## How to compile and run

Everything is plain Java plus the ANTLR jars in `lib/`. No build tool is needed.

```bash
# from the project root
javac -cp "lib/*" -d bin src/generated/*.java src/ofp/*.java

# analyse a program (macOS/Linux)
java -cp "bin:lib/*" ofp.Main test_valid.ofp

# on Windows the classpath separator is ';'
java -cp "bin;lib/*" ofp.Main test_valid.ofp
```

If no file is given on the command line, `test.ofp` is used.

In VS Code the project runs directly with the **Main** launch configuration;
`.vscode/settings.json` already puts `lib/*.jar` on the classpath.

The parser and listeners/visitors in `src/generated/` were produced from `ofp.g4`
with the VS Code ANTLR extension (`"visitors": true`, output directory
`src/generated`). Regenerate only if the grammar changes.

## What the program prints

1. the symbol table as an indented scope tree
2. one line per semantic error, each with its line number
3. a combined total over all three analysis phases

```
Symbol Table:
-global
    sumTo : int
    main : void
  -function sumTo
      n : int
      i : int

Error (line 13): Duplicate 'sumUpTo' function declaration
=====================================
Semantic analysis completed: 1 error(s) found
=====================================
```

The analysis never stops at the first error.

## Source files

| File | Role |
|---|---|
| `ofp.g4` | the OFP grammar (alternative labels on statements and expressions) |
| `src/ofp/OFPType.java` | one object per OFP type, plus `ERROR` for already-reported expressions |
| `src/ofp/OFPSymbol.java` | a declared name and its type |
| `src/ofp/OFPParamSymbol.java` | a function parameter |
| `src/ofp/OFPFunctionSymbol.java` | a function: return type plus its parameters in order |
| `src/ofp/OFPScope.java` | one scope: name→symbol map, enclosing scope, child scopes, printing |
| `src/ofp/SymbolTableListener.java` | walk 1: builds the scope tree, reports duplicate declarations |
| `src/ofp/CheckRefListener.java` | walk 2: reports undeclared identifiers and calls |
| `src/ofp/TypeCheckVisitor.java` | walk 3: all type checking |
| `src/ofp/PrintListener.java` | optional debug print of the parse tree (disabled in `Main`) |
| `src/ofp/ScopeTest.java` | small standalone test of the scope classes |
| `src/ofp/Main.java` | parses the input and runs the three walks |

## Test programs

| File | Expected |
|---|---|
| `test_valid.ofp` | 0 errors — exercises every construct that must be accepted |
| `test.ofp` | 1 error (a duplicate function) |
| `test_duplicates.ofp` | 6 errors — duplicate variables, parameters and functions; legal shadowing ignored |
| `test_undeclared.ofp` | 9 errors — undeclared variables, arrays and function calls, out-of-scope use |
| `test_types.ofp` | 15 errors — operators, conditions, assignment, returns, print |
| `test_arrays.ofp` | 19 errors — literals, `new`, `.length`, indexing, element types, array parameters |
| `test_calls.ofp` | 22 errors — argument count, order and types, call result type, void misuse |

Every line in the error files is commented with the mistake it is meant to trigger.

## Checks implemented

**Walk 1 — symbol table**
- a scope for the program, each function and each nested block
- a function body shares the function's scope, so a parameter and a local with the
  same name are a duplicate
- variables, parameters and functions are defined with their types; a function keeps
  its parameters in order
- duplicate declarations in the same scope are reported and not added; shadowing in an
  inner scope is allowed

**Walk 2 — references**
- every identifier must resolve in the current scope chain
- function calls are resolved in the global scope, so a local variable cannot hide a
  function, and the symbol must really be a function

**Walk 3 — types**
- operands of `+ - * /` must match and be int or float (no string concatenation)
- `<` and `>` need int or float; `==` also allows char, never string, bool or arrays
- assignment, declaration initialisers, and array element assignment must match exactly
  (OFP has no implicit conversion)
- `if` and `while` conditions must be bool
- `print`/`println` take int, float, bool, char or string, never an array
- `return` must match the declared type; void functions may not return a value
- calls: argument count, order and types against the parameter list; the call's type is
  the function's return type
- `[]` and `.length` only on arrays or strings; the index must be int; the element type
  of `int[]` is int and of a string is char
- strings are immutable, so `s[0] = 'x'` is rejected
- array literals must be homogeneous, and `new int[n]` needs an int size

## Known limitations

- Execution paths are not analysed, so a non-void function whose body can finish without
  a `return` is not reported. The assignment explicitly excludes this check.
- A bare `return;` inside a void function is accepted.
- Use of an uninitialised variable is not tracked.
- The body of a duplicate function is still type-checked against the first declaration
  with that name, which can produce a follow-on message.
