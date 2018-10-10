import java.util.ArrayList;
import java.util.List;

/**
 * Created by GMUK on 2018/10/9 0009.
 */
public class Stack<T> {
    private int top;
    private List<T> list;

    public Stack() {
        top=-1;
        list=new ArrayList<T>();
    }

    public T getTop(){
        return list.get(top);
    }

    public T pop(){
        T template=list.get(top);
        list.remove(top);
        top--;
        return template;
    }

    public T push(T data){
        list.add(data);
        top++;
        T template=list.get(top);
        return template;
    }

    public Boolean isEmpty(){
        if (top==-1){
            return true;
        }else {
            return false;
        }
    }
}
