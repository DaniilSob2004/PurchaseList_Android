package com.example.purchaselist.utils;

public class AppConstant {
    // константы приложения
    public static final String MY_LIST_ADD_DIALOG_KEY = "MyListAddDialogKey";
    public static final String MY_LIST_DETAILS_KEY = "MyListDetailsKey";
    public static final String MY_LIST_ADD_DIALOG_REQUEST_KEY = "MyListAddDialogRequestKey";

    // массивы столбцов для запросов
    public static final String[] TYPE_COLUMNS = new String[] {
            AppConstant.COLUMN_TYPE_ID,
            AppConstant.COLUMN_TYPE_LABEL,
            AppConstant.COLUMN_TYPE_RULE,
    };

    public static final String[] LIST_COLUMNS = new String[] {
            AppConstant.COLUMN_LIST_ID,
            AppConstant.COLUMN_LIST_NAME,
            AppConstant.COLUMN_LIST_DATA,
            AppConstant.COLUMN_LIST_DESCRIPTION,
    };

    public static final String[] PRODUCT_COLUMNS = new String[] {
            AppConstant.COLUMN_PRODUCT_ID,
            AppConstant.COLUMN_PRODUCT_NAME,
            AppConstant.COLUMN_PRODUCT_COUNT,
            AppConstant.COLUMN_PRODUCT_ID_LIST,
            AppConstant.COLUMN_PRODUCT_CHECKED,
            AppConstant.COLUMN_PRODUCT_ID_TYPE,
    };

    // БД
    public static final String DATABASE_NAME = "purchase.db";
    public static final int SCHEMA = 1;  // версия БД

    // названия таблиц
    public static final String LIST_TABLE = "lists";
    public static final String TYPE_TABLE = "type";
    public static final String PRODUCT_TABLE = "product";

    // поля LIST_TABLE
    public static final String COLUMN_LIST_ID = "id";
    public static final String COLUMN_LIST_NAME = "name";
    public static final String COLUMN_LIST_DATA = "date";
    public static final String COLUMN_LIST_DESCRIPTION = "description";

    // поля TYPE_TABLE
    public static final String COLUMN_TYPE_ID = "id";
    public static final String COLUMN_TYPE_LABEL = "label";
    public static final String COLUMN_TYPE_RULE = "rule";

    // поля PRODUCT_TABLE
    public static final String COLUMN_PRODUCT_ID = "id";
    public static final String COLUMN_PRODUCT_NAME = "name";
    public static final String COLUMN_PRODUCT_COUNT = "count";
    public static final String COLUMN_PRODUCT_ID_LIST = "id_list";
    public static final String COLUMN_PRODUCT_CHECKED = "checked";
    public static final String COLUMN_PRODUCT_ID_TYPE = "id_type";

    // DROP
    public static final String DROP_LIST = "DROP TABLE IF EXISTS " + AppConstant.LIST_TABLE;
    public static final String DROP_TYPE = "DROP TABLE IF EXISTS " + AppConstant.TYPE_TABLE;
    public static final String DROP_PRODUCT = "DROP TABLE IF EXISTS " + AppConstant.PRODUCT_TABLE;

    // CREATE
    public static final String CREATE_TABLE_TYPE =
            "CREATE TABLE " + TYPE_TABLE + " (\n" +
                COLUMN_TYPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                COLUMN_TYPE_LABEL + " TEXT NOT NULL,\n" +
                COLUMN_TYPE_RULE + " TEXT NOT NULL\n" +
            ");\n";

    public static final String CREATE_TABLE_LIST =
            "CREATE TABLE " + LIST_TABLE + " (\n" +
                    COLUMN_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    COLUMN_LIST_NAME + " TEXT NOT NULL,\n" +
                    COLUMN_LIST_DATA + " INTEGER NOT NULL,\n" +
                    COLUMN_LIST_DESCRIPTION + " TEXT\n" +
            ");\n";

    public static final String CREATE_TABLE_PRODUCT =
            "CREATE TABLE " + PRODUCT_TABLE + " (\n" +
                COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                COLUMN_PRODUCT_NAME + " TEXT NOT NULL,\n" +
                COLUMN_PRODUCT_COUNT + " REAL NOT NULL,\n" +
                COLUMN_PRODUCT_ID_LIST + " INTEGER NOT NULL,\n" +
                COLUMN_PRODUCT_CHECKED + " INTEGER NOT NULL,\n" +
                COLUMN_PRODUCT_ID_TYPE + " INTEGER NOT NULL,\n" +
            "   FOREIGN KEY (" + COLUMN_PRODUCT_ID_LIST + ") REFERENCES " + LIST_TABLE + "(" + COLUMN_LIST_ID + ") ON DELETE CASCADE,\n" +
            "   FOREIGN KEY (" + COLUMN_PRODUCT_ID_TYPE + ") REFERENCES " + TYPE_TABLE + "(" + COLUMN_TYPE_ID + ") ON DELETE CASCADE,\n" +
            ");";

    // INSERT
    public static final String INSERT_TYPE_TABLE =
            "INSERT INTO " + TYPE_TABLE + " (" + COLUMN_TYPE_LABEL + ", " + COLUMN_TYPE_RULE + ")\n" +
            "VALUES\n" +
            "    ('шт', 'int'),\n" +
            "    ('кг', 'float'),\n" +
            "    ('л', 'float');";

    public static final String INSERT_LIST_TABLE =
            "INSERT INTO " + LIST_TABLE + " (" + COLUMN_LIST_NAME + ", " + COLUMN_LIST_DATA + ", " + COLUMN_LIST_DESCRIPTION + ")\n" +
            "VALUES\n" +
            "    ('Покупки на неделю', 1699478400, 'Продукты для недели'),\n" +
            "    ('Хозяйственные товары', 1699564800, 'Средства для уборки'),\n" +
            "    ('Овощи и фрукты', 1699824000, 'Свежие овощи и фрукты на неделю'),\n" +
            "    ('Молочные продукты', 1699996800, 'Молоко, сыр, йогурты'),\n" +
            "    ('Напитки', 1700169600, 'Соки, вода, чай, кофе'),\n" +
            "    ('Сладости', 1700256000, 'Шоколад, печенье, конфеты');";

    public static final String INSERT_PRODUCT_TABLE =
            "INSERT INTO product (" + COLUMN_PRODUCT_NAME + ", " + COLUMN_PRODUCT_COUNT + ", " + COLUMN_PRODUCT_ID_LIST + ", " + COLUMN_PRODUCT_CHECKED + ", " + COLUMN_PRODUCT_ID_TYPE + ")\n" +
            "VALUES\n" +
            "    ('Хлеб', 2.0, 1, 0, 1),\n" +
            "    ('Молоко', 1.5, 4, 1, 2),\n" +
            "    ('Яблоки', 1.0, 3, 0, 3),\n" +
            "    ('Шоколад', 0.3, 6, 0, 1),\n" +
            "    ('Сыр', 0.4, 4, 1, 2),\n" +
            "    ('Кофе', 1.0, 5, 0, 3),\n" +
            "    ('Чай', 1.0, 5, 1, 1),\n" +
            "    ('Сахар', 1.0, 1, 0, 1),\n" +
            "    ('Огурцы', 1.5, 3, 0, 3),\n" +
            "    ('Помидоры', 1.0, 3, 1, 3),\n" +
            "    ('Вода', 2.0, 5, 0, 3),\n" +
            "    ('Конфеты', 0.5, 6, 0, 1),\n" +
            "    ('Йогурт', 1.0, 4, 1, 2),\n" +
            "    ('Масло', 0.5, 1, 0, 1),\n" +
            "    ('Бананы', 1.0, 3, 0, 3),\n" +
            "    ('Курица', 1.2, 1, 1, 2),\n" +
            "    ('Рыба', 0.8, 1, 0, 2),\n" +
            "    ('Салфетки', 2.0, 2, 1, 1),\n" +
            "    ('Мука', 1.0, 1, 0, 1),\n" +
            "    ('Картофель', 3.0, 3, 0, 3),\n" +
            "    ('Капуста', 1.5, 3, 1, 3),\n" +
            "    ('Сок', 1.5, 5, 0, 3),\n" +
            "    ('Печенье', 0.4, 6, 1, 1),\n" +
            "    ('Крупа', 1.0, 1, 0, 1),\n" +
            "    ('Шампунь', 0.3, 2, 1, 1);";
}
