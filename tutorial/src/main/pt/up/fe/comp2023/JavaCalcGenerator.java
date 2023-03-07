package pt.up.fe.comp2023;

import pt.up.fe.comp.jmm.ast.AJmmVisitor;
import pt.up.fe.comp.jmm.ast.JmmNode;

public class JavaCalcGenerator extends AJmmVisitor<String, String> {
    private String className;

    public JavaCalcGenerator(String className) {
        this.className = className;
    }

    protected void builVisitor() {
        addVisit("Program", this::dealWithProgram);
        addVisit("Assignment", this::dealWithAssignment);
        addVisit("Integer", this::dealWithLiteral);
        addVisit("Identifier", this::dealWithLiteral);
    }

    private String dealWithProgram(JmmNode jmmNode, String s) {
        s = (s != null ? s : "");
        String ret = s + "public class " + this.className + "{\n";
        String s2 = s + "\t";
        ret += s2 + "public static void main(String[] args) {\n";

        for(jmmNode child: jmmNode.getChildren()) {
            ret += visit(child, s2 + "\t");
            ret += "\n";
        }

        ret += s2 + "}\n";
        ret += s + "}\n";
        return ret;
    }
}
