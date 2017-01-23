package com.example.adrian.proyectotrimestral_adriandavia;

public class BDTareas {
    interface ColumnasUsuarios{
        String name = "name";
        String subname = "subname";
        String user = "user";
        String password = "password";
        String email = "email";
    }
    interface ColumnasTareas{
        String id_task = "id";
        String date = "date";
        String name = "name";
        String description = "description";
        String state = "state";
    }

    public static class BDUsuarios implements ColumnasUsuarios{

    }
    public static class BDTarea implements ColumnasTareas{

    }
}
