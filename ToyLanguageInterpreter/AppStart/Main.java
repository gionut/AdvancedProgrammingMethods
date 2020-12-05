package AppStart;

import controller.Controller;
import exception.TypeCheckerException;
import model.ProgramState;
import model.expressions.*;
import model.hashtable.IHashTable;
import model.hashtable.TypeEnvironment;
import model.statements.*;
import model.types.*;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;
import repository.Repository;
import view.TextMenu;
import view.commands.ExitCommand;
import view.commands.RunExample;



public abstract class Main {

    private static Controller createProgram(String logFilePath, IStatement ex) {
        Repository repository = new Repository(logFilePath);
        IHashTable<String, IType> typeEnvironment = new TypeEnvironment();
        try{
            ex.typeCheck(typeEnvironment);
        }
        catch(TypeCheckerException e)
        {
            System.out.println(e.toString());
        }
        ProgramState program = new ProgramState(ex, repository.getHeap());
        repository.addProgram(program);
        Controller controller = new Controller(repository);
        controller.setDebugFlag(true);
        controller.setLogFlag(true);
        return controller;
    }

    public static void main(String[] args)
    {
        String logFilePath = "E:\\IONUT\\Semestrul 3\\Advanced Programming Methods\\src\\Log\\logFile.txt";
        IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement( new AssignStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));

        Controller controller1 = createProgram(logFilePath, ex1);


        IStatement ex2 = new CompoundStatement( new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ArithmeticExpression('+',new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression('*',new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignStatement("b",new ArithmeticExpression('+',new VariableExpression("a"), new
                                        ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));


        Controller controller2 = createProgram(logFilePath, ex2);

        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                new CompoundStatement(new IfStatement(  new VariableExpression("a"),
                                                        new AssignStatement("v",new ValueExpression(new IntValue(2))),
                                                        new AssignStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));

        Controller controller3 = createProgram(logFilePath, ex3);

        IStatement ex4 = new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
                new CompoundStatement(new AssignStatement("varf", new ValueExpression(new StringValue("E:\\IONUT\\Semestrul 3\\Advanced Programming Methods\\src\\test.in"))),
                new CompoundStatement(new OpenReadFileStatement(new VariableExpression("varf")),
                new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()),
                new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                        new CloseReadFileStatement(new VariableExpression("varf"))))))))));

        Controller controller4 = createProgram(logFilePath, ex4);

        //if( ((2 < 3) and (3 >= 2)) or (2 == 3)))
        IStatement ex5 = new IfStatement(
                        new LogicalExpression(new LogicalExpression(
                        new RelationalExpression(new ValueExpression(new IntValue(2)), new ValueExpression(new IntValue(3)), "<"),
                        new RelationalExpression(new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(2)), ">="), "and"),
                        new RelationalExpression(new ValueExpression(new IntValue(2)), new ValueExpression(new IntValue(3)), "=="),
                        "or"),
                        new PrintStatement(new ValueExpression(new StringValue("OK!"))),
                        new PrintStatement(new ValueExpression(new StringValue("NOT OK!"))));

        Controller controller5 = createProgram(logFilePath, ex5);

        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a) - Exception, v is not a referneceType
        IStatement ex6 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement( "a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement((new VariableExpression("a"))))))));

        Controller controller6 = createProgram(logFilePath, ex6);

        //Ref int v;new(v,20);Ref Ref int a; new(a,v); write(readHeap(v));write(readHeap(readHeap(a))+5)

        IStatement ex7 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                new CompoundStatement(new VariableDeclarationStatement( "a", new ReferenceType(new ReferenceType(new IntType()))),
                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                new PrintStatement(new ArithmeticExpression('+',
                                    new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))),
                                    new ValueExpression(new IntValue(5)))))))));

        Controller controller7 = createProgram(logFilePath, ex7);

        //Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);

        IStatement ex8 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                new CompoundStatement(new WriteHeapStatement("v", new ValueExpression(new IntValue(30))),
                        new PrintStatement(new ArithmeticExpression('+',
                                            new ReadHeapExpression(new VariableExpression("v")),
                                            new ValueExpression(new IntValue(5))))))));

        Controller controller8 = createProgram(logFilePath, ex8);

        //int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStatement ex9 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(4))),
                new CompoundStatement(new WhileStatement(/*new ValueExpression(new IntValue(2))*/new RelationalExpression(
                        new VariableExpression("v"), new ValueExpression(new IntValue(0)), ">"),
                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                new AssignStatement("v",
                                                        new ArithmeticExpression('-',
                                                                new VariableExpression("v"),
                                                                new ValueExpression(new IntValue(1)))))),
                        new PrintStatement(new VariableExpression("v")))));

        Controller controller9 = createProgram(logFilePath, ex9);

        //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        IStatement ex10 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                new CompoundStatement(new NewStatement("a", new VariableExpression("v")),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(30))),
                        new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a")))))))));

        Controller controller10 = createProgram(logFilePath, ex10);

        //int v; Ref int a; v=10;new(a,22);
        //fork(wH(a,30);v=32;print(v);print(rH(a)));
        //print(v);print(rH(a));

        IStatement ex11 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new IntType())),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(10))),
                new CompoundStatement(new NewStatement("a", new ValueExpression(new IntValue(22))),
                new CompoundStatement(new ForkStatement(new CompoundStatement(new WriteHeapStatement("a", new ValueExpression(new IntValue(30))),
                        new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(32))),
                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                        new PrintStatement(new ReadHeapExpression(new VariableExpression("a"))))))),
                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))))))));

        Controller controller11 = createProgram(logFilePath, ex11);

        IStatement ex12 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement( new AssignStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));

        Controller controller12 = createProgram(logFilePath, ex12);

        TextMenu menu = new TextMenu();

        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),controller1));
        menu.addCommand(new RunExample("2",ex2.toString(),controller2));
        menu.addCommand(new RunExample("3",ex3.toString(),controller3));
        menu.addCommand(new RunExample("4",ex4.toString(),controller4));
        menu.addCommand(new RunExample("5",ex5.toString(),controller5));
        menu.addCommand(new RunExample("6",ex6.toString(),controller6));
        menu.addCommand(new RunExample("7",ex7.toString(),controller7));
        menu.addCommand(new RunExample("8",ex8.toString(),controller8));
        menu.addCommand(new RunExample("9",ex9.toString(),controller9));
        menu.addCommand(new RunExample("10",ex10.toString(),controller10));
        menu.addCommand(new RunExample("11",ex11.toString(),controller11));
        menu.show();

    }
}
