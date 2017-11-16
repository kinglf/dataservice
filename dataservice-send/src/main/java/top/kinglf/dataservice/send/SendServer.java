package top.kinglf.dataservice.send;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import okhttp3.*;
import top.kinglf.dataservice.send.model.KMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SendServer implements Runnable {
    private static List<KMessage> msgList = new ArrayList<KMessage>();
    String path="http://receive.kinglf.top:9090/msg/receives";
    private long projectId;
    OkHttpClient httpClient;

    public SendServer(long projectId,String url){
        this.projectId=projectId;
        this.path=url;
        new Thread(this).start();
    }
    public SendServer(long projectId){
        this.projectId=projectId;
        new Thread(this).start();
    }


    @Override
    public void run() {
        while (true) {
            if (msgList.size() == 0) {
                try {
                    //无数据休眠两秒
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < msgList.size(); i++) {
                KMessage msg = msgList.remove(0);
                jsonArray.add(msg);
            }
            String send = send(jsonArray.toJSONString());
            System.out.println("数据发送成功" + send);
            try {
                System.err.println("zhou-info="+"休眠5秒,准备下一次发送");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }
    public void sendData(String type, String context) {
        KMessage kMessage=new KMessage();
        kMessage.setProjectId(projectId);
        kMessage.setCrawlDate(new Date());
        kMessage.setContext(context);
        kMessage.setType(type);
        sendData(kMessage);
    }
    public void sendData(KMessage data) {
        msgList.add(data);
    }
    public void sendDataByJsonArray(String type,String arrayStr) {
        JSONArray jsonArray = JSON.parseArray(arrayStr);
        sendDataByJsonArray(type,jsonArray);
    }

    public void sendDataByJsonArray(String type,JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.size(); i++) {
            KMessage kMessage=new KMessage();
            kMessage.setProjectId(projectId);
            kMessage.setCrawlDate(new Date());
            kMessage.setContext(jsonArray.getJSONObject(i).toJSONString());
            kMessage.setType(type);
            sendData(kMessage);
        }
    }


    public String send(String data) {
        RequestBody body = new FormBody.Builder().add("data", data).build();
        Request request = new Request.Builder()
                .url(path)
                .post(body)
                .build();

        getDefaultHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                Log.e("zhou-error","发送失败="+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.d("zhou-successful","发送成功");
            }
        });


        return null;
    }

    public OkHttpClient getDefaultHttpClient() {
        if (httpClient == null) {
            httpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build();
        }
        return httpClient;
    }
}
