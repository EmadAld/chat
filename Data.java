package com.example.pcc.chatting;



import java.io.*;
import java.util.ArrayList;


class Data {
    public static ArrayList<User> list=new ArrayList<>();

    static void loadUsersData() throws IOException, ClassNotFoundException {
        list=get_Users();
        User user=list.get(list.size() - 1);
        Server_full.setID(user.getId());
    }

    //TODO check if an email is exist
    static Boolean checkTheUser(User user) throws IOException, ClassNotFoundException {
    Boolean result=true;
        //first we will check if we have that email
        //second we will check if the password is exist
        //if we don't have the email case and if we have it another case
        //third we will check if he would to sign up and the email isn't an email
        //and we will check if he enter a password over 8 char
        //and we will check if he entered a valid name
        //we will type the cases here :
        // 0 not found email
        // 1 founded email and correct password
        // 2 false password

        for(User ignored : list) {
            if (user.getEmail().equals(ignored.getEmail()))
                result = false;
        }
       return result;
    }
    static Boolean checkTheUser_SignIn(User user) throws IOException, ClassNotFoundException {
        Boolean result=true;
        for(User ignored : list) {
            if (user.getEmail().equals(ignored.getEmail()) && user.getPassword().equals(ignored.getPassword()))
                result = false;
        }
        return result;
    }



    static void store(User user) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usersData"));
        list.add(user);
        oos.writeObject(list);
        oos.close();
       /* oos = new ObjectOutputStream(new DeflaterOutputStream(new FileOutputStream("StartupInfo")));
        oos.writeObject(new Server_full.StartUpInfo(user.getId()));
        oos.close();*/
    }

    static ArrayList<User> get_Users() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("usersData"));
        ArrayList<User> list = (ArrayList<User>) ois.readObject(); // cast is needed.
        ois.close();
        return list;
    }

    static void print_Users(ArrayList<User> list){
        // ListIterator<User> it=list.listIterator(0);
        for(User user : list){
            user.printUser();
        }
        System.out.println("\n \n  ----------------------------------------------------------");
    }

}
