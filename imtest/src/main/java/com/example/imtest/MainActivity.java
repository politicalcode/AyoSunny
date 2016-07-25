package com.example.imtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
public class MainActivity extends Activity {

    private LinearLayout msgList;
    private EditText msg;
    private Button send;
    private Chat topChat;
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        msgList = (LinearLayout) findViewById(R.id.messages);
        msg = (EditText) findViewById(R.id.msg);
        send = (Button) findViewById(R.id.send);



        XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder();

        configBuilder.setHost("106.75.11.26");
        configBuilder.setPort(5222);
        configBuilder.setServiceName("0b87ef7c304e4c8");
        final AbstractXMPPConnection connection =
                new XMPPTCPConnection(configBuilder.build());
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    setText("准备连接...");
                    Thread.sleep(3000);
                    connection.connect();
                    setText("连接服务器成功@106.75.11.26:5222");
                    connection.login("cowthan2", "111111");
                    Presence presence = new Presence(Presence.Type.available);
                    presence.setStatus("I am online");
                    connection.sendStanza(presence);
                    setText("登陆成功");
                } catch (Exception e) {
                    setText("连接失败: " + e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        }).start();

        ChatManager cm = ChatManager.getInstanceFor(connection);
        topChat = cm.createChat("admin2@106.75.11.26:5222", new ChatMessageListener() {
            @Override
            public void processMessage(Chat chat, Message message) {

            }
        });


        send.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), msg.getText().toString() + ", " + (topChat != null), Toast.LENGTH_LONG).show();
                if(!msg.getText().toString().equals("") && topChat != null)
                {
                    try {
                        String string = msg.getText().toString();
                        Message mm = new Message();
                        mm.setBody(string);
                        mm.setFrom("cowthan2@106.75.11.26:5222");
                        mm.setTo("cowthan2@106.75.11.26:5222");

                        topChat.sendMessage(mm);

                        TextView view = new TextView(MainActivity.this);
                        view.setText("我： "+string);
                        msgList.addView(view);
                        msg.setText("");
                    } catch (NotConnectedException e) {
                        Toast.makeText(MainActivity.this, "发送失败", Toast.LENGTH_LONG).show();

                        setText("发送失败：" + e.getLocalizedMessage());

                        e.printStackTrace();
                    }
                }
            }
        });

        cm.addChatListener(new ChatManagerListener(){

            @Override
            public void chatCreated(Chat arg0, boolean arg1) {
                arg0.addMessageListener(new ChatMessageListener(){

                    @Override
                    public void processMessage(Chat arg0, Message arg1) {
                        topChat = arg0;
                        if(null!=arg1.getBody())
                        {
                            String from = arg1.getFrom().substring(0,arg1.getFrom().indexOf("@"));
                            setText("from "+from+" : "+arg1.getBody());
                        }
                    }
                });
            }
        });
    }

    private void setText(final String text)
    {
        runOnUiThread(new Runnable(){
            public void run() {
                TextView tv = new TextView(MainActivity.this);
                tv.setText(text);
                msgList.addView(tv);
            };
        });
    }


}
