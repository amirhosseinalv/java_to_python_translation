# OFP Compiler — Assignment 3 (Python code generation)

Course 4DV507 / 4DT902, Linnaeus University.

**Authors:** Amirhossein Alavi and Filippo Draghetti (submitted as a pair).

Given a valid OFP program `X.ofp`, the compiler checks it (Assignment 2) and then
generates an executable Python 3 program `out/X.py` with the same output.

## How to compile and run

Everything is plain Java plus the ANTLR jars in `lib/`. No build tool is needed.

```bash
# from the project root
javac -cp "lib/*" -d bin src/generated/*.java src/ofp/*.java

# translate a program (macOS/Linux)
java -cp "bin:lib/*" ofp.Main arrays.ofp

# run the generated Python program
python3 out/arrays.py

# on Windows the classpath separator is ';'
java -cp "bin;lib/*" ofp.Main arrays.ofp
```

If no file is given on the command line, `test.ofp` is used.

The generated file is written to the `out/` folder (created if missing), so the
reference files `arrays.py` and `strings.py` in the project root are never overwritten.
A Python file is only generated when the semantic analysis finds no errors.

In VS Code the project runs directly with the **Main** launch configuration;
`.vscode/settings.json` already puts `lib/*.jar` on the classpath.

The parser and listeners/visitors in `src/generated/` were produced from `ofp.g4`
with the VS Code ANTLR extension (`"visitors": true`, output directory
`src/generated`). Regenerate only if the grammar changes.

## Translation rules (Assignment 3)

`src/ofp/PythonGenerator.java` is a visitor (`ofpBaseVisitor<String>`): every
`visitXxx` method returns the Python text for its node. Statements return complete
lines (indentation + code + newline); expressions return only the expression text.

| OFP | Python |
|---|---|
| `{ ... }` | indentation (4 spaces per level); an empty block becomes `pass` |
| `int f(int a, int[] b) { ... }` | `def f(a, b):` (no types) |
| body of `void main()` | top-level code at the end, after the `# Program entry point - main` header |
| `int x = 5;` / `int x;` | `x = 5` / `x = 0` (defaults: `0`, `0.0`, `False`, `''`, `""`) |
| `int[] a;` | `a = []` |
| `print(x);` / `println(x);` | `print(x, end='')` / `print(x)` |
| `new int[n]` | `[0]*n` (`[0.0]`, `['']` for float and char; `[0]*(n + 1)` when needed) |
| `{1, 2, 3}` | `[1, 2, 3]` |
| `a.length` | `len(a)` |
| `true` / `false` | `True` / `False` |
| int `a / b` | `int(a / b)` — OFP integer division truncates, Python `/` gives a float |
| `( ... )` | kept, so precedence is preserved |
| names that are Python keywords/builtins | prefixed with `ofp_` everywhere (`len` → `ofp_len`, `max` → `ofp_max`) |

To decide whether `/` is an integer division, the generator tracks the current scope
while it walks and asks `TypeCheckVisitor.typeOf(expr, scope)` for the operand type.

## What the program prints (semantic analysis, Assignment 2)

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
| `src/ofp/PythonGenerator.java` | A3: visitor that translates the parse tree to Python |
| `src/ofp/Main.java` | parses the input, runs the three walks, writes `out/X.py` |

## Test programs

Valid programs (each translates to Python and runs): `arithmetic.ofp`, `arrays.ofp`,
`boolean.ofp`, `float.ofp`, `max.ofp`, `recursion.ofp`, `strings.ofp`, `while.ofp`,
`test_valid.ofp`. The generated `out/strings.py` is identical to the reference
`strings.py`; `out/arrays.py` differs only in spacing (`2 * n` vs `2*n`).

Semantic-analysis tests (Assignment 2):

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

**Code generation**
- Python has no block scope. A variable redeclared in an inner block (shadowing) becomes
  the same Python variable as the outer one, so its value leaks out of the block. The
  reference `arrays.py` translates shadowing the same way (`n` in `reverse`). Example:
  in `test_valid.ofp` the inner `int i = 99;` changes the outer `i`, so the later
  `ints[i - 10] = 8;` fails with an `IndexError` in Python.
- Booleans print as `True`/`False` (Python style), as in the reference files.

**Semantic analysis**

- Execution paths are not analysed, so a non-void function whose body can finish without
  a `return` is not reported. The assignment explicitly excludes this check.
- A bare `return;` inside a void function is accepted.
- Use of an uninitialised variable is not tracked.
- The body of a duplicate function is still type-checked against the first declaration
  with that name, which can produce a follow-on message.
