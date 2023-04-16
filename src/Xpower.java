import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Xpower extends Factor implements Term{
    public Xpower(String input){
        super(input);
    }

    @Override
    public void optimize() {
        if(super.getData().matches("x\\^0*1")){//matches是整体字符串的匹配
            setData("x");
        }
        else if(super.getData().matches("x\\^0*")){
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
        if(data.matches("1")){
            return "0";
        }
        else if(data.matches("x")){
            return "1";
        }
        String s = "[+-]?\\d+";
        Pattern p = Pattern.compile(s);
        Matcher m = p.matcher(data);
        BigInteger power;//次幂
        BigInteger coefficient;//系数
        if(m.find()){
            coefficient = new BigInteger(m.group());
            power = new BigInteger(m.group()).subtract(BigInteger.ONE);
            if(power.subtract(new BigInteger("9999")).signum()==1){
                throw new Exception();
            }
        }
        else{
            coefficient = BigInteger.ZERO;
            power = BigInteger.ZERO;
        }
        String res = "";
        if(!coefficient.equals(BigInteger.ONE)){
            res = coefficient.toString()+"*";
        }
        res += "x";
        if(!power.equals(BigInteger.ONE)){
            res += "^"+power.toString();
        }
        return res;
    }
}
