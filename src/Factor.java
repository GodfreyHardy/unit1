public abstract class Factor implements Term{
    private String data;

    public Factor(String input){
        this.data = input;
    }

    public void setData(String input){
        this.data = input;
    }

    public String getData(){
        return this.data;
    }
}
