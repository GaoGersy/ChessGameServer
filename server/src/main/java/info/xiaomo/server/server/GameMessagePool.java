package info.xiaomo.server.server;

import info.xiaomo.gameCore.protocol.Message;
import info.xiaomo.gameCore.protocol.MessagePool;
import info.xiaomo.server.protocol.message.user.ReqLoginMessage;

import java.util.HashMap;
import java.util.Map;

public class GameMessagePool implements MessagePool {

    // 消息类字典
    private final Map<Integer, Class<? extends Message>> messages = new HashMap<>();

    public GameMessagePool() {
        register(101101, ReqLoginMessage.class);
    }

    @Override
    public AbstractMessage getMessage(int messageId) {
        Class<?> clazz = messages.get(messageId);
        if (clazz != null) {
            try {
                return (AbstractMessage) clazz.newInstance();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public void register(int messageId, Class<? extends Message> messageClazz) {
        try {
            messages.put(messageId, messageClazz);
        } catch (Exception e) {
            throw new RuntimeException("消息注册错误....");
        }
    }
}
