# Code Conventions in Java

## Class and Interface Declarations
* **Class (static) variables**: public, protected, package level (no access modifier), private.
* **Instance variables**: public, protected, package level, private.
* **Methods**: Grouped by functionality (not by scope or accessibility).

## Wrapping Lines
* Break after a comma.
* Break before an operator.
* Prefer higher-level breaks to lower-level breaks.
* Align the new line with the beginning of the expression at the same level on the previous line.

## Declarations
* One declaration per line.
* Put declarations only at the beginning of blocks.
* No space between a method name and the parenthesis "(" starting its parameter list.

## Statements
* Each line should contain at most one statement.
### if, if-else, if else-if else
~~~
if (condition) {
    statements;
}

if (condition) {
    statements;
} else {
    statements;
}

if (condition) {
    statements;
} else if (condition) {
    statements;
} else {
    statements;
}
~~~
### Switch statements
Every time a case falls through (doesn't include a break statement), add a comment (/* falls through */) where the break statement would normally be.  
Every switch statement should include a default case. The break in the default case is redundant, but it prevents a fall-through error if later another case is added.
### try-catch
~~~
try {
    statements;
} catch (ExceptionClass e) {
    statements;
} finally {
    statements;
}
~~~

## Blank Spaces
Casts should be followed by a blank space.
~~~
myMethod((byte) aNum, (Object) x);
~~~

## Naming Conventions
### Variables
* Begin variable with a letter.
* $ is never used at all.
* If the variable name consists of more than one word, capitalize the first letter of each subsequent word.
* For constant variables, capitalize every letter and seperate subsequent words with underscore character.

