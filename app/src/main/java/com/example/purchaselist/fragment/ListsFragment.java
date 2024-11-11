package com.example.purchaselist.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.purchaselist.R;
import com.example.purchaselist.activity.MainActivity;
import com.example.purchaselist.adapter.MyListAdapter;
import com.example.purchaselist.db.DatabaseManager;
import com.example.purchaselist.dialog.AddListDialog;
import com.example.purchaselist.models.MyList;
import com.example.purchaselist.service.MyNotificationManager;
import com.example.purchaselist.utils.AppConstant;

import java.util.List;

public class ListsFragment extends Fragment {

    private String deleteText;

    private ImageButton btnAdd;
    private MyListAdapter myListAdapter;
    private ListView myListsList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // значения для ContextMenu
        deleteText = getString(R.string.delete_text);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lists, container, false);

        // инициализация
        init(view);

        // настройка адаптера коллекции "lists"
        setListsAdapter(view);

        // регистрируем ListView для контекстного меню
        registerForContextMenu(myListsList);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // слушаем результат от диалога
        getParentFragmentManager().setFragmentResultListener(AppConstant.MY_LIST_ADD_DIALOG_REQUEST_KEY, getViewLifecycleOwner(), (requestKey, result) -> {
            MyList myList = result.getParcelable(AppConstant.MY_LIST_ADD_DIALOG_KEY);  // получаем объект из результата
            if (myList != null) {
                addList(myList);
            }
        });
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.my_lists_list) {
            menu.add(deleteText);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // получаем информацию о выбранном элементе в контекстном меню
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (info == null) {
            return false;
        }

        int position = info.position;  // получаем позицию элемента
        MyList myListSelected = myListAdapter.getList().get(position);

        String menuTitle = item.getTitle().toString();
        if (menuTitle.equals(deleteText)) {
            deleteList(myListSelected);
        }
        else {
            Log.d("ListsFragment", "onContextItemSelected: ERROR");
            return super.onContextItemSelected(item);
        }

        return true;
    }


    public void clickAdd() {
        // вызов фрагмента AddListDialog
        AddListDialog dialog = new AddListDialog();
        dialog.show(getParentFragmentManager(), "dialog");

        // отправка уведомления
        MyNotificationManager.showNotification(getContext(), getString(R.string.app_name), getString(R.string.add_list_text_message));
    }


    private void addList(MyList myList) {
        // добавление в БД
        DatabaseManager dbManager = new DatabaseManager(getContext());
        dbManager
                .open()
                .addList(myList);
        dbManager.close();

        // добавление в коллекцию
        myListAdapter.addList(myList);
    }

    private void deleteList(MyList myList) {
        // удаление из БД
        DatabaseManager dbManager = new DatabaseManager(getContext());
        dbManager
                .open()
                .deleteList(myList.getId());
        dbManager.close();

        // удаление из коллекции
        myListAdapter.deleteList(myList);
    }

    private void setListsAdapter(View view) {
        // получаем элемент ListView
        myListsList = view.findViewById(R.id.my_lists_list);

        // создаём адаптер
        List<MyList> lists = getAllLists();
        myListAdapter = new MyListAdapter(getContext(), R.layout.my_lists_item, lists);

        // устанавливаем адаптер в список
        myListsList.setAdapter(myListAdapter);

        // установка обработчика по клику
        myListsList.setOnItemClickListener((parent, view1, position, id) -> {
            if (getActivity() instanceof MainActivity) {
                ListDetailsFragment fragment = new ListDetailsFragment();

                // установка аргументов
                Bundle bundle = new Bundle();
                MyList selectedList = myListAdapter.getList().get(position);
                bundle.putParcelable(AppConstant.MY_LIST_DETAILS_KEY, selectedList);
                fragment.setArguments(bundle);

                ((MainActivity) getActivity()).setContFragment(fragment);
            }
        });
    }

    private List<MyList> getAllLists() {
        DatabaseManager dbManager = new DatabaseManager(getContext());
        dbManager.open();
        List<MyList> lists = dbManager.getLists();
        dbManager.close();
        return lists;
    }

    private void init(View view) {
        btnAdd = view.findViewById(R.id.add_btn);
        btnAdd.setOnClickListener(v -> clickAdd());
    }
}
