package com.mirea.practice2_2;

//import java.io.*;
import java.util.HashMap;
import org.json.*;

public class Practice2_2 {

    public static void main(String[] args) {
        System.out.println("Start program!");
        String server = "https://android-for-students.ru";
        String serverPath = "/materials/practical/hello.php";
        HashMap<String,String> map = new HashMap();
        map.put("name", "Korneev");
        map.put("group", "RIBO-04-22");
        HTTPRunnable httpRunnable = new HTTPRunnable(server+serverPath, map);
        Thread th = new Thread(httpRunnable);
        th.start();
        try{
            th.join();
        }
        catch(InterruptedException ex){
            
        }
        finally{
            /*try{
                FileWriter fw = new FileWriter("C:\\Users\\user\\Desktop\\MIREA\\resp.html");
                fw.write(httpRunnable.getResponseBody());
                fw.close();
                System.out.println("Successfully saved response from server: "+server);
            }
            catch(IOException ex){
                System.out.println("Error response saving"+ex.getMessage());   
            }*/
            //System.out.println(httpRunnable.getResponseBody());
            JSONObject jSONObject = new JSONObject(httpRunnable.getResponseBody());
            int result = jSONObject.getInt("result_code");
            System.out.println("Result: "+result);
            System.out.println("Type: "+jSONObject.getString("message_type"));
            System.out.println("Result: "+jSONObject.getString("message_text"));
            switch(result){
                case 1: 
                    JSONArray jSONArray = jSONObject.getJSONArray("task_list");
                    System.out.println("Task list: ");
                    for(int i = 0;i<jSONArray.length();i++){
                        System.out.println((i+1)+") "+jSONArray.getString(i));
                    }
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        }
    }
}