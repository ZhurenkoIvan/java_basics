import java.util.ArrayList;

public class TodoList {
    private ArrayList<String> todoList = new ArrayList<>();

    public void add(String todo) {
        todoList.add(todo);
        // TODO: добавьте переданное дело в конец списка
    }

    public void add(int index, String todo) {
        if(index >= 0  && index <= todoList.size()) {
            todoList.add(index, todo);
        } else {
            todoList.add(todoList.size(), todo);
        }
        // TODO: добавьте дело на указаный индекс,
        //  проверьте возможность добавления
    }

    public void edit(String todo, int index) {
        if(index < todoList.size() && index >= 0) {
            todoList.remove(index);
            todoList.add(index, todo);
        }
        // TODO: заменить дело на index переданным todo индекс,
        //  проверьте возможность изменения
    }

    public void delete(int index) {
        if(index < todoList.size() && index >= 0) {
            todoList.remove(index);
        }
        // TODO: удалить дело находящееся по переданному индексу,
        //  проверьте возможность удаления дела
    }

    public ArrayList<String> getTodos() {
        // TODO: вернуть список дел

        return todoList;
    }

    public int size() {
        return todoList.size();
    }

    public String getTodoListAtIndex(int index) {
        return todoList.get(index);
    }

}