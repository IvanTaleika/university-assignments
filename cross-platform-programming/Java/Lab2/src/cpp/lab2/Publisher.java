package cpp.lab2;

public interface Publisher {
    public void subscribe(Subscriber subscriber);
    public void unSubscribe(Subscriber subscriber);
}
