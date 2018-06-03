package com.example.pcc.chatting;


import sun.plugin2.message.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_full {
    private static ServerSocket server;
    private static int ID=0;//if we add delete .. we need to redo that ... and if we have X button ...



    private static void StartUp() throws Exception{
        server=new ServerSocket(8080);
        //loadStartUpInformation();
        Data.loadUsersData();
        Received_Clients.start();
    }


    private static Thread Received_Clients=new Thread(new Runnable() {
        Socket s;
        DataOutputStream oos;
        ObjectInputStream din;
        @Override
        public void run() {
            try {
                while(true) {
                    s = server.accept();
                    System.out.println("Accept...");
                    oos = new DataOutputStream(s.getOutputStream());
                    din = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
                    User newUser = (User) din.readObject();
                        if (newUser.getMessage()==null) {
                            int id = newUser.getId();
                            String user = newUser.getUser();
                            String password = newUser.getPassword();
                            String email = newUser.getEmail();
                            System.out.println(id + " " + user + " " + password + " " + email);
                            if (newUser.getUser() != null) {
                                Boolean x = Data.checkTheUser(newUser);
                                if (!x) {
                                    //Data.print_Users(Data.get_Users());
                                    System.out.println("false");
                                    oos.writeBoolean(x);
                                    oos.flush();
                                } else if (x) {
                                    newUser.setId(idGenerator());
                                    Data.store(newUser);
                                    Data.print_Users(Data.get_Users());
                                    System.out.println("true");
                                    oos.writeBoolean(x);
                                    oos.flush();
                                }
                            }
                            if (newUser.getUser() == null) {
                                Boolean x1 = Data.checkTheUser_SignIn(newUser);
                                if (x1) {
                                    //Data.print_Users(Data.get_Users());
                                    System.out.println(x1);
                                    oos.writeBoolean(x1);
                                    oos.flush();
                                } else {
                                    System.out.println(x1);
                                    oos.writeBoolean(x1);
                                    oos.flush();
                                }
                            }
                        }
                        if (newUser.getMessage()!=null)
                        {
                            System.out.println(newUser.getMessage());
                            String str="From Server :" + newUser.getMessage();
                            oos.writeUTF(str);
                            oos.flush();
                        }

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Problem");
            }
            finally {
                if (din!=null)
                {
                    try {
                        din.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (oos!=null)
                {
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (s!=null)
                {
                    try {
                        s.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    });

    public static void main(String[] args) throws Exception {

/*ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("usersData"));
User use=new User();
use.setId(1);
use.setUser("h1");
use.setEmail("h1");
use.setPassword("h1");
Data.list.add(use);
oos.writeObject(Data.list);*/
        //we will focus on 4 work :
        //the first work which will received  the new client
        // the second work which can check it
        // the second line which can store it
        //we will remind sign in and sign up
        //check out if email is email and password is password
        //read about : ObjectInputStream ObjectOutputStream
        // DeflaterOutputStream InflaterInputStream FileInputStream FileOutputStream
        //we should remove the int id and replace it by the StartUppInfo class and it's variable
        StartUp();
        /*Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Socket s1 = server.accept();
                        System.out.println("Accept_1");
                        DataOutputStream dout = new DataOutputStream(s1.getOutputStream());
                        System.out.println("outut");
                        DataInputStream din1 = new DataInputStream(new BufferedInputStream(s1.getInputStream()));
                        System.out.println("Input");
                        String string = din1.readUTF();
                        System.out.println("read");
                        System.out.println(string);
                        dout.writeUTF(string);
                        System.out.println("write");
                        dout.flush();
                        din1.close();
                        s1.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();*/
    }
    private static int idGenerator(){
        ID++;
        return ID;
    }
    public static void setID(int ID) {
        Server_full.ID = ID;
    }
   /* private static void loadStartUpInformation() throws IOException, ClassNotFoundException {
        ObjectInputStream ois=new ObjectInputStream(new InflaterInputStream(new FileInputStream("StartUpInfo")));
        StartUpInfo t=(StartUpInfo) ois.readObject();
        ID=t.id;
    }*/
   /* static class StartUpInfo implements Serializable{
        private int id ;

        public StartUpInfo(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
        //we don't know if we have more details...
    }*/


}
