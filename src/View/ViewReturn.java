package View;

public class ViewReturn<T> {
    private T result;

    public ViewReturn () {}

    public void setResult (T result) {
        this.result = result;
    }

    public T getResult () {
        return result;
    }
}
