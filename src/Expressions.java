import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expressions implements Term{
    private String exp;
    public Expressions(String data){
        setData(data);
    }

    private void setData(String data){
        this.exp = data;
    }

    public void optimize(){
        //去除最外层的一对括号
        if(exp.matches("^[(].*[)]$")){
            String output = exp.replaceAll("^[(]","").replace("[)]$","");
            if(!exp.matches("^[(].*[)]$")){
                setData(output);
            }
        }
    }

    public String getData(){
        optimize();
        return this.exp;
    }

    public String singlederivative(String input)throws Exception{
        String data = input.replace("&","+")
                .replace("|","-").replace("{","(")
                .replace("}",")");
        String op = data.charAt(0)+"";
        String deri = new Item(data.replaceFirst("^[+-]","")).derivative();
        if(deri.matches("\\d+")){
            return op+deri;
        }
        return op + "("+deri+")";
    }
    public String derivative()throws Exception{
        String data = getData();
        String s1 = "[(][^()]*[)]";
        Pattern p1 = Pattern.compile(s1);
        Matcher m1 = p1.matcher(s1);
        while(m1.find()){
            data = data.replace(m1.group(),m1.group().replace("+","&")
                    .replace("-","|").replace("(","{")
                    .replace(")","}"));
            m1 = p1.matcher(data);
        }
        //根据加减号分理出项
        String s2 = "[+-].*[^*(][+-]";
        Pattern p2 = Pattern.compile(s2);
        Matcher m2 = p2.matcher(data);
        String res = "";
        if(m2.find()){
            String first = m2.group().replace("&","+").replace("|","-")
                    .replace("{","(").replace("}",")");
            String firstop = "^[+-]";
            Pattern p_fop = Pattern.compile(firstop);
            Matcher m_fop = p_fop.matcher(first);
            m_fop.find();
            res += m_fop.group()+"(";
            String endop = "[+-]$";
            Pattern p_eop = Pattern.compile(endop);
            Matcher m_eop = p_eop.matcher(first);
            m_eop.find();
            String back = data.replaceFirst(s2,"").replace("&","+").replace("|","-")
                    .replace("{","(").replace("}",")");
            back = m_eop.group()+back;
            res += new Item(first.replaceFirst(firstop,"").replaceFirst(endop,""))
                    .derivative()+")";
            //递归求导
            res += new Expressions(back).derivative();
            return res;
        }
        return singlederivative(data);
    }
}
