import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        String s = "";
//        if(s.matches("^[(].*[)]$")){
//            System.out.println("11111");
//        }
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNext()){
            s = scanner.nextLine();
        }
        scanner.close();
        InputReader inputReader = new InputReader(s);
        if (!inputReader.isValid(inputReader.getData())) {
            System.out.print("WRONG FORMAT!");
            System.out.println("11");
            return;
        }
        Expressions poly = new Expressions(inputReader.getProcessed());
        try {
            String output = poly.derivative();
            Expressions op = new Expressions(output);
            System.out.println(op.getData().replaceFirst("^\\+", ""));//去除前面的加号
        } catch (Exception e) {
            System.out.println("WRONG FORMAT!");
            System.out.println("22");
        }
    }
//                "+6*cos((cos(x)*x++1*(x**+0*x*7-+x*x**+8*sin(+ 030))))**+7*x",
//                "sin(x**9)**+5*cos(sin((--x**+7)**1))+-+3*x**7*sin(x)**+6",
//                "cos(    cos(x**+7))**+7*(sin((cos((sin((cos(((x**+3)))**+6))))))",
//                "-6*sin(sin((cos(sin((x**9*x**6*x**3**1)))++sin(x**+5)**6)))*x",
//                "-0*cos((99*x**9223372036854775809))+8*sin((sin(x)*sin(9)))**+8",
//                "7*x*cos((5*sin(cos((-x*sin(sin(cos(x)**+3)))))))-+x*x*cos(sin(x**+2))",//6
//                "x**+4*cos(cos((x*sin(((())))+sin(x**2))))*-4*(sin((3*x**6+x*x**5)))",
//                "-2*cos((x*cos(x**6)**3))-(-x**-34*sin(cos(x))*(+8*-7)+cos(0)-1)*x**40",//8
//                "sin((x+sin(x)*(1-sin(x)+sin((x))**-1)-cos(x)**2))+sin(x)*cos(x)**-1",
//                "sin((-sin((-cos((-sin((x**4*x-7*x**2*x**3))))))))**8+x**5*sin(x)**2",//10
//                "+-6*sin((x**+9*(  sin(sin(x))**+1*x**-7*sin(x**5)---9*(3) )))*cos(x)",
//                "+-6*sin( (x**+9 *( (sin(sin(x))**+1*x**-7*sin(x**5)---9*(3)))))*cos(x)",
//                "((sin(cos(x**+6)**+2)*sin(x))*x-cos(x**8)*sin(x))*(9*-5*sin(x))",
//                "((x*cos((x**5*2)))*cos(x**+6)**+8*sin(x)-+x**+2*x)*x**50*x**-43*8",
//                "3*sin(x**+6)-+sin(x**+7)*sin(cos(x**+7)**+7)*sin(cos((x**-2*x**2)))",
//                "+7*x*(cos(x)*cos((x**+1))**7*x-(+x)*cos(sin((4*(-3))))*cos(x**2))",//15
//                "sin(x**+9)*x**3*x+x**1*(x**-0+cos(cos(x**9))*sin(4396)**-6*x++x*5)",
//                "(x**3*x*(sin(cos(cos(x**+4))**5)**2*(--x**7*x)*cos(x)))*sin(x**6)*x",
//                "sin(sin(0))*1906030*(-6*x**2*x**6)+sin(cos((-x))**1)+x**+2*cos(1)",
//                "(1+x)*(1-x)*(1+2*x)*(1-2*x)*(1+3*x)*(1-3*x)*(1+4*x)*(1-4*x)*sin(x)",
//                "--4+x*(+3+x*(+9+x*(+6+x*(+5+x*(8+x*(9+x*(5+x*(2+x*(7+x*(1+x))))))))))",
//             "--cos(x)*x**2+ sin((x**2 -4))-5",

}
