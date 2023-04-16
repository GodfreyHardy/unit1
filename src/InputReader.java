public class InputReader {
    String data;
    public InputReader(String input){
        this.data = input;
    }
    public String getData(){
        return this.data;
    }
    public boolean isValid(String data){
        if (data.length() == 0) {
            return false;
        }
        if (data.matches(".*[^sinco()^x\\d \t+*\\-].*") ||
                data.matches(".*\\^[ \t]*[+-][ \t]+[\\d].*") ||
                data.matches(".*[+-][ \t]*[+-][ \t]*[+-][^\\d].*")) {
            return false;
        }
        if (data.matches(".*s[ \t]+i[ \t]*n.*") ||
                data.matches(".*s[ \t]*i[ \t]+n.*") ||
                data.matches(".*c[ \t]+o[ \t]*s.*") ||
                data.matches(".*c[ \t]*o[ \t]+s.*")) {
            return false;
        }
        String s2 = data.replaceAll("[ \t]", "");
        return !s2.matches(".*\\^[+-]{2,}.*") && !s2.matches(".*[+-]{4,}.*");
    }
    public String getProcessed(){
        data = data.replaceAll("[\t]","");
        data = "+"+data;
        data = data.replace("^+","^");
        data = data.replace("++","+");
        data = data.replace("++","+");
        data = data.replace("-+","-");
        data = data.replace("-+","-");
        data = data.replace("+-","-");
        data = data.replace("+-","-");
        data = data.replace("--","+");
        data = data.replace("--","+");
        return data;
    }
}
