//package com.zl.setup.websocket;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//
//import javax.websocket.*;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.CopyOnWriteArrayList;
//
///**
// * Created by zhangliang on 2018/12/24.
// *
// */
//
//@Controller
//@ServerEndpoint("/websocket/{uid}")
//public class WebSocketServer {
//    private Session session;
//    private static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
//    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
//    private static int onlineCount = 0;
//
//    private static List<WebSocketServer> webSocketServers = new CopyOnWriteArrayList<>();
//
//    /**
//     * 连接建立成功调用的方法*/
//    @OnOpen
//    public void onOpen(Session session,@PathParam("uid") String uid) {
//        int maxSize = 200 * 1024;//200K
//        //可以缓冲的传入二进制消息的最大长度
//        session.setMaxBinaryMessageBufferSize(maxSize);
//        // 可以缓冲的传入文本消息的最大长度
//        session.setMaxTextMessageBufferSize(maxSize);
//
//        this.session = session;
//
//        webSocketServers.add(this);
//
//        addOnlineCount();           //在线数加1
//    }
//
//    /**
//     * 推送给卖家
//     */
//    private void push2Customer(long userId){
//        boolean isSuccess = false;
//
//        for (WebSocketServer webSocketServer : webSocketServers) {
//            Session currentSession = webSocketServer.session;
//            if(currentSession.isOpen() && userId == Long.parseLong(currentSession.getPathParameters().get("uid"))){
//                Map map = new HashMap();
//                map.put("code", code);
//                map.put("info", info);
//                map.put("content", content);
//                map.put("unreadCount", badge);
//                String pushJson = JSONObject.toJSONString(map);
//                currentSession.getAsyncRemote().sendText(pushJson);
//
//                result4Push(resultMap,false,receiverId,true,"");
//                isSuccess = true;
//            }
//        }
//
//        if (!isSuccess) {
//            result4Push(resultMap,false,receiverId,false,"未找到客户端");
//        }
//    }
//
//    private void push2App(String receiverId,String info,String content,int badge,Map resultMap) {
//        try {
//            PushResult result = jPushUtil.pushOneToBuyerOrSeller(badge, info, content, receiverId);
//            if (result.statusCode == 0) {
//                result4Push(resultMap,true,receiverId,true,"");
//            }else {//发送失败
//                result4Push(resultMap,true,receiverId,false,result.error.getMessage());
//            }
//        } catch (APIConnectionException e) {
//            e.printStackTrace();
//            result4Push(resultMap,true,receiverId,false,e.getMessage());
//        } catch (APIRequestException e) {
//            e.printStackTrace();
//            result4Push(resultMap,true,receiverId,false,e.getMessage());
//        }
//
//    }
//
//    private void result4Push(Map resultMap , boolean toApp, String receiverId, boolean isSuccess, String msg) {
//        resultMap.put("receiverId", receiverId);
//        if (toApp) {
//            resultMap.put("appSuccess",isSuccess);
//            resultMap.put("appMsg", msg);
//        }else {
//            resultMap.put("webSuccess",isSuccess);
//            resultMap.put("webMsg", msg);
//        }
//
//
//    }
//    /**
//     * 收到客户端消息后调用的方法
//     *
//     * @param message 客户端发送过来的消息
//     **/
//    @OnMessage
//    @SuppressWarnings("all")
//    public void onMessage(String message, Session session) throws IOException {
//        handleTextMessage(session, message);
//    }
//
//    public synchronized void handleTextMessage(Session session,String message) {
////        try {
////
////            if ("".equals(message)) {
////                return;
////            }
////
////            String uid = session.getPathParameters().get("uid");
////            Map<String ,Object > gpsMap = (Map)JSON.parse(message);
////            String flag = (String)gpsMap.get("flag");
////            String data = (String)gpsMap.get("data");
////            UpmsUserMail upmsUserMail = JSON.parseObject(data, UpmsUserMail.class);
////            switch (flag) {
////                case "update"://设置已读
////                    upmsUserMailMapper.updateByPrimaryKeySelective(upmsUserMail);
////                    break;
////                case "delete"://删除
////                    //1.删除user_mail表一条记录
////                    upmsUserMailMapper.deleteByPrimaryKey(upmsUserMail);
////                    //2.判断该消息拥有者数量如果大于0，mail表该消息不删，否则删除该消息
////                    int count = upmsUserMailMapper.getCountByMailId(upmsUserMail.getMailId());
////                    if (count == 0) {
////                        UpmsMail upmsMail = new UpmsMail();
////                        upmsMail.setId(upmsUserMail.getMailId());
////                        upmsMailMapper.deleteByPrimaryKey(upmsMail);
////                    }
////                    break;
////
////            }
////            List<MailVo> mailVos = upmsUserMailMapper.getUnreadMessage(uid);
////
////            String result = JSONObject.toJSONString(mailVos);
////            session.getBasicRemote().sendText(result);
////        } catch (Exception e) {
////            e.printStackTrace();
////            logger.error(e.toString());
////        }
////
////        logger.info("指令 ---->{}",message);
//    }
//
//
//    /**
//     *
//     * @param session
//     * @param error
//     */
//    @OnError
//    public void onError(Session session, Throwable error) {
//        try {
//            session.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            logger.error(error.toString());
//        }finally {
//            webSocketServers.remove(this);
//        }
//
//    }
//
//
//    public static synchronized int getOnlineCount() {
//        return onlineCount;
//    }
//
//    public synchronized void addOnlineCount() {
//        WebSocketServer.onlineCount++;
//        logger.info("current onlineCount is {}",onlineCount);
//    }
//
//    public synchronized void subOnlineCount() {
//        WebSocketServer.onlineCount--;
//        logger.info("current onlineCount is {}",onlineCount);
//
//    }
//
//    /**
//     * 连接关闭调用的方法
//     */
//    @OnClose
//    public void onClose() {
//        webSocketServers.remove(this);
//        logger.info("webSocketServers is {}",webSocketServers);
//        subOnlineCount();//在线数减1
//    }
//
//    public static void main(String[] args) {
//        List<Integer> list = new CopyOnWriteArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            list.add(i);
//        }
//
//        for (Integer integer :list) {
//            list.remove(integer);
//        }
//        System.out.println(list);
//    }
//
//}
