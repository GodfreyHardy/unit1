import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Item implements Term{
    private String item;

    public Item(String input){
        setData(input);
    }
    public void optimize(){
        if(item.matches("^[(].*[)]$")){
            String output = item.replaceAll("^[(]","").replace("[)]$","");
            if(!item.matches("^[(].*[)]$")){
                setData(output);
            }
        }
    }
    private void setData(String input){
        this.item = input;
    }

    public String singlederivative(String input) throws Exception{
        String first = input.replace("%","*")
                .replace("{","(").replace("}",")");
        if(first.matches("[+-]?\\d+")){
            return "0";
        }
        else if(first.matches("x(?:\\^d+)?")){
            return new Xpower(first).derivative();
        }
        else if(first.matches("sin[(].*[)](?:\\^d+)?")){
            return new Sinxpower(first).derivative();
        }
        else if(first.matches("cos[(].*[)](?:\\^d+)?")){
            return new Cosxpower(first).derivative();
        }
        else if(first.matches("[(].*[)]")){
            return new Expressions(first).derivative();
        }
        else{
            throw new Exception();
        }
    }

    @Override
    public String derivative() throws Exception {
        String data =  getData();
        String pre = "[(][^()]*[)]";
        Pattern pattern = Pattern.compile(pre);
        Matcher matcher = pattern.matcher(data);
        while(matcher.find()){
            data = data.replace(matcher.group(),matcher.group().replace("*","%")
                    .replace("(","{").replace(")","}"));
            matcher = pattern.matcher(data);
        }
        String s1 = ".*?\\*";
        Pattern pattern1 = Pattern.compile(s1);
        Matcher matcher1 = pattern1.matcher(data);
        if(matcher1.find()){
            String first = matcher1.group();
            String back = data.replaceFirst(s1,"");
            first = first.replace("%","*")
                    .replace("{","(").replace("}",")");
            back = back.replace("%","*")
                    .replace("{","(").replace("}",")");
            String first_deri;
            String back_deri = new Item(back).derivative();
            if(first.matches("[+-]?\\d+")){
                if(back_deri.matches("0")){
                    return "0";
                }
                return first+"*"+"("+back_deri+")";
            }
            else if(first.matches("x(?:\\^d+)?")){
                first_deri = new Xpower(first).derivative();
            }
            else if(first.matches("sin[(].*[)](?:\\^d+)?")){
                first_deri = new Sinxpower(first).derivative();
            }
            else if(first.matches("cos[(].*[)](?:\\^d+)?")){
                first_deri = new Cosxpower(first).derivative();
            }
            else if(first.matches("[(].*[)]")){
                first_deri = new Expressions(first).derivative();
            }
            else{
                throw new Exception();
            }
            String res = "("+first_deri+")"+"*"+back;
            if(back_deri.matches("0")){
                if(first_deri.matches("0")){
                    return "0";
                }
                return back+"*("+first_deri+")";
            }
            return res + "+("+back_deri+")*"+first;
        }
        return singlederivative(data);
    }

    @Override
    public String getData() {
        optimize();
        return this.item;
    }
}
