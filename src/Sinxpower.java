import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sinxpower extends Factor implements Term{
    public Sinxpower(String input){
        super(input);
    }

    @Override
    public void optimize() {
        if(super.getData().matches(".*\\^0*1")){
            setData(super.getData().replaceAll(".*\\^0*1",""));
        }
        else if(super.getData().matches(".*\\^0*")){
            setData("1");
        }
    }

    @Override
    public String getData() {
        optimize();
        return super.getData();
    }

    @Override
    public String derivative() throws Exception {
        String data = getData();
        String res = "";
        if(data.matches("1")){
            return "0";
        }
        else if(data.matches(".*\\^\\d+")){
            String s = "\\d+$";
            Pattern p = Pattern.compile(s);
            Matcher m = p.matcher(data);
            m.find();
            BigInteger coefficient = new BigInteger(m.group());
            BigInteger power = new BigInteger(m.group()).subtract(BigInteger.ONE);
            if(power.subtract(new BigInteger("9999")).signum() == 1){
                throw new Exception();
            }
            res = coefficient.toString()+"*";
            String factor = data.replaceFirst("\\^\\d+$","");
            res += factor+"^"+power.toString()+"*(";
            res += new Item(factor).derivative()+")";
        }
        else{//sin(x)
            res = data.replaceFirst("sin","cos");
            res += "*(";
            String factor;
            factor = data.replaceFirst("sin[(]","").replaceFirst("[)]$","");
            res += new Item(factor).derivative()+")";
        }
        return res;
    }
}
