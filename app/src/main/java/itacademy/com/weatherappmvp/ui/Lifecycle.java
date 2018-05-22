package itacademy.com.weatherappmvp.ui;

public interface Lifecycle<V> {

    void bind(V view);

    void unbind();

}
